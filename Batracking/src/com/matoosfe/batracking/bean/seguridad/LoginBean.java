package com.matoosfe.batracking.bean.seguridad;

import java.io.IOException;
import java.io.Serializable;
import java.util.TimeZone;

import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.matoosfe.batracking.bean.util.EnumTipoEntidad;
import com.matoosfe.batracking.modelo.Usuario;
import com.matoosfe.batracking.negocio.seguridad.UsuarioFacade;
import com.matoosfe.kernel.web.bean.AbstractManagedBean;

/**
 * Clase para administrar el formulario de Login <br>
 * <b><a href="mailto:mtoscano@matoosfe.com?Subject=SoporteUAcasys">SOPORTE
 * UACASYS</a></b><br>
 * 
 * @author martosfre - Ing. Marco Antonio Toscano Freire <br>
 *         19/06/2017 - 19:03:55<br>
 *         <b><a href="http://www.matoosfe.com">Matoosfe</a>
 */
@ManagedBean
@SessionScoped
public class LoginBean extends AbstractManagedBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	private TimeZone timezone;
	private MenuModel menuModel;

	@EJB
	private UsuarioFacade admUsuario;

	public LoginBean() {
		this.usuario = new Usuario();
		this.menuModel = new DefaultMenuModel();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the timezone
	 */
	public TimeZone getTimezone() {
		timezone = TimeZone.getDefault();
		return timezone;
	}

	/**
	 * @return the menuModel
	 */
	public MenuModel getMenuModel() {
		return menuModel;
	}

	/**
	 * @param menuModel
	 *            the menuModel to set
	 */
	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

	/**
	 * MÃ©todo para validar el usuario
	 */
	public void validarUsuario() {
		try {
			usuario = admUsuario.validarUsuario(usuario.getUsuNombre(), usuario.getUsuPassword());
			cargarMenuRol();
			getExternalContext().redirect(getExternalContext().getRequestContextPath() + "/principal.mat");
		} catch (Exception e) {
			addError("Usuario incorrecto");
		}
	}

	/**
	 * MÃ©todo para cargar el menÃº de acuerdo al rol ingresado
	 */
	private void cargarMenuRol() {
		menuModel.getElements().clear();

		DefaultSubMenu menuSegu = new DefaultSubMenu("Seguimiento", "ui-icon-flag");

		DefaultMenuItem iteTra = new DefaultMenuItem("Tracking Producto");
		iteTra.setUrl("/pages/seguimiento/tracking.mat");

		DefaultMenuItem iteVen = new DefaultMenuItem("Vendedor");
		iteVen.setUrl("/pages/seguimiento/vendedor.mat");

		DefaultSubMenu menuRep = new DefaultSubMenu("Reporte", "ui-icon-print");

		DefaultMenuItem iteRepPro = new DefaultMenuItem("Reporte");
		iteRepPro.setUrl("/pages/reportes/repVidaUtil.mat");
		
		if (usuario.getEntidad().getTipoEntidad().getTipentNombre().equals(EnumTipoEntidad.FABRICA.toString())) {
		
			DefaultMenuItem iteRepCo = new DefaultMenuItem("Códigos");
			iteRepCo.setUrl("/pages/reportes/repCodigos.mat");
			
			DefaultSubMenu menuSeg = new DefaultSubMenu("Seguridad", "ui-icon-key");

			DefaultMenuItem iteUsu = new DefaultMenuItem("Usuario");
			iteUsu.setUrl("/pages/seguridad/usuario.mat");

			DefaultMenuItem itePar = new DefaultMenuItem("Parametrización");
			itePar.setUrl("/pages/admin/parametrizacion.mat");

			menuSeg.addElement(iteUsu);
			menuSeg.addElement(itePar);
			menuModel.addElement(menuSeg);

			DefaultSubMenu menuAdm = new DefaultSubMenu("Administración", "ui-icon-person");

			DefaultMenuItem iteEnt = new DefaultMenuItem("Entidad");
			iteEnt.setUrl("/pages/admin/entidad.mat");

			DefaultMenuItem iteRelEnt = new DefaultMenuItem("Relación Entidad");
			iteRelEnt.setUrl("/pages/seguridad/relacionEntidad.mat");

			DefaultMenuItem itePro = new DefaultMenuItem("Producto");
			itePro.setUrl("/pages/admin/producto.mat");

			DefaultMenuItem itePal = new DefaultMenuItem("Pallet");
			itePal.setUrl("/pages/admin/pallet.mat");
			
			DefaultMenuItem iteEspecificacion = new DefaultMenuItem("Especificación");
			iteEspecificacion.setUrl("/pages/admin/especificacion.mat");
			
			DefaultMenuItem iteModeloAuto = new DefaultMenuItem("Modelo");
			iteModeloAuto.setUrl("/pages/admin/modelo.mat");
			
			DefaultMenuItem iteMarcaAuto = new DefaultMenuItem("Marca");
			iteMarcaAuto.setUrl("/pages/admin/pallet.mat");

			menuAdm.addElement(iteEnt);
			menuAdm.addElement(iteRelEnt);
			menuAdm.addElement(itePro);
			menuAdm.addElement(itePal);
			menuAdm.addElement(iteEspecificacion);
			menuAdm.addElement(iteModeloAuto);
			menuAdm.addElement(iteMarcaAuto);
			
			menuModel.addElement(menuAdm);

			menuSegu.addElement(iteTra);
			menuModel.addElement(menuSegu);

			menuRep.addElement(iteRepPro);
			menuRep.addElement(iteRepCo);
			menuModel.addElement(menuRep);			
						

		} else if (usuario.getEntidad().getTipoEntidad().getTipentNombre()
				.equals(EnumTipoEntidad.VENDEDOR.toString())) {
			menuSegu.addElement(iteTra);
			menuSegu.addElement(iteVen);
			menuModel.addElement(menuSegu);

			menuRep.addElement(iteRepPro);
			menuModel.addElement(menuRep);
			
		
		} else {
			menuSegu.addElement(iteTra);
			menuModel.addElement(menuSegu);

			menuRep.addElement(iteRepPro);
			menuModel.addElement(menuRep);
					
		}
	}

	/**
	 * MÃ©todo para cerrar cesiÃ³n general
	 */
	public void cerrarCesion() {
		ExternalContext ec = getExternalContext();
		ec.getSessionMap().remove("loginBean");
		try {
			ec.redirect(ec.getRequestContextPath() + "/inicio.mat");
		} catch (IOException e) {
			throw new FacesException(e);
		}
	}

	/**
	 * MÃ©todo para regresar al inicio
	 */
	public void regresarInicio() {
		try {
			getExternalContext().redirect(getExternalContext().getRequestContextPath() + "/login.mat");
		} catch (IOException e) {
			throw new FacesException(e);
		}
	}

}
