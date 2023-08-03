package ec.edu.ups.ppw.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Sitio {
	
	//Generamos los atributos para la clase Sitio
	@Id
	@Column(name="sit_ubicacion")
	private String ubicacion;
	
	@Column(name="sit_numeroSitio")
	private int numeroSitio;
	
	@Column(name="sit_piso")
	private int piso;
	
	@Column(name="sit_estado")
	private boolean estado;

	
	//Generamos los getter y setter para la clase Sitio
	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public int getNumeroSitio() {
		return numeroSitio;
	}

	public void setNumeroSitio(int numeroSitio) {
		this.numeroSitio = numeroSitio;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}
