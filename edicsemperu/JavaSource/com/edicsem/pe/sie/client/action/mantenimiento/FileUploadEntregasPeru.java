package com.edicsem.pe.sie.client.action.mantenimiento;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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

import com.ctc.wstx.util.DataUtil;
import com.edicsem.pe.sie.beans.EntregasPeruDTO;
import com.edicsem.pe.sie.beans.MetasDiariasDTO;
import com.edicsem.pe.sie.beans.SistemaIntegradoDTO;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
@SuppressWarnings("serial")
@ManagedBean(name = "FileUploadEntregasPeru")
@SessionScoped
public class FileUploadEntregasPeru implements Serializable {

	public static Log log = LogFactory.getLog(FileUploadEntregasPeru.class);
	private String nombreArchivo;
	private List<EntregasPeruDTO> leadsNuevos;
	private String mensaje ;
	
//	@EJB
//	private MetasDiariasDTOService objMetasDiariasDTOService;
		  
	public FileUploadEntregasPeru() {

	
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
		EntregasPeruDTO personalead = new EntregasPeruDTO();
//	
//
//		for (int i = 0; i < leadsNuevos.size(); i++) {
//
//			EntregasPeruDTO credito = leadsNuevos.get(i);
//			
//			System.out.println("cantidad: " + leadsNuevos.size());
//			System.out.println("id 1: " + leadsNuevos.get(i).getEmpresa() );
//			System.out.println("nombre 1: " + leadsNuevos.get(i).getNombredecliente());
		
//		EntregasPeruDTO.insertMetasDiariasDTO(credito);
			

//		}
		
		return null;
	}

	
//	personaDao.persist(credito);
	
	
//	objPersona.persist(credito);

	    public String handleFileUpload(FileUploadEvent event) throws ParseException {
	    	System.out.println("entro al metodo fileUpload");
//			FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
//			FacesContext.getCurrentInstance().addMessage(null, msg);
			
			UploadedFile file = event.getFile();

			try {
				InputStreamAFile(file.getInputstream(), file.getFileName());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			EntregasPeruDTO credito = new EntregasPeruDTO();
			nombreArchivo = file.getFileName();
			
			/********aqui me quede*********/
			
			leadsNuevos = new ArrayList<EntregasPeruDTO>();

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

						for (int i = 0; i < 15; i++) {

							@SuppressWarnings("static-access")
							HSSFCell cell = row.getCell(i,
									row.RETURN_NULL_AND_BLANK);

							data.add(cell);
						}

						int tamano = data.size();

						if (tamano > 1) {
							log.info(" >1 ");
					credito = new EntregasPeruDTO();
							
							if (data.get(0)!=null) {
								
								log.info("EMPRESA-->"+data.get(0));
				String empresa = getCellValueAsString(data.get(0));
				credito.setEmpresa(empresa);

			
				log.info("NdContrato-->"+data.get(6));	
				credito.setNumerodecontrato(getCellValueAsString(data.get(6)));
						
				log.info("NOMBRE COMPLETO-->"+data.get(7));	
				String nombreCompleto = getCellValueAsString(data.get(7));
				String [ ] palabra = nombreCompleto.split("\\ ");
				
				
				log.info("DNI-->"+getCellValueAsString(data.get(8)));	
				String numDoc = getCellValueAsString(data.get(8));
				if(numDoc.length()==8){
				credito.setDnidelcliente(numDoc);
				}
				else if(numDoc.length()==6){
				credito.setDnidelcliente("00"+numDoc);
				}
				else if(numDoc.length()==7){
				credito.setDnidelcliente("0"+numDoc);
				}


				
				if(palabra.length==3){
							log.info(" ***** "+ palabra);
							credito.setNombredecliente(palabra[0]);
							credito.setApepatcliente(palabra[1]);
							credito.setApematcliente(palabra[2]);
							
							leadsNuevos.add(credito);
							sheetData.add(data);
							
						}else{
							
							log.info(" nombres > 4  ");
						}						
						
							}else { 
								log.info(data.get(0));
					}
							
			}
			
		}
					
	}
				
				log.info(" tamano total "+leadsNuevos.size());
				mensaje=  "Cargó exitosamente";
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
						Constants.MESSAGE_INFO_TITULO,mensaje);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			catch (Exception e) {
				mensaje = " EntregasPeru: "+ credito.getNumerodecontrato() +",    "+e.getMessage();
				
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error Formato EXCEL", mensaje);
				FacesContext.getCurrentInstance().addMessage(null, msg);

				

				leadsNuevos = new ArrayList<EntregasPeruDTO>();

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
			log.info("cantidad: " + leadsNuevos.size());
			
return null;


	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    public void mensajeDeVacio(String mensaje) {

FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Deudor", mensaje);
FacesContext.getCurrentInstance().addMessage(null, msg);
			
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

//		public List<MetasDiariasDTO> getLeadsNuevos() {
//			return leadsNuevos;
//		}
//
//		public void setLeadsNuevos(List<MetasDiariasDTO> leadsNuevos) {
//			this.leadsNuevos = leadsNuevos;
//		}

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
			FileUploadEntregasPeru.log = log;
		}

		/**
		 * @return the leadsNuevos
		 */
		public List<EntregasPeruDTO> getLeadsNuevos() {
			return leadsNuevos;
		}

		/**
		 * @param leadsNuevos the leadsNuevos to set
		 */
		public void setLeadsNuevos(List<EntregasPeruDTO> leadsNuevos) {
			this.leadsNuevos = leadsNuevos;
		}


	
}
