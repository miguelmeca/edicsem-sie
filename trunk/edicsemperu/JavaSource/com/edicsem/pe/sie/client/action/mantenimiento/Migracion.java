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

import com.edicsem.pe.sie.beans.SistemaIntegradoDTO;
import com.edicsem.pe.sie.entity.ContratoSie;
import com.edicsem.pe.sie.service.facade.ContratoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "migracion")
@SessionScoped
public class Migracion extends BaseMantenimientoAbstractAction implements Serializable {
	
	public static Log log = LogFactory.getLog(Migracion.class);
	private String nombreArchivo;
	private List<SistemaIntegradoDTO> sistMig;
	private List<String> listaContratosManual;
	private String mensaje ;
	
	@EJB
	private ContratoService objContratoService;
		  
	public Migracion() {
	
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		nombreArchivo="";
		sistMig=new ArrayList<SistemaIntegradoDTO>();
		listaContratosManual = new ArrayList<String>();
		mensaje ="";
		return getViewMant();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MIGRAR_SISTEMA_INTEGRADO;
	}
	
	public void InputStreamAFile(InputStream entrada, String nombreArchivo) {
		try {

			ServletContext ctx = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();

			String path = ctx.getRealPath("/SistemaIntegrado");

			File directory = new File(path);
			Boolean existe = directory.exists();
			if (!existe) {
				directory.mkdir();
			}

			log.info("path  "+path);

			File f = new File(path + "/" + nombreArchivo);

			log.info("Ruta del archivo "+f.getAbsolutePath());

			OutputStream salida = new FileOutputStream(f);
			byte[] buf = new byte[1024];
			int len;
			while ((len = entrada.read(buf)) > 0) {
				salida.write(buf, 0, len);
			}
			salida.close();
			entrada.close();
			log.info("Se realizo la conversion con exito");
		} catch (IOException e) {
			log.info("Se produjo el error : " + e.toString());
		}
	}
	

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() throws Exception {
		
		log.info("subirBD()");
		
		if(sistMig.size()==0){
			mensaje=  "Debe subir el excel a importar";
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN,Constants.MESSAGE_INFO_TITULO,mensaje);
		}else{
			mensaje = objContratoService.insertMigracion(sistMig);
			if(mensaje ==null){
				mensaje=  "Se realizó la migración exitosamente";
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO,mensaje);
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,Constants.MESSAGE_INFO_TITULO,mensaje);
			}
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		return getViewMant();
	}

	public String handleFileUpload(FileUploadEvent event) throws ParseException {
	    log.info("handleFileUpload");
	    mensaje=null;
		UploadedFile file = event.getFile();
			
			try {
				InputStreamAFile(file.getInputstream(), file.getFileName());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			SistemaIntegradoDTO sis = new SistemaIntegradoDTO();
			nombreArchivo = file.getFileName();
			
			sistMig = new ArrayList<SistemaIntegradoDTO>();

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

						for (int i = 0; i < 71; i++) {

							@SuppressWarnings("static-access")
							HSSFCell cell = row.getCell(i,
									row.RETURN_NULL_AND_BLANK);

							data.add(cell);
						}

						int tamano = data.size();

						if (tamano > 1) {
							log.info(" >1 ");
							sis = new SistemaIntegradoDTO();
							if (data.get(0)!=null) {
								sis.setCodContrato(getCellValueAsString(data.get(5)));
								//buscar codigo de contrato 
								ContratoSie contrato = objContratoService.buscarXcodigoContrato(sis.getCodContrato());
								if(contrato!=null){
									sistMig= new ArrayList<SistemaIntegradoDTO>();
									mensaje ="El contrato con el código  '"+contrato.getCodcontrato()+"' ya existe";
									msg = new FacesMessage(FacesMessage.SEVERITY_WARN, Constants.MESSAGE_INFO_TITULO,mensaje);
									break;
								}
								
								sis.setEmpresa(getCellValueAsString(data.get(0)));
								if(!data.get(5).toString().isEmpty()){
									sis.setFechaEntrega(DateUtil.convertStringToDate(getCellValueAsString(data.get(1))));
								}
								
								String nombreCompleto = getCellValueAsString(data.get(6));
								
								String [ ] palabra = nombreCompleto.split("\\ ");
								String numDoc = getCellValueAsString(data.get(7));
								if(numDoc.length()==8){
									sis.setNumdocumento(numDoc);
								}
								else if(numDoc.length()<8){
									int cantCeros= 8 - numDoc.length();
									String ceros="";
									for (int i = 0; i < cantCeros; i++) {
										ceros+="0";
									}
									sis.setNumdocumento(ceros+numDoc);
								}
								if(data.get(8).toString().isEmpty()||data.get(8).toString().equals("")||data.get(8).toString().trim().equals("")){
								
								}else{
									sis.setFecnacimiento(DateUtil.convertStringToDate(getCellValueAsString(data.get(8))));
								}
								sis.setCorreo(getCellValueAsString(data.get(9)));
								log.info("Telefonos:  "+data.get(10));
								sis.setNumTelefono(getCellValueAsString(data.get(10)));
								sis.setDireccion(getCellValueAsString(data.get(11)));
								
								sis.setDistrito(getCellValueAsString(data.get(12)));
								if(sis.getDistrito()==null||sis.getDistrito().isEmpty()){
									sis.setDistrito("LIMA");
								}
								sis.setPlanoDistrito(getCellValueAsString(data.get(13)));
								sis.setLetraSector(getCellValueAsString(data.get(14)));
								sis.setNumSector(getCellValueAsString(data.get(15)));
								sis.setLugarTrabajo(getCellValueAsString(data.get(16)));
								sis.setCargotrabajo(getCellValueAsString(data.get(17)));								
								sis.setTelftrabajo(getCellValueAsString(data.get(18)));
								sis.setAnexo(getCellValueAsString(data.get(19)));
								sis.setDirectrabajo(getCellValueAsString(data.get(20)));
								sis.setDistritoTrabajo(getCellValueAsString(data.get(21)));
								sis.setPlanoTrabajo(getCellValueAsString(data.get(22)));
								sis.setLetraSectorTrabajo(getCellValueAsString(data.get(23)));
								sis.setNumSectorTrabajo(getCellValueAsString(data.get(24)));
								if(!(data.get(25).toString().trim().equals(""))){
									sis.setCantMercaderia(Integer.parseInt(getCellValueAsString(data.get(25)).toString().trim()));
								}
								sis.setTipoMercaderia(getCellValueAsString(data.get(26)));
								sis.setCantCuotas(Integer.parseInt(getCellValueAsString(data.get(27))));
								sis.setNumLetra(getCellValueAsString(data.get(28)));
								sis.setImporteInicial(Double.parseDouble(getCellValueAsString(data.get(29))));
								sis.setImporteCobrado(Double.parseDouble(getCellValueAsString(data.get(30))));
								sis.setImportemasmora(Double.parseDouble(getCellValueAsString(data.get(31))));
								sis.setFechaVencimiento(data.get(32).getDateCellValue());
								sis.setLugarPago(getCellValueAsString(data.get(38)));
								sis.setBanco(getCellValueAsString(data.get(39)));
								if(!(data.get(45).toString().isEmpty()||data.get(45).toString().equals("")||data.get(45).toString().trim().equals(""))){
								sis.setFehaPago(DateUtil.convertStringToDate(getCellValueAsString(data.get(45))));
								}
								sis.setEncargadoCobranza(getCellValueAsString(data.get(52)));
								sis.setVendedorResponsable(getCellValueAsString(data.get(53)));
								if(data.get(55)!=null){
									if(!(data.get(55).toString().isEmpty()||data.get(55).toString().equals("")||data.get(55).toString().trim().equals(""))){
										sis.setFechaNotifica(DateUtil.convertStringToDate(getCellValueAsString(data.get(55))));
									}
								}
								String diasRetrazo =getCellValueAsString(data.get(59));
								sis.setDiasRetraso(Integer.parseInt(diasRetrazo));
								//dependiendo de los dias de retrazo se analiza el tipo de cliente.
								
								sis.setInfocorp(getCellValueAsString(data.get(63)));
								if(data.get(64)!=null){
									if(!(data.get(64).toString().isEmpty()||data.get(64).toString().equals("")||data.get(64).toString().trim().equals(""))){
										sis.setRegistroReniec(DateUtil.convertStringToDate(getCellValueAsString(data.get(64))));
									}
								}
								sis.setCalificaInforcorp(getCellValueAsString(data.get(65)));
								sis.setHistoria(getCellValueAsString(data.get(66)));
								if(data.get(68)!=null){
									if(!(data.get(68).toString().isEmpty()||data.get(68).toString().equals("")||data.get(68).toString().trim().equals(""))){
										sis.setFechaCalificado(DateUtil.convertStringToDate(getCellValueAsString(data.get(68))));
									}
								}
								sis.setCalificacionCliente(getCellValueAsString(data.get(69)));
								
								if(palabra.length==3){
									sis.setNombrecliente(palabra[0]);
									sis.setApepatcliente(palabra[1]);
									sis.setApematcliente(palabra[2]);
									
									sistMig.add(sis);
									sheetData.add(data);
								}else{
									log.info(" nombres > 4  ");
									listaContratosManual.add(sis.getCodContrato());
								}
								
							}else {
								log.info(data.get(0));
							}
						}
					}
				}
				log.info(" tamano total "+sistMig.size());
				if(mensaje!=null){
					mensaje=  "Cargó exitosamente el archivo "+nombreArchivo;
					if( listaContratosManual.size()>0){
							mensaje+=  	", no se cargó  "+ listaContratosManual.size()+" registros, por favor verfique Archivo Log";
					}
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO,mensaje);
				}
				
				FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			catch (Exception e) {
				mensaje = " Contrato: "+sis.getCodContrato()+",    "+e.getMessage()+", causa: "+e.getCause();
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error Formato EXCEL", mensaje);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				sistMig = new ArrayList<SistemaIntegradoDTO>();
				e.printStackTrace();
	    }finally {
	    	if (fis != null) {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
	    }
		log.info("cantidad registros pase Manual " +listaContratosManual.size() +" Cantidad registros subido a BD"+ sistMig.size());
		return null;
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
		
		/**
		 * @return the sistMig
		 */
		public List<SistemaIntegradoDTO> getSistMig() {
			return sistMig;
		}

		/**
		 * @param sistMig the sistMig to set
		 */
		public void setSistMig(List<SistemaIntegradoDTO> sistMig) {
			this.sistMig = sistMig;
		}
		
		
}
