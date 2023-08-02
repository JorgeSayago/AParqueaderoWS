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
	
	public void guardarSitio(Sitio sitio) throws Exception{
		if(daoSitio.read(sitio.getUbicacion())==null) {
			try {
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
		
	public void eliminarSitio(Sitio sitio) throws Exception {
		Sitio sitio1 =daoSitio.read(sitio.getUbicacion());
		if (sitio1 == null) {
			 throw new Exception("El sitio  con el codigo " + sitio.getUbicacion() + " no existe");
		}
		try {
			daoSitio.delete(sitio.getUbicacion());
		} catch (Exception e) {
			 throw new Exception("Error al eliminar: " + e.getMessage());
		}
	}
	public void actualizarSitio(Sitio sitio) throws Exception{
		
		Sitio sitioExistente =daoSitio.read(sitio.getUbicacion());
		if (sitioExistente ==null) {
			throw new Exception("El sitio  con el codigo " + sitio.getUbicacion() + " no existe");
		
		}
		try {
			daoSitio.update(sitio);
		} catch (Exception e) {
			throw new Exception("Error al actualizar: " + e.getMessage());
		}
	}
	
	public List<Sitio>getSitios(){
		return daoSitio.getAll();
	}

}
