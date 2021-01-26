package com.sanalberto.ad_orm;

import java.math.BigDecimal;
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
		//getCustomer(1);
		//getCustomer(60);
		//getCustomers();
		//changeFirstName(3, "Aran");
		
		//getArtist(150);
		//getAllArtists();
		
		
		//getAlbumTracks(240);
		getPlaylistTracks(3);
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
	
	public static void changeCustomerFirstName(int id, String firstName) {
		
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
	
	public static void deleteAlbum(int id) {
		
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		Album alb = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			alb = em.find(Album.class, id);
			em.remove(alb);
			em.persist(alb);
			et.commit();
			System.out.println("Se ha eliminado el álbum");
		} catch(Exception ex) {
			if(et != null) {
				System.out.println("Problemas al eliminar el álbum");
				et.rollback();
			}
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	
	public static Artist getArtist(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		
		Artist a = em.find(Artist.class, id);
		
		System.out.println(a.getName());
		//getArtistAlbums(a);
		
		em.close();
		return a;
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

	public static void addArtist(String artist) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		et = em.getTransaction();
		et.begin();
		Artist art = new Artist();
		art.setName(artist);
		em.persist(art);
		
		
	}
	
	public static void addAlbum(Artist art, String title) {
		
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			Album alb1 = new Album();
			alb1.setArtist(art);
			alb1.setTitle(title);
			em.persist(alb1);
			et.commit();
			System.out.println("El álbum: "+title +" se ha añadido a la BD");
		} catch(Exception ex) {
			et.rollback();
			ex.printStackTrace();
		} finally {
			em.close();
		}
		
		
		
	}
	
	public static Album getAlbum(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		Album alb = em.find(Album.class, id);
		em.close();
		return alb;
	}
	
	public static void addTrackToAlbum(Album alb, String title, int mediatype, int genre, String composer) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		try {
			MediaType med = em.find(MediaType.class, mediatype);
			Genre gen = em.find(Genre.class, genre);
				
			et = em.getTransaction();
			et.begin();
			
			Track t = new Track();
			t.setName(title);
			t.setAlbum(alb);
			t.setGenre(gen);
			t.setMediaType(med);
			
			int duracion = (int) (Math.random() * (600000 - 60000)+60000);
			t.setMilliseconds(duracion);
			
			t.setComposer(composer);
			BigDecimal bd = new BigDecimal(0.99);
			t.setUnitPrice(bd);
			
			int bytes = (int) (Math.random()* (10000 - 5000)+5000);
			t.setBytes(bytes);
			
			em.persist(t);
			et.commit();
			System.out.println(title +" se ha insertado en la BD");
		} catch (Exception ex) {
			et.rollback();
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public static void getAlbumTracks(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		Album a = em.find(Album.class, id);
		
		Set<Track> tracks = a.getTracks();
		System.out.println(a.getTitle());
		System.out.println("Hay "+ tracks.size() +" Canciones del álbum: ");
		tracks.forEach(track -> System.out.println(track.getName()));
		em.close();	
	}
	
	public static void getPlaylistTracks(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		Playlist p = em.find(Playlist.class, id);
		
		Set<Track> tracks = p.getTracks();
		
		System.out.println("Hay "+ tracks.size() +" canciones de la playlist: ");
		tracks.forEach(track -> System.out.println(track.getName()));
	}



		
	
	
}
