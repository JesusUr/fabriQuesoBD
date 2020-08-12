package logic;

import java.io.Serializable;

public class Cilindro extends Queso implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected float radioExterior;
	protected float altura;



	public Cilindro(float costoBase, float costoUnit, int cantQueso, String code, String tipoqueso, float radioExterior,
			float altura) {
		super(costoBase, costoUnit, cantQueso, code, tipoqueso);
		this.radioExterior = radioExterior;
		this.altura = altura;
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
		float vol = 0;
		vol = (areaBase(radioExterior)*altura);
		return vol;
	}

	protected float areaBase(float radio) {
		float area = 0;
		area = (float) ((float) Math.PI*Math.pow(radio, 2));
		
		return area;
	}

}
