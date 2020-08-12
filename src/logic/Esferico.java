package logic;

import java.io.Serializable;

public class Esferico extends Queso implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private float radioEsfera;
	
	// --- CONSTRUCTOR --- //




	public Esferico(float costoBase, float costoUnit, int cantQueso, String code, String tipoqueso, float radioEsfera) {
		super(costoBase, costoUnit, cantQueso, code, tipoqueso);
		this.radioEsfera = radioEsfera;
	}


	@Override
	public float volumen() {
		float vol = 0;
		vol = ((float)((Math.PI)*(Math.pow(radioEsfera, 3))*4) / 3);
		return vol;
	}

	// --- SETS AND GETS --- //
	public float getRadioEsfera() {
		return radioEsfera;
	}


	public void setRadioEsfera(float radioEsfera) {
		this.radioEsfera = radioEsfera;
	}

}
