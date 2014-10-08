package br.com.soundbird.myradio.control.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.soundbird.myradio.model.bean.Usuario;
import br.com.soundbird.myradio.model.dao.JPAUtil;
import br.com.soundbird.myradio.model.dao.UsuarioDAO;

@RequestScoped
@ManagedBean(name = "loginMB")
public class LoginMB {
	
	@ManagedProperty(value = "#{usuarioMB}")
	private UsuarioMB usuarioMB = null;

	private Usuario usuario = new Usuario();
	private String mensagem = null;
	
	public void setUsuarioMB(UsuarioMB usuarioMB) {
		this.usuarioMB = usuarioMB;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public String autenticar() {
		EntityManager em = JPAUtil.getEntityManager();
		UsuarioDAO dao = new UsuarioDAO(em);

		try {
			Usuario usuario = dao.consultarEmail(this.usuario.getEmail());
			if (usuario.autenticar(this.usuario)) {
				usuarioMB.setUsuario(usuario);
				return "usuario?faces-redirect=true";
			}
		} catch (NoResultException e) {
			this.mensagem = "Erro ao logar!";
		}

		return "";
	}
}
