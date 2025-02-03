package negocioImpl;

import java.sql.Date;

import daoImpl.daoReporteImpl;

public class negocioReporteImpl {

	public negocioReporteImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public String generarReporte(int reporteSelected) {
		String reported="";
		daoReporteImpl daoReporte= new daoReporteImpl();
		reported = daoReporte.generarReporte(reporteSelected);
		return reported;
	}
	
	public String generarReporteFechas(int reporteSelected, Date fechaInicio, Date fechaFin) {
		String reported="";
		daoReporteImpl daoReporte= new daoReporteImpl();
		reported = daoReporte.generarReporteFechas(reporteSelected, fechaInicio, fechaFin);
		return reported;
	}

}
