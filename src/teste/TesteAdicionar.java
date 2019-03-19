package teste;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dao.DaoFabrica;
import dao.VendedorDAO;
import entity.Departamento;
import entity.Vendedor;

public class TesteAdicionar {
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Departamento d = new Departamento(2,"Eletronicos");
		Vendedor v = null;
		try {
			v = new Vendedor(null,"Maria Green","maria@gmail.com",sdf.parse("20/08/1998"),3090.00,d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		VendedorDAO dao = DaoFabrica.criarVendedorDAO();

		dao.adicionar(v);
	}
}
