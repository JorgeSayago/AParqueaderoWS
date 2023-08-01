package ec.edu.ups.ppw.modelo;

public class AgregarPersonaRequest {
	private int ticketCodigo;
    private String cedula;
	public int getTicketCodigo() {
		return ticketCodigo;
	}
	public void setTicketCodigo(int ticketCodigo) {
		this.ticketCodigo = ticketCodigo;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
}
