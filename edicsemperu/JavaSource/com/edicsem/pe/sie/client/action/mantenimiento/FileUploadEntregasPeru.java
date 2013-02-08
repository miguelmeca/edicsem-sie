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

import com.edicsem.pe.sie.beans.EntregasPeruDTO;
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

		log.info("Subir excel a la Base de Datos");
		
	
		
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

						for (int i = 0; i < 90; i++) {

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
				
				log.info(" FECHA_String -> "+data.get(1).toString());
				if(data.get(1)!=null){
					if(!(data.get(1).toString().isEmpty()||data.get(1).toString().equals("")||data.get(1).toString().trim().equals(""))){
						credito.setFecha(DateUtil.convertStringToDate(getCellValueAsString(data.get(1))));
					}
				}
				
				
				
				
				log.info("BOLETO-->"+data.get(5).toString());		
				credito.setBoleta(getCellValueAsString(data.get(5)));
			
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

				log.info(" fecha de cumpleanios--> "+data.get(9).toString());
				if(data.get(9).toString().isEmpty()||data.get(9).toString().equals("")||data.get(9).toString().trim().equals("")){
				}else{
				credito.setFechadecuempleanios(DateUtil.convertStringToDate(getCellValueAsString(data.get(9))));
				}
				
				log.info("CORREO-->"+data.get(10));
				credito.setCorreo(getCellValueAsString(data.get(10)));
				
				log.info("NdTELEFONO-->"+data.get(11));
				credito.setNumerotelefono(getCellValueAsString(data.get(11)));
						
				log.info("DOMICILIO_CLIENTE-->"+data.get(12).toString());
				credito.setDomiciliodelcliente(getCellValueAsString(data.get(12)));
				
				log.info("DISTRITO-->"+data.get(13));
				credito.setDomiciliodistrito(getCellValueAsString(data.get(13)));
				
				
				
				log.info("PLANO_DOMICILIO-->"+data.get(14));
				credito.setPlanodomicilio(getCellValueAsString(data.get(14)));
			
				log.info("LETRA DOMICILIO-->"+data.get(15));
				credito.setLetrasectordomicilio(getCellValueAsString(data.get(15)));
				
				log.info("NUM_SECTOR_DOMICILIO-->"+data.get(16));
				credito.setNumerosectordomicilio(getCellValueAsString(data.get(16)));
				
						
				log.info("LUGAR DE TRABAJO-->"+data.get(17));
				credito.setLugardetrabajo(getCellValueAsString(data.get(17)));
						
				log.info("CARGO_LABORAL-->"+data.get(18));
				credito.setCargolaboral(getCellValueAsString(data.get(18)));
				
				log.info("TEL_TRABAJO-->"+data.get(19));		
				credito.setTelefonodeltrabajo(getCellValueAsString(data.get(19)));
				
				log.info("ANEXO-->"+data.get(20).toString());
				if(!(data.get(20).toString().trim().equals(""))){

					credito.setAnexo(Integer.parseInt(getCellValueAsString(data.get(20)).toString().trim()));
					}
						
						
			
				log.info("DIRECION_TRABAJO-->"+data.get(21));
				credito.setDirecciondetrabajo(getCellValueAsString(data.get(21)));
				
				
				log.info("TRABAJO_DISTRITO-->"+data.get(22));
				credito.setTrabajodistrito(getCellValueAsString(data.get(22)));
				
				log.info("PLANO_TRABAJO-->"+data.get(23).toString());
				if(!(data.get(23).toString().trim().equals(""))){

					credito.setPlanotrabajo(Integer.parseInt(getCellValueAsString(data.get(23)).toString().trim()));
					}
				
				log.info("LETRA_SECTOR_TRABAJO-->"+data.get(24));
				credito.setLetrasectortrabajo(getCellValueAsString(data.get(24)));
				
				log.info("NUM_SECTOR_TRABAJO-->"+data.get(25).toString());
				if(!(data.get(25).toString().trim().equals(""))){

					credito.setNumerosectortrabajo(Integer.parseInt(getCellValueAsString(data.get(25)).toString().trim()));
					}
				
				
				log.info("LUGAR_ENTREGA-->"+data.get(26));
				credito.setLugardelaentrega(getCellValueAsString(data.get(26)));
				
				
				log.info("DISTRITOENTREGA-->"+data.get(27));
				credito.setDistritodelaentrega(getCellValueAsString(data.get(27)));
				
				log.info("ZONADELANETREGA-->"+data.get(28));
				credito.setZonadelaentrega(getCellValueAsString(data.get(28)));
				
				log.info("ZONADEENTREGA-->"+data.get(29));
				credito.setZonadeentrega(getCellValueAsString(data.get(29)));
				
				log.info("LETRASECTORENTREGA-->"+data.get(30));
				credito.setLetrasectorentrega(getCellValueAsString(data.get(30)));
				
				
				
				
				
				
				log.info("NOMB_VENDEDOR-->"+data.get(32));
				credito.setNombredelvendedor(getCellValueAsString(data.get(32)));
				
				log.info("NOMB_EXPOSITOR-->"+data.get(33));
				credito.setNombredelexpositor(getCellValueAsString(data.get(33)));
				
				log.info("NOMB_SUPERVISOR-->"+data.get(34));
				credito.setNombredelsupervisor(getCellValueAsString(data.get(34)));
				
				
				
				
				
				
				log.info("CANT_MERCADERIA-->"+data.get(35).toString());
				if(!(data.get(35).toString().trim().equals(""))){

					credito.setCantidaddemercaderia(Integer.parseInt(getCellValueAsString(data.get(35)).toString().trim()));
					}
				
				log.info("COD_MERCADERIA-->"+data.get(36));
				credito.setCodigodemercaderia(getCellValueAsString(data.get(36)));
				
				

				log.info("MONTO_ADELANTO-->"+data.get(38).toString());
				if(!(data.get(38).toString().trim().equals(""))){

					credito.setMontodeadelanto(Double.parseDouble(getCellValueAsString(data.get(38)).toString().trim()));
					}

				
				
				
				
				
				
				
				
				log.info("PUNTO_VENTA-->"+data.get(40));
				credito.setPuntodeventa(getCellValueAsString(data.get(40)));
				
				log.info("NOMB_RELACIONISTA-->"+data.get(41));
				credito.setNombredelrelacionista(getCellValueAsString(data.get(41)));
						
				log.info("DISTRITO_PUNTO_VENTA-->"+data.get(42));					
				credito.setDistritodelpunto(getCellValueAsString(data.get(42)));
				
				log.info("EVENTO_VENTA-->"+data.get(43));
				credito.setEventodeventa(getCellValueAsString(data.get(43)));
				
				
				log.info(" FECHA_COMPROMISO-> "+data.get(44).toString());				
				if(data.get(44)!=null){
					if(!(data.get(44).toString().isEmpty()||data.get(44).toString().equals("")||data.get(44).toString().trim().equals(""))){
						credito.setFechadecompromiso(DateUtil.convertStringToDate(getCellValueAsString(data.get(44))));
					}
				}
				
				log.info("ENCARGADO_ENTREGA-->"+data.get(51));
				credito.setEncargadodelanetrega(getCellValueAsString(data.get(51)));
				
				log.info(" FECHA_LLAMADA_VISITA -> "+data.get(52).toString());				
				if(data.get(52)!=null){
					if(!(data.get(52).toString().isEmpty()||data.get(52).toString().equals("")||data.get(52).toString().trim().equals(""))){
						credito.setFechadellamadaovisita(DateUtil.convertStringToDate(getCellValueAsString(data.get(52))));
					}
				}
				
				log.info(" FECHA_POSTERGADA -> "+data.get(53).toString());				
				if(data.get(53)!=null){
					if(!(data.get(53).toString().isEmpty()||data.get(53).toString().equals("")||data.get(53).toString().trim().equals(""))){
						credito.setFechapostergada(DateUtil.convertStringToDate(getCellValueAsString(data.get(53))));
					}
				}
				
				log.info(" FECHA_FINAL -> "+data.get(60).toString());				
				if(data.get(60)!=null){
					if(!(data.get(60).toString().isEmpty()||data.get(60).toString().equals("")||data.get(60).toString().trim().equals(""))){
						credito.setFechafinal(DateUtil.convertStringToDate(getCellValueAsString(data.get(60))));
					}
				}
				
				log.info("ESTADO_FACTURADO-->"+data.get(61));
				credito.setEstadofinal(getCellValueAsString(data.get(61)));
				
				
				
				
				
				
				log.info("OBSERVACIONES-->"+data.get(66));
				credito.setObservaciones(getCellValueAsString(data.get(66)));
				
				
				log.info("ESTADO_REAL-->"+data.get(67));
				credito.setEstadoreal(getCellValueAsString(data.get(67)));
				
				
				
				log.info("NOMB_PATROCINADOR-->"+data.get(68));
				credito.setNombredelpatrocinado(getCellValueAsString(data.get(68)));
				
				
				log.info("COMSION_DEL_VENDEDOR-->"+data.get(69).toString());
				if(!(data.get(69).toString().trim().equals(""))){

					credito.setComisiondelexpositor(Double.parseDouble(getCellValueAsString(data.get(69)).toString().trim()));
					}

				
				log.info(" FECHADEPAGOVENDEDOR -> "+data.get(70).toString());				
				if(data.get(70)!=null){
					if(!(data.get(70).toString().isEmpty()||data.get(70).toString().equals("")||data.get(70).toString().trim().equals(""))){
						credito.setFechadepagoalvendedor(DateUtil.convertStringToDate(getCellValueAsString(data.get(70))));
					}
				}
				
				log.info("SEPAGOVENDEDOR-->"+data.get(71));
				credito.setEstadoreal(getCellValueAsString(data.get(71)));
				
				log.info("COMSIONEXPOSITOR-->"+data.get(72).toString());
				if(!(data.get(72).toString().trim().equals(""))){

		credito.setComisiondelexpositor(Double.parseDouble(getCellValueAsString(data.get(72)).toString().trim()));
					}

				
				log.info(" FECHADEPAGOEXPOSITOR -> "+data.get(73).toString());				
				if(data.get(73)!=null){
					if(!(data.get(73).toString().isEmpty()||data.get(73).toString().equals("")||data.get(73).toString().trim().equals(""))){
						credito.setFechadepagoalexpositor(DateUtil.convertStringToDate(getCellValueAsString(data.get(73))));
					}
				}
				
				
				log.info("SEPAGOEXPOSITOR-->"+data.get(74));
				credito.setEstadoreal(getCellValueAsString(data.get(74)));
				
				log.info("COMSIONRELACIONISTA-->"+data.get(75).toString());
				if(!(data.get(75).toString().trim().equals(""))){

		credito.setComisiondelrelacionista(Double.parseDouble(getCellValueAsString(data.get(75)).toString().trim()));
					}
				

				log.info(" FECHADEPAGORELACIONISTA -> "+data.get(76).toString());				
				if(data.get(76)!=null){
					if(!(data.get(76).toString().isEmpty()||data.get(76).toString().equals("")||data.get(76).toString().trim().equals(""))){
						credito.setFechadepagoalrelacionista(DateUtil.convertStringToDate(getCellValueAsString(data.get(76))));
					}
				}
				
				log.info("SEPAGORELACIONISTA-->"+data.get(77));
				credito.setSepagoalRelacionista(getCellValueAsString(data.get(77)));
				
				log.info("COMSIONSUPERVISOR-->"+data.get(78).toString());
				if(!(data.get(78).toString().trim().equals(""))){

		credito.setComisiondelsupervisor(Double.parseDouble(getCellValueAsString(data.get(78)).toString().trim()));
					}
				
				
				log.info(" FECHADEPAGOSUPERVISOR -> "+data.get(79).toString());				
				if(data.get(79)!=null){
					if(!(data.get(79).toString().isEmpty()||data.get(79).toString().equals("")||data.get(79).toString().trim().equals(""))){
						credito.setFechadepagoalsupervisor(DateUtil.convertStringToDate(getCellValueAsString(data.get(79))));
					}
				}
				
				log.info("SEPAGOSUPERVISOR-->"+data.get(80));
				credito.setEstadoreal(getCellValueAsString(data.get(80)));
				
				log.info("COMSIONPATROCINADOR-->"+data.get(81).toString());
				if(!(data.get(81).toString().trim().equals(""))){

		credito.setComisiondelpatrocinador(Double.parseDouble(getCellValueAsString(data.get(81)).toString().trim()));
					}
				
				log.info(" FECHADEPAGOPATROCINADOR -> "+data.get(82).toString());				
				if(data.get(82)!=null){
					if(!(data.get(82).toString().isEmpty()||data.get(82).toString().equals("")||data.get(82).toString().trim().equals(""))){
						credito.setFechadepagoalpatrocinador(DateUtil.convertStringToDate(getCellValueAsString(data.get(82))));
					}
				}
				
				
				
				log.info("SEPAGOPATROCINADOR-->"+data.get(83));
				credito.setEstadoreal(getCellValueAsString(data.get(83)));
				
				
				log.info("PRECIOTOTAL-->"+data.get(84).toString());
				if(!(data.get(84).toString().trim().equals(""))){

		credito.setPreciototal(Double.parseDouble(getCellValueAsString(data.get(84)).toString().trim()));
					}
				
				
				
				if(data.get(85)!=null){
				if(!(data.get(85).toString().trim().equals(""))){

					credito.setPuntaje(Integer.parseInt(getCellValueAsString(data.get(85)).toString().trim()));
					}
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
