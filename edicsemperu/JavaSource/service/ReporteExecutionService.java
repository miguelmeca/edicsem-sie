package service;
import javax.ejb.Local;
import clases.ReporteParams;
import clases.ReporteResult;

@Local
public interface ReporteExecutionService {
	
	/**
	 * 
	 * @param reportParams
	 *            parametros del reporte
	 * @return ReporteExecutionResult con los resultados de la ejecucion.
	 */
	public ReporteResult executeReporte(ReporteParams reportParams);
	/**
	 * Metodo que  implementa la construccion del jasper 
	 * Virtualizado
	 * @param reportParams
	 * @return
	 */
	public ReporteResult executeReporteVirtualizer(ReporteParams reportParams);
	
	/**
	 * Metodo que genera varios reportes segun parametros
	 * @param reportParams
	 */
	public ReporteResult executeReporteMultiple(ReporteParams reportParams);
}
