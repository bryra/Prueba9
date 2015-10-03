package bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class MainBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String url = "/home";

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	private void redireccionar(String pagina){
		try {
			FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
			context.getExternalContext().redirect(pagina + ".xhtml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void iniciarSesion(){
		redireccionar("login");
	}
	
	public void registrarse(){
		redireccionar("registro");
	}
	
	public void cerrarSesion(){
		FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		session.setAttribute("usuarioBean", null);
		redireccionar("index");
	}
	
	public void inicio(){
		redireccionar("index");
	}
}
