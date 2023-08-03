package ec.edu.ups.ppw.dao;

import java.io.Serializable;
import java.util.List;

import ec.edu.ups.ppw.modelo.Carro;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class CarroDAO implements Serializable {
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;
	
	
	//Metodo para insertar carro a la base de datos
	public void insert(Carro carro) {
		em.persist(carro);
	}
	
	//Metodo para actualizar carro 
	public void update(Carro carro) {
		em.merge(carro);
	}
	
	//Metodo para obtener un carro a partir de la placa
	public Carro read(String placa) {
		Carro c = em.find(Carro.class, placa);
		return c;
	}
	
	//Metodo para eliminar un carro a partir de la placa
	public void delete(String placa) {
		Carro c = em.find(Carro.class, placa);
		em.remove(c);
	}
	
	//Metodo para obtener todos los carros disponibles en la base de datos
	public List<Carro> getAll(){
		String jpql = "SELECT c FROM Carro c";
		Query q = em.createQuery(jpql);
		return q.getResultList();
	}
	
}
