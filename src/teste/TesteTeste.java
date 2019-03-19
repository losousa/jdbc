package teste;

import java.util.List;

import dao.DaoFabrica;
import dao.VendedorDAO;
import entity.Departamento;
import entity.Vendedor;

public class TesteTeste {

	public static void main(String[] args) {
		VendedorDAO dao = DaoFabrica.criarVendedorDAO();
		
		System.out.println("--- Testando procurar a pessoa pelo ID ---");
		Vendedor v = dao.procurarPorId(9);
		System.out.println(v);

		System.out.println("--- Procurando pessoa pelo ID do departamento ---");
		Departamento d = new Departamento(2,null);
		List<Vendedor> lista = dao.procurarPorDepartamento(d);
		for(Vendedor vv : lista) {
			System.out.println(vv);
		}
		System.out.println("--- Listando as pessoas ---");
		lista = dao.listar();
		for(Vendedor vv : lista) {
			System.out.println(vv);
		}
		
		
		
	}
}
