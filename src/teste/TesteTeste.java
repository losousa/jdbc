package teste;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dao.DaoFabrica;
import dao.VendedorDAO;
import entity.Departamento;
import entity.Vendedor;

public class TesteTeste {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		VendedorDAO dao = DaoFabrica.criarVendedorDAO();
		
		System.out.println("--- 1-Testando procurar a pessoa pelo ID ---");
		Vendedor v = dao.procurarPorId(9);
		System.out.println(v);

		System.out.println("--- 2-Procurando pessoa pelo ID do departamento ---");
		Departamento d = new Departamento(7,null);
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
		//Vendedor novoVendedor = new Vendedor(null,"Lucas","lucas@gmail.com",sdf.parse("20/08/1998"),5500.0,d);
		//dao.adicionar(novoVendedor);
		
		System.out.println("--- 5-Atualizando a pessoa ---");
		/*Vendedor vendedor = dao.procurarPorId(2);
		vendedor.setNome("Guilherme Innocencio");
		vendedor.setEmail("gsantosi@indracompany.com");
		dao.atualizar(vendedor);*/
		
		System.out.println("--- 6-Excluindo a pessoa ---");
		dao.removerPorId(5);
	}
}
