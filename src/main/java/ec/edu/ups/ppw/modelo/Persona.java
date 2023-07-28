package ec.edu.ups.ppw.modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Persona {
	
	@Id
	@Column(name="per_cedula")
	private String cedula;
	
	@Column(name="per_nombre")
	private String nombre;

	@Column(name="per_direccion")
	private String direccion;
	
	@Column(name="per_telefono")
	private String telefono;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="per_cedula")
	private List<Carro> carros;

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Carro> getCarros() {
		return carros;
	}
	public void setCarros(List<Carro> carros) {
		this.carros = carros;
	}
	
	public void addCarros(Carro carro) {
		if(carros == null) {
			carros= new ArrayList<Carro>();
		}
		carros.add(carro);
	}

	@Override
	public String toString() {
		return "Persona [cedula=" + cedula + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono="
				+ telefono + ", carros=" + carros + "]";
	}

	public Persona() {
		
	}
	
	
}
