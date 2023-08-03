package ec.edu.ups.ppw.modelo;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Carro {
	
	//Generamos los atributos para la clase Carro
	@Id
	@Column(name="car_placa")
	private String placa;
	
	@Column(name="car_marca")
	private String marca;
	
	@Column(name="car_modelo")
	private String modelo;
	
	@Column(name="car_estado")
	private boolean estado;
	
	//Creacion de getters y setters para la clase carro

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

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	//Generamos el toString para la clase carro
	@Override
	public String toString() {
		return "Carro [placa=" + placa + ", marca=" + marca + ", modelo=" + modelo + ", estado=" + estado + "]";
	}
	
	
	
}
