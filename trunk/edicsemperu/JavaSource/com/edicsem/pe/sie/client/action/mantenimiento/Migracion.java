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

import com.edicsem.pe.sie.entity.ClienteSie;
import com.edicsem.pe.sie.service.facade.ClienteService;
import com.edicsem.pe.sie.util.constants.DateUtil;

@ManagedBean(name = "migracion")
@SessionScoped
public class Migracion implements Serializable {

	public static Log log = LogFactory.getLog(Migracion.class);
	private String nombreArchivo;
	private List<ClienteSie> clienteMig;
	
	@EJB
	private ClienteService objClienteService;
		  
	public Migracion() {
	
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

			log.info("path  "+path);

			File f = new File(path + "/" + nombreArchivo);// Aqui le dan el
															// nombre y/o con la

			log.info("Ruta del archivo "+f.getAbsolutePath());

			OutputStream salida = new FileOutputStream(f);
			byte[] buf = new byte[1024];
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

		log.info("subirCreditos()");
	

		for (int i = 0; i < clienteMig.size(); i++) {

			ClienteSie cli = clienteMig.get(i);
			
			System.out.println("cantidad: " + clienteMig.size());
			System.out.println("id 1: " + clienteMig.get(i).getApepatcliente());
			System.out.println("nombre 1: " + clienteMig.get(i).getApematcliente());
		
			objClienteService.insertCliente(cli);
		}
		return null;
	}

	   public String handleFileUpload(FileUploadEvent event) throws ParseException {
	    	log.info("entro file  ---> :D");
			FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
			UploadedFile file = event.getFile();

			try {
				InputStreamAFile(file.getInputstream(), file.getFileName());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			nombreArchivo = file.getFileName();
			
			/********aqui me quede*********/
			
			clienteMig = new ArrayList<ClienteSie>();

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
							log.info(" >1 ");
							ClienteSie cli = new ClienteSie();
							if (data.get(0)!=null) {
								String id = getCellValueAsString(data.get(0));
								//cli.setIdcliente(Integer.parseInt(id)); --> Id de la BD sequencial
								
								String nombreCompleto = getCellValueAsString(data.get(3));
								log.info("nombre completo "+nombreCompleto);
								
								String [ ] palabra = nombreCompleto.split("\\ ");
								String numDoc = getCellValueAsString(data.get(4));
								if(numDoc.length()==8){
									cli.setNumdocumento(numDoc);
								}else{
									cli.setNumdocumento("0"+numDoc);
								}
								cli.setFecnacimiento(DateUtil.convertStringToDate(getCellValueAsString(data.get(5))));
								int j=0;
								
								if(palabra.length==3){
									log.info(" agrega");
									cli.setNombrecliente(palabra[0]);
									cli.setApepatcliente(palabra[1]);
									cli.setApematcliente(palabra[2]);
									
									clienteMig.add(cli);
									sheetData.add(data);
								}else{
									log.info(" nombres > 4  "+nombreCompleto);
								}
								
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

				clienteMig = null;

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

log.info("cantidad: " + clienteMig.size());
log.info("nombre: " + clienteMig.get(0).getNombrecliente());
log.info("ape pat: " + clienteMig.get(0).getApepatcliente());
log.info("ape mat: " + clienteMig.get(1).getApematcliente());

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
			Migracion.log = log;
		}

		/**
		 * @return the clienteMig
		 */
		public List<ClienteSie> getClienteMig() {
			return clienteMig;
		}

		/**
		 * @param clienteMig the clienteMig to set
		 */
		public void setClienteMig(List<ClienteSie> clienteMig) {
			this.clienteMig = clienteMig;
		}
}
