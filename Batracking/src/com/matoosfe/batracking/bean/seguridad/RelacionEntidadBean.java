package com.matoosfe.batracking.bean.seguridad;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.matoosfe.batracking.modelo.Entidad;
import com.matoosfe.batracking.modelo.RelacionEntidad;
import com.matoosfe.batracking.modelo.TipoEntidad;
import com.matoosfe.batracking.negocio.admin.EntidadFacade;
import com.matoosfe.batracking.negocio.seguridad.RelacionEntidadFacade;
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
public class RelacionEntidadBean extends AbstractManagedBean {
	private List<SelectItem> listaEntidades;
	private List<SelectItem> listaTipoEntidades;
	private List<SelectItem> listaTipoEntidadesPadre;
	private List<String> entidadesPadreSel;

	private int idTipEnt;
	private int idEnt;
	private int idTipEntPad;
	private List<SelectItem> listaEntidadesPadre;
	private String labelEntidad;
	private String labelEntidadPadre;
	private TreeNode root;

	@EJB
	private RelacionEntidadFacade adminRelacionEntidad;
	@EJB
	private EntidadFacade adminEntidad;
	@EJB
	private TipoEntidadFacade adminTipoEntidad;

	public RelacionEntidadBean() {
		this.listaEntidades = new ArrayList<>();
		this.listaTipoEntidades = new ArrayList<>();
		this.listaTipoEntidadesPadre = new ArrayList<>();
		this.listaEntidadesPadre = new ArrayList<>();
		this.labelEntidad = "N/A";
		this.labelEntidadPadre = "N/A:";
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
	 * @return the listaTipoEntidadesPadre
	 */
	public List<SelectItem> getListaTipoEntidadesPadre() {
		return listaTipoEntidadesPadre;
	}

	/**
	 * @param listaTipoEntidadesPadre
	 *            the listaTipoEntidadesPadre to set
	 */
	public void setListaTipoEntidadesPadre(List<SelectItem> listaTipoEntidadesPadre) {
		this.listaTipoEntidadesPadre = listaTipoEntidadesPadre;
	}

	/**
	 * @return the entidadesPadreSel
	 */
	public List<String> getEntidadesPadreSel() {
		return entidadesPadreSel;
	}

	/**
	 * @param entidadesPadreSel
	 *            the entidadesPadreSel to set
	 */
	public void setEntidadesPadreSel(List<String> entidadesPadreSel) {
		this.entidadesPadreSel = entidadesPadreSel;
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
	 * @return the idTipEntPad
	 */
	public int getIdTipEntPad() {
		return idTipEntPad;
	}

	/**
	 * @param idTipEntPad
	 *            the idTipEntPad to set
	 */
	public void setIdTipEntPad(int idTipEntPad) {
		this.idTipEntPad = idTipEntPad;
	}

	/**
	 * @return the listaEntidadesPadre
	 */
	public List<SelectItem> getListaEntidadesPadre() {
		return listaEntidadesPadre;
	}

	/**
	 * @param listaEntidadesPadre
	 *            the listaEntidadesPadre to set
	 */
	public void setListaEntidadesPadre(List<SelectItem> listaEntidadesPadre) {
		this.listaEntidadesPadre = listaEntidadesPadre;
	}

	/**
	 * @return the labelEntidad
	 */
	public String getLabelEntidad() {
		return labelEntidad;
	}

	/**
	 * @param labelEntidad
	 *            the labelEntidad to set
	 */
	public void setLabelEntidad(String labelEntidad) {
		this.labelEntidad = labelEntidad;
	}

	/**
	 * @return the labelEntidadPadre
	 */
	public String getLabelEntidadPadre() {
		return labelEntidadPadre;
	}

	/**
	 * @param labelEntidadPadre
	 *            the labelEntidadPadre to set
	 */
	public void setLabelEntidadPadre(String labelEntidadPadre) {
		this.labelEntidadPadre = labelEntidadPadre;
	}

	/**
	 * @return the root
	 */
	public TreeNode getRoot() {
		return root;
	}

	/**
	 * @param root
	 *            the root to set
	 */
	public void setRoot(TreeNode root) {
		this.root = root;
	}

	/**
	 * Método para guardar
	 */
	public void guardar() {
		try {
			if (entidadesPadreSel != null && !entidadesPadreSel.isEmpty()) {
				List<RelacionEntidad> listaRelacionEnt = new ArrayList<>();
				for (String idEntPad : entidadesPadreSel) {
					RelacionEntidad relEnt = new RelacionEntidad();
					relEnt.setIdEntidadPadre(Integer.parseInt(idEntPad));
					relEnt.setIdEntidad(idEnt);
					relEnt.setRelentFechaAsigna(new Date());
					listaRelacionEnt.add(relEnt);
				}

				adminRelacionEntidad.guardar(listaRelacionEnt, idEnt);
				resetearFormulario();
				addInfo("Relaciones guardadas correctamente");
			} else {
				addError("Se debe configurar al menos una relación");
			}
		} catch (Exception e) {
			addError("No se pudo guardar as relaciones de entidad:" + e.getMessage());
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
		try {
			this.listaEntidadesPadre.clear();
			TipoEntidad tipoEnt = adminTipoEntidad.buscarPorId(idTipEntPad);
			this.labelEntidadPadre = String.valueOf(tipoEnt.getTipentNombre().charAt(0)).toUpperCase()
					+ tipoEnt.getTipentNombre().substring(1, tipoEnt.getTipentNombre().length()).toLowerCase() + ": *";
			for (Entidad ent : adminEntidad.buscarEntidadPorTipo(tipoEnt.getTipentNombre().toUpperCase())) {
				this.listaEntidadesPadre.add(new SelectItem(ent.getIdEntidad(), ent.getEntNombre()));
			}

			// Cargar las entidades
			for (Entidad entTmp : adminRelacionEntidad.buscarEntidadesAsociadas(idEnt)) {
				if (this.entidadesPadreSel == null) {
					this.entidadesPadreSel = new ArrayList<>();
					this.entidadesPadreSel.add(String.valueOf(entTmp.getIdEntidad()));
				} else {
					this.entidadesPadreSel.add(String.valueOf(entTmp.getIdEntidad()));
				}
			}
		} catch (Exception e) {
			addError("No se pudo cargar las entidades padre:" + e.getMessage());
		}

	}

	/**
	 * Método para cargar las entidades según tipo de entidad
	 */
	private void cargarEntidades() {
		this.listaEntidades.clear();

		TipoEntidad tipoEnt = adminTipoEntidad.buscarPorId(idTipEnt);
		this.labelEntidad = String.valueOf(tipoEnt.getTipentNombre().charAt(0)).toUpperCase()
				+ tipoEnt.getTipentNombre().substring(1, tipoEnt.getTipentNombre().length()).toLowerCase() + ":";
		for (Entidad ent : adminEntidad.buscarEntidadPorTipo(tipoEnt.getTipentNombre().toUpperCase())) {
			this.listaEntidades.add(new SelectItem(ent.getIdEntidad(), ent.getEntNombre()));
		}

	}

	/**
	 * Método para cargar las entidades tipo padre
	 */
	public void cargarTipoEntPadre() {
		this.listaTipoEntidadesPadre.clear();
		if (root != null) {
			this.root = null;
		}

		for (TipoEntidad tipEnt : adminTipoEntidad.buscarJerarquia(idTipEnt)) {
			this.listaTipoEntidadesPadre.add(new SelectItem(tipEnt.getIdTipoEntidad(), tipEnt.getTipentNombre()));
		}
		if (listaTipoEntidadesPadre.isEmpty()) {
			addInfo("No se tiene entidades padre para la entidad seleccionada.");
		}
		this.idTipEntPad = 0;
		this.labelEntidadPadre = "N/A";
		this.entidadesPadreSel = null;
		this.listaEntidadesPadre.clear();
		cargarEntidades();
	}

	/**
	 * Método para cargar el árbol de entidades
	 */
	public void cargarArbolEntidades() {
		try {
			Entidad entidadSel = adminEntidad.buscarPorId(idEnt);
			root = new DefaultTreeNode(entidadSel, null);
			cargarEntidadesJerarquia(entidadSel, root);
		} catch (Exception e) {
			addError("No se pudo cargar el árbol de entidades:" + e.getMessage());
		}
	}

	/**
	 * Método para cargar el árbol jerárquico de entidades
	 * 
	 * @param entidadSel
	 * @param root
	 */
	private void cargarEntidadesJerarquia(Entidad entidadSel, TreeNode root) throws Exception {
		List<Entidad> listaEntidades = adminRelacionEntidad.buscarEntidadesAsociadas(entidadSel.getIdEntidad());
		if (!listaEntidades.isEmpty()) {
			for (Entidad entTmp : adminRelacionEntidad.buscarEntidadesAsociadas(entidadSel.getIdEntidad())) {
				TreeNode nodoAct = new DefaultTreeNode(entTmp, root);
				cargarEntidadesJerarquia(entTmp, nodoAct);
			}
		}

	}

	/**
	 * Método para resetear un formulario
	 */
	public void resetearFormulario() {
		this.idTipEnt = 0;
		this.idEnt = 0;
		this.idTipEntPad = 0;
		this.listaEntidadesPadre.clear();
		this.entidadesPadreSel = null;
		this.root = null;
		this.labelEntidadPadre = "N/A";
		this.labelEntidad = "N/A";
	}

	/**
	 * Método para inicializar el formulario
	 */
	@PostConstruct
	public void inicializar() {
		cargarTiposEntidad();
	}

}
