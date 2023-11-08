package com.weberp.app.controllers;

import com.weberp.app.common.view.BaseController;
import com.weberp.app.domain.Factura;
import com.weberp.app.enums.TipoDocumentoEnum;
import com.weberp.app.exception.FacturaException;
import com.weberp.app.services.*;
import com.weberp.app.utils.JasperReportUtil;
import com.weberp.app.validator.FacturaValidator;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by claudioruiz on 7/27/16.
 */

@Controller
@RequestMapping(value = "/facturas")
public class FacturaController extends BaseController {

    JasperReportUtil jasperReportUtil;
    private FacturaService facturaService;

    private ProductoService productoService;

    private ClienteService clienteService;

    private TipoDocumentoService tipoDocumentoService;

    @Autowired
    private ServletContext context;

    @Autowired
    public FacturaController(FacturaService facturaService, ClienteService clienteService,
                             ComprobanteFiscalService comprobanteFiscalService, ProductoService productoService,
                             TipoDocumentoService tipoDocumentoService, EstatusService estatusService, FacturaValidator facturaValidator,
                             UsuarioService usuarioService) {
        super(usuarioService);
        this.facturaService = facturaService;
        this.clienteService = clienteService;
        this.tipoDocumentoService = tipoDocumentoService;
        this.productoService = productoService;
    }

    @RequestMapping("")
    public String list(Model model) {
        setUsuario(model);


        return "Factura/ConsultaFactura";
    }

    @RequestMapping("crear")
    public String crear(Model model) {
        setUsuario(model);

        return "Factura/FormularioFactura";
    }

    @RequestMapping(value = "/agregarDetalleFactura", params = {"agregarDetalleFactura"}, method = RequestMethod.POST)
    public String agregarDetalleFactura(Factura factura, Model model) {
        setUsuario(model);


        return "Factura/FormularioFactura";
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/quitarDetalleFactura", params = {"quitarDetalleFactura"}, method = RequestMethod.POST)
    public String quitarDetalleFactura(Factura factura, HttpServletRequest req, Model model) {
        setUsuario(model);

        return "Factura/FormularioFactura";
    }


    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping("editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        setUsuario(model);
        model.addAttribute("id", id);
        model.addAttribute("factura", facturaService.getFacturaById(id));
        model.addAttribute("clienteList", clienteService.listaClientes());
        model.addAttribute("tipoDocumentoList", tipoDocumentoService.listaTiposDocumentosSalida());

        model.addAttribute("productoLista", productoService.listaProductos());

        return "Factura/FormularioFactura";
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping("view/{id}")
    public String view(@PathVariable Long id, Model model) {
        setUsuario(model);
        model.addAttribute("id", id);

        return "Factura/ViewFactura";
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "cambiarEstatusFactura", method = RequestMethod.POST)
    public String aprobar(Factura factura, Model model) throws FacturaException {

        setUsuario(model);
        facturaService.cambiarEstatusFactura(factura);

        return "redirect:/facturas/view/" + factura.getId();
    }

    @RequestMapping(value = "imprimirDocumento/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void imprimirFactura(@PathVariable Long id, HttpServletResponse response, Model model)
            throws JRException, IOException, SQLException {
        setUsuario(model);

        Factura factura = facturaService.getFacturaById(id);
        jasperReportUtil = new JasperReportUtil();

        if (null == factura.getTipoDocumento() && factura.getTipoDocumento().getLlaveDocumento().equals(""))
            return;

        if (factura.getTipoDocumento().getLlaveDocumento().equals(TipoDocumentoEnum.FACTURA)) {
            jasperReportUtil.imprimirDocumento(response, id, factura.getNumeroDocumento(), context, JasperReportUtil.REPORTE_FACTURACION);
        }

        if (factura.getTipoDocumento().getLlaveDocumento().equals(TipoDocumentoEnum.COTIZACION)) {
            jasperReportUtil.imprimirDocumento(response, id, factura.getNumeroDocumento(), context, JasperReportUtil.REPORTE_COTIZACION);
        }

    }


}
