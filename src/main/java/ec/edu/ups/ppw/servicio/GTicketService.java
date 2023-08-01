package ec.edu.ups.ppw.servicio;

import ec.edu.ups.ppw.modelo.Carro;
import ec.edu.ups.ppw.modelo.Ticket;
import ec.edu.ups.ppw.negocio.AgregarRequest;
import ec.edu.ups.ppw.negocio.GestionTicket;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("tickets")
public class GTicketService {
	
	
	@Inject
	private GestionTicket gTicket;
	
	@GET
	@Path("saludo")
	public String saludo() {
		return "Hola mundo";
	}
	
	@POST	
	@Produces("application/json")
	@Consumes("application/json")
	public Response guardarTicket(AgregarRequest request) {
		try {
			gTicket.guardarTicket(request);
			return Response.status(Response.Status.OK).entity(request).build();
		}catch(Exception e){
			e.printStackTrace();
			Error error = new Error();
			error.setCodigo(99);
			error.setMensaje("Error al guardar: " +e.getMessage());
			return Response.status(Response.Status.OK).entity(error).build();
		}
	}

}
