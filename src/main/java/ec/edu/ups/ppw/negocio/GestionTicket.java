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
	
	//Metodo para crear un ticket haciendo uso de la clase de apoyo 
	public void guardarTicket(AgregarRequest request) throws Exception{
		//Creamos un nuevo ticket
		Ticket ticket = new Ticket();
		//Leseteamos los atributos
		ticket.setHoraInicio(new Date());
		ticket.setFecha(new Date());
		
		//Obtenemos el vaor de la clase especial y calculamos valor a pagar
		Double precioTarifa = Double.valueOf(request.getTiempo());
		if (precioTarifa >= 5 && precioTarifa < 10) {
			ticket.setPrecioPagar(precioTarifa*0.10);
        } else if (precioTarifa >= 10 && precioTarifa < 30) {
        	ticket.setPrecioPagar(precioTarifa*0.20);
        } else if (precioTarifa >= 30 && precioTarifa < 50) {
        	ticket.setPrecioPagar(precioTarifa*0.35);
        } else if (precioTarifa >= 50 && precioTarifa <= 120) {
        	ticket.setPrecioPagar(precioTarifa*0.50);
        } else if (precioTarifa > 120 && precioTarifa <= 15000) {
        	ticket.setPrecioPagar(precioTarifa*0.75);
        } else {
        	ticket.setPrecioPagar(0.0);
        }
		//ticket.setUbicacion(request.getUbicacion());
		//Buscamos sitio y lo agregamos
		Sitio s = daoSitio.read(request.getUbicacion());
		s.setEstado(false);
		daoSitio.update(s);
		//Buscamos carro
		 Carro c = daoCarro.read(request.getPlaca());
		 //Buscamos persona
		 Persona p = daoPersona.read(request.getCedula());
		 //Seteamos los atributos en el ticket
		 ticket.setCarro(c);
		 ticket.setPersona(p);
		 ticket.setSitio(s);
			try {
				//Insertamos el ticket a la base de datos
				daoTicket.insert(ticket);
			}catch(Exception e) {
				throw new Exception("Error al insertar: " + e.getMessage());
			}
	}
	
	
	//Metodo para elimianr un ticket de la base de datos
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
	
	//Metodo para actualizar un ticket de la base de datos 
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
	
	//Metodo para listar todos los tickets de la base de datos
	public List<ListarRequest>getTickets(){
		//Creamos una lista de respaldo
		List<ListarRequest> listadoF = new ArrayList<ListarRequest>();
		//obtenemos todos los tickets co uso del dao
		List<Ticket> ticketsE=daoTicket.getAll();
		//Recorremos la lista de tickets
		for(Ticket t:ticketsE) {
			//Damos valor a las  variables
			String placa=t.getCarro().getPlaca();
			String cedula=t.getPersona().getCedula();
			Date horaInicio=t.getHoraInicio();
			Date horaFin=t.getHoraFin();
			Double precioPagar=t.getPrecioPagar();
			Date fecha=t.getFecha();
			int codigo=t.getCodigo();
			String ubicacion = t.getSitio().getUbicacion();
			//Con uso del constructor creamos el objeto
			ListarRequest lista = new ListarRequest(placa, cedula, horaInicio, horaFin, precioPagar, fecha,codigo,ubicacion);
			//agregamos el objeto a la lista
			listadoF.add(lista);
		}
		//devolvemos la nueva lista
		return listadoF;
	}
}

