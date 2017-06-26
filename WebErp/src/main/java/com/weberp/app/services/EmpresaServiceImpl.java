package com.weberp.app.services;

import com.weberp.app.common.model.UsuarioUtil;
import com.weberp.app.domain.Empresa;
import com.weberp.app.dto.EmpresaDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by claudioruiz on 6/8/17.
 */
@Service
public class EmpresaServiceImpl extends ConfigMapper implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public List<Empresa> listaEmpresa() {
        return (List<Empresa>) empresaRepository.findAll();
    }

    @Override
    public Empresa guardar(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    @Override
    public Empresa getEmpresaById(Long id) {
        return empresaRepository.findOne(id);
    }

    @Override
    public void borrar(Long id) {
    empresaRepository.delete(id);
    }

    @Override
    public List<Empresa> listaEmpresaPorUsuario() {

        Long empresaId = usuarioService.findByUsuario(UsuarioUtil.getCurrentUser()).getEmpresa().getId();
        return empresaRepository.findById(empresaId);
    }

    @Override
    public Page<EmpresaDTO> findPaginated(int page, int size) {
        Long empresaId = UsuarioUtil.getCurrentUserEmpresa().getEmpresa().getId();

        Page<Empresa> empresaPage=  (empresaRepository.findAll(new PageRequest(page,size)));

        final Page<EmpresaDTO> contactDtoPage = empresaPage.map(this::convertToDtoEmpresa);
        return contactDtoPage;
    }

    @Override
    public Page<EmpresaDTO> findEmpresaAndPaginated(String nombre, Pageable pageRequest) {
        Page<Empresa> empresaPage=  empresaRepository.findByNombre(nombre,pageRequest);

        final Page<EmpresaDTO> contactDtoPage = empresaPage.map(this::convertToDtoEmpresa);
        return contactDtoPage;
    }
}
