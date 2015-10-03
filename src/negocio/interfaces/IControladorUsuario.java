package negocio.interfaces;

import javax.ejb.Local;

@Local
public interface IControladorUsuario {
	
	public boolean existeUsuario(String nick);
	
	public boolean credencialesCorrectas(String nick, String pass);
	
	public boolean registrarUsuario(String nombre, String apellido, String nick, String pass);
	
}