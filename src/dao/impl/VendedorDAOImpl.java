package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.VendedorDAO;
import entity.Departamento;
import entity.Vendedor;
import exception.MinhaException;

public class VendedorDAOImpl implements VendedorDAO{

	private Connection conn;
	
	public VendedorDAOImpl(Connection c) {
		this.conn = c;
	}

	public Vendedor instanciarVendedor(ResultSet rs,Departamento d) throws SQLException {
		Vendedor v = new Vendedor();
		v.setId(rs.getInt("id"));
		v.setNome(rs.getString("nome"));
		v.setEmail(rs.getString("email"));
		v.setDataAniversario(rs.getDate("aniversario"));
		v.setSalario(rs.getDouble("salario"));
		v.setDepartamento(d);
		return v;
	}
	
	public Departamento instanciarDepartamento(ResultSet rs) throws SQLException {
		Departamento d= new Departamento();
		d.setId(rs.getInt("departamentoId"));
		d.setNome(rs.getString("dep"));
		return d;
	}
	@Override
	public void adicionar(Vendedor d) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
			
		String sql = "insert into vendedor(id,nome,email,aniversario,salario,departamentoId)values(id.nextval,?,?,?,?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, d.getNome());
			stmt.setString(2, d.getEmail());
			stmt.setDate(3, new java.sql.Date(d.getDataAniversario().getTime()));
			stmt.setDouble(4, d.getSalario());
			stmt.setInt(5, d.getDepartamento().getId());
			
			int linhasAfetadas = stmt.executeUpdate();
			
			System.out.println("Adicionado com sucesso");
			System.out.println("Linhas afetadas : "+linhasAfetadas);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void atualizar(Vendedor d) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		String sql = "UPDATE vendedor SET nome = ? WHERE id = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, d.getNome());
			stmt.setInt(2, d.getId());
			
			int linhasAfetadas = stmt.executeUpdate();
			
			System.out.println("Atualizado com sucesso");
			System.out.println("Linhas afetadas : "+linhasAfetadas);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void removerPorId(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vendedor procurarPorId(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT vendedor.id,vendedor.nome,vendedor.email,vendedor.aniversario,"
					+ "vendedor.salario,vendedor.departamentoId,departamento.nome as dep "
					+"FROM vendedor INNER JOIN departamento ON vendedor.departamentoId = departamento.departamentoId "
					+"WHERE vendedor.id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if(rs.next()) {
				Departamento dep = new Departamento();
				dep.setId(rs.getInt("departamentoId"));
				dep.setNome(rs.getString("dep"));
				
				Vendedor v = new Vendedor();
				
				v.setId(rs.getInt("id"));
				v.setNome(rs.getString("nome"));
				v.setEmail(rs.getString("email"));	
				v.setDataAniversario(rs.getDate("aniversario"));
				v.setSalario(rs.getDouble("salario"));
				v.setDepartamento(dep);
				
				System.out.println("Pesquisa feita com sucesso");
				return v;
			}
			return null;
		} catch (SQLException e) {
			throw new MinhaException(e.getMessage());
		}	
		}
		
	

	@Override
	public List<Vendedor> listar() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT vendedor.id,vendedor.nome,vendedor.email,vendedor.aniversario,"
					+"vendedor.salario,vendedor.departamentoId,departamento.nome as dep "
					+"FROM vendedor,departamento where vendedor.departamentoId = departamento.departamentoID ";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			List<Vendedor> lista = new ArrayList<>();
			Map<Integer,Departamento> map = new HashMap<>();
			while(rs.next()) {
				
				Departamento dep = map.get(rs.getInt("departamentoId"));
				
				if(dep == null) {
					
					dep = instanciarDepartamento(rs);
					map.put(rs.getInt("departamentoId"),dep);
				}
				
					Vendedor v = instanciarVendedor(rs, dep);
					lista.add(v);
				System.out.println("Pesquisa feita com sucesso");
			}
			return lista;
		} catch (SQLException e) {
			throw new MinhaException(e.getMessage());
		}	
		}
	

	@Override
	public List<Vendedor> procurarPorDepartamento(Departamento d) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT vendedor.id,vendedor.nome,vendedor.email,vendedor.aniversario,"
					+"vendedor.salario,vendedor.departamentoId,departamento.nome as dep "
					+"FROM vendedor INNER JOIN departamento ON vendedor.departamentoId = departamento.departamentoID "
					+"WHERE vendedor.departamentoId = ? ORDER BY vendedor.nome";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, d.getId());
			rs = stmt.executeQuery();
			List<Vendedor> lista = new ArrayList<>();
			Map<Integer,Departamento> map = new HashMap<>();
			while(rs.next()) {
				
				Departamento dep = map.get(rs.getInt("departamentoId"));
				
				if(dep == null) {
					
					dep = instanciarDepartamento(rs);
					map.put(rs.getInt("departamentoId"),dep);
				}
				
					Vendedor v = instanciarVendedor(rs, dep);
					lista.add(v);
				System.out.println("Pesquisa feita com sucesso");
			}
			return lista;
		} catch (SQLException e) {
			throw new MinhaException(e.getMessage());
		}	
		}
		
}
