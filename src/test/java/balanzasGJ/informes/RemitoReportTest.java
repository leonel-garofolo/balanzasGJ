package balanzasGJ.informes;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.balanzasgj.app.informes.RemitoReport;
import com.balanzasgj.app.informes.model.RemitoFieldType;
import com.balanzasgj.app.model.GlobalParameter;
import com.balanzasgj.app.persistence.RemitoFieldDao;
import com.balanzasgj.app.persistence.impl.jdbc.RemitoFieldDaoImpl;
import com.balanzasgj.app.services.GlobalParameterService;

public class RemitoReportTest {
    private GlobalParameterService globalParameterService; 
	private RemitoFieldDao dao;

	@Before
	public void createConn() {
		try {
			this.dao = new RemitoFieldDaoImpl();
			this.globalParameterService =new GlobalParameterService();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testRemito() {

		try {
			Map<String, String> data = new HashMap<String, String>();
			data.put(RemitoFieldType.DENOMINACION.label, "<DENOMINACION>");
			data.put(RemitoFieldType.DOMICILIO.label, globalParameterService.get(GlobalParameter.P_EMPRESA_DIR_BAL));
			data.put(RemitoFieldType.LOCALIDAD.label, globalParameterService.get(GlobalParameter.P_EMPRESA_LOC_BAL));
			data.put(RemitoFieldType.PROVINCIA.label, globalParameterService.get(GlobalParameter.P_EMPRESA_PROV_BAL));
			data.put(RemitoFieldType.CUIT.label, "20-3163.107-8");
			data.put(RemitoFieldType.CONDUCTOR.label, "<TARA_CONDUCTOR>");
			data.put(RemitoFieldType.ACOPLADO.label, "<TARA_ACOPLADO>");
			data.put(RemitoFieldType.PESO_ENTRADA.label, "<PESO_ENTRADA>");
			data.put(RemitoFieldType.PESO_SALIDA.label, "<PESO_SALIDA>");
			RemitoReport remito = new RemitoReport(dao.queryForAll(), data);
			remito.build();
			remito.show();
			new Thread().sleep(20000);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
