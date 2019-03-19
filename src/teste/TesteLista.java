package teste;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConnectionFactory;

public class TesteLista {
	public static void main(String[] args) throws SQLException {
	
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			con = ConnectionFactory.getConnection();
			st = con.createStatement();
			rs = st.executeQuery("select * from pessoa");
			
			while(rs.next()) {
				System.out.println(rs.getInt("id")+", "+rs.getString("nome")+", "+rs.getInt("idade")+", "+rs.getString("cpf"));
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			rs.close();
			st.close();
			con.close();
		}
	}
}
