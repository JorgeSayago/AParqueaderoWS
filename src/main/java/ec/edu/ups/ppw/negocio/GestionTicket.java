package ec.edu.ups.ppw.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ec.edu.ups.ppw.dao.CarroDAO;
import ec.edu.ups.ppw.dao.LugarParqueoDAO;
import ec.edu.ups.ppw.dao.PersonaDAO;
import ec.edu.ups.ppw.dao.SitioDAO;
import ec.edu.ups.ppw.dao.TicketDAO;
import ec.edu.ups.ppw.modelo.Carro;
import ec.edu.ups.ppw.modelo.LugarParqueo;
import ec.edu.ups.ppw.modelo.Persona;
import ec.edu.ups.ppw.modelo.Sitio;
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
	
	@Inject 
	private SitioDAO daoSitio ;
	
	public void guardarTicket(AgregarRequest request) throws Exception{
		Ticket ticket = new Ticket();
		ticket.setHoraInicio(new Date());
		ticket.setFecha(new Date());
		ticket.setPrecioPagar(0.0);
		//ticket.setUbicacion(request.getUbicacion());
		Sitio s = daoSitio.read(request.getUbicacion());
		 Carro c = daoCarro.read(request.getPlaca());
		 Persona p = daoPersona.read(request.getCedula());
		 ticket.setCarro(c);
		 ticket.setPersona(p);
		 ticket.setSitio(s);
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
	
	public List<ListarRequest>getTickets(){
		List<ListarRequest> listadoF = new ArrayList<ListarRequest>();
		List<Ticket> ticketsE=daoTicket.getAll();
		for(Ticket t:ticketsE) {
			String placa=t.getCarro().getPlaca();
			String cedula=t.getPersona().getCedula();
			Date horaInicio=t.getHoraInicio();
			Date horaFin=t.getHoraFin();
			Double precioPagar=t.getPrecioPagar();
			Date fecha=t.getFecha();
			int codigo=t.getCodigo();
			String ubicacion = t.getSitio().getUbicacion();
			ListarRequest lista = new ListarRequest(placa, cedula, horaInicio, horaFin, precioPagar, fecha,codigo,ubicacion);
			listadoF.add(lista);
		}
		return listadoF;
	}
	/*public List<Ticket>getTickets(){
		return daoTicket.getAll();
	}**/
	
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

