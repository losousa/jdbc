package dao;

import connection.ConnectionFactory;
import dao.impl.DepartamentoDAOImpl;
import dao.impl.VendedorDAOImpl;

public class DaoFabrica {
	
	public static VendedorDAO criarVendedorDAO() {
		return new VendedorDAOImpl(new ConnectionFactory().getConnection());
	}

	public static DepartamentoDAO criarDepartamentoDAO() {
		return new DepartamentoDAOImpl(new ConnectionFactory().getConnection());
	}
}
