package com.edicsem.pe.sie.client.action.mantenimiento;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Time;
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

import com.edicsem.pe.sie.beans.RecaudacionDTO;
import com.edicsem.pe.sie.service.facade.CobranzaService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "recaudacion")
@SessionScoped
public class MigracionRecaudacion extends BaseMantenimientoAbstractAction implements Serializable {
	
	public static Log log = LogFactory.getLog(MigracionRecaudacion.class);
	private String nombreArchivo;
	private List<RecaudacionDTO> recaudaMig;
	private String mensaje ;
	
	@EJB
	private CobranzaService objCobranzaService;
	
	public MigracionRecaudacion() {
	
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		recaudaMig=new ArrayList<RecaudacionDTO>();
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
		
		if(recaudaMig.size()==0){
			mensaje=  "Debe subir el excel a importar";
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN,Constants.MESSAGE_INFO_TITULO,mensaje);
		}else{
			mensaje = objCobranzaService.MigrarRecaudacion(recaudaMig);
			if(mensaje ==null){
				mensaje=  "Se realizó la migración exitosamente";
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO,mensaje);
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,Constants.MESSAGE_INFO_TITULO,mensaje);
			}
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		return null;
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
			RecaudacionDTO sis = new RecaudacionDTO();
			nombreArchivo = file.getFileName();
			
			recaudaMig = new ArrayList<RecaudacionDTO>();
			
			List<List<HSSFCell>> sheetData = new ArrayList<List<HSSFCell>>();

			FileInputStream fis = null;

			try {
				HSSFWorkbook workbook = new HSSFWorkbook(file.getInputstream());
				HSSFSheet sheet = workbook.getSheetAt(0);

				@SuppressWarnings("rawtypes")
				Iterator rows = sheet.rowIterator();
				
				while (rows.hasNext()) {

					HSSFRow row = (HSSFRow) rows.next();

					if (row.getRowNum() > 6) {

						List<HSSFCell> data = new ArrayList<HSSFCell>();

						for (int i = 0; i < 16; i++) {
							@SuppressWarnings("static-access")
							HSSFCell cell = row.getCell(i,row.RETURN_NULL_AND_BLANK);
							data.add(cell);
						}
						int tamano = data.size();

						if (tamano > 1) {
							sis = new RecaudacionDTO();
							if (data.get(0)!=null) {
								sis.setCodigoDepositante(getCellValueAsString(data.get(0)));
								sis.setInfoRetorno(getCellValueAsString(data.get(1)));
								sis.setFecpago(DateUtil.convertStringToDate(getCellValueAsString(data.get(2))));
								sis.setFechaVencimiento(DateUtil.convertStringToDate(getCellValueAsString(data.get(3))));
								sis.setMontoPagado(Double.parseDouble(getCellValueAsString(data.get(4))));
								sis.setMoraPagada(Double.parseDouble(getCellValueAsString(data.get(5))));
								sis.setCargoFijoPagado(Double.parseDouble(getCellValueAsString(data.get(6))));
								sis.setMontoTotalPagado(Double.parseDouble(getCellValueAsString(data.get(7))));
								sis.setAgencia(getCellValueAsString(data.get(8)));
								sis.setNumOperacion(getCellValueAsString(data.get(9)));
								sis.setReferencia(getCellValueAsString(data.get(10)));
								sis.setTerminal(getCellValueAsString(data.get(11)));
								sis.setMedioAtencion(getCellValueAsString(data.get(12)));
								HSSFCell a= data.get(13);
								Time hora  = new Time(a.getDateCellValue().getTime());
								sis.setHoraAtencion(hora);
								sis.setNrocheque(getCellValueAsString(data.get(14)));
								sis.setBancoCheque(getCellValueAsString(data.get(15)));
								recaudaMig.add(sis);
								sheetData.add(data);
								mensaje=  "Cargó exitosamente el archivo "+nombreArchivo;
								msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO,mensaje);
							}else {
							}
						}else{
							//mandar mensaje
							mensaje="Cargó correctamente, pero no se encontraron datos en el archivo"+nombreArchivo;
							msg = new FacesMessage(FacesMessage.SEVERITY_WARN,Constants.MESSAGE_INFO_TITULO,mensaje);
						}
					}
				}
				log.info(" tamano total "+recaudaMig.size());
				
				FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			catch (Exception e) {
				mensaje = " Contrato: "+sis.getCodigoDepositante()+",    "+e.getMessage();
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error Formato EXCEL", mensaje);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				recaudaMig = new ArrayList<RecaudacionDTO>();
				e.printStackTrace();
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
		 * @return the recaudaMig
		 */
		public List<RecaudacionDTO> getRecaudaMig() {
			return recaudaMig;
		}

		/**
		 * @param recaudaMig the recaudaMig to set
		 */
		public void setRecaudaMig(List<RecaudacionDTO> recaudaMig) {
			this.recaudaMig = recaudaMig;
		}

		/**
		 * @return the mensaje
		 */
		public String getMensaje() {
			return mensaje;
		}

		/**
		 * @param mensaje the mensaje to set
		 */
		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}
		
		
}
