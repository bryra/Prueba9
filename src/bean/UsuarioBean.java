package bean;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import negocio.interfaces.IControladorUsuario;

@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;

	private String apellido;

	private Date fecha;

	private String mail;

	private String nick;

	private String nombre;

	private String password;
	
	private String mensaje;
	
	@EJB
	private IControladorUsuario controlUsuario;
	
	public UsuarioBean(){
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public void iniciarSesion(){
		if(controlUsuario.credencialesCorrectas(this.nick, this.password)){
			this.mensaje = null;
			redireccionar("home");
		}
		else
			this.setMensaje("El usuario y/o contraseña son incorrectos!");
	}
	
	@PostConstruct
	public void init() {
		FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		UsuarioBean usuarioBean = (UsuarioBean) session.getAttribute("usuarioBean");
		this.asignar(usuarioBean);
	}
	
	public void registrarUsuario(){
		if(!controlUsuario.existeUsuario(this.nick)){
			if(controlUsuario.registrarUsuario(nombre, apellido, nick, password)){
				this.mensaje = null;
				redireccionar("home");
			}
			else
				this.mensaje = "Error al crear el usuario, intente nuevamente!";
		}
		else{
			this.mensaje = "El usuario ya existe, debe ingresar otro Nick.";
		}
	}
	
	private void redireccionar(String pagina){
		try {
			FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
			context.getExternalContext().redirect(pagina + ".xhtml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void asignar(UsuarioBean usuarioBean){
		if (usuarioBean != null) {
			this.nombre = usuarioBean.nombre;
			this.apellido = usuarioBean.apellido;
			this.nick = usuarioBean.nick;
			this.password = usuarioBean.password;
		}
	}
}
