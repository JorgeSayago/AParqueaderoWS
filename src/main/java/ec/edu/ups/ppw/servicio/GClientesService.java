package ec.edu.ups.ppw.servicio;

import java.util.List;

import ec.edu.ups.ppw.servicio.Error;
import ec.edu.ups.ppw.dao.PersonaDAO;
import ec.edu.ups.ppw.modelo.Persona;
import ec.edu.ups.ppw.modelo.Carro;
import ec.edu.ups.ppw.negocio.GestionPersona;
import ec.edu.ups.ppw.negocio.GestionCarro;
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
import jakarta.ws.rs.core.Response;

@Path("clientes")
public class GClientesService {
	
	@Inject
	private GestionPersona gClientes;
	
	@Inject
	private GestionCarro gCarros;

	@GET
	@Path("saludo")
	public String saludo() {
		return "Hola mundo";
	}
	
	@GET
	@Path("suma")
	public String suma(@QueryParam("a") int a,
			@QueryParam("b") int b) {
		
		return " R = " + (a + b);
	}
	
	@GET
	@Path("multiplicacion/{a}/{b}")
	public String multiplicacion(@PathParam("a") int a,
			@PathParam("b") int b) {
		
		return " R = " + (a * b);
	}
	
	@GET
	@Path("misdatos")
	@Produces("application/json")
	public Persona misDatos() {
		Persona p = new Persona();
		p.setCedula("0103");
		p.setNombre("Cristian Timbi");
		
		return p;
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
	
	@PUT
	@Path("addCarro")
	@Produces("application/json")
	@Consumes("application/json")
	public Response agregarCarro(String placa , String cedula) {
	    try {
	        gCarros.agregarCarro(placa, cedula);
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
