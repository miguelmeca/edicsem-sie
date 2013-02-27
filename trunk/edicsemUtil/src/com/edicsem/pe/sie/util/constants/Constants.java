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
	
	public static final String MESSAGE_INFO_TITULO = "Mensaje: ";
	public static final String MESSAGE_REGISTRO_TITULO = "Se registró correctamente";
	public static final String MESSAGE_ACTUALIZO_TITULO = "Se actualizó correctamente";
	public static final String MESSAGE_DESHABILITO_TITULO = "Se deshabilitó correctamente";
	public static final String MESSAGE_DESHABILITAR_TITULO = "Se deshabilito correctamente";
	public static final String MESSAGE_ERROR_FATAL_TITULO = "Mensaje de Error";
	public static final String MESSAGE_ERROR_FATAL_TITULO2 = "Mensaje de Error2";
	public static final String MESSAGE_ERROR_META_MES_FATAL_TITULO = "debe Seleccionar un Mes para poder ingresar la meta";
	public static final String MESSAGE_ERROR_ID_COBRANZA = "Seleccione una Cobranza para continuar";
	public static final String MESSAGE_ERROR_ID_NOT_FOUND = "No se encuentra el Id";
	public static final String MESSAGE_ERROR_TITULO = "Mensaje de Error";
	public static final String ESQUEMA_SIE_POSTGRE = "sie";
	//Tablas
	public static final String TB_COBRANZA_OPERADORA_SIE = ESQUEMA_SIE_POSTGRE+"."+"tb_cobranza_operadora";
	public static final String TB_TIPO_PRODUCTO_SIE = ESQUEMA_SIE_POSTGRE+"."+ "tb_tipo_producto";
	public static final String ENCRYPTION_SHA_512 = "SHA-512";
	
	public static final String PAGE_MODULE = "mantenimiento";
	public static final String LISTA_CARGO_PAGE = "../edicsemperu/mantenimientoCargoEmpleadoFormList";
	
	public static final String MANT_EMPRESA_FORM_PAGE = "mantenimientoEmpresaForm";
	public static final String MANT_EMPRESA_FORM_LIST_PAGE = "mantenimientoEmpresaFormList";
	public static final String MANT_EMPRESA_EMPLEADO_PRODUCTO_FORM_LIST_PAGE = "mantenimientoEmpresaDerivacionFormList";
	public static final String MANT_CARGOEMPLEADO_FORM_LIST_PAGE = "mantenimientoCargoEmpleadoDerivacionFormList";
	
	public static final String MANT_META_LIST_PAGE = "metaEmpresa";


	
	public static final String MANT_CLIENTE_FORM_LIST_PAGE = "mantenimientoClienteFormList";
	public static final String MANT_CLIENTE_FORM_PAGE = "mantenimientoClienteForm";

	public static final String MANT_PRODUCTO_FORM_PAGE = "mantenimientoProductoForm";
	public static final String MANT_PRODUCTO_FORM_LIST_PAGE = "mantenimientoProductoFormList";
	
	public static final String MANT_TIPO_PRODUCTO_FORM_LIST_PAGE = "mantenimientoTipoProductoFormList";
	
	public static final String MANT_CARGO_EMPLEADO_FORM_LIST_PAGE = "mantenimientoCargoEmpleadoFormList";
	
	public static final String MIGRACION_METAS_DIARIAS_FORM_LIST_PAGE = "excelMetasDiarias";
	
	
	public static final String MOVIMIENTO_FORM_LIST_PAGE = "movimientoMercaderiaForm";
	
	public static final String CONTRATO_FORM_PAGE = "RegistroContratoForm";
	
	public static final String MANT_ALMACEN_FORM_PAGE = "mantenimientoPuntoAlmacenForm";
	public static final String MANT_ALMACEN_FORM_LIST_PAGE = "mantenimientoPuntoAlmacenFormList";
	
	public static final String MANT_COMPROBANTE_FORM_PAGE = "mantenimientoComprobanteForm";
	public static final String MANT_COMPROBANTE_FORM_LIST_PAGE = "mantenimientoComprobanteFormList";
	
	public static final String MANT_DET_EMPRESA_EMPLEADO_FORM_PAGE = "mantenimientoDetEmpresaEmpleadoForm";
	public static final String MANT_DET_EMPRESA_EMPLEADO_FORM_LIST_PAGE = "mantenimientoDetEmpresaEmpleadoFormList";
	
	public static final String MANT_HORARIO_PERSONAL = "mantenimientoHorarioPersonal";
	
	public static final String MANT_HORARIO_PERSONAL_VENTAS = "mantenimientoHorarioVendedor";
	public static final String MANT_HORARIO_ASISTENCIA = "horarioAsistencia";
	
	public static final String MANT_HORARIO_PUNTO_VENTA = "mantenimientoHorarioPuntoVenta";
	
	public static final String MANT_FACTOR_SANCION_FORM_LIST_PAGE = "mantenimientoFactorSancionFormList";
	
	public static final String MANT_FILTRO_HORARIO_PERSONAL_VENTAS = "mantenimientoFiltroHorarioVendedor";
	
	public static final String MANT_SANCION_FORM_LIST_PAGE = "mantenimientoSancionList";
	public static final String MANT_SANCION_FORM_PAGE  = "mantenimientoSancionForm";
	
	public static final String MANT_PAQUETEBIBLICO_FORM_LIST_PAGE = "mantenimientoPaqueteBiblicoList";
