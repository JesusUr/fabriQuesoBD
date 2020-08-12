package logic;

import java.sql.Connection;
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		connectionSQL con = new connectionSQL();
		con.getConnectionSQL();
			
		/*Empresa miEmpresa = new Empresa();
		Queso esferico = new Esferico(50, 100, 10, "40", 10);
		Queso cilindro = new Cilindro(75, 100, 10, "25", 10, 10);
		Queso hueco = new Hueco(100, 100, 10, "10", 10, 10, 5);
	//	Queso esferico1 = new Esferico(50, 100, 5, "QE-1", 5);	
		Cliente micliente = new Cliente("Jose", "El funso", "809-264-8543", "40");
		ArrayList<Queso> miqueso = new ArrayList<>();//carrito
		
		miEmpresa.AgregarQueso(esferico);
		miEmpresa.AgregarQueso(cilindro);
		miEmpresa.AgregarQueso(hueco);
		esferico.setCantQueso(5);
		cilindro.setCantQueso(4);
		hueco.setCantQueso(3);
		
		miqueso.add(esferico);
		miqueso.add(cilindro);
		miqueso.add(hueco);
		
		Factura miFactura = miEmpresa.crearFactura(micliente, miqueso);
		miEmpresa.realizarCompra(miFactura);
		
		for (int i=0; i < miEmpresa.getMiQuesos().size();i++) {
			System.out.println(miEmpresa.getMiQuesos().get(i).getCode()+" Volumen: "+miEmpresa.getMiQuesos().get(i).volumen()+" Precio: "+miEmpresa.getMiQuesos().get(i).costo()+" Cant: "+miEmpresa.getMiQuesos().get(i).getCantQueso());
		}*/
	}

}
