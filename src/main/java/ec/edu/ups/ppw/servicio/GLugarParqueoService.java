package ec.edu.ups.ppw.servicio;

import java.util.List;

import ec.edu.ups.ppw.modelo.Carro;
import ec.edu.ups.ppw.modelo.LugarParqueo;
import ec.edu.ups.ppw.negocio.GestionLugarParqueo;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("lugares")
public class GLugarParqueoService {
	
	@Inject
	private GestionLugarParqueo gLugarParqueo;
	
	@GET
	@Path("saludo")
	public String saludo() {
		return "Hola mundo";
	}
	
	@POST	
	@Produces("application/json")
	@Consumes("application/json")
	public Response guardarLugar(LugarParqueo lugarParqueo) {
		try {
			gLugarParqueo.guardarLugar(lugarParqueo);
			return Response.status(Response.Status.OK).entity(lugarParqueo).build();
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
	public Response getLugares() {
		List<LugarParqueo> listado = gLugarParqueo.getParqueos();
		return Response.status(Response.Status.OK).entity(listado).build();
	}
	
	
	@DELETE
	@Produces("application/json")
	@Consumes("application/json")
	public Response eliminarLugar(LugarParqueo lugarParqueo) {
	    try {
	        gLugarParqueo.eliminarLugar(lugarParqueo);
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
	public Response actualizarLugar(LugarParqueo lugarParqueo) {
	    try {
	        gLugarParqueo.actualizarLugar(lugarParqueo);
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
