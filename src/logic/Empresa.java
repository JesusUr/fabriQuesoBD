package logic;

import java.io.Serializable;
import java.net.Socket;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class Empresa extends connectionSQL implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Factura> miFactura;
	private ArrayList<Cliente> miCliente;
	private ArrayList<Queso> miQuesos;
	private static Empresa miEmpresa=null;
	private static Socket sfd = null;
	private int factNumb = 1;
	static int PORT = 8880;
	/* PATRON SINGLETON */

	public static Empresa getInstance(){
		if(miEmpresa == null){
			miEmpresa = new Empresa();
		}
		return miEmpresa;
	}


	/* CONSTRUCTOR */

	public Empresa() {
		super();
		this.miFactura = new ArrayList<>();
		this.miCliente = new ArrayList<>();
		this.miQuesos = new ArrayList<>();
	}
	// apra cargar datos de la clase controladora a la principal visual
	public void loadInitData(Empresa myCheese) {

		// call stored procedure

		try {
			Connection myConnection = getConnectionSQL();
			CallableStatement callstmt = myConnection.prepareCall("EXEC SP_InitEsf");
			CallableStatement callstmt1 = myConnection.prepareCall("EXEC SP_InitCil");
			CallableStatement callstmt2 = myConnection.prepareCall("EXEC SP_InitHue");
			CallableStatement callstmt3 = myConnection.prepareCall("Select * from Cliente");
			CallableStatement callstmt4 = myConnection.prepareCall("Select * from Factura");
			ResultSet rs = callstmt.executeQuery();
			ResultSet rs1 = callstmt1.executeQuery();
			ResultSet rs2 = callstmt2.executeQuery();
			ResultSet rs3 = callstmt3.executeQuery();
			ResultSet rs4 = callstmt4.executeQuery();
			while (rs.next()) {
				String code = rs.getString(1);
				Float costoBase = rs.getFloat(2);
				Float costoUnit= rs.getFloat(3);
				Integer cantQueso = rs.getInt(4);
				String tipoqueso = rs.getString(5);
				Float radioEsfera = rs.getFloat(6);
				if (tipoqueso.equalsIgnoreCase("Esfera")) {
					Queso aux = new Esferico(costoBase, costoUnit, cantQueso, code, tipoqueso, radioEsfera);	
					miQuesos.add(aux);
				}

			}
			while (rs1.next()) {

				String code = rs1.getString(1);
				Float costoBase = rs1.getFloat(2);
				Float costoUnit= rs1.getFloat(3);
				Integer cantQueso = rs1.getInt(4);
				String tipoqueso = rs1.getString(5);
				Float Radio_Exterior = rs1.getFloat(6); 
				Float AlturaCil = rs1.getFloat(7);

				if (tipoqueso.equalsIgnoreCase("Cilindro")) {
					Queso aux = new Cilindro(costoBase, costoUnit, cantQueso, code, tipoqueso, Radio_Exterior, AlturaCil);	
					miQuesos.add(aux);
				}
			}
			while (rs2.next()) {

				String code = rs2.getString(1);
				Float costoBase = rs2.getFloat(2);
				Float costoUnit= rs2.getFloat(3);
				Integer cantQueso = rs2.getInt(4);
				String tipoqueso = rs2.getString(5);
				Float Radio_Interior= rs2.getFloat(6);
				Float Radio_ExteriorHue = rs2.getFloat(7);
				Float AlturaHue = rs2.getFloat(8);

				if (tipoqueso.equalsIgnoreCase("Hueco")) {
					Queso aux = new Hueco(costoBase, costoUnit, cantQueso, code, tipoqueso, Radio_Interior, Radio_ExteriorHue, AlturaHue);	
					miQuesos.add(aux);
				}
			}
			while (rs3.next()) {
				String cedula = rs3.getString(1);
				String nombre = rs3.getString(2);
				String apellido = rs3.getString(3);
				String direccion = rs3.getString(4);
				String telefono = rs3.getString(5);

				Cliente aux = new Cliente(cedula, nombre, apellido, direccion, telefono);
				miCliente.add(aux);

			}

			while(rs4.next()) {
				String code = rs4.getString(1);
				String cedula = rs4.getString(2);
				Cliente aux1 = findClient(cedula);
				Factura aux = new Factura(ordenQueso(miQuesos), code, aux1);
				miFactura.add(aux);
			}

		} catch (SQLException e) {
			System.out.println("Error agregarQueso!");
			e.printStackTrace();

		}

	}
	
	
	/* GETS & SETS */

	public ArrayList<Factura> getMiFactura() {
		return miFactura;
	}

	public void setMiFactura(ArrayList<Factura> miFactura) {
		this.miFactura = miFactura;
	}

	public ArrayList<Cliente> getMiCliente() {
		return miCliente;
	}

	public void setMiCliente(ArrayList<Cliente> miCliente) {
		this.miCliente = miCliente;
	}

	public ArrayList<Queso> getMiQuesos() {
		return miQuesos;
	}

	public void setMiQuesos(ArrayList<Queso> miQuesos) {
		this.miQuesos = miQuesos;
	}

	public static Empresa getMiEmpresa() {
		return miEmpresa;
	}

	public static void setMiEmpresa(Empresa miEmpresa) {
		Empresa.miEmpresa = miEmpresa;
	}

	public void crearCliente(Cliente aux) {
		miCliente.add(aux);
	}


	
	public boolean agregarFactura(Factura aux) {
		String InsertFact = "EXEC SP_Crear_Factura ?,?,?,?";
		System.out.println("entro agregarfact");
	
		try {
			System.out.println("entro al try");
			Connection myConnection = getConnectionSQL();
			PreparedStatement callstmt = myConnection.prepareStatement(InsertFact);
			callstmt.setString(1, aux.getCode());
			callstmt.setString(2, aux.getMicliente().getCedula());
			callstmt.setFloat(3, aux.totalFacturado());
			callstmt.setInt(4, aux.getCantQuesos());
			callstmt.execute();		
			miFactura.add(aux);
			System.out.println("salio try fact");
			return true;
		} catch (SQLException e) {
			System.out.println("Error agregarfact");
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean agregarCliente(Cliente aux) {
		String InsertCliente = "EXEC SP_Crear_Cliente ?,?,?,?,?";
		System.out.println("entro agregarClie");
	
		try {
			System.out.println("entro al try");
			Connection myConnection = getConnectionSQL();
			PreparedStatement callstmt = myConnection.prepareStatement(InsertCliente);
			callstmt.setString(1, aux.getCedula());
			callstmt.setString(2, aux.getNombre());
			callstmt.setString(3, aux.getApellido());
			callstmt.setString(4, aux.getDireccion());
			callstmt.setString(5, aux.getTelefono());
			callstmt.execute();		
			miCliente.add(aux);
			System.out.println("salio try");
			return true;
		} catch (SQLException e) {
			System.out.println("Error agregarQueso!");
			e.printStackTrace();
			return false;
		}
	}
	public boolean AgregarQueso(Queso aux) {
		String insertQueso = "EXEC SP_Crear_Queso ?,?,?,?,?";
		String insertEsf = "EXEC SP_Crear_Esferico ?,?";
		String insertCil = "EXEC SP_Crear_Cilindro ?,?,?";
		String insertHuec = "EXEC SP_Crear_Hueco ?,?,?,?";

		// call stored procedure
		try {
			Connection myConnection = getConnectionSQL();
			if(aux instanceof Esferico) {
				aux.setCode("QESF-"+(miQuesos.size()+1));
				PreparedStatement callstmt = myConnection.prepareStatement(insertQueso);
				callstmt.setString(1, aux.getCode());
				callstmt.setFloat(2, aux.getCostoBase());
				callstmt.setFloat(3, aux.getCostoUnit());
				callstmt.setInt(4, aux.getCantQueso());
				callstmt.setString(5, aux.getTipoqueso());
				PreparedStatement callstmt1 = myConnection.prepareStatement(insertEsf);
				callstmt1.setString(1, aux.getCode());
				callstmt1.setFloat(2, ((Esferico) aux).getRadioEsfera());
				callstmt.execute();
				callstmt1.execute();
			}
			if(aux instanceof Hueco) {

				aux.setCode("QHUE-"+(miQuesos.size()+1));
				PreparedStatement callstmt = myConnection.prepareStatement(insertQueso);
				callstmt.setString(1, aux.getCode());
				callstmt.setFloat(2, aux.getCostoBase());
				callstmt.setFloat(3, aux.getCostoUnit());
				callstmt.setInt(4, aux.getCantQueso());
				callstmt.setString(5, aux.getTipoqueso());
				PreparedStatement callstmt1 = myConnection.prepareStatement(insertHuec);
				callstmt1.setString(1, aux.getCode());
				callstmt1.setFloat(2, ((Hueco) aux).getRadioInterior());
				callstmt1.setFloat(3, ((Hueco) aux).getRadioExterior());
				callstmt1.setFloat(4, ((Hueco) aux).getAltura());
				callstmt.execute();
				callstmt1.execute();

			}
			if(aux instanceof Cilindro) {

				aux.setCode("QCIL-"+(miQuesos.size()+1));
				PreparedStatement callstmt = myConnection.prepareStatement(insertQueso);
				callstmt.setString(1, aux.getCode());
				callstmt.setFloat(2, aux.getCostoBase());
				callstmt.setFloat(3, aux.getCostoUnit());
				callstmt.setInt(4, aux.getCantQueso());
				callstmt.setString(5, aux.getTipoqueso());
				PreparedStatement callstmt1 = myConnection.prepareStatement(insertCil);
				callstmt1.setString(1, aux.getCode());
				callstmt1.setFloat(2, ((Cilindro) aux).getRadioExterior());
				callstmt1.setFloat(3, ((Cilindro) aux).getAltura() );
				callstmt.execute();
				callstmt1.execute();
			}

			miQuesos.add(aux);
			return true;
		} catch (SQLException e) {
			System.out.println("Error agregarQueso!");
			e.printStackTrace();
			return false;
		}

	}
	

	public Factura crearFactura(Cliente creaCliente, ArrayList<Queso> quesoCliente) {
		Cliente aux = findClient(creaCliente.getCedula());
		Factura crearFactura = null;

		if(aux!=null) {
			crearFactura = new Factura(ordenQueso(quesoCliente),"FACT-"+(miFactura.size()+1), aux);
		}else {
			crearCliente(creaCliente);
			crearFactura = new Factura(ordenQueso(quesoCliente),"FACT-"+(miFactura.size()+1), creaCliente);
		}

		return crearFactura;
	}

	public float calculoVolumen(Queso q1) {
		return q1.volumen();
	}

	public Cliente findClient(String cedula){
		Cliente aux = null;
		boolean find = false;
		int i = 0;
		while( i < miCliente.size() && !find){
			if(miCliente.get(i).getCedula().equalsIgnoreCase(cedula))
			{
				find = true;
				aux = miCliente.get(i);
			}
			i++;
		}
		return aux;
	}

	private ArrayList<Queso> ordenQueso(ArrayList<Queso> quesito){
		ArrayList<Queso> aux = new ArrayList<>();
		int i=0;
		while(i < quesito.size()) {
			aux.add(quesito.get(i));
			i++;
		} 
		return aux;
	}

	public boolean verifiCantQueso(Queso aux) {
		boolean find=false;
		boolean hay=false;
		int i=0;
		while(i < miQuesos.size() && !find) {
			if(miQuesos.get(i).getCode().equalsIgnoreCase(aux.getCode())) {
				find = true;
				if(miQuesos.get(i).getCantQueso()>=aux.getCantQueso() && miQuesos.get(i).getCantQueso()!=0) {
					hay=true;
					miQuesos.get(i).setCantQueso(miQuesos.get(i).getCantQueso()-aux.getCantQueso());
				}
			}
			i++;
		}
		return hay;
	}

	public boolean verifiCantQuesoVolver(Queso aux) {
		boolean find=false;
		boolean hay=false;
		int i=0;
		while(i < miQuesos.size() && !find) {
			if(miQuesos.get(i).getCode().equalsIgnoreCase(aux.getCode())) {
				find = true;

				miQuesos.get(i).setCantQueso(miQuesos.get(i).getCantQueso()+aux.getCantQueso());
				hay=true;
			}
			i++;
		}
		return hay;
	}

	public float precioFactura(Factura aux) {
		float precio=0;
		int i=0;
		while (i<aux.getArrqueso().size()) {
			precio+=aux.getArrqueso().get(i).costo();
		}

		return precio;
	}
	public void realizarCompra(Factura auxi) {
		miFactura.add(auxi);
	}



}

