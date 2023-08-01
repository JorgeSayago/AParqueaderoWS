package ec.edu.ups.ppw.negocio;

import java.util.List;

import ec.edu.ups.ppw.dao.CarroDAO;
import ec.edu.ups.ppw.dao.LugarParqueoDAO;
import ec.edu.ups.ppw.dao.PersonaDAO;
import ec.edu.ups.ppw.dao.TicketDAO;
import ec.edu.ups.ppw.modelo.Carro;
import ec.edu.ups.ppw.modelo.LugarParqueo;
import ec.edu.ups.ppw.modelo.Persona;
import ec.edu.ups.ppw.modelo.Ticket;
import jakarta.inject.Inject;

public class GestionTicket {
	
	@Inject 
	private TicketDAO daoTicket;
	
	@Inject 
	private PersonaDAO daoPersona;
	
	@Inject 
	private CarroDAO daoCarro;
	
	@Inject 
	private LugarParqueoDAO daoLugarParqueo;
	
	
	public void guardarTicket(Ticket ticket) throws Exception{
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
	}
	
	public void agregarPersona(Ticket ticket, String cedula)throws Exception{
		Ticket ticketExistente = daoTicket.read(ticket.getCodigo());
		if (ticketExistente == null) {
			throw new Exception("El ticket no existe " + ticket.getCodigo() + " no existe");
		}
		try {
			Persona personaAgregar = daoPersona.read(cedula);
			ticketExistente.setPersona(personaAgregar);
			daoTicket.update(ticketExistente);
		} catch (Exception e) {
			throw new Exception("Error al actualizar: " + e.getMessage());
		}
	}
	
	
	public void agregarCarro(Ticket ticket, String placa)throws Exception{
		Ticket ticketExistente = daoTicket.read(ticket.getCodigo());
		if (ticketExistente == null) {
			throw new Exception("El ticket no existe " + ticket.getCodigo() + " no existe");
		}
		try {
			Carro carroAgregar = daoCarro.read(placa);
			carroAgregar.setEstado(false);
			daoCarro.update(carroAgregar);
			ticketExistente.setCarro(carroAgregar);
			daoTicket.update(ticketExistente);
		} catch (Exception e) {
			throw new Exception("Error al actualizar: " + e.getMessage());
		}
	}
	
	public void agregarLugar(Ticket ticket, int codigo)throws Exception{
		Ticket ticketExistente = daoTicket.read(ticket.getCodigo());
		if (ticketExistente == null) {
			throw new Exception("El ticket no existe " + ticket.getCodigo() + " no existe");
		}
		try {
			LugarParqueo lugarAgregar = daoLugarParqueo.read(codigo);
			lugarAgregar.setEstado(false);
			daoLugarParqueo.update(lugarAgregar);
			ticketExistente.setLugarParqueo(lugarAgregar);
			daoTicket.update(ticketExistente);
		} catch (Exception e) {
			throw new Exception("Error al actualizar: " + e.getMessage());
		}
	}
	
	public List<Ticket>getTickets(){
		return daoTicket.getAll();
	}
}
