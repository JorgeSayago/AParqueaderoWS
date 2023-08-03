package ec.edu.ups.ppw.servicio;

import jakarta.ws.rs.Path;

import java.util.List;

import ec.edu.ups.ppw.modelo.Carro;
import ec.edu.ups.ppw.modelo.LugarParqueo;
import ec.edu.ups.ppw.modelo.Sitio;
import ec.edu.ups.ppw.negocio.GestionLugarParqueo;
import ec.edu.ups.ppw.negocio.GestionSitio;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("sitios")
public class GSitioService {
	
	@Inject
	private GestionSitio gSitio;
	
	@GET
	@Path("saludo")
	public String saludo() {
		return "Hola mundo";
	}
	
	//Servicio para guardar Sitio
	@POST	
	@Produces("application/json")
	@Consumes("application/json")
	public Response guardarSitio(Sitio sitio) {
		try {
			gSitio.guardarSitio(sitio);
			return Response.status(Response.Status.OK).entity(sitio).build();
		}catch(Exception e){
			e.printStackTrace();
			Error error = new Error();
			error.setCodigo(99);
			error.setMensaje("Error al guardar: " +e.getMessage());
			return Response.status(Response.Status.OK).entity(error).build();
		}
	}
	
	//Servicio para obtener todos los sitios
	@GET
	@Path("all")
	@Produces("application/json")
	public Response getSitios() {
		List<Sitio> listado = gSitio.getSitios();
		return Response.status(Response.Status.OK).entity(listado).build();
	}

}
