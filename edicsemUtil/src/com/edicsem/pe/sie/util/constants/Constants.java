package com.edicsem.pe.sie.util.constants;

/**
 * Constantes usadas en toda la aplicaci�n.
 * <p>
 * <a href="Constants.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jvelasquez@edicsem.com">Jorge Luis Velasquez</a>
 */
public class Constants {
	
	public static final String MESSAGE_INFO_TITULO = "Mensaje: ";
	public static final String MESSAGE_REGISTRO_TITULO = "Se registr� correctamente";
	public static final String MESSAGE_ACTUALIZO_TITULO = "Se actualiz� correctamente";
	public static final String MESSAGE_DESHABILITO_TITULO = "Se deshabilit� correctamente";
	public static final String MESSAGE_DESHABILITAR_TITULO = "Se deshabilito correctamente";
	public static final String MESSAGE_ERROR_FATAL_TITULO = "Mensaje de Error";
	public static final String MESSAGE_ERROR_FATAL_TITULO_DETALLE = "Seleccione un Paquete B�blico para poder agregar producto";
	public static final String MESSAGE_ERROR_META_MES_FATAL_TITULO = "debe Seleccionar un Mes para poder ingresar la meta";
	public static final String MESSAGE_ERROR_ID_COBRANZA = "Seleccione una Cobranza para continuar";
	public static final String MESSAGE_ERROR_ID_NOT_FOUND = "No se encuentra el Id";
	public static final String ESQUEMA_SIE_POSTGRE = "sie";
	public static final String MESSAGE_ERROR_TELEFONO_CLIENTE = "Los Cambios se realizaran despues de hacer clic en el boton Guardar";
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
	
	//Par�metros del sistema
	public static final String PARAM_MORA_ACTUAL="MORA_ACTUAL";
	public static final String PARAM_EFECTIVIDAD_VENTAS="EFECTIVIDAD_VENTAS";
	public static final String PARAM_CANT_MES_VENDEDORES_INICIALES="CANT_MES_VENDEDORES_INICIALES";
	public static final String PARAM_ADMINISTRADOR_SISTEMA = "Administrador del Sistema";
	
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
	
	public static final String MANT_DETALLEPAQUETEBIBLICO_FORM_PAGE  = "mantenimientoDetallePaqueteBiblicoForm";
																		 
	public static final String MANT_PAGO_VENDEDOR_FORM_PAGE = "mantenimientoPagoVentaForm";
	public static final String MANT_PAGO_VENDEDOR_LIST_PAGE = "mantenimientoPagoVentaList";
	
	public static final String MANT_META_EMPLEADO_FORM_PAGE = "mantenimientoMetaEmpleadoForm";
	public static final String MANT_META_EMPLEADO_LIST_PAGE = "mantenimientoMetaEmpleadoFormList";
	
	public static final String MANT_MOTIVO_FORM_LIST_PAGE = "mantenimientoMotivoFormList";
	
	public static final String ASIGNAR_PERMISOS_FORM_PAGE = "asignarPermisosForm";
	
	public static final String GENERAR_LISTAS_COBRANZA_FORM_PAGE = "GenerarListasCobranzaForm";

	public static final String CONSULTA_CONTRATO_FORM_PAGE = "ConsultaContratoForm";
	public static final String MIGRAR_SISTEMA_INTEGRADO = "MigracionSistemaIntegrado";
	public static final String MIGRAR_ENTREGAS_PERU = "excelEntregasPeru";
	public static final String MIGRAR_SISTEMA_RECAUDACION = "MigracionSistemaRecaudacion";
	public static final String GESTIONAR_CONTRATO_FORM_PAGE = "GestionarContratoForm";

	public static final String CONTROL_MERCADERIA_FORM = "controlMercaderiaForm";

	public static final String VERIFICA_CLIENTE_FORM = "VerificaClienteForm";
	
	public static final String MANT_ASIGNAR_GRUPO_VENTA = "asignarGrupoVentaForm";

	public static final String MANT_ENTREGA_LETRAS = "EntregaLetrasContratoForm";
	
	public static final String MANT_COMISION_FORM_LIST_PAGE = "mantenimientoComisionVentaFormList";
	
	public static final String MANT_COMISION_FORM_PAGE = "mantenimientoComisionVentaForm";
	
	public static final String MANT_EVENTO_FORM_LIST_PAGE = "mantenimientoEventoFormList";
	public static final String MANT_EVENTO_FORM_PAGE = "mantenimientoEventoForm";

	public static final String MANT_PROVEEDOR_FORM_LIST_PAGE = "mantenimientoProveedorList";
	public static final String MANT_PROVEEDOR_FORM_PAGE = "mantenimientoProveedorForm";
	
	public static final String MANT_CRITERIO_FORM_LIST_PAGE = "mantenimientoCriterioFormList";
	
	public static final String MANT_LUGAR_FORM_LIST_PAGE = "mantenimientoLugarVentaFormList";
	
	public static final String MANT_GRUPO_FORM_LIST_PAGE = "mantenimientoGrupoVentaFormList";

	public static final String MANT_DISTRIBUCION_HORARIO_VENTA = "DistribucionHorarioGrupoVentaForm";

	public static final String MANT_HORARIO_TURNO = "mantenimientoHorarioTurnoForm";

