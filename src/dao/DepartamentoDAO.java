package dao;

import java.util.List;

import entity.Departamento;

public interface DepartamentoDAO {
	void adicionar(Departamento d);
	void atualizar(Departamento d);
	void removerPorId(int id);
	Departamento procurarPorId(int id);
	List<Departamento> listar();

}
