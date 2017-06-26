package com.weberp.app.restcontroller;

import com.weberp.app.dto.DiarioGeneralDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.services.DiarioGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * Created by claudioruiz on 6/21/17.
 */

@RestController
@RequestMapping("/rest/diariogeneral")
public class DiarioGeneralRestController extends ConfigMapper {

    @Autowired
    private DiarioGeneralService diarioGeneralService;




    @RequestMapping(value="/pagination",params = { "page", "size" },method = RequestMethod.GET)
    public Page<DiarioGeneralDTO> findPaginated(
            @RequestParam("page") int page, @RequestParam("size") int size) {

        Page<DiarioGeneralDTO> resultPage = diarioGeneralService.listaDiarioGeneral(new PageRequest(page,size, Sort.Direction.DESC,"id"));
        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe facturas registradas.");
        }

        return resultPage;
    }
    @RequestMapping(value="/filtrar",params = { "fechaDesde","fechaHasta","page", "size" },method = RequestMethod.GET)
    public Page<DiarioGeneralDTO> findTipoProductoAndPaginated( @RequestParam("fechaDesde") String fechaDesde,@RequestParam("fechaHasta") String fechaHasta,
                                                          @RequestParam("page") int page, @RequestParam("size") int size) throws ParseException{


        Page<DiarioGeneralDTO> resultPage = diarioGeneralService.findDiarioGeneralAndPaginated(fechaDesde,fechaHasta,new PageRequest(page,size, Sort.Direction.DESC,"id"));


        if (page > resultPage.getTotalPages()) {
            throw new IllegalArgumentException("No existe productos registrados.");
        }

        return resultPage;
    }

}
