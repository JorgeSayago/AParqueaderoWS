package ec.edu.ups.ppw.servicio;

import java.util.List;

import ec.edu.ups.ppw.modelo.Carro;
import ec.edu.ups.ppw.modelo.Persona;
import ec.edu.ups.ppw.negocio.GestionCarro;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("carros")
public class GCarrosService {
	
	@Inject
	private GestionCarro gCarros;
	
	@GET
	@Path("saludo")
	public String saludo() {
		return "Hola mundo";
	}
	
	//Servicio para guardar carro
	@POST	
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
	
	//Servicio para para obtener todos los carros
	@GET
	@Path("all")
	@Produces("application/json")
	public Response getCarros() {
		List<Carro> listado = gCarros.getCarros();
		
		return Response.status(Response.Status.OK).entity(listado).build();
	}
	
	//Servicio para eliminar carros
	@DELETE
	@Produces("application/json")
	@Consumes("application/json")
	public Response eliminarCarro(Carro carro) {
	    try {
	        gCarros.eliminarCarro(carro);
	        return Response.status(Response.Status.OK).build();
	    } catch (Exception e) {
	        e.printStackTrace();
	        Error error = new Error();
	        error.setCodigo(99);
	        error.setMensaje("Error al eliminar: " + e.getMessage());
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
	    }
	}
	
	//Servicio para actualizar carros
	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	public Response actualizarCarro(Carro carro) {
	    try {
	        gCarros.actualizarCarro(carro);
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
