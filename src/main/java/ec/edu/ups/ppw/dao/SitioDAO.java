package ec.edu.ups.ppw.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.io.Serializable;
import java.util.List;

import ec.edu.ups.ppw.modelo.Sitio;

@Stateless
public class SitioDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;
	
	
	//Metodo para insertar un sitio a la base de datos
	public void insert(Sitio sitio) {
		em.persist(sitio);
	}
	
	//Metodo para actualizar un sitio 
	public void update(Sitio sitio) {
		em.merge(sitio);
	}
	
	//Metodo para buscar un sitio a partir de la ubicacion o codigo
	public Sitio read(String ubicacion) {
		Sitio s = em.find(Sitio.class, ubicacion);
		return s;
	}
	
	//Metodo para eliminar un Sitio a partir de la ubicacion o codigo
	public void delete(String ubicacion) {
		Sitio s = em.find(Sitio.class, ubicacion);
		em.remove(s);
	}
	
	//Metodo para obtener una lista de sitios de la base de datos
	public List<Sitio> getAll(){
		String jpql = "SELECT s FROM Sitio s";
		Query q = em.createQuery(jpql);
		return q.getResultList();
	}

}
