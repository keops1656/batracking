package com.matoosfe.batracking.bean.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import com.matoosfe.batracking.modelo.MarcaAuto;
import com.matoosfe.batracking.negocio.admin.MarcaAutoFacade;
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
public class MarcaAutoBean extends AbstractManagedBean {
	private MarcaAuto marca;
	private MarcaAuto marcaSel;
	private List<MarcaAuto> listaMarcas;
	private String estado;

	@EJB
	private MarcaAutoFacade adminMarca;

	public MarcaAutoBean() {
		this.marca = new MarcaAuto();
		this.listaMarcas = new ArrayList<>(); 
	}

	/**
	 * @return the marca de auto
	 */
	public MarcaAuto getMarca() {
		return marca;
	}

	/**
	 * @param marca
	 *            the marca de auto to set
	 */
	public void setMarca(MarcaAuto marca) {
		this.marca = marca;
	}

	/**
	 * @return the marcaSel
	 */
	public MarcaAuto getMarcaSel() {
		return marcaSel;
	}

	/**
	 * @param parametroSel
	 *            the marcaSel to set
	 */
	public void setMarcaSel(MarcaAuto marcaSel) {
		this.marcaSel = marcaSel;
	}

	/**
	 * @return the lista marcas
	 */
	public List<MarcaAuto> getListaMarcas() {
		return listaMarcas;
	}

	/**
	 * @param listamarcas
	 *            the listaMarcas to set
	 */
	public void setListaMarca(List<MarcaAuto> listaMarcas) {
		this.listaMarcas = listaMarcas;
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
	 * M�todo para seleccionar un registro de especificacion
	 * 
	 * @param ev
	 */
	public void seleccionarRegistro(SelectEvent ev) {
		this.marcaSel = (MarcaAuto) ev.getObject();
	}

	/**
	 * Método para guardar
	 */
	public void guardar() {
		try {
			if (marca.getId() > 0) {
				adminMarca.actualizar(marca);
			} else {
				adminMarca.guardar(marca);
			}
			resetearFormulario();
			cargarMarcas();
			addInfo("Marca de Auto guardada correctamente!!");
		} catch (Exception e) {
			addError("No se pudo guardar la marca de auto(s), revisar que el nombre no este duplicado");
		}
	}

	/**
	 * Método para editar un parametro
	 */
	public void editar() {
		try {
			if (marcaSel != null) {
				this.marca = marcaSel;
			} else {
				addError("Se debe seleccionar una marca de auto!!");
			}
		} catch (Exception e) {
			addError("No se pudo editar una marca de auto:" + e.getMessage());
		}
	}

	/**
	 * M�todo para eliminar
	 */
	public void eliminar() {
		try {
			if (marcaSel != null) {
				adminMarca.eliminar(marcaSel, false);
				resetearFormulario();
				cargarMarcas();
				addInfo("Marca de auto eliminada correctamente");
			} else {
				addError("Se debe seleccionar una marca de auto!!");
			}
		} catch (Exception e) {
			addError("No se pudo eliminar la Marca de auto,  existen modelos de autos que la utilizan");
		}
	}

	/**
	 * Metodo para cargar las marcas de autos
	 */
	private void cargarMarcas() {
		try {
			this.listaMarcas = adminMarca.buscarTodos();
		} catch (Exception e) {
			addError("No se pudo cargar las marcas de auto: " + e.getMessage());
		}
	}

	/**
	 * Metodo para resetear formulario
	 */
	public void resetearFormulario() {
		this.marcaSel = new MarcaAuto();
		this.marcaSel = null;
	}

	/**
	 * Método para inicializar el formulario
	 */
	@PostConstruct
	public void inicializar() {
		cargarMarcas();
	}

}
