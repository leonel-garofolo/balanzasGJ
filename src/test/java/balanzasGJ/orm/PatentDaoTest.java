package balanzasGJ.orm;

import com.balanzasgj.app.model.Patent;
import com.balanzasgj.app.persistence.PatentDao;
import com.balanzasgj.app.persistence.impl.jdbc.PatentDaoImpl;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;

public class PatentDaoTest extends ConnectionORMTest{
	PatentDao dao;
	
	@Before
	public void createConn() {
		try {
			this.dao = new PatentDaoImpl();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void save() {
		Patent p = new Patent();
		p.setCodigo("JDOWW22212");
		p.setDiasVenc(0);
		p.setTara(0.0);
		p.setUpdate(new Date());

		dao.save(p);
	}
}
