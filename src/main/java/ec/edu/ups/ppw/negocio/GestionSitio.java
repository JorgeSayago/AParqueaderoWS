package ec.edu.ups.ppw.negocio;

import jakarta.ejb.Stateless;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import ec.edu.ups.ppw.dao.SitioDAO;
import ec.edu.ups.ppw.modelo.Sitio;

@Stateless
public class GestionSitio {
	
	@Inject
	private SitioDAO daoSitio;
	
	//Metodo para guardar sitio en la base de datos
	public void guardarSitio(Sitio sitio) throws Exception{
		if(daoSitio.read(sitio.getUbicacion())==null) {
			try {
				//Inserta el sitio en la base de datos
				daoSitio.insert(sitio);
			}catch(Exception e) {
				throw new Exception("Error al insertar: "+e.getMessage());
			}
			}else {
				try {
					daoSitio.update(sitio);
				} catch (Exception e) {
					throw new Exception("Error al actualizar: " + e.getMessage());
				}
			}
		}
		
	//Metodo para eliminar un sitio de la base de datos
	public void eliminarSitio(Sitio sitio) throws Exception {
		Sitio sitio1 =daoSitio.read(sitio.getUbicacion());
		if (sitio1 == null) {
			 throw new Exception("El sitio  con el codigo " + sitio.getUbicacion() + " no existe");
		}
		try {
			//Con el uso del dao eliminamos el sitio
			daoSitio.delete(sitio.getUbicacion());
		} catch (Exception e) {
			 throw new Exception("Error al eliminar: " + e.getMessage());
		}
	}
	
	//Metodo para actualizar el sitio a la base de datos
	public void actualizarSitio(Sitio sitio) throws Exception{
		
		Sitio sitioExistente =daoSitio.read(sitio.getUbicacion());
		if (sitioExistente ==null) {
			throw new Exception("El sitio  con el codigo " + sitio.getUbicacion() + " no existe");
		
		}
		try {
			//Con uso del dao acualizamos un sitio en la base de datos
			daoSitio.update(sitio);
		} catch (Exception e) {
			throw new Exception("Error al actualizar: " + e.getMessage());
		}
	}
	
	//Obtenemos los sitios que se encuentran en la base de datos con uso del dao
	public List<Sitio>getSitios(){
		return daoSitio.getAll();
	}

}
