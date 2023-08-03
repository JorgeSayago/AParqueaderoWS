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

	//Metodo para guardar carro con uso del dao
	public void guardarCarro(Carro carro) throws Exception{
		//Validamos la placa del vehiculo
		if (!this.isPlacaValida(carro.getPlaca())) 
			throw new Exception("Placa incorrecta");
		if(daoCarro.read(carro.getPlaca()) == null) {
			try {
				//Insertamos carro
				daoCarro.insert(carro);
				carro.setEstado(true);
				daoCarro.update(carro);
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
	
	//Metodo para validar la placa del carro
	private boolean isPlacaValida(String placa) {
        String regex = "^[A-Z]{3}-\\d{4}$";
        // Compilar la expresión regular en un objeto Pattern
        Pattern pattern = Pattern.compile(regex);
        // Crear un objeto Matcher para evaluar la placa
        Matcher matcher = pattern.matcher(placa);
        // Verificar si la placa coincide con la expresión regular
        return matcher.matches();
	}
	
	//Metodo para eliminar carro 
	public void eliminarCarro(Carro carro) throws Exception{
		if (!this.isPlacaValida(carro.getPlaca())) {
			throw new Exception("Placa incorrecta");
		}
		//busca el carro con el uso del dao
		Carro carro1 = daoCarro.read(carro.getPlaca());
		if (carro1 == null) {
			throw new Exception("El carro con la placa " + carro.getPlaca() + " no existe");
		}
		try {
			//Con el carro ya obtenido eliminamos con el dao
			daoCarro.delete(carro.getPlaca());
		} catch (Exception e) {
			throw new Exception("Error al eliminar: " + e.getMessage());
		}

	}
	
	//Metodo para actualizar carro
	public void actualizarCarro(Carro carro)throws Exception{
		if (!this.isPlacaValida(carro.getPlaca())) {
			throw new Exception("Placa incorrecta");
		}
		//Buscamos el carro a actualizar con uso del dao
		Carro carroExistente = daoCarro.read(carro.getPlaca());
		if (carroExistente == null) {
			throw new Exception("El carro con la placa " + carro.getPlaca() + " no existe");
		}
		try {
			//Con el carro ya encontrado con el dao lo actualizamos
			daoCarro.update(carro);
		} catch (Exception e) {
			throw new Exception("Error al actualizar: " + e.getMessage());
		}
	}
	
	//Metodo para obtener los carros guardados en la base de datos con uso del dao
	public List<Carro>getCarros(){
		return daoCarro.getAll();
	}
}
