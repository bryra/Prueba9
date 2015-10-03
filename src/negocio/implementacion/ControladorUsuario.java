package negocio.implementacion;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import entity.Usuario;
import negocio.interfaces.IControladorUsuario;
import persistencia.interfaces.IUsuarioDAO;

/**
 * Session Bean implementation class ControladorUsuarioLocal
 */
@Stateless
@LocalBean
public class ControladorUsuario implements IControladorUsuario {

	@EJB
	private IUsuarioDAO usuarioDAO;
	
    /**
     * Default constructor. 
     */
    public ControladorUsuario() {
        
    }
    
	@Override
	public boolean existeUsuario(String nick) {
		boolean existe = false;
		try {
			//Si nos devuelve un usuario, existe es igual a true 
			existe = (this.usuarioDAO.buscarUsuario(nick) != null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return existe;
	}

	@Override
	public boolean credencialesCorrectas(String nick, String pass) {
		boolean correcto = false;
		try {
			Usuario usuario = usuarioDAO.buscarUsuario(nick);
			correcto = (usuario != null && usuario.getPass().equals(pass));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return correcto;
	}

	@Override
	public boolean registrarUsuario(String nombre, String apellido, String nick, String pass) { 
		boolean registrado = false;
		try {
			Usuario nuevo = new Usuario(nombre, apellido, nick, pass);
			usuarioDAO.altaUsuario(nuevo);
			registrado = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return registrado;
	}

}