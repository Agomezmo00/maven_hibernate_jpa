package com.sanalberto.ad_orm;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class TestSystem {
	
	/* 
	 * El EMF se puede crear también mediante anotaciones, 
	 * el nombre que le pasamos es el de la persistence-unit del persistence.xml
	 */
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("gestorPersistencia");
	
	public static void main(String[] args) {
		
		
		//addCustomer("Antonio", "Gomez", "agomez@iessanalberto.com");
		//addCustomer("Juan", "Lopez", "jlopez@iessanalberto.com");
		//addCustomer("Agustin", "Perez", "aperez@iessanalberto.com");
		getCustomer(1);
		getCustomer(60);
		//getCustomers();
		//changeFirstName(3, "Aran");
		
		getArtist(150);
		//getAllArtists();
		
		ENTITY_MANAGER_FACTORY.close();
		
	}
	
	
	public static void addCustomer(String firstName, String lastName, String email) {
		
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			Customer customer = new Customer();
			customer.setFirstName(firstName);
			customer.setLastName(lastName);
			customer.setEmail(email);
			em.persist(customer);
			et.commit();
		} catch(Exception ex) {
			if(et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		} finally {
			em.close();
		}
		
	}
	
	
	public static void getCustomer(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		
		// Las consultas están hechas con JPQL (no es SQL sino que viene de JPA)
		// https://www.tutorialspoint.com/es/jpa/jpa_jpql.htm
		String query = "SELECT c FROM Customer c WHERE c.customerId = :custID";
		
		TypedQuery<Customer> tq = em.createQuery(query, Customer.class);
		tq.setParameter("custID", id);
		Customer cust = null;
		try {
			cust = tq.getSingleResult();
			System.out.println("----> Customer: "+cust.getFirstName()+" "+cust.getLastName());
		} catch(Exception ex) {
			System.out.println("Error recuperando el customer");
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	
	public static void getCustomers() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

		// Las consultas están hechas con JPQL (no es SQL sino que viene de JPA)
		String strQuery = "SELECT c FROM Customer c WHERE c.customerId IS NOT NULL";
		String strQuerySimple = "FROM Customer";
		
		TypedQuery<Customer> tq = em.createQuery(strQuerySimple, Customer.class);
		List<Customer> custs;
		try {
			custs = tq.getResultList();
			System.out.println("Lista customers, tiene "+ custs.size() +" registros");
			custs.forEach(cust-> System.out.println(cust.getFirstName()+" "+cust.getLastName()));
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			em.close();
		}
		
	}
	
	
	public static void changeFirstName(int id, String firstName) {
		
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		Customer cust = null;
		try {
			et = em.getTransaction();
			et.begin();
			cust = em.find(Customer.class, id);
			cust.setFirstName(firstName);
			
			em.persist(cust);
			et.commit();
		} catch(Exception ex) {
			if(et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		} finally {
			em.close();
		}
		
	}
	
	
	public static void deleteCustomer(int id) {
		
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		Customer cust = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			cust = em.find(Customer.class, id);
			em.remove(cust);
			em.persist(cust);
			et.commit();
		} catch(Exception ex) {
			if(et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		} finally {
			em.close();
		}
		
	}
	
	
	public static void getArtist(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		
		Artist a = em.find(Artist.class, id);
		
		System.out.println(a.getName());
		getArtistAlbums(a);
		
		em.close();
	}
	
	public static void getArtistAlbums(Artist art) {
		
		Set<Album> albums = art.getAlbums();
		
		System.out.println("Albums del artista: ");
		albums.forEach(album -> System.out.println(album.getTitle()));
		
	}
	
	public static void getAllArtists() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		
		String query= "FROM Artist";
		TypedQuery<Artist> tq = em.createQuery(query, Artist.class);
	
		List<Artist>  artists = tq.getResultList();
		System.out.println("Hay actualmente "+artists.size()+" artistas en la base de datos");
		artists.forEach(artist -> System.out.println(artist.getName()));
		
		em.close();
	}
	
	
	
}
