package ec.edu.ups.ppw.servicio;

import java.util.List;

import ec.edu.ups.ppw.servicio.Error;
import ec.edu.ups.ppw.modelo.Persona;
import ec.edu.ups.ppw.modelo.Ticket;
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
	
	@GET
	@Path("saludo")
	public String saludo() {
		return "Hola mundo";
	}
	
	//Servicio para guardar cliente
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
	
	//Servicio para obtener todos los clientes
	@GET
	@Path("all")
	@Produces("application/json")
	public Response getClientes() {
		List<Persona> listado = gClientes.getClientes();
		
		return Response.status(Response.Status.OK).entity(listado).build();
	}
	
	//Servicio para eliminar cliente
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
	
	
	//Servicio para actualizar cliente
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
	
}
