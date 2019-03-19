package teste;

import java.text.SimpleDateFormat;
import java.util.Date;

import entity.Departamento;
import entity.Vendedor;

public class TesteDepartamento {
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Departamento d = new Departamento(1,"Livros");
		Vendedor v = new Vendedor(1,"Leonardo","leonardo@gmail.com",new Date(),3000.0,d);
		System.out.println(v);
	}

}
