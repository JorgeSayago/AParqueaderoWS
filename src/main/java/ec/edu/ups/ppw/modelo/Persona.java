package ec.edu.ups.ppw.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

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

	@Override
	public String toString() {
		return "Persona [cedula=" + cedula + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono="
				+ telefono + "]";
	}
	
	
}
