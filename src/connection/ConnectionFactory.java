package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public static Connection getConnection() {
		
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","jacksolut","@Ad2011ov");
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

}