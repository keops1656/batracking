package com.matoosfe.batracking.bean.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import com.matoosfe.batracking.modelo.Entidad;
import com.matoosfe.batracking.modelo.TipoEntidad;
import com.matoosfe.batracking.negocio.admin.EntidadFacade;
import com.matoosfe.batracking.negocio.seguridad.TipoEntidadFacade;
import com.matoosfe.kernel.web.bean.AbstractManagedBean;

/**
 * Clase para administrar el formulario de Entidad
 * 
 * @author MATOOSFE - Ing. Marco Toscano Freire. Msc -martosfre 8 ago. 2017-
 *         23:30:29<br>
 *         <a href="mailto:mtoscano@matoosfe.com?Subject=Soporte Batracking"
 *         target="_top">Soporte</a><br>
 *         <a href="http://www.matoosfe.com">Matoosfe</a>
 */
@ManagedBean
@ViewScoped
public class EntidadBean extends AbstractManagedBean {
	private Entidad entidad;
	private Entidad entidadSel;
	private List<Entidad> listaEntidades;
	private List<SelectItem> listaTipoEntidades;

	private int idTipEnt;

	@EJB
	private EntidadFacade adminEntidad;
	@EJB
	private TipoEntidadFacade adminTipoEntidad;

	public EntidadBean() {
		this.entidad = new Entidad();
		this.listaEntidades = new ArrayList<>();
		this.listaTipoEntidades = new ArrayList<>();
	}

	/**
	 * @return the entidad
	 */
	public Entidad getEntidad() {
		return entidad;
	}

	/**
	 * @param entidad
	 *            the entidad to set
	 */
	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	/**
	 * @return the entidadSel
	 */
	public Entidad getEntidadSel() {
		return entidadSel;
	}

	/**
	 * @param entidadSel
	 *            the entidadSel to set
	 */
	public void setEntidadSel(Entidad entidadSel) {
		this.entidadSel = entidadSel;
	}

	/**
	 * @return the listaEntidades
	 */
	public List<Entidad> getListaEntidades() {
		return listaEntidades;
	}

	/**
	 * @param listaEntidades
	 *            the listaEntidades to set
	 */
	public void setListaEntidades(List<Entidad> listaEntidades) {
		this.listaEntidades = listaEntidades;
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
	 * Método para seleccionar un registro
	 * 
	 * @param ev
	 */
	public void seleccionarRegistro(SelectEvent ev) {
		this.entidadSel = (Entidad) ev.getObject();
	}

	/**
	 * Método para guardar
	 */
	public void guardar() {
		try {
			TipoEntidad tipoEnt = adminTipoEntidad.buscarPorId(idTipEnt);
			entidad.setTipoEntidad(tipoEnt);
			if (entidad.getIdEntidad() > 0) {
				adminEntidad.actualizar(entidad);
			} else {
				adminEntidad.guardar(entidad);
			}
			resetearFormulario();
			cargarEntidades();
			addInfo("Entidad guardado correctamente");
		} catch (Exception e) {
			addError("No se pudo guardar entidad:" + e.getMessage());
		}
	}

	/**
	 * Método para editar un entidad
	 */
	public void editar() {
		try {
			if (entidadSel != null) {
				this.entidad = entidadSel;
				this.idTipEnt = entidad.getTipoEntidad().getIdTipoEntidad();
			} else {
				addError("Se debe seleccionar un entidad");
			}
		} catch (Exception e) {
			addError("No se pudo cargar el tipo entidad:" + e.getMessage());
		}
	}

	/**
	 * Método para eliminar
	 */
	public void eliminar() {
		try {
			if (entidadSel != null) {
				adminEntidad.eliminar(entidadSel, false);
				addInfo("Entidad eliminado correctamente");
				resetearFormulario();
				cargarEntidades();
			} else {
				addError("Se debe seleccionar un entidad");
			}
		} catch (Exception e) {
			addError("No se pudo eliminar el entidad:" + e.getMessage());
		}
	}

	/**
	 * Método para cargar los entidads
	 */
	private void cargarEntidades() {
		try {
			this.listaEntidades = adminEntidad.buscarTodos();
		} catch (Exception e) {
			addError("No se pudo cargar los entidads:" + e.getMessage());
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
	 * Método para resetear un formulario
	 */
	public void resetearFormulario() {
		this.entidad = new Entidad();
		this.entidadSel = null;
		this.idTipEnt = 0;
	}

	/**
	 * Método para inicializar el formulario
	 */
	@PostConstruct
	public void inicializar() {
		cargarEntidades();
		cargarTiposEntidad();
	}

}
