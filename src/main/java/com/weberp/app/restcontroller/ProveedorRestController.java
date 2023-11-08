package com.weberp.app.restcontroller;

import com.weberp.app.domain.Proveedor;
import com.weberp.app.dto.ProveedorDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.services.ProveedorService;
import com.weberp.app.validator.ProveedorValidator;
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
 * Created by claudioruiz on 6/7/17.
 */
@RestController
@RequestMapping("/rest/proveedor")
public class ProveedorRestController extends ConfigMapper{

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private ProveedorValidator proveedorValidator;



    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<ProveedorDTO> getCliente() {
        //...
        List<Proveedor> clientesList = proveedorService.getProveedores();
        return clientesList.stream()
                .map(proveedor -> convertProveedorToDto(proveedor))
                .collect(Collectors.toList());
    }
    @RequestMapping(value="/pagination",params = { "page", "size" },method = RequestMethod.GET)
    public Page<ProveedorDTO> findPaginated(
            @RequestParam("page") int page, @RequestParam("size") int size) {

        Page<ProveedorDTO> resultPage = proveedorService.findPaginated(page,size);
        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe facturas registradas.");
        }

        return resultPage;
    }
    @RequestMapping(value="/filtrar",params = { "busqueda","page", "size" },method = RequestMethod.GET)
    public Page<ProveedorDTO> findProveedorAndPaginated( @RequestParam("busqueda") String busqueda,
                                                     @RequestParam("page") int page, @RequestParam("size") int size) {


        Page<ProveedorDTO> resultPage = proveedorService.findProveedorAndPaginated(busqueda,new PageRequest(page,size, Sort.Direction.ASC,"id"));
        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe productos registrados.");
        }

        return resultPage;
    }



    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ProveedorDTO getPost(@PathVariable("id") Long id) {
        return convertProveedorToDto(proveedorService.getProveedorById(id));
    }
    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ProveedorDTO save(@Valid @RequestBody ProveedorDTO proveedorDTO, BindingResult result, HttpServletResponse httpServletResponse) {
        Proveedor proveedor = null;
        try {
            proveedor = convertProveedorToEntity(proveedorDTO);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilderError = new StringBuilder();


        proveedorValidator.validate(proveedor, result);
        if (result.hasErrors()) {

            for (ObjectError objectError : result.getAllErrors()) {
                stringBuilderError.append(objectError.getDefaultMessage()+" \n ");
            }
            throw new IllegalArgumentException(stringBuilderError.toString());

        }


        proveedor = proveedorService.guardar(proveedor);
        return convertProveedorToDto(proveedor);

    }


}
