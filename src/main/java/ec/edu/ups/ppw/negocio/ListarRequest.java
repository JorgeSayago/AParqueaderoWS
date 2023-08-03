package ec.edu.ups.ppw.negocio;

import java.util.Date;

public class ListarRequest {
	//Atributos de la placa necesarios para usarlos en el servicio que listara todos los tickets
	private String placa;
	private String cedula;
	private Date horaInicio;
	private Date horaFin;
	private Double precioPagar;
	private Date fecha;
	private int codigo;
	private String ubicacion;
	
	//Getter y setter para los atributos de la clase
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
	public Date getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}
	public Date getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}
	public Double getPrecioPagar() {
		return precioPagar;
	}
	public void setPrecioPagar(Double precioPagar) {
		this.precioPagar = precioPagar;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	//Cosntructor para crear objetos de esta clase necesarios para el servicio de listar todos los tickets disponibles
	public ListarRequest(String placa, String cedula, Date horaInicio, Date horaFin, Double precioPagar, Date fecha,
			int codigo, String ubicacion) {
		super();
		this.placa = placa;
		this.cedula = cedula;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.precioPagar = precioPagar;
		this.fecha = fecha;
		this.codigo = codigo;
		this.ubicacion = ubicacion;
	}
	
	
	
}
