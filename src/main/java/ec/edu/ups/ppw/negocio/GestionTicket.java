package ec.edu.ups.ppw.negocio;

import java.util.Date;
import java.util.List;

import ec.edu.ups.ppw.dao.CarroDAO;
import ec.edu.ups.ppw.dao.LugarParqueoDAO;
import ec.edu.ups.ppw.dao.PersonaDAO;
import ec.edu.ups.ppw.dao.TicketDAO;
import ec.edu.ups.ppw.modelo.Carro;
import ec.edu.ups.ppw.modelo.LugarParqueo;
import ec.edu.ups.ppw.modelo.Persona;
import ec.edu.ups.ppw.modelo.Ticket;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionTicket {
	
	@Inject 
	private TicketDAO daoTicket;
	
	@Inject 
	private CarroDAO daoCarro;
	
	@Inject 
	private PersonaDAO daoPersona;

	@Inject 
	private LugarParqueoDAO daoLugarParqueo;
	
	public void guardarTicket(AgregarRequest request) throws Exception{
		
		Ticket ticket = new Ticket();
		ticket.setNumeroTicket(request.getNumeroTicket());
		ticket.setHoraInicio(new Date());
		ticket.setHoraFin(new Date());
		ticket.setFecha(new Date());
		ticket.setPrecioPagar(0.0);
		Carro c = daoCarro.read(request.getPlaca());
		Persona p = daoPersona.read(request.getCedula());
		LugarParqueo l = daoLugarParqueo.read(request.getCodigo());
		daoLugarParqueo.update(l);
		ticket.setCarro(c);
		ticket.setPersona(p);
		ticket.setLugarParqueo(l);
			try {
				daoTicket.insert(ticket);
			}catch(Exception e) {
				throw new Exception("Error al insertar: " + e.getMessage());
			}
	}
	
	public void eliminarTicket(Ticket ticket) throws Exception {
		
	    Ticket ticket2 = daoTicket.read(ticket.getCodigo());
	    if (ticket2 == null) {
	        throw new Exception("El ticket con la codigo " + ticket2.getCodigo() + " no existe");
	    }

	    try {
	        daoTicket.delete(ticket.getCodigo());
	    } catch (Exception e) {
	        throw new Exception("Error al eliminar: " + e.getMessage());
	    }
	}
	
	public void actualizarTicket(Ticket ticket) throws Exception {
	    Ticket ticketExistente = daoTicket.read(ticket.getCodigo());
	    if (ticketExistente == null) {
	        throw new Exception("El ticket con codigo " + ticket.getCodigo() + " no existe");
	    }

	    try {
	        daoTicket.update(ticket); 
	    } catch (Exception e) {
	        throw new Exception("Error al actualizar: " + e.getMessage());
	    }
	}
	
	public List<Ticket>getTickets(){
		return daoTicket.getAll();
	}
	
	/**
	 * public void guardarTicket(Ticket ticket) throws Exception{
		if(daoTicket.read(ticket.getCodigo()) == null) {
			try {
				daoTicket.insert(ticket);
			}catch(Exception e) {
				throw new Exception("Error al insertar: " + e.getMessage());
			}
		}else {
			try {
				daoTicket.update(ticket);
			}catch(Exception e) {
				throw new Exception("Error al actualizar: " + e.getMessage());
			}
		}
	}*/
}

