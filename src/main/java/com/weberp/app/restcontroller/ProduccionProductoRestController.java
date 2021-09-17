package com.weberp.app.restcontroller;

import com.weberp.app.domain.ProduccionProducto;
import com.weberp.app.dto.ProduccionProductoDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.services.ProduccionProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by claudioruiz on 6/18/17.
 */
@RestController
@RequestMapping("/rest/produccionProducto")
public class ProduccionProductoRestController extends ConfigMapper {

    @Autowired
    private ProduccionProductoService produccionProductoService;




    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<ProduccionProductoDTO> getProducionProducto() {
        //...
        List<ProduccionProducto> produccionProductoList = produccionProductoService.listaProduccionProductos();
        return produccionProductoList.stream()
                .map(this::convertProduccionProductoToDto)
                .collect(Collectors.toList());
    }

}
