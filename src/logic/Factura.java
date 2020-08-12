package logic;

import java.io.Serializable;
import java.util.ArrayList;



public class Factura implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Queso> arrqueso;
	private String code;
	private Cliente micliente;
	
	
	public Factura(ArrayList<Queso> arrqueso, String code, Cliente micliente) {
		super();
		this.arrqueso = arrqueso;
		this.code = code;
		this.micliente = micliente;
	} 


	public ArrayList<Queso> getArrqueso() {
		return arrqueso;
	}


	public void setArrqueso(ArrayList<Queso> arrqueso) {
		this.arrqueso = arrqueso;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}
	
	public float precioQueso() {
		float precio = 0, volumen = 0;
		
		for(int i = 0;i < arrqueso.size();i++) {
			if(arrqueso.get(i) instanceof Esferico) {
				if(arrqueso.get(i).volumen() > volumen) {
					volumen = arrqueso.get(i).volumen();
					precio = arrqueso.get(i).costo();
				}
			}
			
		}
		
		return precio;
	}
	


	public Cliente getMicliente() {
		return micliente;
	}


	public void setMicliente(Cliente micliente) {
		this.micliente = micliente;
	}
	public int getCantQuesos() {
		int cantidad = 0;
		for (Queso queso : arrqueso) {
			cantidad += queso.getCantQueso();
		}
		
		return cantidad;
	}
	
	public float totalFacturado() {
		float total=0;
		int i=0;
		while(i<arrqueso.size()) {
			//System.out.println(arrqueso.get(i).costo()+" "+arrqueso.get(i).cantQueso);
			total+=(arrqueso.get(i).costo()*arrqueso.get(i).cantQueso);
			i++;
		}
	
		return (float) (total+total*0.18+total*0.10);
	}
	
}
