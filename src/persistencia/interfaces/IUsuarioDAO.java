package persistencia.interfaces;

import javax.ejb.Local;

import entity.Usuario;


@Local
public interface IUsuarioDAO {

	public boolean altaUsuario(Usuario user);
	public Usuario buscarUsuario(String nick);
	public boolean actualizarUsuario(Usuario user);
	
}