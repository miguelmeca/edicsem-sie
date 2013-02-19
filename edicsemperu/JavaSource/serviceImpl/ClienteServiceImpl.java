package serviceImpl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.collections.MapUtils;
import org.apache.velocity.app.VelocityEngine;

import service.ClienteReporteService;

import com.edicsem.pe.sie.service.facade.ClienteService;

public class ClienteServiceImpl extends HttpServlet implements
        ClienteReporteService {
	
	@EJB
	private ClienteService clienteserv;
    private VelocityEngine velocityEngine;
    
    /**
     * @return Returns the velocityEngine.
     */
    public VelocityEngine getVelocityEngine() {
        return velocityEngine;
    }

    /**
     * @param velocityEngine
     *            The velocityEngine to set.
     */
    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }
    
	/* (non-Javadoc)
	 * @see service.ClienteReporteService#printClientes(java.lang.String)
	 */
	public void printClientes(String codigoCli) {
		
        Map criteria = new HashMap();
        criteria.put("codigoCli", codigoCli);
        
        List clientes = clienteserv.listarClientes();
        Iterator i = clientes.iterator();
        
        while (i.hasNext()) {
            Map consultora = (Map) i.next();
            String codigoConsultora = MapUtils.getString(consultora, "CODIGO_CONSULTORA");
            
            FileOutputStream fos = null;
            File file = new File( "/Reporte", "Reporte_de_cliente.pdf");
            try {
                fos = new FileOutputStream(file);
//                JasperReportsUtils.renderAsPdf(getReport(), getParameters(
//                		codigoCli, codigoConsultora),
//                        getDataSource(consultora), fos);
                fos.close();
                fos = null;
            } catch (Exception e) {
                if (fos != null) {
                    try {
                        fos.close();
                        fos = null;
                    } catch (IOException ignore) {
                    }
                }
            }
        }
	}
	
    private JasperReport getReport() throws Exception {
    	ServletContext context2 = getServletContext();
		String reportLocation = context2.getRealPath("Reporte");

		FileInputStream fis = new FileInputStream(reportLocation+"/reporte_de_cliente.jasper");
		BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
        return (JasperReport) JRLoader.loadObject(bufferedInputStream);
    }
    
	private JRDataSource getDataSource(Map consultora) {
	     List list = new ArrayList();
	     list.add(consultora);

	     return new JRMapCollectionDataSource(list);
	 }
}