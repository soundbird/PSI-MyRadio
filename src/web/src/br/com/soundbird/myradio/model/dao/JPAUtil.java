package br.com.soundbird.myradio.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("myRadio");
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
