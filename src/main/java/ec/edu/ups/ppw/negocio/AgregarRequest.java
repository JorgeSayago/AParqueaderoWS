package ec.edu.ups.ppw.negocio;

public class AgregarRequest {
	//Atributos para poder crear una placa
	private String placa;
	private String cedula;
	private String ubicacion;
	private String tiempo;
	//Getter y Setter para los atributos de una placa
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getTiempo() {
		return tiempo;
	}
	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}
	
	
}
