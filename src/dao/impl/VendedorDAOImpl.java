package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Statement;
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
	public void adicionar(Vendedor v) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("insert into vendedor(nome,email,aniversario,salario,departamentoId) values (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			st.setString(1, v.getNome());
			st.setString(2, v.getEmail());
			st.setDate(3, new java.sql.Date(v.getDataAniversario().getTime()));
			st.setDouble(4, v.getSalario());
			st.setInt(5,v.getDepartamento().getId());
			
			int linhasAfetadas = st.executeUpdate();
			
			System.out.println("Cadastrado !");
			System.out.println("Linhas afetadas : "+linhasAfetadas);
		}catch(SQLException e) {
			throw new MinhaException(e.getMessage());
		}finally {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void atualizar(Vendedor v) {
		PreparedStatement stmt = null;
		
		String sql = "UPDATE vendedor SET nome=?,email=?,aniversario=?,salario=?,departamentoId=? WHERE id =?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, v.getNome());
			stmt.setString(2, v.getEmail());
			stmt.setDate(3, new java.sql.Date(v.getDataAniversario().getTime()));
			stmt.setDouble(4, v.getSalario());
			stmt.setInt(5,v.getDepartamento().getId());
			stmt.setInt(6, v.getId());
			
			int linhasAfetadas = stmt.executeUpdate();
			
			System.out.println("Atualizado com sucesso !");
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
	public void removerPorId(int id) {
		PreparedStatement stmt = null;
		String sql = "DELETE FROM vendedor WHERE id = ?";
		
		int linhasAfetadas;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,id);
			linhasAfetadas = stmt.executeUpdate();
			if(linhasAfetadas == 0) {
				throw new MinhaException("Não existe.");
			}else {

			System.out.println("Excluido com sucesso !");
			System.out.println("Linhas afetadas : "+linhasAfetadas);
			}
		} catch (SQLException e) {
			throw new MinhaException(e.getMessage());
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
