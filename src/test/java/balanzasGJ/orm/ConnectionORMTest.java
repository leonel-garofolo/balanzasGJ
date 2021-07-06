package balanzasGJ.orm;

import java.sql.SQLException;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;

public class ConnectionORMTest {
	
	protected JdbcPooledConnectionSource getConnection() {
		String databaseUrl = "jdbc:mysql://localhost:3306/sist_pesada";
		// create a connection source to our database

		// pooled connection source
		JdbcPooledConnectionSource connectionSource = null;
		try {
			connectionSource = new JdbcPooledConnectionSource(databaseUrl, "root", "1234");			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connectionSource;
	}

}
