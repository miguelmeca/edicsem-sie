package com.edicsem.pe.sie.util.constants;

/**
 * Constantes usadas en toda la aplicación.
 * <p>
 * <a href="Constants.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jvelasquez@edicsem.com">Jorge Luis Velasquez</a>
 */
public class Constants {
	
	public static final String MESSAGE_INFO_TITULO = "Se registro correctamente";
	public static final String MESSAGE_ERROR_FATAL_TITULO = "Mensaje de Error";
	public static final String MESSAGE_ERROR_ID_NOT_FOUND = "No se encuentra el Id";
	public static final String ESQUEMA_SIE_POSTGRE = "sie";
	//Tablas
	public static final String TB_COBRANZA_OPERADORA_SIE = ESQUEMA_SIE_POSTGRE+"."+"tb_cobranza_operadora";
	public static final String TB_TIPO_PRODUCTO_SIE = ESQUEMA_SIE_POSTGRE+"."+ "tb_tipo_producto";
	public static final String ENCRYPTION_SHA_512 = "SHA-512";
	
	public static final String PAGE_MODULE = "mantenimiento";
	public static final String LISTA_CARGO_PAGE = "../edicsemperu/mantenimientoCargoEmpleadoFormList";
	
	public static final String MANT_EMPRESA_FORM_PAGE = "mantenimientoEmpresaForm";
	public static final String MANT_EMPRESA_FORM_LIST_PAGE = "mantenimientoEmpresaFormList";
	public static final String MANT_META_LIST_PAGE = "metaEmpresa";
	
	
	public static final String MANT_CLIENTE_FORM_LIST_PAGE = "mantenimientoClienteFormList";
	public static final String MANT_CLIENTE_FORM_PAGE = "mantenimientoClienteForm";

	
	public static final String MANT_PRODUCTO_FORM_PAGE = "mantenimientoProductoForm";
	public static final String MANT_PRODUCTO_FORM_LIST_PAGE = "mantenimientoProductoFormList";
	
	public static final String MANT_CARGO_EMPLEADO_FORM_LIST_PAGE = "mantenimientoCargoEmpleadoFormList";
	
	public static final String MOVIMIENTO_FORM_LIST_PAGE = "movimientoMercaderiaForm";
	
	public static final String CONTRATO_FORM_PAGE = "RegistroContratoForm";
	
	public static final String MANT_ALMACEN_FORM_PAGE = "mantenimientoPuntoAlmacenForm";
	public static final String MANT_ALMACEN_FORM_LIST_PAGE = "mantenimientoPuntoAlmacenFormList";
	
	public static final String MANT_COMPROBANTE_FORM_PAGE = "mantenimientoComprobanteForm";
	public static final String MANT_COMPROBANTE_FORM_LIST_PAGE = "mantenimientoComprobanteFormList";
	
	public static final String MANT_DET_EMPRESA_EMPLEADO_FORM_PAGE = "mantenimientoDetEmpresaEmpleadoForm";
	public static final String MANT_DET_EMPRESA_EMPLEADO_FORM_LIST_PAGE = "mantenimientoDetEmpresaEmpleadoFormList";
	
	//constantes de tablas con la TB_ESTADO_GENERAL
	
	public static final String COD_ESTADO_TB_CARGO_EMPLEADO = "CE";
	public static final String COD_ESTADO_TB_EMPLEADO = "EM";
	public static final String COD_ESTADO_TB_PRODUCTO = "PR";
	public static final String COD_ESTADO_TB_EMPRESA = "EMP";
	public static final String COD_ESTADO_TB_META_MES =  "MET";
	public static final String COD_ESTADO_TB_PUNTO_ALMACEN =  "PA";
	
	//aviso mensaje "las contraseñas no coinciden"
	public static final String MESSAGE_PASSWORDS_DESIGUALES = "Contraseñas no coinciden";
	
	}