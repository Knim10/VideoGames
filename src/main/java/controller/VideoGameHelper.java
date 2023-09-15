package controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.VideoGame;

/**
 * @author Kenneth Nimmo - Knimmo
 * CIS175 - Fall 2021
 * Sep 13, 2023
 */
public class VideoGameHelper {
	
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("VideoGames");
	
	public void insertItem(VideoGame vg) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(vg);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<VideoGame> showAllGames() {
		EntityManager em = emfactory.createEntityManager();
		List<VideoGame> allItems = em.createQuery("SELECT i FROM VideoGame i").getResultList();
		return allItems;
	}
	
	public void deleteGame(VideoGame toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<VideoGame> typedQuery = em.createQuery("select vg from VideoGame vg where vg.store = :selectedStore and vg.game = :selectedGame and vg.price = :selectedPrice", VideoGame.class);
		
		typedQuery.setParameter("selectedStore", toDelete.getStore());
		typedQuery.setParameter("selectedGame", toDelete.getGame());
		typedQuery.setParameter("selectedPrice", toDelete.getPrice());
		
		typedQuery.setMaxResults(1);
		
		VideoGame result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	public VideoGame searchForGameById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		VideoGame found = em.find(VideoGame.class, idToEdit);
		em.close();
		return found;
	}
	
	public void updateItem(VideoGame toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<VideoGame> searchForGameByStore(String storeName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<VideoGame> typedQuery = em.createQuery("select vg from VideoGame vg where vg.store = :selectedStore", VideoGame.class);
		typedQuery.setParameter("selectedStore", storeName);
		List<VideoGame> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public List<VideoGame> searchForGameByGameName(String game) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<VideoGame> typedQuery = em.createQuery("select vg from VideoGame vg where vg.game = :selectedGame", VideoGame.class);
		typedQuery.setParameter("selectedGame", game);
		List<VideoGame> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public List<VideoGame> searchForGameByPrice(double price) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<VideoGame> typedQuery = em.createQuery("select vg from VideoGame vg where vg.price = :selectedPrice", VideoGame.class);
		typedQuery.setParameter("selectedPrice", price);
		List<VideoGame> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public void cleanUp() {
		emfactory.close();
	}
	
}
