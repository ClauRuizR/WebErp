package com.weberp.app.restcontroller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.weberp.app.services.DiarioGeneralService;
import com.weberp.app.services.ProductoService;

/**
 * Created by claudioruiz on 6/21/17.
 */

@RestController
@RequestMapping("/rest/home")
public class HomeRestController {

    @Autowired
    private DiarioGeneralService diarioGeneralService;

    @Autowired
    private ProductoService productoService;


    @RequestMapping(value="/getIngresosMensuales",method = RequestMethod.GET)
    @ResponseBody
    public List<Double> getIngresos() {
        //...
        try {
            return diarioGeneralService.getIngresosMensuales();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    return null;
    }


    @RequestMapping(value="/getProductoStockLabel",method = RequestMethod.GET)
    @ResponseBody
    public List<String> getProductoStockLabel() {
        //...
        try {
            return productoService.getStockProductoLabel();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }


    @RequestMapping(value="/getProductoStockCantidad",method = RequestMethod.GET)
    @ResponseBody
    public List<Long> getProductoStockCantidad() {
        //...
        try {
            return productoService.getStockProductos();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
