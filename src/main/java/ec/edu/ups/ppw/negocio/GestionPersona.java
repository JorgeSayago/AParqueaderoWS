package ec.edu.ups.ppw.negocio;

import java.util.List;


import ec.edu.ups.ppw.dao.PersonaDAO;
import ec.edu.ups.ppw.modelo.Persona;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionPersona {
	@Inject
	private PersonaDAO daoPersona; 
	
	public void guardarClientes(Persona persona) throws Exception {
		if(!this.isCedulaValida(persona.getCedula()))
			throw new Exception("Cedula incorrecta");
		
		if(daoPersona.read(persona.getCedula()) == null) {
			try {
				daoPersona.insert(persona);
			}catch(Exception e) {
				throw new Exception("Error al insertar: " + e.getMessage());
			}
		}else {
			try {
				daoPersona.update(persona);
			}catch(Exception e) {
				throw new Exception("Error al actualizar: " + e.getMessage());
			}
		}
	}
	
	private boolean isCedulaValida(String cedula) {
		return cedula.length() == 10;
	}
public void guardarClientes(String cedula, String nombre, String direccion) {
		
	}
	
	public void eliminarCliente(Persona persona) throws Exception {
	    if (!this.isCedulaValida(persona.getCedula())) {
	        throw new Exception("Cedula incorrecta");
	    }

	    Persona persona1 = daoPersona.read(persona.getCedula());
	    if (persona1 == null) {
	        throw new Exception("El cliente con la cedula " + persona.getCedula() + " no existe");
	    }

	    try {
	        daoPersona.delete(persona.getCedula());
	    } catch (Exception e) {
	        throw new Exception("Error al eliminar: " + e.getMessage());
	    }
	}
	
	public void actualizarCliente(Persona persona) throws Exception {
	    if (!isCedulaValida(persona.getCedula())) {
	        throw new Exception("Cédula incorrecta");
	    }

	    Persona personaExistente = daoPersona.read(persona.getCedula());
	    if (personaExistente == null) {
	        throw new Exception("El cliente con la cédula " + persona.getCedula() + " no existe");
	    }

	    try {
	        daoPersona.update(persona); 
	    } catch (Exception e) {
	        throw new Exception("Error al actualizar: " + e.getMessage());
	    }
	}

	
	public List<Persona> getClientes(){
		return daoPersona.getAll();
	}
	
}
