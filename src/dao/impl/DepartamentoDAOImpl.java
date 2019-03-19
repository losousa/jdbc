package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import dao.DepartamentoDAO;
import entity.Departamento;

public class DepartamentoDAOImpl implements DepartamentoDAO{
	
	private Connection conn;
	
	public DepartamentoDAOImpl(Connection c) {
		this.conn = c;
	}

	@Override
	public void adicionar(Departamento d) {
		PreparedStatement stmt = null;
		String sql ="insert into departamento(departamentoId,nome)values(?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, d.getId());
			stmt.setString(2, d.getNome());
			
			int linhasAfetadas = stmt.executeUpdate();
			
			System.out.println("Adicionado com sucesso");
			System.out.println("Linhas Afetadas : "+linhasAfetadas);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void atualizar(Departamento d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removerPorId(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Departamento procurarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Departamento> listar() {
		// TODO Auto-generated method stub
		return null;
	}

}
