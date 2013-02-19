package com.edicsem.pe.sie.client.report.service;
import javax.ejb.Local;
import javax.servlet.http.HttpServletResponse;

import com.edicsem.pe.sie.beans.ReporteParams;


@Local
public interface ReporteExecutionService {
	
	public abstract void executeReporte(ReporteParams reportParams, HttpServletResponse response );
	
}