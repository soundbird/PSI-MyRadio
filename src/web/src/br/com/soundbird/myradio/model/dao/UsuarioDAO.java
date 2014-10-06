package br.com.soundbird.myradio.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.soundbird.myradio.model.bean.Usuario;

public class UsuarioDAO {

	private EntityManager entityManager;
	
	public UsuarioDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void cadastrar(Usuario usuario) {
		entityManager.persist(usuario);
	}

	public void alterar(Usuario usuario) {
		entityManager.merge(usuario);
	}
	
	public void excluir(Usuario usuario) {
		usuario = entityManager.merge(usuario);
		entityManager.remove(usuario);
	}
	
	public Usuario consultar(Long id) {
		return entityManager.getReference(Usuario.class, id);
	}
	
	public Usuario consultarEmail(String email) throws NoResultException {
		String jpql = "SELECT u FROM Usuario u WHERE u.email = :email";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("email", email);
		return (Usuario) query.getSingleResult();
	}
	
	public List<Usuario> listar() {
		String jpql = "SELECT u FROM Usuario u ORDER BY u.nome";
		Query query = entityManager.createQuery(jpql);
		@SuppressWarnings("unchecked")
		List<Usuario> resultado = query.getResultList();
		return resultado;
	}
}
