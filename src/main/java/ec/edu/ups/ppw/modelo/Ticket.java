package ec.edu.ups.ppw.modelo;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


@Entity
public class Ticket {
	
	@Id
	@GeneratedValue
	@Column(name="tic_codigo")
	private int codigo;
	
	@Column(name="tic_numeroTicket")
	private String numeroTicket;
	
	@Column(name="tic_horaInicio")
	private Date horaInicio;
	
	@Column(name="tic_horaFin")
	private Date horaFin;
	
	@Column(name="tic_fecha")
	private Date fecha;
	
	@Column(name="tic_precioPagar")
	private Double precioPagar;
	
	@OneToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="per_cedula")
	private Persona persona;
	
	@OneToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="lug_codigo")
	private LugarParqueo lugarParqueo;
	
	@OneToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="car_placa")
	private Carro carro;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNumeroTicket() {
		return numeroTicket;
	}

	public void setNumeroTicket(String numeroTicket) {
		this.numeroTicket = numeroTicket;
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

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public LugarParqueo getLugarParqueo() {
		return lugarParqueo;
	}

	public void setLugarParqueo(LugarParqueo lugarParqueo) {
		this.lugarParqueo = lugarParqueo;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public Double getPrecioPagar() {
		return precioPagar;
	}

	public void setPrecioPagar(Double precioPagar) {
		this.precioPagar = precioPagar;
	}

	@Override
	public String toString() {
		return "Ticket [codigo=" + codigo + ", numeroTicket=" + numeroTicket + ", horaInicio=" + horaInicio
				+ ", horaFin=" + horaFin + ", fecha=" + fecha + ", precioPagar=" + precioPagar + ", persona=" + persona
				+ ", lugarParqueo=" + lugarParqueo + ", carro=" + carro + "]";
	}

	
	

}
