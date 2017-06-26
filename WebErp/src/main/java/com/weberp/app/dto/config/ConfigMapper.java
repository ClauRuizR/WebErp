package com.weberp.app.dto.config;

import com.weberp.app.domain.*;
import com.weberp.app.dto.*;
import com.weberp.app.mapperobject.Mapper;
import com.weberp.app.mapperobject.MapperObject;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.List;


public  abstract class ConfigMapper {

    @Autowired
    private  ModelMapper modelMapper;


    public LocalidadDTO convertLocalidadToDto(Localidad localidad) {
        LocalidadDTO localidadDTO = modelMapper.map(localidad, LocalidadDTO.class);

        return localidadDTO;
    }


    public Localidad convertLocalidadToEntity(LocalidadDTO localidadDTO) throws ParseException {
        Localidad localidad = modelMapper.map(localidadDTO, Localidad.class);

        return localidad;
    }

    public ProductoDTO convertProductoToDto(Producto producto) {
        ProductoDTO productoDTO = modelMapper.map(producto, ProductoDTO.class);

        return productoDTO;
    }


    public Producto convertProductoToEntity(ProductoDTO productoDTO) throws ParseException {
        Producto producto = modelMapper.map(productoDTO, Producto.class);

        return producto;
    }

    public  GrupoEmpresaDTO convertToDtoGrupoEmpresa(GrupoEmpresa grupoEmpresa) {
        GrupoEmpresaDTO grupoEmpresaDTO = modelMapper.map(grupoEmpresa, GrupoEmpresaDTO.class);

        return grupoEmpresaDTO;
    }

    public  GrupoEmpresa convertGrupoEmpresaDtoToEntity(GrupoEmpresaDTO grupoEmpresaDTO) throws ParseException {
        GrupoEmpresa grupoEmpresa = modelMapper.map(grupoEmpresaDTO, GrupoEmpresa.class);

        return grupoEmpresa;
    }


    public TipoProductoDTO convertToDtoTipoProducto(TipoProducto tipoProducto) {
        TipoProductoDTO tipoProductoDTO = modelMapper.map(tipoProducto, TipoProductoDTO.class);

        return tipoProductoDTO;
    }

    public  TipoProducto convertTipoProductoDtoToEntity(TipoProductoDTO tipoProductoDTO) throws ParseException {
        TipoProducto tipoProducto = modelMapper.map(tipoProductoDTO, TipoProducto.class);

        return tipoProducto;
    }

    public FacturaDTO convertFacturaToDto(Factura factura) {
        FacturaDTO facturaDTO = modelMapper.map(factura, FacturaDTO.class);

        return facturaDTO;
    }


    public Factura convertFacturaToEntity(FacturaDTO facturaDTO) throws ParseException {
        Factura cliente = modelMapper.map(facturaDTO, Factura.class);

        return cliente;
    }

    public   ProveedorDTO convertProveedorToDto(Proveedor proveedor) {
        ProveedorDTO proveedorDTO = modelMapper.map(proveedor, ProveedorDTO.class);

        return proveedorDTO;
    }

    public Proveedor convertProveedorToEntity(ProveedorDTO proveedorDTO) throws ParseException {
        Proveedor proveedor = modelMapper.map(proveedorDTO, Proveedor.class);

        return proveedor;
    }


    public EmpresaDTO convertToDtoEmpresa(Empresa empresa) {
        EmpresaDTO empresaDTO = modelMapper.map(empresa, EmpresaDTO.class);

        return empresaDTO;
    }

    public  Empresa convertEmpresaDtoToEntity(EmpresaDTO empresaDTO) throws ParseException {
        Empresa empresa = modelMapper.map(empresaDTO, Empresa.class);

        return empresa;
    }


    public   ClienteDTO convertClienteToDto(Cliente cliente) {
        ClienteDTO clienteDTO = modelMapper.map(cliente, ClienteDTO.class);

        return clienteDTO;
    }

    public Cliente convertClienteDtoToEntity(ClienteDTO clienteDTO) throws ParseException {
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);

