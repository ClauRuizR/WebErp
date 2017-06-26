package com.weberp.app.services;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.stream.Collectors.*;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.weberp.app.common.model.UsuarioUtil;
import com.weberp.app.dto.DiarioGeneralDTO;
import com.weberp.app.dto.config.ConfigMapper;
import com.weberp.app.reportes.IngresosMensualesReporte;
import org.h2.expression.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.weberp.app.domain.DiarioGeneral;
import com.weberp.app.repositories.DiarioGeneralRepository;
@Service
public class DiarioGeneralServiceImpl extends ConfigMapper implements DiarioGeneralService {


    DiarioGeneral diarioGeneral;
	
	@Autowired
	private DiarioGeneralRepository diarioGeneralRepository;

	@Autowired
    private UsuarioService usuarioService;
	
	@Override
	public Page<DiarioGeneralDTO> listaDiarioGeneral(Pageable pageable) {
        Long empresaId = UsuarioUtil.getCurrentUserEmpresa().getEmpresa().getId();

        Page<DiarioGeneral> generalPage = diarioGeneralRepository.findByEmpresa_Id(empresaId,pageable);
        final Page<DiarioGeneralDTO> contactDtoPage = generalPage.map(this::convertDiarioGeneralToDto);
        return contactDtoPage;
	}

    @Override
    public List<DiarioGeneral> listaDiarioGeneral() {
        Long empresaId = UsuarioUtil.getCurrentUserEmpresa().getEmpresa().getId();
        return diarioGeneralRepository.findByEmpresa_Id(empresaId);
    }

    @Override
	public DiarioGeneral guardar(DiarioGeneral diarioGeneral) {
		// TODO Auto-generated method stub
		return diarioGeneralRepository.save(diarioGeneral);
	}

	@Override
	public DiarioGeneral getDiarioGeneralById(Long id) {
		// TODO Auto-generated method stub
		return diarioGeneralRepository.findOne(id);
	}

	@Override
	public void borrar(Long id) {
		diarioGeneralRepository.delete(id);

	}

	@Override
	public Page<DiarioGeneralDTO> findPaginated(int page, int size) {
		Page<DiarioGeneral> diarioGeneralPage=  (diarioGeneralRepository.findAll(new PageRequest(page,size)));

		final Page<DiarioGeneralDTO> contactDtoPage = diarioGeneralPage.map(this::convertDiarioGeneralToDto);
		return contactDtoPage;
	}

	@Override
	public Page<DiarioGeneralDTO> findDiarioGeneralAndPaginated(String fechaDesde, String fechaHasta, Pageable pageRequest)  throws ParseException {

		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Page<DiarioGeneral> diarioGeneralPage;

			Date newfechaDesde = df.parse(fechaDesde);
			Date newfechaHasta = df.parse(fechaHasta);
			diarioGeneralPage	=  diarioGeneralRepository.findByFechaBetween(newfechaDesde,newfechaHasta,pageRequest);
			final Page<DiarioGeneralDTO> contactDtoPage = diarioGeneralPage.map(this::convertDiarioGeneralToDto);

			return contactDtoPage;

	}

	@Override
	public    List<Double>  getIngresosMensuales() throws ParseException {


        Calendar calDate = Calendar.getInstance();

        calDate.set(Calendar.DAY_OF_YEAR, 1);
        Date yearStartDate = calDate.getTime();



        calDate.set(Calendar.DAY_OF_YEAR, calDate.getActualMaximum(Calendar.DAY_OF_YEAR));
        Date yearEndDate = calDate.getTime();

        SimpleDateFormat sdfMes = new SimpleDateFormat("MM");



	     List<DiarioGeneral>  ingresosMensualesReporteList =
        diarioGeneralRepository.findByFechaBetween(yearStartDate, yearEndDate);
       List<Double> listaMeses = new ArrayList<>();
         ingresosMensualesReporteList.stream().collect(Collectors.groupingBy(diarioGeneral -> sdfMes.format(diarioGeneral.getFecha()),
                Collectors.summingDouble(n-> n.getDebito().subtract(n.getCredito()).doubleValue())))
                .forEach((fecha, monto)->

                        {
                            if (fecha.equals("01")) {
                               listaMeses.add(monto);
                            }else{
                                listaMeses.add(0.00);
                            }


                            if (fecha.contains("02")) {
                                listaMeses.add(monto);
                            }else{
                                listaMeses.add(0.00);
                            }

                            if (fecha.contains("03")) {
                                listaMeses.add(monto);
                            }else{
                                listaMeses.add(0.00);
                            }

                            if (fecha.contains("04")) {
                                listaMeses.add(monto);
                            }else{
                                listaMeses.add(0.00);
                            }

                            if (fecha.contains("05")) {
                                listaMeses.add(monto);
                            }else{
                                listaMeses.add(0.00);
                            }

                            if (fecha.contains("06")) {
                                listaMeses.add(monto);
                            }else{
                                listaMeses.add(0.00);
                            }

                            if (fecha.contains("07")) {
                                listaMeses.add(monto);
                            }else{
                                listaMeses.add(0.00);
                            }

                            if (fecha.contains("08")) {
                                listaMeses.add(monto);
                            }else{
                                listaMeses.add(0.00);
                            }

                            if (fecha.contains("09")) {
                                listaMeses.add(monto);
                            }else{
                                listaMeses.add(0.00);
                            }

                            if (fecha.contains("10")) {
                                listaMeses.add(monto);
                            }else{
                                listaMeses.add(0.00);
                            }

                            if (fecha.contains("11")) {
                                listaMeses.add(monto);
                            }else{
                                listaMeses.add(0.00);
                            }

                            if (fecha.contains("12")) {
                                listaMeses.add(monto);
                            }else{
                                listaMeses.add(0.00);
                            }


                        }

                        );




		return listaMeses;
	}


}
