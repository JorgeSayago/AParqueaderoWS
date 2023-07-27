package ec.edu.ups.ppw.modelo;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Carro {

	@Id
	@Column(name="car_placa")
	private String placa;
	
	@Column(name="car_marca")
	private String marca;
	
	@Column(name="car_modelo")
	private String modelo;
	
	@Column(name="car_anio")
	private String anio;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	@Override
	public String toString() {
		return "Carro [placa=" + placa + ", marca=" + marca + ", modelo=" + modelo + ", anio=" + anio + "]";
	}
}
