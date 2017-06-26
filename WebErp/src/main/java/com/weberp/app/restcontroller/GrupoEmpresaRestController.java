package com.weberp.app.restcontroller;

import com.weberp.app.common.view.BaseRestController;
import com.weberp.app.domain.GrupoEmpresa;
import com.weberp.app.dto.GrupoEmpresaDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.repositories.GrupoEmpresaRepository;
import com.weberp.app.services.GrupoEmpresaService;
import com.weberp.app.validator.GrupoEmpresaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by claudioruiz on 6/9/17.
 */

@RestController
@RequestMapping("/rest/grupoempresa")
public class GrupoEmpresaRestController extends ConfigMapper{


    @Autowired
    private GrupoEmpresaValidator grupoEmpresaValidator;

    @Autowired
    private GrupoEmpresaService grupoEmpresaService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<GrupoEmpresaDTO> getCliente() {
        //...
        List<GrupoEmpresa> clientesList = grupoEmpresaService.listaGrupoEmpresa();
        return clientesList.stream()
                .map(grupoEmpresa -> convertToDtoGrupoEmpresa(grupoEmpresa))
                .collect(Collectors.toList());
    }

    @RequestMapping(value="/pagination",params = { "page", "size" },method = RequestMethod.GET)
    public Page<GrupoEmpresaDTO> findPaginated(
            @RequestParam("page") int page, @RequestParam("size") int size) {

        Page<GrupoEmpresaDTO> resultPage = grupoEmpresaService.findPaginated(page,size);
        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe facturas registradas.");
        }

        return resultPage;
    }
    @RequestMapping(value="/filtrar",params = { "nombre","page", "size" },method = RequestMethod.GET)
    public Page<GrupoEmpresaDTO> findGrupoEmpresaAndPaginated( @RequestParam("nombre") String nombre,
                                                          @RequestParam("page") int page, @RequestParam("size") int size) {


        Page<GrupoEmpresaDTO> resultPage = grupoEmpresaService.findGrupoEmpresaAndPaginated(nombre,new PageRequest(page,size, Sort.Direction.ASC,"id"));
        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe productos registrados.");
        }

        return resultPage;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public GrupoEmpresaDTO getPost(@PathVariable("id") Long id) {
        return convertToDtoGrupoEmpresa(grupoEmpresaService.getGrupoEmpresaById(id));
    }
    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void save(@Valid @RequestBody GrupoEmpresaDTO grupoEmpresaDTO, BindingResult result, HttpServletResponse httpServletResponse) {
        GrupoEmpresa grupoEmpresa = null;
        try {
            grupoEmpresa = convertGrupoEmpresaDtoToEntity(grupoEmpresaDTO);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilderError = new StringBuilder();


        grupoEmpresaValidator.validate(grupoEmpresa, result);
        if (result.hasErrors()) {

            for (ObjectError objectError : result.getAllErrors()) {
                stringBuilderError.append(objectError.getDefaultMessage()+" \n ");
            }
            throw new IllegalArgumentException(stringBuilderError.toString());

        }


        grupoEmpresaService.guardar(grupoEmpresa);

    }
}
