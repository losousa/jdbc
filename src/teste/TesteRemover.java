package teste;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionFactory;

public class TesteRemover {
	public static void main(String[] args) throws SQLException {
	
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = ConnectionFactory.getConnection();
			String sql = "DELETE FROM pessoa WHERE id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, 2);
			
			int linhasAfetadas = stmt.executeUpdate();
			System.out.println("Excluido com sucesso");
			System.out.println("Linhas afetadas : "+linhasAfetadas);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stmt.close();
		con.close();
	}
}