	public static final String MANT_EFECTIVIDAD_FORM = "EfectividadVentasList";
	
	public static final String ASIGNAR_CORREOS_INCIDENTE_FORM_PAGE = "asignarCorreosIncidenteForm";
	
	public static final String MANT_TIPO_CLIENTE_FORM_LIST_PAGE = "mantenimientoTipoClienteFormList";

	public static final String MANT_CONFIG_COBRANZA_OPERA_LIST = "mantenimientoConfigCobranzaOperaList";
	public static final String MANT_CONFIG_COBRANZA_OPERA_FORM = "mantenimientoConfigCobranzaOperaForm";
	
	public static final String MANT_CONFIG_PUNTAJE_LIST = "mantenimientoConfigPuntajeList";
	public static final String MANT_CONFIG_PUNTAJE_FORM = "mantenimientoConfigPuntajeForm";
	
	public static final String MANT_CONFIG_TIPO_REFINAN_LIST = "mantenimientoConfigTipoRefinanciaList";
	public static final String MANT_CONFIG_TIPO_REFINAN_FORM = "mantenimientoConfigTipoRefinanciaForm";

	public static final String MANT_CONFIG_NOTIFICACION_LIST = "mantenimientoConfigNotificacionList";
	
	public static final String MANT_CAJA_FORM_LIST_PAGE = "mantenimientoCajaFormList";
	
	public static final String MIGRAR_PRODUCTO_PAQUETE = "migracionProductos";

	public static final String REPORTE_LLAMADA_OPERADORA = "ReporteTrabajoOperadoraList";
	
	public static final String MOV_CAJA_FORM_LIST_PAGE = "movimientoCajaFormList";
	
	public static final String REPORTE_COBRANZA_CASA = "ReporteCobranzaCasaForm";

	public static final String MANT_RESPONDER_LLAMADA_FORM = "ResponderLlamadaClienteForm";
	
	public static final String ASIGNAR_TIPO_CLIENTE_FORM = "AsignarTipoClienteForm";
	
	//constantes de tablas con la TB_ESTADO_GENERAL
	
	public static final String COD_ESTADO_TB_CARGO_EMPLEADO = "CE";
	public static final String COD_ESTADO_TB_EMPLEADO = "EM";
	public static final String COD_ESTADO_TB_PRODUCTO = "PR";
	public static final String COD_ESTADO_TB_EMPRESA = "EMP";
	public static final String COD_ESTADO_TB_META_MES =  "MET";
	public static final String COD_ESTADO_TB_PUNTO_ALMACEN =  "PA";
	public static final String COD_ESTADO_TB_DET_CONTRATO_PRODUCTO ="PRC0";
	public static final String COD_ESTADO_TB_CONTRATO = "CON";
	
	public static final String CARGO_EXPOSITOR = "EXPOSITOR";
	public static final String CARGO_CERRADOR = "CERRADOR";
	public static final String CARGO_VENDEDOR = "VENDEDOR";
	public static final String CARGO_RELACIONISTA = "RELACIONISTA";
	
	//aviso mensaje "las contrase�as no coinciden"
	public static final String MESSAGE_PASSWORDS_DESIGUALES = "Contrase�as no coinciden";
	
	//Valores para Login
	public static final String INDEX_PAGE = "index";
	public static final String LOGIN_PAGE = "login";
	public static final String USER_KEY = "loginUserSession";
	public static final String CARGO_USER = "cargoSession";
	
	//Variables para el reporte
	public static final String RUTA_REPORTE = "E:\\Sie\\Reporte\\";
	public static final String REPORTE_CLIENTE_JASPER = "Reporte/report6.jasper";
	public static final String REPORTE_KARDEX_JASPER = "Reporte/reportKardex.jasper";
	public static final String REPORTE_EXPORTACION_JASPER = "Reporte/reportExportacionSI.jasper";
	public static final String REPORTE_PAGOS_CONTRATO_JASPER = "Reporte/reportPagos.jasper";
	public static final String REPORTE_TITULO ="titulo";
	
	//Reporte Cliente
	public static final String REPORTE_TIPO_CLIENTE ="tipoCliente";
	public static final String REPORTE_CLIENTE_LIST = "Reporte Cliente";
	
	//Reporte Kardex
	public static final String REPORTE_KARDEX_ALMACEN ="almacen";
	public static final String REPORTE_KARDEX_PRODUCTO ="producto";
	public static final String REPORTE_KARDEX_FECHA_DESDE ="fechaDesde";
	public static final String REPORTE_KARDEX_FECHA_HASTA ="fechaHasta";
	public static final String REPORTE_CODIGO_CONTRATO = "codcontrato";
	
	//Reporte Pagos Cliente
	public static final String REPORTE_PAGOS_LIST = "Reporte de Pagos";
	
	public static final String REPORTE_KARDEX_LIST="Reporte Kardex";
	public static final String RUTA_IMAGENES_PRODUCTO = "C:\\proyecto-sie\\Producto" ;
	public static final String RUTA_DOC_SUSTENTARIO = "C:\\Images\\Docs";
	public static final String RUTA_IMAGEN_DEFECTO = "C:\\proyecto-sie\\Images\\noDisponible.jpg";
	
	//Reporte de Exportacion Sistemas Integrados
	public static final String REPORTE_EXPORTACION_SI_LIST="Reporte Sistema Integrado";
	
	}