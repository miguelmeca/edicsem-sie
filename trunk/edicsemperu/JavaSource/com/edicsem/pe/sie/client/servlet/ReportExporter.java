package com.edicsem.pe.sie.client.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ReportExporter {

	public static final Log log = LogFactory.getLog(ReportExporter.class);

	public static void exportReportPDF(JasperPrint jp, String path, HttpServletResponse response) throws JRException, IOException {
		JRPdfExporter exporter = new JRPdfExporter();
		
		File outputFile = new File(path);
		File parentFile = outputFile.getParentFile();
		
		if (!parentFile.exists()){
			parentFile.mkdirs();
		}
		ServletOutputStream out = response.getOutputStream();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
		exporter.exportReport();
		JasperExportManager.exportReportToPdfFile(jp, path);
	}

	public static void exportReportXls(JasperPrint jp, String out) throws JRException, FileNotFoundException {
		JRXlsExporter exporter = new JRXlsExporter();
		File outputFile = new File(out);
		File parentFile = outputFile.getParentFile();
		
		if (!parentFile.exists()){
			parentFile.mkdirs();
		}
		FileOutputStream fos = new FileOutputStream(outputFile);
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
		exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_GRAPHICS,Boolean.FALSE);
		exporter.exportReport();
		log.debug("XLS Report exported: " );
	}

	public static void exportReportHtml(JasperPrint jp, String path) throws JRException, FileNotFoundException {
		JRHtmlExporter exporter = new JRHtmlExporter();
		File outputFile = new File(path);
		File parentFile = outputFile.getParentFile();
		
		if (!parentFile.exists()){
			parentFile.mkdirs();
		}
		FileOutputStream fos = new FileOutputStream(outputFile);
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
		exporter.exportReport();
		
		log.debug("HTML Report exported: " + path);
	}

	public static void exportReportPlainXls(JasperPrint jp, String path)throws JRException, FileNotFoundException {
		
		JExcelApiExporter exporter = new JExcelApiExporter();
		File outputFile = new File(path);
		File parentFile = outputFile.getParentFile();
		
		if (!parentFile.exists()){
			parentFile.mkdirs();
		}
		FileOutputStream fos = new FileOutputStream(outputFile);
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,Boolean.TRUE);
		exporter.exportReport();
		log.debug("Report exported: " + path);
	}
}