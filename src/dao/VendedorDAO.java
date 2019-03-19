package dao;

import java.util.List;

import entity.Departamento;
import entity.Vendedor;

public interface VendedorDAO {
	void adicionar(Vendedor d);
	void atualizar(Vendedor d);
	void removerPorId(int id);
	Vendedor procurarPorId(int id);
	List<Vendedor> listar();
	List<Vendedor> procurarPorDepartamento(Departamento d);

}
