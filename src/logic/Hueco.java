package logic;

import java.io.Serializable;

public class Hueco extends Queso implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float radioInterior;
	private float radioExterior;
	private float altura;




	public Hueco(float costoBase, float costoUnit, int cantQueso, String code, String tipoqueso, float radioInterior,
			float radioExterior, float altura) {
		super(costoBase, costoUnit, cantQueso, code, tipoqueso);
		this.radioInterior = radioInterior; 
		this.radioExterior = radioExterior;
		this.altura = altura;
	}


	public float getRadioInterior() {
		return radioInterior;
	}

	public void setRadioInterior(float radioInterior) {
		this.radioInterior = radioInterior;
	}
	

	public float getRadioExterior() {
		return radioExterior;
	}


	public void setRadioExterior(float radioExterior) {
		this.radioExterior = radioExterior;
	}


	public float getAltura() {
		return altura;
	}


	public void setAltura(float altura) {
		this.altura = altura;
	}
	

	@Override
	public float volumen() {
		float volum=0;
		volum= (areaBase(radioExterior)*altura)-(areaBase(radioInterior)*altura);
		return volum;
	}
	
	public float areaBase(float radio) {
		float area = 0;
		area = (float) ((float) Math.PI*Math.pow(radio, 2));
		
		return area;
	}
}
