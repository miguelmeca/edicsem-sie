package com.edicsem.pe.sie.client.action.mantenimiento;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;


import com.edicsem.pe.sie.entity.Personalead;
import com.edicsem.pe.sie.service.facade.PersonaService;
@SuppressWarnings("serial")
@ManagedBean(name = "FileUpload")
@SessionScoped
public class FileUpload implements Serializable {

	public static Log log = LogFactory.getLog(FileUpload.class);
	private String nombreArchivo;
	private List<Personalead> leadsNuevos;
	
	
	@EJB
	private PersonaService objPersona;
		  
	public FileUpload() {

	
	}
	
	public void InputStreamAFile(InputStream entrada, String nombreArchivo) {
		try {

			ServletContext ctx = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();

			String path = ctx.getRealPath("/interseguros");

			File directory = new File(path);
			Boolean existe = directory.exists();
			if (!existe) {
				directory.mkdir();
			}

			System.out.println(path);

			File f = new File(path + "/" + nombreArchivo);// Aqui le dan el
															// nombre y/o con la

			System.out.println(f.getAbsolutePath());
			// ruta del archivo salida

			OutputStream salida = new FileOutputStream(f);
			byte[] buf = new byte[1024];// Actualizado me olvide del 1024
			int len;
			while ((len = entrada.read(buf)) > 0) {
				salida.write(buf, 0, len);
			}
			salida.close();
			entrada.close();
			System.out.println("Se realizo la conversion con exito");
		} catch (IOException e) {
			System.out.println("Se produjo el error : " + e.toString());
		}
	}
	
	
	public String subirCreditos() {

		System.out.println("subirCreditos()");
		Personalead personalead = new Personalead();
	

		for (int i = 0; i < leadsNuevos.size(); i++) {

			Personalead credito = leadsNuevos.get(i);
			
			System.out.println("cantidad: " + leadsNuevos.size());
			System.out.println("id 1: " + leadsNuevos.get(i).getId());
			System.out.println("nombre 1: " + leadsNuevos.get(i).getNombre());
		
			objPersona.insertPersona(credito);
			

		}
		
		return null;
	}

	
//	personaDao.persist(credito);
	
	
//	objPersona.persist(credito);

	    public String handleFileUpload(FileUploadEvent event) {
	    	System.out.println("entro file");
			FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
			UploadedFile file = event.getFile();

			try {
				InputStreamAFile(file.getInputstream(), file.getFileName());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			nombreArchivo = file.getFileName();
			
			/********aqui me quede*********/
			
			leadsNuevos = new ArrayList<Personalead>();

			List<List<HSSFCell>> sheetData = new ArrayList<List<HSSFCell>>();

			FileInputStream fis = null;

			try {
				HSSFWorkbook workbook = new HSSFWorkbook(file.getInputstream());
				HSSFSheet sheet = workbook.getSheetAt(0);

				@SuppressWarnings("rawtypes")
				Iterator rows = sheet.rowIterator();

				while (rows.hasNext()) {

					HSSFRow row = (HSSFRow) rows.next();

					if (row.getRowNum() > 0) {

						List<HSSFCell> data = new ArrayList<HSSFCell>();

						for (int i = 0; i < 31; i++) {

							@SuppressWarnings("static-access")
							HSSFCell cell = row.getCell(i,
									row.RETURN_NULL_AND_BLANK);

							data.add(cell);
						}

						int tamano = data.size();

						if (tamano > 1) {

							Personalead credito = new Personalead();
							if (data.get(0)!=null) {
								String id = getCellValueAsString(data.get(0));
								credito.setId(Integer.parseInt(id));
								
								String nombre = getCellValueAsString(data.get(1));
								credito.setNombre(nombre);
								
								leadsNuevos.add(credito);
								sheetData.add(data);
							}else { 
								log.info(data.get(0));
							}
							
						}
			
		}
					
	}
				
				FacesMessage msg2 = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Leads", "Cargado exitosamente.");
				FacesContext.getCurrentInstance().addMessage(null, msg2);
	
				}
			catch (IOException e) {
				e.printStackTrace();
				FacesMessage msg2 = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Leads", " errores -> IOException.");

				FacesContext.getCurrentInstance().addMessage(null, msg2);

				leadsNuevos = null;

				return null;
	    
}finally {
	if (fis != null) {
		try {
			fis.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}

System.out.println("cantidad: " + leadsNuevos.size());
System.out.println("id: " + leadsNuevos.get(0).getId());
System.out.println("id: " + leadsNuevos.get(0).getNombre());
System.out.println("nombre: " + leadsNuevos.get(1).getNombre());

return null;


	    }

	    public void mensajeDeVacio(String mensaje) {

			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Deudor", mensaje);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			// setCreditosNuevos(null);

		}
	    
	    
	    
	    
	    public String getCellValueAsString(HSSFCell cell) {
			String strCellValue = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if (cell != null) {
				switch (cell.getCellType()) {
				case HSSFCell.CELL_TYPE_STRING:
					strCellValue = cell.toString().trim();
					break;
				case HSSFCell.CELL_TYPE_NUMERIC:
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						strCellValue = sdf.format(cell.getDateCellValue()).trim();
					} else {
						Double value = cell.getNumericCellValue();
						Long longValue = value.longValue();
						strCellValue = new String(longValue.toString().trim());
					}
					break;
				case HSSFCell.CELL_TYPE_BOOLEAN:
					strCellValue = new String(new Boolean(
							cell.getBooleanCellValue()).toString().trim());
					break;
				case HSSFCell.CELL_TYPE_BLANK:
					strCellValue = "";
					break;
				}
			}
			return strCellValue;
		}

		public String getNombreArchivo() {
			return nombreArchivo;
		}

		public void setNombreArchivo(String nombreArchivo) {
			this.nombreArchivo = nombreArchivo;
		}

		public List<Personalead> getLeadsNuevos() {
			return leadsNuevos;
		}

		public void setLeadsNuevos(List<Personalead> leadsNuevos) {
			this.leadsNuevos = leadsNuevos;
		}

		/**
		 * @return the log
		 */
		public static Log getLog() {
			return log;
		}

		/**
		 * @param log the log to set
		 */
		public static void setLog(Log log) {
			FileUpload.log = log;
		}

		/**
		 * @return the objPersona
		 */
		public PersonaService getObjPersona() {
			return objPersona;
		}

		/**
		 * @param objPersona the objPersona to set
		 */
		public void setObjPersona(PersonaService objPersona) {
			this.objPersona = objPersona;
		}

}