//	public static final String MANT_DETALLEPAQUETEBIBLICO_FORM_LIST_PAGE = "mantenimientoDetallePaqueteBiblicoList";
	public static final String MANT_DETALLEPAQUETEBIBLICO_FORM_PAGE  = "mantenimientoDetallePaqueteBiblicoForm";
																		 
	public static final String MANT_PAGO_VENDEDOR_FORM_PAGE = "mantenimientoPagoVentaForm";
	public static final String MANT_PAGO_VENDEDOR_LIST_PAGE = "mantenimientoPagoVentaList";
	
	public static final String MANT_META_EMPLEADO_FORM_PAGE = "mantenimientoMetaEmpleadoForm";
	public static final String MANT_META_EMPLEADO_LIST_PAGE = "mantenimientoMetaEmpleadoFormList";
	
	public static final String MANT_MOTIVO_FORM_LIST_PAGE = "mantenimientoMotivoFormList";
	
	public static final String ASIGNAR_PERMISOS_FORM_PAGE = "AsignarPermisosForm";
	
	public static final String GENERAR_LISTAS_COBRANZA_FORM_PAGE = "GenerarListasCobranzaForm";

	public static final String CONSULTA_CONTRATO_FORM_PAGE = "ConsultaContratoForm";
	public static final String MIGRAR_SISTEMA_INTEGRADO = "MigracionSistemaIntegrado";
	public static final String MIGRAR_ENTREGAS_PERU = "excelEntregasPeru";
	public static final String MIGRAR_SISTEMA_RECAUDACION="MigracionSistemaRecaudacion";
	public static final String GESTIONAR_CONTRATO_FORM_PAGE = "GestionarContratoForm";

	public static final String CONTROL_MERCADERIA_FORM = "ControlMercaderiaForm";

	public static final String VERIFICA_CLIENTE_FORM = "VerificaClienteForm";
	
	//constantes de tablas con la TB_ESTADO_GENERAL
	
	public static final String COD_ESTADO_TB_CARGO_EMPLEADO = "CE";
	public static final String COD_ESTADO_TB_EMPLEADO = "EM";
	public static final String COD_ESTADO_TB_PRODUCTO = "PR";
	public static final String COD_ESTADO_TB_EMPRESA = "EMP";
	public static final String COD_ESTADO_TB_META_MES =  "MET";
	public static final String COD_ESTADO_TB_PUNTO_ALMACEN =  "PA";
	public static final String COD_ESTADO_TB_DET_CONTRATO_PRODUCTO ="PRC0";
	public static final String COD_ESTADO_TB_CONTRATO = "CON";
	
	//aviso mensaje "las contraseñas no coinciden"
	public static final String MESSAGE_PASSWORDS_DESIGUALES = "Contraseñas no coinciden";
	
	//Valores para Login
	public static final String INDEX_PAGE = "index";
	public static final String LOGIN_PAGE = "login";
	public static final String USER_KEY = "loginUserSession";
	public static final String CARGO_USER = "cargoSession";
	
	//Variables para el reporte
	public static final String RUTA_REPORTE = "E:\\Sie\\Reporte\\";
	public static final String REPORTE_CLIENTE_JASPER = "Reporte/report6.jasper";
	public static final String REPORTE_KARDEX_JASPER = "Reporte/reportKardex.jasper";
	public static final String REPORTE_TITULO ="titulo";
	
	//Reporte Cliente
	public static final String REPORTE_TIPO_CLIENTE ="tipoCliente";
	
	public static final String REPORTE_CLIENTE_LIST = "Reporte Cliente";
	
	//Reporte Kardex
	public static final String REPORTE_KARDEX_ALMACEN ="almacen";
	public static final String REPORTE_KARDEX_PRODUCTO ="producto";
	public static final String REPORTE_KARDEX_FECHA_DESDE ="fechaDesde";
	public static final String REPORTE_KARDEX_FECHA_HASTA ="fechaHasta";
	
	public static final String REPORTE_KARDEX_LIST="Reporte Kardex";
	public static final String RUTA_IMAGENES_PRODUCTO = "E:\\Producto\\Images" ;
	public static final String RUTA_DOC_SUSTENTARIO = "E:\\Images\\Docs";
	public static final String RUTA_IMAGEN_DEFECTO = "E:\\Producto\\Images\\bibliaXDefecto.png";
	
	}