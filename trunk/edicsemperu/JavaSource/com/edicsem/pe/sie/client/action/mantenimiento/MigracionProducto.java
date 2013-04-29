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
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.edicsem.pe.sie.beans.ProductoDTO;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.service.facade.ProductoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "migracionpro")
@SessionScoped
public class MigracionProducto extends BaseMantenimientoAbstractAction implements Serializable {
	
	public static Log log = LogFactory.getLog(MigracionProducto.class);
	private String nombreArchivo;
	private List<ProductoDTO> sisPro;
	private String mensaje ;
	
	@EJB
	private ProductoService objProductoService;
		  
	public MigracionProducto() {
	
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		nombreArchivo="";
		sisPro = new ArrayList<ProductoDTO>();
		mensaje ="";
		return getViewMant();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MIGRAR_PRODUCTO_PAQUETE;
	}
	
	public void InputStreamAFile(InputStream entrada, String nombreArchivo) {
		try {

			ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			String path = ctx.getRealPath("/MigracionProducto");

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
		mensaje =null;
		log.info("insertar()");
		RequestContext context = RequestContext.getCurrentInstance();
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		EmpleadoSie sessionUsuario = (EmpleadoSie)session.getAttribute(Constants.USER_KEY);
		
		if(sisPro.size()==0){
			mensaje = "Debe subir el excel a importar";
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN,Constants.MESSAGE_INFO_TITULO,mensaje);
		}else{
			 objProductoService.migrarProducto(sisPro, sessionUsuario.getUsuario());
			if(mensaje ==null){
				mensaje=  "Se realizó la migración exitosamente";
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO,mensaje);
			}else{
				context.execute("statusDialogSI.hide()");
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
			log.info("Mesaje: "+e1.getMessage()+" cause: "+e1.getCause());
		}
			ProductoDTO prod = new ProductoDTO();
			nombreArchivo = file.getFileName();
			
			sisPro = new ArrayList<ProductoDTO>();

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

						for (int i = 0; i < 2; i++) {

							@SuppressWarnings("static-access")
							HSSFCell cell = row.getCell(i,
									row.RETURN_NULL_AND_BLANK);

							data.add(cell);
						}

						int tamano = data.size();

						if (tamano > 1) {
							prod = new ProductoDTO();
							
							String codigo = getCellValueAsString(data.get(0));
							if(codigo==null){
								log.info("ya no hay productos ");
								break;
							}
							log.info("codigoo  "+codigo);
							codigo = codigo.replace(" ", "");
							prod.setCodproducto(codigo);
							prod.setDescripcionproducto(getCellValueAsString(data.get(1)).trim().toUpperCase());
							
							sisPro.add(prod);
							sheetData.add(data);
							}else{
								log.info(data.get(0));
							}
					}
				}
				log.info(" tamano total "+sisPro.size());
				if(mensaje==null){
					mensaje=  "Cargó exitosamente el archivo "+nombreArchivo;
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.MESSAGE_INFO_TITULO,mensaje);
				}
			}
			catch (Exception e) {
				//log.info("Mensaje "+e.getMessage()+"cause "+e.getCause()+" contrato "+prod.getCodContrato());
				//mensaje = " Contrato: "+prod.getCodContrato()+",    "+e.getMessage()+", causa: "+e.getCause();
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error Formato EXCEL", mensaje);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				sisPro = new ArrayList<ProductoDTO>();
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
		FacesContext.getCurrentInstance().addMessage(null, msg);
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

	public List<ProductoDTO> getSisPro() {
		return sisPro;
	}

	public void setSisPro(List<ProductoDTO> sisPro) {
		this.sisPro = sisPro;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
