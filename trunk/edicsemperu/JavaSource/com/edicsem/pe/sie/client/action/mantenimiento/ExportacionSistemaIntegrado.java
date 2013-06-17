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

import com.edicsem.pe.sie.beans.SistemaIntegradoDTO;
import com.edicsem.pe.sie.entity.ContratoSie;
import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.service.facade.ContratoService;
import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;
import com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction;

@ManagedBean(name = "migracion")
@SessionScoped
public class ExportacionSistemaIntegrado extends BaseMantenimientoAbstractAction implements Serializable {
	
	public static Log log = LogFactory.getLog(ExportacionSistemaIntegrado.class);
	private List<SistemaIntegradoDTO> sistMig;
	private String mensaje ;
	
	@EJB
	private ContratoService objContratoService;
		  
	public ExportacionSistemaIntegrado() {
	
	}

	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#agregar()
	 */
	public String agregar() {
		sistMig = new ArrayList<SistemaIntegradoDTO>();
		mensaje ="";
		return getViewMant();
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#getViewMant()
	 */
	public String getViewMant() {
		return Constants.MIGRAR_SISTEMA_INTEGRADO;
	}
	
	/* (non-Javadoc)
	 * @see com.edicsem.pe.sie.util.mantenimiento.util.BaseMantenimientoAbstractAction#insertar()
	 */
	public String insertar() throws Exception {
		log.info("insertar()");
		RequestContext context = RequestContext.getCurrentInstance();
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		EmpleadoSie sessionUsuario = (EmpleadoSie)session.getAttribute(Constants.USER_KEY);
		
		if(sistMig.size()==0){
			mensaje = "Debe subir el excel a importar";
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN,Constants.MESSAGE_INFO_TITULO,mensaje);
		}else{
			//mensaje = objContratoService.insertMigracion(sistMig, sisMigUpdate, sessionUsuario.getUsuario());
			if(mensaje ==null){
				mensaje=  "Se realizó la migración exitosamente";
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO,Constants.MESSAGE_INFO_TITULO,mensaje);
			}else{
				
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,Constants.MESSAGE_INFO_TITULO,mensaje);
			}
		}
		context.execute("statusDialogSI.hide()");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		return getViewMant();
	}

	public String handleFileUpload(FileUploadEvent event) throws ParseException {
	    log.info("handleFileUpload");
	    mensaje=null;
		SistemaIntegradoDTO sis = new SistemaIntegradoDTO();
		List<ContratoSie> lista = objContratoService.listarContratos();
		FileInputStream fis = null;
		try {
				for (int i = 0; i < lista.size(); i++) {
					//Buscar codigo de contrato 
					ContratoSie con =lista.get(i);
					sis.setEmpresa(con.getTbEmpresa().getRazonsocial());
					sis.setFechaEntrega(DateUtil.convertStringToDate(con.getFechaEntregaString()));
					sis.setCodContrato(con.getCodcontrato());
					sis.setNombrecliente(con.getTbCliente().getNombresCompletos());
					sis.setNumdocumento(con.getTbCliente().getNumdocumento());
					sis.setFecnacimiento(con.getTbCliente().getFecnacimiento());
					sis.setCorreo(con.getTbCliente().getCorreo());
					
					//Buscar Telefonos del cliente
					
					//sis.setNumTelefono(con.getTbCliente());
					//Buscar Domicilio
//					sis.setDireccion(getCellValueAsString(data.get(11)));
//					sis.setDistrito(getCellValueAsString(data.get(12)));
					//sis.setPlanoDistrito(con.getTbCliente().getTbDomicilioPersona());
//					sis.setLetraSector(getCellValueAsString(data.get(14)));
//					sis.setNumSector(getCellValueAsString(data.get(15)));
//					sis.setLugarTrabajo(getCellValueAsString(data.get(16)));
//					sis.setCargotrabajo(getCellValueAsString(data.get(17)));								
//					sis.setTelftrabajo(getCellValueAsString(data.get(18)));
//					sis.setAnexo(getCellValueAsString(data.get(19)));
//					sis.setDirectrabajo(getCellValueAsString(data.get(20)));
//					sis.setDistritoTrabajo(getCellValueAsString(data.get(21)));
//					sis.setPlanoTrabajo(getCellValueAsString(data.get(22)));
//					sis.setLetraSectorTrabajo(getCellValueAsString(data.get(23)));
//					sis.setNumSectorTrabajo(getCellValueAsString(data.get(24)));
//					sis.setCantMercaderia(Integer.parseInt(getCellValueAsString(data.get(25)).toString().trim()));
//					sis.setTipoMercaderia(getCellValueAsString(data.get(26))+"");
					//Detalle de pagos
//					sis.setCantCuotas(Integer.parseInt(getCellValueAsString(data.get(27))));
//					sis.setNumLetra(getCellValueAsString(data.get(28)));
//					sis.setImporteInicial(Double.parseDouble(getCellValueAsString(data.get(29))));
//					sis.setImporteCobrado(Double.parseDouble(getCellValueAsString(data.get(30))));
//					sis.setImportemasmora(Double.parseDouble(getCellValueAsString(data.get(31))));
//					sis.setFechaVencimiento(data.get(32).getDateCellValue());
//					sis.setLugarPago(getCellValueAsString(data.get(38)));
//					sis.setBanco(getCellValueAsString(data.get(39)));
//					sis.setFehaPago(DateUtil.convertStringToDate(getCellValueAsString(data.get(45))));
//					sis.setEncargadoCobranza(getCellValueAsString(data.get(52)));
//					sis.setVendedorResponsable(getCellValueAsString(data.get(53)));
//					sis.setFechaNotifica(DateUtil.convertStringToDate(getCellValueAsString(data.get(55))));
//					sis.setDiasRetraso(Integer.parseInt(diasRetrazo));
//					sis.setInfocorp(getCellValueAsString(data.get(63)));
					//sis.setRegistroReniec( );
//					sis.setCalificaInforcorp(getCellValueAsString(data.get(65)));
//					sis.setHistoria(getCellValueAsString(data.get(66)));
//					sis.setFechaCalificado(data.get(68).getDateCellValue());
					sis.setCalificacionCliente(con.getTbCliente().getTbCalificacion().getDescripcion());
				}
			}
			catch (Exception e) {
				mensaje = " Contrato: ";
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error Exportación ", mensaje);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				sistMig = new ArrayList<SistemaIntegradoDTO>();
				e.printStackTrace();
	    }
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return null;
	 }
	
}
