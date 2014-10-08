package br.com.soundbird.myradio.control.mb;

import java.util.Calendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import br.com.soundbird.myradio.model.bean.Usuario;
import br.com.soundbird.myradio.model.dao.JPAUtil;
import br.com.soundbird.myradio.model.dao.UsuarioDAO;

@SessionScoped
@ManagedBean(name = "usuarioMB")
public class UsuarioMB {

	private Usuario usuario = new Usuario();
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario){
		this.usuario = usuario;
	}

	public String salvar() {
		EntityManager em = JPAUtil.getEntityManager();
		UsuarioDAO dao = new UsuarioDAO(em);
		em.getTransaction().begin();
		usuario.setDataCadastro(Calendar.getInstance());
		dao.cadastrar(usuario);
		em.getTransaction().commit();
		em.close();
		usuario = new Usuario();
		return "default?faces-redirect=true";
	}
	
	public String cadastrar() {
		usuario = new Usuario();
		return "CriaUsuario?faces-redirect=true";
	}

	public String atualizar() {
		EntityManager em = JPAUtil.getEntityManager();
		UsuarioDAO dao = new UsuarioDAO(em);
		em.getTransaction().begin();
		dao.alterar(usuario);
		em.getTransaction().commit();
		em.close();
		return "/usuario?faces-redirect=true";
	}
	
	public String excluir(){
		EntityManager em = JPAUtil.getEntityManager();
		UsuarioDAO dao = new UsuarioDAO(em);
		em.getTransaction().begin();
		dao.excluir(usuario);
		em.getTransaction().commit();
		em.close();
		usuario = new Usuario();
		return "/default?faces-redirect=true";
	}
	
}
