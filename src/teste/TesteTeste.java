package teste;

import java.util.Date;
import java.util.List;

import dao.DaoFabrica;
import dao.VendedorDAO;
import entity.Departamento;
import entity.Vendedor;

public class TesteTeste {

	public static void main(String[] args) {
		VendedorDAO dao = DaoFabrica.criarVendedorDAO();
		
		System.out.println("--- 1-Testando procurar a pessoa pelo ID ---");
		Vendedor v = dao.procurarPorId(9);
		System.out.println(v);

		System.out.println("--- 2-Procurando pessoa pelo ID do departamento ---");
		Departamento d = new Departamento(2,null);
		List<Vendedor> lista = dao.procurarPorDepartamento(d);
		for(Vendedor vv : lista) {
			System.out.println(vv);
		}
		System.out.println("--- 3-Listando as pessoas ---");
		lista = dao.listar();
		for(Vendedor vv : lista) {
			System.out.println(vv);
		}
		System.out.println("--- 4-Cadastrando a pessoa ---");
		Vendedor novoVendedor = new Vendedor(null,"Greg","greg@gmail.com",new Date(),4000.0,d);
		dao.adicionar(novoVendedor);
		System.out.println("Novo ID = "+novoVendedor.getId());
		
	}
}
