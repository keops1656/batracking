package com.matoosfe.batracking.bean.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import com.matoosfe.batracking.modelo.EspecificacionBateria;
import com.matoosfe.batracking.negocio.admin.EspecificacionFacade;
import com.matoosfe.kernel.web.bean.AbstractManagedBean;

/**
 * Clase para administrar el formulario de ParametrizaciÃ³n
 * 
 * @author MATOOSFE - Ing. Marco Toscano Freire. Msc -martosfre 7 ago. 2017-
 *         23:06:59<br>
 *         <a href="mailto:mtoscano@matoosfe.com?Subject=Soporte Batracking"
 *         target="_top">Soporte</a><br>
 *         <a href="http://www.matoosfe.com">Matoosfe</a>
 */
@ManagedBean
@ViewScoped
public class EspecificacionBean extends AbstractManagedBean {
	private EspecificacionBateria especificacion;
	private EspecificacionBateria especificacionSel;
	private List<EspecificacionBateria> listaEspecificaciones;
	private String estado;

	@EJB
	private EspecificacionFacade adminEspecificacion;

	public EspecificacionBean() {
		this.especificacion = new EspecificacionBateria();
		this.listaEspecificaciones = new ArrayList<>(); 
	}

	/**
	 * @return the especificacion
	 */
	public EspecificacionBateria getEspecificacion() {
		return especificacion;
	}

	/**
	 * @param especificacion
	 *            the especificacion to set
	 */
	public void setEspecificacion(EspecificacionBateria especificacion) {
		this.especificacion = especificacion;
	}

	/**
	 * @return the especificacionSel
	 */
	public EspecificacionBateria getEspecificacionSel() {
		return especificacionSel;
	}

	/**
	 * @param parametroSel
	 *            the parametroSel to set
	 */
	public void setParametroSel(EspecificacionBateria especificacionSel) {
		this.especificacionSel = especificacionSel;
	}

	/**
	 * @return the listaParametros
	 */
	public List<EspecificacionBateria> getListaEspecificaciones() {
		return listaEspecificaciones;
	}

	/**
	 * @param listaParametros
	 *            the listaParametros to set
	 */
	public void setListaEspecificaciones(List<EspecificacionBateria> listaEspecificaciones) {
		this.listaEspecificaciones = listaEspecificaciones;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Método para seleccionar un registro de especificacion
	 * 
	 * @param ev
	 */
	public void seleccionarRegistro(SelectEvent ev) {
		this.especificacionSel = (EspecificacionBateria) ev.getObject();
	}

	/**
	 * MÃ©todo para guardar
	 */
	public void guardar() {
		try {
			if (especificacion.getId() > 0) {
				adminEspecificacion.actualizar(especificacion);
			} else {
				adminEspecificacion.guardar(especificacion);
			}
			resetearFormulario();
			cargarEspecificaciones();
			addInfo("Especificación guardada correctamente!!");
		} catch (Exception e) {
			addError("No se pudo guardar la especificación(s), revisar que el código no este duplicado");
		}
	}

	/**
	 * MÃ©todo para editar un parametro
	 */
	public void editar() {
		try {
			if (especificacionSel != null) {
				this.especificacion = especificacionSel;
			} else {
				addError("Se debe seleccionar una especificación!!");
			}
		} catch (Exception e) {
			addError("No se pudo editar un parametro:" + e.getMessage());
		}
	}

	/**
	 * Método para eliminar
	 */
	public void eliminar() {
		try {
			if (especificacionSel != null) {
				adminEspecificacion.eliminar(especificacionSel, false);
				resetearFormulario();
				cargarEspecificaciones();
				addInfo("Especificación eliminada correctamente");
			} else {
				addError("Se debe seleccionar una especificación!!");
			}
		} catch (Exception e) {
			addError("No se pudo eliminar la especificación existen configuraciones de productos que la utilizan");
		}
	}

	/**
	 * Metodo para cargar los parametros
	 */
	private void cargarEspecificaciones() {
		try {
			this.listaEspecificaciones = adminEspecificacion.buscarTodos();
		} catch (Exception e) {
			addError("No se pudo cargar las especificaciones:" + e.getMessage());
		}
	}

	/**
	 * Metodo para resetear formulario
	 */
	public void resetearFormulario() {
		this.especificacionSel = new EspecificacionBateria();
		this.especificacionSel = null;
	}

	/**
	 * MÃ©todo para inicializar el formulario
	 */
	@PostConstruct
	public void inicializar() {
		cargarEspecificaciones();
	}

}
