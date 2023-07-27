package ec.edu.ups.ppw.dao;

import java.io.Serializable;
import java.util.List;


import ec.edu.ups.ppw.modelo.Persona;
import ec.edu.ups.ppw.modelo.Ticket;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class TicketDAO implements Serializable{
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;
	
	public void insert(Ticket ticket) {
		em.persist(ticket);
	}
	
	public void update(Ticket ticket) {
		em.merge(ticket);
	}
	
	public Ticket read(int codigo) {
		Ticket t = em.find(Ticket.class, codigo);
		return t;
	}
	
	public void delete(int codigo) {
		Ticket t = em.find(Ticket.class, codigo);
		em.remove(t);
	}
	
	public List<Ticket> getAll(){
		String jpql = "SELECT t FROM Ticket t";
		Query q = em.createQuery(jpql);
		return q.getResultList();
	}
}
