package logic;

import java.io.Serializable;

public abstract class Queso implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected float costoBase;
	protected float costoUnit;
	protected int cantQueso;
	protected String code;
	protected String tipoqueso;
	
	public Queso(float costoBase, float costoUnit, int cantQueso, String code, String tipoqueso) {
		super();
		this.costoBase = costoBase;
		this.costoUnit = costoUnit;
		this.cantQueso = cantQueso;
		this.code = code;
		this.tipoqueso = tipoqueso;
	}


	 
	// --- GETS AND SETS --- //
	public float getCostoBase() {
		return costoBase;
	}
	
	public void setCostoBase(float costoBase) {
		this.costoBase = costoBase;
	}
	
	public float getCostoUnit() {
		return costoUnit;
	}
	
	public void setCostoUnit(float costoUnit) {
		this.costoUnit = costoUnit;
	}
	
	public int getCantQueso() {
		return cantQueso;
	}

	public void setCantQueso(int cantQueso) {
		this.cantQueso = cantQueso;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	
	// --- METODOS --- //
	public abstract float volumen(); //se implementa metodo abstracto, esto es solo su firma
	
	public float costo(){
		return costoBase+costoUnit*volumen();
	}



	public String getTipoqueso() {
		return tipoqueso;
	}



	public void setTipoqueso(String tipoqueso) {
		this.tipoqueso = tipoqueso;
	}




	
	
}
