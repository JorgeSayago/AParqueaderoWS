package ec.edu.ups.ppw.dao;

import java.io.Serializable;
import java.util.List;

import ec.edu.ups.ppw.modelo.Carro;
import ec.edu.ups.ppw.modelo.Persona;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class CarroDAO {
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;
	
	public void insert(Carro carro) {
		em.persist(carro);
	}
	
	public void update(Carro carro) {
		em.merge(carro);
	}
	
	public Carro read(String placa) {
		Carro c = em.find(Carro.class, placa);
		return c;
	}
	
	public void delete(String placa) {
		Carro c = em.find(Carro.class, placa);
		em.remove(c);
	}
	
	public List<Carro> getAll(){
		String jpql = "SELECT c FROM Carro c";
		Query q = em.createQuery(jpql);
		return q.getResultList();
	}
	
}