        return cliente;
    }




    public OrdenCompraDTO convertOrdenCompraToDto(OrdenCompra ordenCompra) {
        OrdenCompraDTO ordenCompraDTO = modelMapper.map(ordenCompra, OrdenCompraDTO.class);

        return ordenCompraDTO;
    }

    public OrdenCompra convertOrdenCompraToEntity(OrdenCompraDTO ordenCompraDTO) throws ParseException {
        OrdenCompra ordenCompra = modelMapper.map(ordenCompraDTO, OrdenCompra.class);

        return ordenCompra;
    }




    //TipoServicio



    public TipoServicioDTO convertTipoServicioToDto(TipoServicio tipoServicio) {
        TipoServicioDTO tipoServicioDTO = modelMapper.map(tipoServicio, TipoServicioDTO.class);

        return tipoServicioDTO;
    }

    public TipoServicio convertTipoServicioToEntity(TipoServicioDTO tipoServicioDTO) throws ParseException {
        TipoServicio tipoServicio = modelMapper.map(tipoServicioDTO, TipoServicio.class);

        return tipoServicio;
    }


    //Servicio




    public ServicioDTO convertServicioToDto(Servicio servicio) {
        ServicioDTO servicioDTO = modelMapper.map(servicio, ServicioDTO.class);

        return servicioDTO;
    }

    public Servicio convertServicioToEntity(ServicioDTO servicioDTO) throws ParseException {
        Servicio servicio = modelMapper.map(servicioDTO, Servicio.class);

        return servicio;
    }



    public ProduccionProductoDTO convertProduccionProductoToDto(ProduccionProducto produccionProducto) {
        ProduccionProductoDTO produccionProductoDTO = modelMapper.map(produccionProducto, ProduccionProductoDTO.class);

        return produccionProductoDTO;
    }

    public ProduccionProducto convertProduccionProductoToEntity(ProduccionProductoDTO produccionProductoDTO) throws ParseException {
        ProduccionProducto produccionProducto = modelMapper.map(produccionProductoDTO, ProduccionProducto.class);

        return produccionProducto;
    }



    ////ROL




    public RolDTO convertRolToDto(Rol rol) {
        RolDTO rolDTO = modelMapper.map(rol, RolDTO.class);

        return rolDTO;
    }

    public Rol convertRolToEntity(RolDTO rolDTO) throws ParseException {
        Rol rol = modelMapper.map(rolDTO, Rol.class);

        return rol;
    }
//USUARIO


    public UsuarioDTO convertUsuarioToDto(Usuario usuario) {
        UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);

        return usuarioDTO;
    }

    public Usuario convertUsuarioToEntity(UsuarioDTO usuarioDTO) throws ParseException {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);

        return usuario;
    }


    //DiarioGeneral


    public DiarioGeneralDTO convertDiarioGeneralToDto(DiarioGeneral diarioGeneral) {
        DiarioGeneralDTO diarioGeneralDTO = modelMapper.map(diarioGeneral, DiarioGeneralDTO.class);

        return diarioGeneralDTO;
    }

    public DiarioGeneral convertDiarioGeneralToEntity(DiarioGeneralDTO diarioGeneralDTO) throws ParseException {
        DiarioGeneral diarioGeneral = modelMapper.map(diarioGeneralDTO, DiarioGeneral.class);

        return diarioGeneral;
    }



    public List<DetalleAlmacenDTO> convertDetalleAlmacenToDto(List<DetalleAlmacen> detalleAlmacen) {

        Type listType = new TypeToken<List<DetalleAlmacenDTO>>() {}.getType();

        List<DetalleAlmacenDTO> detalleAlmacenDTOList = modelMapper.map(detalleAlmacen, listType);

        return detalleAlmacenDTOList;
    }

    public AlmacenDTO convertAlmacenToDto(Almacen almacen) {
        AlmacenDTO almacenDTO = modelMapper.map(almacen, AlmacenDTO.class);

        return almacenDTO;
    }

    public Almacen convertAlmacenToEntity(AlmacenDTO almacenDTO) throws ParseException {
        Almacen almacen = modelMapper.map(almacenDTO, Almacen.class);

        return almacen;
    }

}
