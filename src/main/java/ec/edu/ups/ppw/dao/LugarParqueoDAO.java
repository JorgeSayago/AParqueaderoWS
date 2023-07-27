package ec.edu.ups.ppw.dao;

import java.io.Serializable;
import java.util.List;

import ec.edu.ups.ppw.modelo.Carro;
import ec.edu.ups.ppw.modelo.LugarParqueo;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class LugarParqueoDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;
	
	public void insert(LugarParqueo lugarParqueo) {
		em.persist(lugarParqueo);
	}
	
	public void update(LugarParqueo lugarParqueo) {
		em.merge(lugarParqueo);
	}
	
	public LugarParqueo read(int codigo) {
		LugarParqueo l = em.find(LugarParqueo.class, codigo);
		return l;
	}
	
	public void delete(int codigo) {
		LugarParqueo l = em.find(LugarParqueo.class, codigo);
		em.remove(l);
	}
	
	public List<LugarParqueo> getAll(){
		String jpql = "SELECT l FROM LugarParqueo l";
		Query q = em.createQuery(jpql);
		return q.getResultList();
	}
}
