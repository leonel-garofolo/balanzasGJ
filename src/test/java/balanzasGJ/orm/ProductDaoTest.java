package balanzasGJ.orm;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.balanzasgj.app.model.Product;
import com.balanzasgj.app.persistence.ProductDao;
import com.balanzasgj.app.persistence.impl.jdbc.ProductDaoImpl;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;

public class ProductDaoTest extends ConnectionORMTest{
	ProductDao dao;
	
	@Before
	public void createConn() {
		try {
			this.dao = new ProductDaoImpl();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getAll() {		
		try {
			List<Product> productos = dao.queryForAll();
			for (Product producto: productos) {
				System.out.println(producto.getCodigo() + "|" + producto.getNombre());
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void save() {
		Product p = new Product();
		//p.setCodigo(1);
		p.setAlias("miAlias");
		p.setNombre("PROD111");
		p.setAcumulado(new BigDecimal(1));		
		System.out.println(dao.save(p));				
	}
	
	@Test
	public void update() {
		Product p = new Product();
		p.setCodigo((long)4);
		p.setAlias("miAlias");
		p.setNombre("PROD444");
		p.setAcumulado(new BigDecimal(1));
		dao.save(p);				
	}
}
