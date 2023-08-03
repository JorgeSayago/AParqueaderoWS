package ec.edu.ups.ppw.dao;


import java.io.Serializable;
import java.util.List;


import ec.edu.ups.ppw.modelo.Persona;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class PersonaDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;
	
	//Metodo para insertar persona
	public void insert(Persona persona) {
		em.persist(persona);
	}
	
	//Metodo para actualizar persona
	public void update(Persona persona) {
		em.merge(persona);
	}
	
	//Metodo para obtener una persona con la cedula
	public Persona read( String cedula) {
		Persona p = em.find(Persona.class, cedula);
		return p;
	}
	
	//Metodo para eliminar una persona por su cedula
	public void delete(String cedula) {
		Persona p = em.find(Persona.class, cedula);
		em.remove(p);
	}
	
	//Metodo para obtener todos las personas de la base de datos
	public List<Persona> getAll(){
		String jpql = "SELECT p FROM Persona p";
		Query q = em.createQuery(jpql);
		return q.getResultList();
	}
}
