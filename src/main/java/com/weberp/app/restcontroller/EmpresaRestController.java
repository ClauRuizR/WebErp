package com.weberp.app.restcontroller;

import com.weberp.app.domain.Empresa;
import com.weberp.app.dto.EmpresaDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.services.EmpresaService;
import com.weberp.app.validator.EmpresaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
@RequestMapping("/rest/empresa")
public class EmpresaRestController extends ConfigMapper{

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private EmpresaValidator empresaValidator;


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<EmpresaDTO> getCliente() {
        //...
        List<Empresa> listaEmpresa = empresaService.listaEmpresa();
        return listaEmpresa.stream()
                .map(empresa -> convertToDtoEmpresa(empresa))
                .collect(Collectors.toList());
    }
    @RequestMapping(value="/pagination",params = { "page", "size" },method = RequestMethod.GET)
    public Page<EmpresaDTO> findPaginated(
            @RequestParam("page") int page, @RequestParam("size") int size) {

        Page<EmpresaDTO> resultPage = empresaService.findPaginated(page,size);
        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe facturas registradas.");
        }

        return resultPage;
    }
    @RequestMapping(value="/filtrar",params = { "nombre","page", "size" },method = RequestMethod.GET)
    public Page<EmpresaDTO> findEmpresaAndPaginated( @RequestParam("nombre") String nombre,
                                                               @RequestParam("page") int page, @RequestParam("size") int size) {


        Page<EmpresaDTO> resultPage = empresaService.findEmpresaAndPaginated(nombre,new PageRequest(page,size, Sort.Direction.ASC,"id"));
        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe productos registrados.");
        }

        return resultPage;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public EmpresaDTO getPost(@PathVariable("id") Long id) {
        return convertToDtoEmpresa(empresaService.getEmpresaById(id));
    }


    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void save(@Valid @RequestBody EmpresaDTO empresaDTO, BindingResult result, HttpServletResponse httpServletResponse) {
        Empresa empresa = null;
        try {
            empresa = convertEmpresaDtoToEntity(empresaDTO);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilderError = new StringBuilder();


        empresaValidator.validate(empresa, result);
        if (result.hasErrors()) {

            for (ObjectError objectError : result.getAllErrors()) {
                stringBuilderError.append(objectError.getDefaultMessage()+" \n ");
            }
            throw new IllegalArgumentException(stringBuilderError.toString());

        }

        try {

            empresaService.guardar(empresa);
        }catch (Exception ex)
        {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

}
