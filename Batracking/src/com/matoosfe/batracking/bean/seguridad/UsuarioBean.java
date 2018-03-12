package com.matoosfe.batracking.bean.seguridad;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import com.matoosfe.batracking.modelo.Entidad;
import com.matoosfe.batracking.modelo.TipoEntidad;
import com.matoosfe.batracking.modelo.Usuario;
import com.matoosfe.batracking.negocio.admin.EntidadFacade;
import com.matoosfe.batracking.negocio.seguridad.TipoEntidadFacade;
import com.matoosfe.batracking.negocio.seguridad.UsuarioFacade;
import com.matoosfe.kernel.web.bean.AbstractManagedBean;

/**
 * Clase para administrar el formulario de usuarios
 * 
 * @author MATOOSFE - Ing. Marco Toscano Freire. Msc -martosfre 8 ago. 2017-
 *         23:41:32<br>
 *         <a href="mailto:mtoscano@matoosfe.com?Subject=Soporte Batracking"
 *         target="_top">Soporte</a><br>
 *         <a href="http://www.matoosfe.com">Matoosfe</a>
 */
@ManagedBean
@ViewScoped
public class UsuarioBean extends AbstractManagedBean {
	private Usuario usuario;
	private Usuario usuarioSel;
	private List<Usuario> listaUsuarios;
	private int idTipEnt;
	private List<SelectItem> listaTipoEntidades;
	private int idEnt;
	private List<SelectItem> listaEntidades;

	@EJB
	private UsuarioFacade adminUsuario;
	@EJB
	private EntidadFacade adminEntidad;
	@EJB
	private TipoEntidadFacade adminTipoEntidad;

	public UsuarioBean() {
		this.usuario = new Usuario();
		this.listaUsuarios = new ArrayList<>();
		this.listaTipoEntidades = new ArrayList<>();
		this.listaEntidades = new ArrayList<>();
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the usuarioSel
	 */
	public Usuario getUsuarioSel() {
		return usuarioSel;
	}

	/**
	 * @param usuarioSel
	 *            the usuarioSel to set
	 */
	public void setUsuarioSel(Usuario usuarioSel) {
		this.usuarioSel = usuarioSel;
	}

	/**
	 * @return the listaUsuarios
	 */
	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	/**
	 * @param listaUsuarios
	 *            the listaUsuarios to set
	 */
	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	/**
	 * @return the idTipEnt
	 */
	public int getIdTipEnt() {
		return idTipEnt;
	}

	/**
	 * @param idTipEnt
	 *            the idTipEnt to set
	 */
	public void setIdTipEnt(int idTipEnt) {
		this.idTipEnt = idTipEnt;
	}

	/**
	 * @return the listaTipoEntidades
	 */
	public List<SelectItem> getListaTipoEntidades() {
		return listaTipoEntidades;
	}

	/**
	 * @param listaTipoEntidades
	 *            the listaTipoEntidades to set
	 */
	public void setListaTipoEntidades(List<SelectItem> listaTipoEntidades) {
		this.listaTipoEntidades = listaTipoEntidades;
	}

	/**
	 * @return the idEnt
	 */
	public int getIdEnt() {
		return idEnt;
	}

	/**
	 * @param idEnt
	 *            the idEnt to set
	 */
	public void setIdEnt(int idEnt) {
		this.idEnt = idEnt;
	}

	/**
	 * @return the listaEntidades
	 */
	public List<SelectItem> getListaEntidades() {
		return listaEntidades;
	}

	/**
	 * @param listaEntidades
	 *            the listaEntidades to set
	 */
	public void setListaEntidades(List<SelectItem> listaEntidades) {
		this.listaEntidades = listaEntidades;
	}

	public void selecccionarRegistro(SelectEvent ev) {
		this.usuarioSel = (Usuario) ev.getObject();
	}

	/**
	 * Método para guardar
	 */
	public void guardar() {
		try {
			Entidad entidad = adminEntidad.buscarPorId(idEnt);
			usuario.setEntidad(entidad);
			usuario.setUsuActivo(1);
			if (usuario.getIdUsuario() > 0) {
				usuario.setUsuFechaModificacion(new Date());
				adminUsuario.actualizar(usuario);
			} else {
				usuario.setUsuFechaCreacion(new Date());
				adminUsuario.guardar(usuario);
			}
			resetearFormulario();
			cargarUsuarios();
			addInfo("Usuario guardado correctamente!!");
		} catch (Exception e) {
			addError("No se pudo guardar el usuario:" + e.getMessage());
		}
	}

	/**
	 * Método para editar un usuario
	 */
	public void editar() {
		try {
			if (usuarioSel != null) {
				this.usuario = usuarioSel;
				this.idTipEnt = usuario.getEntidad().getTipoEntidad().getIdTipoEntidad();
				cargarEntidadesPadre();
				this.idEnt = usuario.getEntidad().getIdEntidad();
			} else {
				addError("Se debe seleccionar un usuario!!");
			}
		} catch (Exception e) {
			addError("No se pudo editar un usuario:" + e.getMessage());
		}
	}

	/**
	 * Método para eliminar un usuario
	 */
	public void eliminar() {
		try {
			if (usuarioSel != null) {
				usuarioSel.setUsuActivo(0);
				adminUsuario.eliminar(usuarioSel, true);
				addInfo("Usuario eliminado correctamente");
				cargarUsuarios();
				resetearFormulario();
			} else {
				addError("Se debe seleccionar un usuario!!");
			}
		} catch (Exception e) {
			addError("No se pudo eliminar el usuario:" + e.getMessage());
		}
	}

	/**
	 * Método para cargar los usuarios
	 */
	private void cargarUsuarios() {
		try {
			this.listaUsuarios = adminUsuario.buscarTodos("usuActivo", 1);
		} catch (Exception e) {
			addError("No se pudo cargar los usuarios");
		}

	}

	/**
	 * Método para cargar los tipos de entidad
	 */
	private void cargarTiposEntidad() {
		try {
			this.listaTipoEntidades.clear();
			for (TipoEntidad tipUsu : adminTipoEntidad.buscarTodos()) {
				this.listaTipoEntidades.add(new SelectItem(tipUsu.getIdTipoEntidad(), tipUsu.getTipentNombre()));
			}
		} catch (Exception e) {
			addError("No se pudo cargar los tipos de usuarios:" + e.getMessage());
		}

	}

	/**
	 * Método para cargar las entidades padre
	 */
	public void cargarEntidadesPadre() {
		this.listaEntidades.clear();
		TipoEntidad tipoEnt = adminTipoEntidad.buscarPorId(idTipEnt);
		for (Entidad ent : adminEntidad.buscarEntidadPorTipo(tipoEnt.getTipentNombre().toUpperCase())) {
			this.listaEntidades.add(new SelectItem(ent.getIdEntidad(), ent.getEntNombre()));
		}
	}

	/**
	 * Método para resetear el formulario
	 */
	public void resetearFormulario() {
		this.usuario = new Usuario();
		this.usuarioSel = null;
	}

	/**
	 * Método para inicializar el formulario
	 */
	@PostConstruct
	public void inicializar() {
		cargarUsuarios();
		cargarTiposEntidad();
	}

}
