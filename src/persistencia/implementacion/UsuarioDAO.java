package persistencia.implementacion;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;



import entity.Usuario;
import persistencia.interfaces.IUsuarioDAO;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UsuarioDAO implements IUsuarioDAO {
	
	@javax.persistence.PersistenceContext(unitName="Prueba9")
	private javax.persistence.EntityManager em;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean altaUsuario(Usuario usuario) {
		boolean seRegistro = false;
		try {
			//Persiste un usuario a la base de datos
			em.persist(usuario);		
			seRegistro = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return seRegistro;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario buscarUsuario(String nick) {
		Usuario usuario = null;
		try {
			usuario = (Usuario)em.createNamedQuery("Usuario.usuarioPorNick", Usuario.class)
			.setParameter("nick", nick)
			.getSingleResult();
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return usuario;
	}
	
	//final Query query = em.createNamedQuery("Usuario.usuarioPorNick");
	//List<Usuario> resultado = query.setParameter("nick", nick).getResultList();
	//usuario = em.find(Usuario.class, nick); //Esto solo se usa cuando se busca por el id

	@Override
	public boolean actualizarUsuario(Usuario usuario) {
		boolean seActualizo = false;
		try {
			//Actualiza el en la base de datos.
			em.merge(usuario);
			seActualizo = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return seActualizo;
	}

}
