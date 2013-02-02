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
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
@SuppressWarnings("serial")
@ManagedBean(name = "FileUploadEntregasPeru")
@SessionScoped
public class FileUploadEntregasPeru implements Serializable {

	public static Log log = LogFactory.getLog(FileUploadEntregasPeru.class);
	private String nombreArchivo;
	private List<EntregasPeruDTO> leadsNuevos;
	
	
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

						EntregasPeruDTO credito = new EntregasPeruDTO();
							
							if (data.get(0)!=null) {
								
						
						String empresa = getCellValueAsString(data.get(0));
						credito.setEmpresa(empresa);
								
//						credito.setEmpresa(getCellValueAsString(data.get(0)));
								
								if(!data.get(1).toString().isEmpty()){
						credito.setFecha(DateUtil.convertStringToDate(getCellValueAsString(data.get(1))));
								}
								
								
						credito.setBoleta(Integer.parseInt(getCellValueAsString(data.get(5))));	
						
						credito.setNumerodecontrato(getCellValueAsString(data.get(6)));
						
						
						
//						
//						credito.setNombredecliente((getCellValueAsString(data.get(8))));	
//						
//						
//						
//						String numDoc = getCellValueAsString(data.get(9));
//						if(numDoc.length()==8){
//				
//							credito.setDnidelcliente(numDoc);
//						}
//						else if(numDoc.length()==6){
//							
//							credito.setDnidelcliente("00"+numDoc);
//						}
//						else if(numDoc.length()==7){
//							
//							credito.setDnidelcliente("0"+numDoc);
//						}
//						
//						log.info(" fecha de cumpleanios--> "+data.get(10).toString());
//						if(data.get(10).toString().isEmpty()||data.get(10).toString().equals("")||data.get(10).toString().trim().equals("")){
//						
//						}else{
//							
//							credito.setFechadecuempleanios(DateUtil.convertStringToDate(getCellValueAsString(data.get(10))));
//						}
//						
//						credito.setCorreo(getCellValueAsString(data.get(11)));						
//						credito.setNumerotelefono(getCellValueAsString(data.get(12)));
//						credito.setDomiciliodelcliente(getCellValueAsString(data.get(13)));
//						credito.setDomiciliodistrito(getCellValueAsString(data.get(14)));
//						credito.setPlanodomicilio(Integer.getInteger(getCellValueAsString(data.get(15))));
//						
//						credito.setLetrasectordomicilio(getCellValueAsString(data.get(16)));						
//						credito.setNumerosectordomicilio(Integer.getInteger(getCellValueAsString(data.get(17))));
//						credito.setLugardetrabajo(getCellValueAsString(data.get(18)));
//						credito.setCargolaboral(getCellValueAsString(data.get(19)));
//						credito.setTelefonodeltrabajo(getCellValueAsString(data.get(20)));
//						
//						credito.setAnexo(Integer.getInteger(getCellValueAsString(data.get(21))));
//						credito.setDirecciondetrabajo(getCellValueAsString(data.get(22)));					
//						credito.setTrabajodistrito(getCellValueAsString(data.get(23)));
//						credito.setPlanotrabajo(Integer.getInteger(getCellValueAsString(data.get(24))));
//						credito.setLetrasectortrabajo(getCellValueAsString(data.get(24)));
//						credito.setNumerosectortrabajo(Integer.getInteger(getCellValueAsString(data.get(25))));
//						
//						credito.setLugardelaentrega(getCellValueAsString(data.get(26)));
//						credito.setNombredelvendedor(getCellValueAsString(data.get(32)));
//						credito.setNombredelexpositor(getCellValueAsString(data.get(33)));
//						credito.setNombredelsupervisor(getCellValueAsString(data.get(34)));
//						credito.setCantidaddemercaderia(Integer.getInteger(getCellValueAsString(data.get(35))));
//						
//						credito.setCodigodemercaderia(getCellValueAsString(data.get(36)));
//						credito.setMontodeadelanto(Integer.getInteger(getCellValueAsString(data.get(38))));
//						credito.setPuntodeventa(getCellValueAsString(data.get(40)));
//						credito.setNombredelrelacionista(getCellValueAsString(data.get(41)));
//						
//						
//						credito.setDistritodelpunto(getCellValueAsString(data.get(42)));
//						credito.setEventodeventa(getCellValueAsString(data.get(43)));
//						credito.setFechadecompromiso(DateUtil.convertStringToDate(getCellValueAsString(data.get(44))));
//						credito.setDosde(Integer.getInteger(getCellValueAsString(data.get(45))));
//						credito.setDoshasta(Integer.getInteger(getCellValueAsString(data.get(46))));
//						
//						
//						credito.setEncargadodelanetrega(getCellValueAsString(data.get(51)));
//						credito.setFechadellamadaovisita(DateUtil.convertStringToDate(getCellValueAsString(data.get(52))));
//						credito.setFechapostergada(DateUtil.convertStringToDate(getCellValueAsString(data.get(53))));
//						credito.setTresde(Integer.getInteger(getCellValueAsString(data.get(54))));
//						credito.setTreshasta(Integer.getInteger(getCellValueAsString(data.get(55))));
//						
//						
//						credito.setFechafinal(DateUtil.convertStringToDate(getCellValueAsString(data.get(60))));
//						credito.setEstadofinal(getCellValueAsString(data.get(61)));
//						credito.setObservaciones(getCellValueAsString(data.get(66)));
//						credito.setNombredelpatrocinado(getCellValueAsString(data.get(68)));
//						credito.setComisiondelvendedor(Integer.getInteger(getCellValueAsString(data.get(69))));
//						credito.setFechadepagoalvendedor(DateUtil.convertStringToDate(getCellValueAsString(data.get(70))));
//						
//						credito.setComisiondelexpositor(Integer.getInteger(getCellValueAsString(data.get(72))));
//						credito.setFechadepagoalexpositor(DateUtil.convertStringToDate(getCellValueAsString(data.get(73))));
//						credito.setComisiondelrelacionista(Integer.getInteger(getCellValueAsString(data.get(75))));
//						credito.setFechadepagoalrelacionista(DateUtil.convertStringToDate(getCellValueAsString(data.get(76))));
//						
//						credito.setComisiondelsupervisor(Integer.getInteger(getCellValueAsString(data.get(78))));
//						credito.setFechadepagoalsupervisor(DateUtil.convertStringToDate(getCellValueAsString(data.get(79))));
//						
//						
//						credito.setComisiondelpatrocinador(Integer.getInteger(getCellValueAsString(data.get(81))));
//						credito.setFechadepagoalpatrocinador(DateUtil.convertStringToDate(getCellValueAsString(data.get(82))));
//						
//						
//						credito.setPreciototal(Integer.getInteger(getCellValueAsString(data.get(84))));
//						credito.setPuntaje(Integer.getInteger(getCellValueAsString(data.get(85))));
//						
//				
						
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
			log.info("cantidad: " + leadsNuevos.size());
			
System.out.println("cantidad: " + leadsNuevos.size());
System.out.println("id: " + leadsNuevos.get(0).getEmpresa());


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
