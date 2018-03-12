package com.matoosfe.batracking.bean.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import com.matoosfe.batracking.modelo.Parametro;
import com.matoosfe.batracking.negocio.admin.ParametroFacade;
import com.matoosfe.kernel.web.bean.AbstractManagedBean;

/**
 * Clase para administrar el formulario de Parametrización
 * 
 * @author MATOOSFE - Ing. Marco Toscano Freire. Msc -martosfre 7 ago. 2017-
 *         23:06:59<br>
 *         <a href="mailto:mtoscano@matoosfe.com?Subject=Soporte Batracking"
 *         target="_top">Soporte</a><br>
 *         <a href="http://www.matoosfe.com">Matoosfe</a>
 */
@ManagedBean
@ViewScoped
public class ParametroBean extends AbstractManagedBean {
	private Parametro parametro;
	private Parametro parametroSel;
	private List<Parametro> listaParametros;
	private String estado;

	@EJB
	private ParametroFacade adminParametro;

	public ParametroBean() {
		this.parametro = new Parametro();
		this.listaParametros = new ArrayList<>();
	}

	/**
	 * @return the parametro
	 */
	public Parametro getParametro() {
		return parametro;
	}

	/**
	 * @param parametro
	 *            the parametro to set
	 */
	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}

	/**
	 * @return the parametroSel
	 */
	public Parametro getParametroSel() {
		return parametroSel;
	}

	/**
	 * @param parametroSel
	 *            the parametroSel to set
	 */
	public void setParametroSel(Parametro parametroSel) {
		this.parametroSel = parametroSel;
	}

	/**
	 * @return the listaParametros
	 */
	public List<Parametro> getListaParametros() {
		return listaParametros;
	}

	/**
	 * @param listaParametros
	 *            the listaParametros to set
	 */
	public void setListaParametros(List<Parametro> listaParametros) {
		this.listaParametros = listaParametros;
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
	 * Método para seleccionar un registro de parametro
	 * 
	 * @param ev
	 */
	public void seleccionarRegistro(SelectEvent ev) {
		this.parametroSel = (Parametro) ev.getObject();
	}

	/**
	 * Método para guardar
	 */
	public void guardar() {
		try {
			if (parametro.getParCodigo() > 0) {
				adminParametro.actualizar(parametro);
			} else {
				adminParametro.guardar(parametro);
			}
			resetearFormulario();
			cargarParametros();
			addInfo("Parametro guardado correctamente!!");
		} catch (Exception e) {
			addError("No se pudo guardar el parametro(s), revisar que el código no este duplicado");
		}
	}

	/**
	 * Método para editar un parametro
	 */
	public void editar() {
		try {
			if (parametroSel != null) {
				this.parametro = parametroSel;
			} else {
				addError("Se debe seleccionar un parametro!!");
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
			if (parametroSel != null) {
				adminParametro.eliminar(parametroSel, false);
				resetearFormulario();
				cargarParametros();
				addInfo("Parametro eliminado correctamente");
			} else {
				addError("Se debe seleccionar un parametro!!");
			}
		} catch (Exception e) {
			addError("No se pudo eliminar el parametro existen configuraciones de productos que la utilizan");
		}
	}

	/**
	 * Método para cargar los parametros
	 */
	private void cargarParametros() {
		try {
			this.listaParametros = adminParametro.buscarTodos();
		} catch (Exception e) {
			addError("No se pudo cargar los parametros:" + e.getMessage());
		}
	}

	/**
	 * Método para resetear formulario
	 */
	public void resetearFormulario() {
		this.parametro = new Parametro();
		this.parametroSel = null;
	}

	/**
	 * Método para inicializar el formulario
	 */
	@PostConstruct
	public void inicializar() {
		cargarParametros();
	}

}
