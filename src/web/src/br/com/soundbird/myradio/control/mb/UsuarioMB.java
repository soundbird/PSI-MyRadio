package br.com.soundbird.myradio.control.mb;

import java.util.Calendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.soundbird.myradio.model.bean.Usuario;
import br.com.soundbird.myradio.model.dao.JPAUtil;
import br.com.soundbird.myradio.model.dao.UsuarioDAO;

@SessionScoped
@ManagedBean
public class UsuarioMB {

	private Usuario usuario = new Usuario();
	private String mensagem = null;
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario){
		this.usuario = usuario;
	}
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
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
	
	public String autenticar() {
		EntityManager em = JPAUtil.getEntityManager();
		UsuarioDAO dao = new UsuarioDAO(em);

		try {
			Usuario usuario = dao.consultarEmail(this.usuario.getEmail());
			if (usuario.getSenha().equals(this.usuario.getSenha())) {
				this.usuario = usuario;
				this.mensagem = null;
				return "usuario?faces-redirect=true";
			}
		} catch (NoResultException e) {
			mensagem = "Erro ao logar!";
		}

		return "";
	}

	public String atualizar() {
		EntityManager em = JPAUtil.getEntityManager();
		UsuarioDAO dao = new UsuarioDAO(em);
		em.getTransaction().begin();
		dao.alterar(usuario);
		em.getTransaction().commit();
		em.close();
		return "usuario?faces-redirect=true";
	}
	
	public String excluir(){
		EntityManager em = JPAUtil.getEntityManager();
		UsuarioDAO dao = new UsuarioDAO(em);
		em.getTransaction().begin();
		dao.excluir(usuario);
		em.getTransaction().commit();
		em.close();
		usuario = new Usuario();
		return "default?faces-redirect=true";
	}
	
}
