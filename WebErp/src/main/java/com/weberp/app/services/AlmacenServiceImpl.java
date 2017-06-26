package com.weberp.app.services;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.weberp.app.common.model.UsuarioUtil;
import com.weberp.app.domain.Empresa;
import com.weberp.app.dto.AlmacenDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.utils.LocalDateAttributeConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.weberp.app.domain.Almacen;
import com.weberp.app.domain.DetalleAlmacen;
import com.weberp.app.domain.Producto;
import com.weberp.app.repositories.AlmacenRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Service
@Transactional(noRollbackFor = Exception.class)
public class AlmacenServiceImpl extends ConfigMapper implements AlmacenService {

	Almacen almacen;

	private Logger log = Logger.getLogger(UsuarioServiceImpl.class);

	@Autowired
	private EntityManager entityManager;

	Producto producto;
	@Autowired
	private ProductoService productoService;

    @Autowired
    private UsuarioService usuarioService;

	@Autowired
	private AlmacenRepository almacenRepository;

	@Autowired
	private LocalidadService localidadService;

	@Override
	public List<Almacen> listaAlmacen() {
		Long empresaId = UsuarioUtil.getCurrentUserEmpresa().getEmpresa().getId();
		return (List<Almacen>) almacenRepository.findByLocalidadEmpresa_Id(empresaId);
	}

	@Override
	public Almacen guardar(Almacen almacen) {

        if(null!= almacen.getLocalidad().getId()) {
            almacen.setLocalidad(localidadService.getLocalidadById(almacen.getLocalidad().getId()));
        }
        if(validaExisteAlmacenPrincipal(almacen.getCodigo()))
        	throw new IllegalArgumentException("Existe un almacen registrado como principal");

            for (int i = 0; i < almacen.getDetalleAlmacen().size(); i++) {
                DetalleAlmacen detalleAlmacen = almacen.getDetalleAlmacen().get(i);
                detalleAlmacen.setAlmacen(almacen);
                producto = productoService.getProductoById(detalleAlmacen.getProducto().getId());

                detalleAlmacen.setProducto(producto);

            }

		return almacenRepository.save(almacen);
	}

	@Override
	public Almacen getAlmacenById(Long id) {
		// TODO Auto-generated method stub
		return almacenRepository.findOne(id);
	}

	@Override
	public void borrar(Long id) {
		// TODO Auto-generated method stub
		almacenRepository.delete(id);
	}

	@Override
	public Almacen findByPrincipalAndEstado(boolean principal, int estado) {

		Long empresaId = UsuarioUtil.getCurrentUserEmpresa().getEmpresa().getId();
		return almacenRepository.findByPrincipalAndEstadoAndLocalidadEmpresa_Id(principal, estado,empresaId);
	}

	@Override
	public void afectaCantidadEntradaProductos(Long productoId, Long cantidad) {

		Almacen almacen = buscarAlmacenPorUsuario(UsuarioUtil.getCurrentUser());

		if(null== almacen)
			throw new IllegalArgumentException("Debe crear un almacen que este asignado a este usuario "+UsuarioUtil.getCurrentUser());

        if(!existeProductoRegistradoEnAlmacen(productoId,almacen.getId()))
            throw new IllegalArgumentException("No existe registrado este producto en el almacen "+almacen.getNombre());



		for (int i = 0; i < almacen.getDetalleAlmacen().size(); i++) {
			if (almacen.getDetalleAlmacen().get(i).getProducto().getId().equals(productoId)) {

				almacen.getDetalleAlmacen().get(i)
						.setCantidad(almacen.getDetalleAlmacen().get(i).getCantidad() + cantidad);
				productoService.afectaEntradaProducto(almacen.getDetalleAlmacen().get(i).getProducto(),cantidad);
			}

		}
		almacenRepository.save(almacen);
	}

