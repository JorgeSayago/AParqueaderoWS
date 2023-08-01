package ec.edu.ups.ppw.servicio;

import java.util.List;

import ec.edu.ups.ppw.servicio.Error;
import ec.edu.ups.ppw.dao.PersonaDAO;
import ec.edu.ups.ppw.modelo.Persona;
import ec.edu.ups.ppw.modelo.Ticket;
import ec.edu.ups.ppw.modelo.AgregarCarroRequest;
import ec.edu.ups.ppw.modelo.AgregarLugarRequest;
import ec.edu.ups.ppw.modelo.AgregarPersonaRequest;
import ec.edu.ups.ppw.modelo.Carro;
import ec.edu.ups.ppw.modelo.LugarParqueo;
import ec.edu.ups.ppw.negocio.GestionPersona;
import ec.edu.ups.ppw.negocio.GestionTicket;
import ec.edu.ups.ppw.negocio.GestionCarro;
import ec.edu.ups.ppw.negocio.GestionLugarParqueo;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("clientes")
public class GClientesService {
	
	@Inject
	private GestionPersona gClientes;
	
	@Inject
	private GestionCarro gCarros;
	
	
	@Inject
	private GestionTicket gTickets;
	
	
	@Inject
	private GestionLugarParqueo gLugarParqueos;

	@GET
	@Path("saludo")
	public String saludo() {
		return "Hola mundo";
	}
	@POST	
	@Produces("application/json")
	@Consumes("application/json")
	public Response guardarCliente(Persona persona) {
		try {
			gClientes.guardarClientes(persona);
			return Response.status(Response.Status.OK).entity(persona).build();
		}catch(Exception e){
			e.printStackTrace();
			Error error = new Error();
			error.setCodigo(99);
			error.setMensaje("Error al guardar: " +e.getMessage());
			return Response.status(Response.Status.OK).entity(error).build();
		}
	}
	
	@GET
	@Path("all")
	@Produces("application/json")
	public Response getClientes() {
		List<Persona> listado = gClientes.getClientes();
		
		return Response.status(Response.Status.OK).entity(listado).build();
	}
	
	@DELETE
	@Produces("application/json")
	@Consumes("application/json")
	public Response eliminarCliente(Persona persona) {
	    try {
	        gClientes.eliminarCliente(persona);
	        return Response.status(Response.Status.OK).build();
	    } catch (Exception e) {
	        e.printStackTrace();
	        Error error = new Error();
	        error.setCodigo(99);
	        error.setMensaje("Error al eliminar: " + e.getMessage());
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
	    }
	}
	
	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	public Response actualizarCliente(Persona persona) {
	    try {
	        gClientes.actualizarCliente(persona);
	        return Response.status(Response.Status.OK).build();
	    } catch (Exception e) {
	        e.printStackTrace();
	        Error error = new Error();
	        error.setCodigo(99);
	        error.setMensaje("Error al actualizar: " + e.getMessage());
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
	    }
	}
	@POST
	@Path("carroG")
	@Produces("application/json")
	@Consumes("application/json")
	public Response guardarCarro(Carro carro) {
		try {
			gCarros.guardarCarro(carro);
			return Response.status(Response.Status.OK).entity(carro).build();
		}catch(Exception e){
			e.printStackTrace();
			Error error = new Error();
			error.setCodigo(99);
			error.setMensaje("Error al guardar: " +e.getMessage());
			return Response.status(Response.Status.OK).entity(error).build();
		}
	}
	
	
	
	@POST
	@Path("lugarG")
	@Produces("application/json")
	@Consumes("application/json")
	public Response guardarLugar(LugarParqueo lugarParqueo) {
		try {
			gLugarParqueos.guardarLugar(lugarParqueo);
			return Response.status(Response.Status.OK).entity(lugarParqueo).build();
		}catch(Exception e){
			e.printStackTrace();
			Error error = new Error();
			error.setCodigo(99);
			error.setMensaje("Error al guardar: " +e.getMessage());
			return Response.status(Response.Status.OK).entity(error).build();
		}
	}
	
	
	@POST
	@Path("ticketG")
	@Produces("application/json")
	@Consumes("application/json")
	public Response guardarTicket(Ticket ticket) {
		try {
			gTickets.guardarTicket(ticket);
			return Response.status(Response.Status.OK).entity(ticket).build();
		}catch(Exception e){
			e.printStackTrace();
			Error error = new Error();
			error.setCodigo(99);
			error.setMensaje("Error al guardar: " +e.getMessage());
			return Response.status(Response.Status.OK).entity(error).build();
		}
	}
	
	
	// Método para agregar una persona a un ticket
    @POST
    @Path("agregarPersona")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response agregarPersona(AgregarPersonaRequest request) {
        try {
        	gTickets.agregarPersona(request.getTicketCodigo(), request.getCedula());        	
            return Response.status(Response.Status.OK).entity(request).build();
        } catch (Exception e) {
            e.printStackTrace();
            Error error = new Error();
            error.setCodigo(99);
            error.setMensaje("Error al agregar persona: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }
    }
	
 // Método para agregar un carro a un ticket
    @POST
    @Path("agregarCarro")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response agregarCarro(AgregarCarroRequest request) {
        try {
        	gTickets.agregarCarro(request.getTicketCodigo(), request.getPlaca());        	
            return Response.status(Response.Status.OK).entity(request).build();
        } catch (Exception e) {
            e.printStackTrace();
            Error error = new Error();
            error.setCodigo(99);
            error.setMensaje("Error al agregar persona: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }
    }
    
    
 // Método para agregar un carro a un ticket
    @POST
    @Path("agregarLugar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response agregarLugar(AgregarLugarRequest request) {
        try {
        	gTickets.agregarLugar(request.getTicketCodigo(), request.getCodigoLu());        	
            return Response.status(Response.Status.OK).entity(request).build();
        } catch (Exception e) {
            e.printStackTrace();
            Error error = new Error();
            error.setCodigo(99);
            error.setMensaje("Error al agregar persona: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }
    }
}
