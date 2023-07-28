package ec.edu.ups.ppw.negocio;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ec.edu.ups.ppw.dao.CarroDAO;
import ec.edu.ups.ppw.dao.PersonaDAO;
import ec.edu.ups.ppw.modelo.Carro;
import ec.edu.ups.ppw.modelo.Persona;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionCarro {
	
	@Inject
	private CarroDAO daoCarro;

	@Inject 
	private PersonaDAO daoPersona;

	public void guardarCarro(Carro carro) throws Exception{
		if (!this.isPlacaValida(carro.getPlaca())) 
			throw new Exception("Placa incorrecta");
		if(daoCarro.read(carro.getPlaca()) == null) {
			try {
				daoCarro.insert(carro);
			}catch(Exception e) {
				throw new Exception("Error al insertar: " + e.getMessage());
			}
		}else {
			try {
				daoCarro.update(carro);
			}catch(Exception e) {
				throw new Exception("Error al actualizar: " + e.getMessage());
			}
		}
	}
	
	public void agregarCarro(String placa , String cedula) throws Exception{
		
			try {
				Persona p = daoPersona.read(cedula);
				Carro c = daoCarro.read(placa);
				p.addCarros(c);
				daoPersona.update(p);
				
			}catch(Exception e) {
				throw new Exception("Error al insertar: " + e.getMessage());
			}	
	}
	
	
	private boolean isPlacaValida(String placa) {
        String regex = "^[A-Z]{3}-\\d{4}$";
        // Compilar la expresión regular en un objeto Pattern
        Pattern pattern = Pattern.compile(regex);
        // Crear un objeto Matcher para evaluar la placa
        Matcher matcher = pattern.matcher(placa);
        // Verificar si la placa coincide con la expresión regular
        return matcher.matches();
	}
	
	public void eliminarCarro(Carro carro) throws Exception{
		if (!this.isPlacaValida(carro.getPlaca())) {
			throw new Exception("Placa incorrecta");
		}
		Carro carro1 = daoCarro.read(carro.getPlaca());
		if (carro1 == null) {
			throw new Exception("El carro con la placa " + carro.getPlaca() + " no existe");
		}
		try {
			daoCarro.delete(carro.getPlaca());
		} catch (Exception e) {
			throw new Exception("Error al eliminar: " + e.getMessage());
		}

	}
	public void actualizarCarro(Carro carro)throws Exception{
		if (!this.isPlacaValida(carro.getPlaca())) {
			throw new Exception("Placa incorrecta");
		}
		Carro carroExistente = daoCarro.read(carro.getPlaca());
		if (carroExistente == null) {
			throw new Exception("El carro con la placa " + carro.getPlaca() + " no existe");
		}
		try {
			daoCarro.update(carro);
		} catch (Exception e) {
			throw new Exception("Error al actualizar: " + e.getMessage());
		}
	}
	
	public List<Carro>getCarros(){
		return daoCarro.getAll();
	}
}
