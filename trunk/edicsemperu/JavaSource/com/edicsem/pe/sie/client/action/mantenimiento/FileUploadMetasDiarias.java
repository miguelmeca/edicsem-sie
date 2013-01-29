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


import com.edicsem.pe.sie.beans.MetasDiariasDTO;
import com.edicsem.pe.sie.entity.Personalead;
import com.edicsem.pe.sie.service.facade.MetasDiariasDTOService;
import com.edicsem.pe.sie.service.facade.PersonaService;
@SuppressWarnings("serial")
@ManagedBean(name = "FileUploadMetasDiarias")
@SessionScoped
public class FileUploadMetasDiarias implements Serializable {

	public static Log log = LogFactory.getLog(FileUploadMetasDiarias.class);
	private String nombreArchivo;
	private List<MetasDiariasDTO> leadsNuevos;
	
	
	@EJB
	private MetasDiariasDTOService objMetasDiariasDTOService;
		  
	public FileUploadMetasDiarias() {

	
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
		MetasDiariasDTO personalead = new MetasDiariasDTO();
	

		for (int i = 0; i < leadsNuevos.size(); i++) {

			MetasDiariasDTO credito = leadsNuevos.get(i);
			
			System.out.println("cantidad: " + leadsNuevos.size());
			System.out.println("id 1: " + leadsNuevos.get(i).getGrupo() );
			System.out.println("nombre 1: " + leadsNuevos.get(i).getMeta());
		
			objMetasDiariasDTOService.insertMetasDiariasDTO(credito);
			

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
			
			leadsNuevos = new ArrayList<MetasDiariasDTO>();

			List<List<HSSFCell>> sheetData = new ArrayList<List<HSSFCell>>();

			FileInputStream fis = null;
/***NOTA***/
/***POIFSFileSystem: Esta clase se utiliza para gestiona el ciclo de vida completo del sistema de archivos.***/			
			try {
				/***HSSFWorkbook: Este es el primer objeto construido  para escribir o leer un archivo de Excel.***/
				HSSFWorkbook workbook = new HSSFWorkbook(file.getInputstream());
				/***HSSFSheet: Esta clase se utiliza para crear hojas de cálculo.***/
				HSSFSheet sheet = workbook.getSheetAt(0);

				@SuppressWarnings("rawtypes")
				Iterator rows = sheet.rowIterator();

				while (rows.hasNext()) {
					/***HSSFRow: Esta clase representa la fila de una hoja de cálculo.***/
					HSSFRow row = (HSSFRow) rows.next();

					if (row.getRowNum() > 0) {
						/***HSSFCell: Esta clase representa la celda en una fila de la hoja de cálculo.***/
						List<HSSFCell> data = new ArrayList<HSSFCell>();

						for (int i = 0; i < 31; i++) {

							@SuppressWarnings("static-access")
							HSSFCell cell = row.getCell(i,
									row.RETURN_NULL_AND_BLANK);

							data.add(cell);
						}

						int tamano = data.size();

						if (tamano > 1) {

							MetasDiariasDTO credito = new MetasDiariasDTO();
							if (data.get(0)!=null) {
								
								/***aqui se declaran las columnas   data.get(0) ***/
								
								credito.setGrupo(getCellValueAsString(data.get(0)));
								
//								String grupo = getCellValueAsString(data.get(0));
//								credito.setGrupo(grupo);
								
								String meta = getCellValueAsString(data.get(1));
								credito.setMeta(Integer.parseInt(meta));
								
								String avance = getCellValueAsString(data.get(2));
								credito.setAvance(Integer.parseInt(avance));
								
								String deberianestar  = getCellValueAsString(data.get(3));
								credito.setDeberianestar(Integer.parseInt(deberianestar));
								
								String falta = getCellValueAsString(data.get(4)); 
								credito.setFalta(Double.parseDouble(falta));
								
								String primerasemana  = getCellValueAsString(data.get(5)); 
								credito.setPrimerasemana(Integer.parseInt(primerasemana));
								
								String segundasemana  = getCellValueAsString(data.get(6)); 
								credito.setSegundasemana(Integer.parseInt(segundasemana));
								
								String tercerasemana  = getCellValueAsString(data.get(7)); 
								credito.setTercerasemana(Integer.parseInt(tercerasemana));
								
								String cuartasemana  = getCellValueAsString(data.get(8)); 
								credito.setCuartasemana(Integer.parseInt(cuartasemana));
								
								String quintasemana  = getCellValueAsString(data.get(9)); 
								credito.setQuintasemana(Integer.parseInt(quintasemana));
								
								String metamasivo  = getCellValueAsString(data.get(10)); 
								credito.setMetamasivo(Integer.parseInt(metamasivo));
								
								String metapuntodeventa  = getCellValueAsString(data.get(11)); 
								credito.setMetapuntodeventa(Integer.parseInt(metapuntodeventa));
								
								String avancemasivo  = getCellValueAsString(data.get(12)); 
								credito.setAvancemasivo(Integer.parseInt(avancemasivo));
								
								String avancepuntodeventa  = getCellValueAsString(data.get(13)); 
								credito.setAvancepuntodeventa(Integer.parseInt(avancepuntodeventa));
								
								String faltamasivo = getCellValueAsString(data.get(14)); 
								credito.setFaltamasivo(Double.parseDouble(faltamasivo));
								
								String faltapuntodeventa = getCellValueAsString(data.get(15)); 
								credito.setFaltapuntodeventa(Double.parseDouble(faltapuntodeventa));
								
								String faltatotal = getCellValueAsString(data.get(16)); 
								credito.setFaltatotal(Double.parseDouble(faltatotal));
								
								
								
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
System.out.println("id: " + leadsNuevos.get(0).getGrupo());


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

		public List<MetasDiariasDTO> getLeadsNuevos() {
			return leadsNuevos;
		}

		public void setLeadsNuevos(List<MetasDiariasDTO> leadsNuevos) {
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
			FileUploadMetasDiarias.log = log;
		}

		/**
		 * @return the objMetasDiariasDTOService
		 */
		public MetasDiariasDTOService getObjMetasDiariasDTOService() {
			return objMetasDiariasDTOService;
		}

		/**
		 * @param objMetasDiariasDTOService the objMetasDiariasDTOService to set
		 */
		public void setObjMetasDiariasDTOService(
				MetasDiariasDTOService objMetasDiariasDTOService) {
			this.objMetasDiariasDTOService = objMetasDiariasDTOService;
		}

	
}