	@Override
	public void afectaCantidadSalidaProductos(Long productoId, Long cantidad) {
		try {
			Almacen almacen = buscarAlmacenPorUsuario(UsuarioUtil.getCurrentUser());

			if(null== almacen)
				throw new IllegalArgumentException("Debe crear un almacen que este asignado a este usuario "+UsuarioUtil.getCurrentUser());

			if(!existeProductoRegistradoEnAlmacen(productoId,almacen.getId()))
				throw new IllegalArgumentException("No existe registrado este producto en el almacen "+almacen.getNombre());

			for (int i = 0; i < almacen.getDetalleAlmacen().size(); i++) {
				if (almacen.getDetalleAlmacen().get(i).getProducto().getId().equals(productoId)) {

					validaProductoSinExistencia(almacen.getDetalleAlmacen().get(i),cantidad);

					almacen.getDetalleAlmacen().get(i)
							.setCantidad(almacen.getDetalleAlmacen().get(i).getCantidad() - cantidad);
					productoService.afectaSalidaProducto(almacen.getDetalleAlmacen().get(i).getProducto(),cantidad);
				}

			}
			almacenRepository.save(almacen);
		}catch (Exception ex){
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	@Override
	public boolean existeProductoRegistradoEnAlmacen(Long productoId, Long almacenId) {


        BigInteger rows = (BigInteger)entityManager.createNamedQuery("Almacen.findAlmacenByProducto")
                    .setParameter(1, productoId)
                    .setParameter(2,almacenId)
                    .getSingleResult();


	return rows.compareTo(BigInteger.ZERO)> 0;
	}

	@Override
	public void afectaSalidaProductosBatch(Map<Long, Long> mapProductos) {

		for(Map.Entry<Long,Long> entrys : mapProductos.entrySet()){

			afectaCantidadSalidaProductos(entrys.getKey(),entrys.getValue());

		}

	}

	@Override
	public void afectaEntradaProductosBatch(Map<Long, Long> mapProductos) {
        for(Map.Entry<Long,Long> entrys : mapProductos.entrySet()){

            afectaCantidadEntradaProductos(entrys.getKey(),entrys.getValue());

        }
	}

	@Override
	public boolean validaExisteAlmacenPrincipal(String codigo) {

		Long empresaId = UsuarioUtil.getCurrentUserEmpresa().getEmpresa().getId();
		Almacen almacen = almacenRepository.findByPrincipalAndEstadoAndLocalidadEmpresa_Id(true,1,empresaId);

		if(almacen==null)
			return false;

		return !almacen.getCodigo().equals(codigo);
	}

	@Override
	public Page<AlmacenDTO> findPaginated(int page, int size) {
		Long empresaId = UsuarioUtil.getCurrentUserEmpresa().getEmpresa().getId();
		Page<Almacen> almacenPage=  (almacenRepository.findByLocalidadEmpresa_Id(empresaId,new PageRequest(page,size, Sort.Direction.ASC ,"id")));

		final Page<AlmacenDTO> contactDtoPage = almacenPage.map(this::convertAlmacenToDto);
		return contactDtoPage;
	}

	@Override
	public Page<AlmacenDTO> findAlmacenAndPaginated(String codigo, Pageable pageRequest) {
		Page<Almacen> facturaPage=  almacenRepository.findByCodigo(codigo,pageRequest);

		final Page<AlmacenDTO> contactDtoPage = facturaPage.map(this::convertAlmacenToDto);
		return contactDtoPage;
	}

	public void validaProductoSinExistencia(DetalleAlmacen detalleAlmacen, Long cantidad){

		if(detalleAlmacen.getCantidad()==0)
			throw new IllegalArgumentException("Para el producto "+ detalleAlmacen.getProducto().getNombre()+" no tiene existencia");

		if(detalleAlmacen.getCantidad() < cantidad)
			throw new IllegalArgumentException("La cantidad del producto digitado en la factura, no puede ser mayor a la existencia, favor ver el producto "+detalleAlmacen.getProducto().getNombre());


	}

	public Almacen buscarAlmacenPorUsuario(String usuario){
		Almacen almacen = usuarioService.findAlmacenAsignado(usuario);
		return almacen;
	}

}
