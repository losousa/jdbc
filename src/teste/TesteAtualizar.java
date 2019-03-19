package teste;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionFactory;

public class TesteAtualizar {
	public static void main(String[] args) {
	
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		con = ConnectionFactory.getConnection();
		try {
			ps = con.prepareStatement("UPDATE pessoa SET idade = ? WHERE nome = ?");
			ps.setInt(1,30);
			ps.setString(2, "Leonardo");
			
			int linhasAfetadas = ps.executeUpdate();
			System.out.println("Atualizado com sucesso");
			System.out.println("Linhas Afetadas : "+linhasAfetadas);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
