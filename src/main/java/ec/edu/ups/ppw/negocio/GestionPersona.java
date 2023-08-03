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
	
	//Metodo que recive una persona y lo guarda en la base de datos haciedo uso del dao de la persona
	public void guardarClientes(Persona persona) throws Exception {
		//Validamos la cedulas
		if(!this.isCedulaValida(persona.getCedula()))
			throw new Exception("Cedula incorrecta");
		
		if(daoPersona.read(persona.getCedula()) == null) {
			try {
				//Insertamos la cedula en la base de datos
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
	
	//metodo para validar cedula
	private boolean isCedulaValida(String cedula) {
		return cedula.length() == 10;
	}
public void guardarClientes(String cedula, String nombre, String direccion) {
		
	}
	
	//Metodo que recibe una cedula y que lo envia por el dao para que se elimine
	public void eliminarCliente(Persona persona) throws Exception {
	    //Valida la cedula
		if (!this.isCedulaValida(persona.getCedula())) {
	        throw new Exception("Cedula incorrecta");
	    }
	    
	    //Obtiene la persona haciendo del dao.
	    Persona persona1 = daoPersona.read(persona.getCedula());
	    if (persona1 == null) {
	        throw new Exception("El cliente con la cedula " + persona.getCedula() + " no existe");
	    }

	    try {
	    	//Elimina la persona con el dao
	        daoPersona.delete(persona.getCedula());
	    } catch (Exception e) {
	        throw new Exception("Error al eliminar: " + e.getMessage());
	    }
	}
	
	//Metodo para actualizar la persona
	public void actualizarCliente(Persona persona) throws Exception {
	    if (!isCedulaValida(persona.getCedula())) {
	        throw new Exception("Cédula incorrecta");
	    }

	    //Obtiene la persona con el dao
	    Persona personaExistente = daoPersona.read(persona.getCedula());
	    if (personaExistente == null) {
	        throw new Exception("El cliente con la cédula " + persona.getCedula() + " no existe");
	    }

	    try {
	    	//Actualiza la persona con el DAO
	        daoPersona.update(persona); 
	    } catch (Exception e) {
	        throw new Exception("Error al actualizar: " + e.getMessage());
	    }
	}

	
	//Metodo para obtener toda la lista de clietes con uso del dao
	public List<Persona> getClientes(){
		return daoPersona.getAll();
	}
	
}
