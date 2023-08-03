package ec.edu.ups.ppw.modelo;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


@Entity
public class Ticket {
	
	//Generamos los atributos para la clase Ticket
	@Id
	@GeneratedValue
	@Column(name="tic_codigo")
	private int codigo;
	
	@Column(name="tic_horaInicio")
	private Date horaInicio;
	
	@Column(name="tic_horaFin")
	private Date horaFin;
	
	@Column(name="tic_fecha")
	private Date fecha;
	
	@Column(name="tic_precioPagar")
	private Double precioPagar;
	
	@Column(name="tic_ubicacion")
	private String ubicacion;
	
	//Aqui realizamos la relación del ticket con la persona
	@OneToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="per_cedula")
	private Persona persona;
	
	//Aqui realizamos la relación del ticket con el sitio
	@OneToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="sit_ubicacion")
	private Sitio sitio;
	
	//Aqui realizamos la relación del ticket con el carro
	@OneToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="car_placa")
	private Carro carro;

	//Getter y setter para la clase ticket
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getPrecioPagar() {
		return precioPagar;
	}

	public void setPrecioPagar(Double precioPagar) {
		this.precioPagar = precioPagar;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	
public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public Sitio getSitio() {
		return sitio;
	}

	public void setSitio(Sitio sitio) {
		this.sitio = sitio;
	}

	//toString para la clase ticket
	@Override
	public String toString() {
		return "Ticket [codigo=" + codigo + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", fecha=" + fecha
				+ ", precioPagar=" + precioPagar + ", ubicacion=" + ubicacion + ", persona=" + persona + ", sitio="
				+ sitio + ", carro=" + carro + "]";
	}
	
}
