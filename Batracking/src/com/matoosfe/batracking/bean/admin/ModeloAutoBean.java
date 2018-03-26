package com.matoosfe.batracking.bean.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import com.matoosfe.batracking.bean.seguridad.LoginBean;
import com.matoosfe.batracking.modelo.Entidad;
import com.matoosfe.batracking.modelo.EspecificacionBateria;
import com.matoosfe.batracking.modelo.MarcaAuto;
import com.matoosfe.batracking.modelo.ModeloAuto;
import com.matoosfe.batracking.modelo.Parametro;
import com.matoosfe.batracking.modelo.Producto;
import com.matoosfe.batracking.modelo.Seguimiento;
import com.matoosfe.batracking.modelo.TipoBateria;
import com.matoosfe.batracking.modelo.Usuario;
import com.matoosfe.batracking.negocio.admin.EntidadFacade;
import com.matoosfe.batracking.negocio.admin.EspecificacionFacade;
import com.matoosfe.batracking.negocio.admin.MarcaAutoFacade;
import com.matoosfe.batracking.negocio.admin.ModeloAutoFacade;
import com.matoosfe.batracking.negocio.admin.ParametroFacade;
import com.matoosfe.batracking.negocio.admin.ProductoFacade;
import com.matoosfe.kernel.web.bean.AbstractManagedBean;

/**
 * Clase para administrar el formulario de producto
 * 
 * @author MATOOSFE - Ing. Marco Toscano Freire. Msc -martosfre 9 ago. 2017-
 *         00:15:28<br>
 *         <a href="mailto:mtoscano@matoosfe.com?Subject=Soporte Batracking"
 *         target="_top">Soporte</a><br>
 *         <a href="http://www.matoosfe.com">Matoosfe</a>
 */
@ManagedBean
@ViewScoped
public class ModeloAutoBean extends AbstractManagedBean {
	private ModeloAuto modelo;
	private ModeloAuto modeloSel;
	private List<ModeloAuto> listaModeloAuto;
	private List<SelectItem> listaEspecificaciones;
	private List<SelectItem> listaMarcaAuto;

	private int idEspecificacion;
	private int idEspecificacionFull;
	private int idMarcaAuto;
	private String descripcion;
	private String motor;
	private String anio;
//	
//	private String valorBusquedaProd;
//	private List<Producto> listaProductosBus;
//	private int idPara;
//	private int idParaLot;
//	private List<SelectItem> listaParametrizacion;
//	private int idTipBat;
//	private List<SelectItem> listaTipoBaterias;

	private Usuario usuarioSesion;

	@EJB
	private ProductoFacade adminProducto;
	@EJB
	private EntidadFacade adminFabricante;
	@EJB
	private ParametroFacade adminParametro;
	@EJB
	private ModeloAutoFacade adminModeloAuto;
	@EJB
	private EspecificacionFacade adminEspecificacion;
	@EJB
	private MarcaAutoFacade adminMarcaAuto;

	public ModeloAutoBean() {
		this.modelo = new ModeloAuto();
		this.listaModeloAuto = new ArrayList<>();
		this.listaMarcaAuto = new ArrayList<>();
		this.listaEspecificaciones = new ArrayList<>();
		this.usuarioSesion = ((LoginBean) recuperarParametroSession("loginBean")).getUsuario();
	}

	public ModeloAuto getModelo() {
		return modelo;
	}



	public void setModelo(ModeloAuto modelo) {
		this.modelo = modelo;
	}



	public ModeloAuto getModeloSel() {
		return modeloSel;
	}



	public void setModeloSel(ModeloAuto modeloSel) {
		this.modeloSel = modeloSel;
	}



	public List<ModeloAuto> getListaModeloAuto() {
		return listaModeloAuto;
	}



	public void setListaModeloAuto(List<ModeloAuto> listaModeloAuto) {
		this.listaModeloAuto = listaModeloAuto;
	}



	public List<SelectItem> getListaEspecificaciones() {
		return listaEspecificaciones;
	}



	public void setListaEspecificaciones(List<SelectItem> listaEspecificaciones) {
		this.listaEspecificaciones = listaEspecificaciones;
	}



	public List<SelectItem> getListaMarcaAuto() {
		return listaMarcaAuto;
	}



	public void setListaMarcaAuto(List<SelectItem> listaMarcaAuto) {
		this.listaMarcaAuto = listaMarcaAuto;
	}



	public int getIdEspecificacion() {
		return idEspecificacion;
	}



	public void setIdEspecificacion(int idEspecificacion) {
		this.idEspecificacion = idEspecificacion;
	}



	public int getIdEspecificacionFull() {
		return idEspecificacionFull;
	}



	public void setIdEspecificacionFull(int idEspecificacionFull) {
		this.idEspecificacionFull = idEspecificacionFull;
	}



	public int getIdMarcaAuto() {
		return idMarcaAuto;
	}



	public void setIdMarcaAuto(int idMarcaAuto) {
		this.idMarcaAuto = idMarcaAuto;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public String getMotor() {
		return motor;
	}



	public void setMotor(String motor) {
		this.motor = motor;
	}



	public String getAnio() {
		return anio;
	}



	public void setAnio(String anio) {
		this.anio = anio;
	}



	/**
	 * @return the usuarioSesion
	 */
	public Usuario getUsuarioSesion() {
		return usuarioSesion;
	}

	/**
	 * @param usuarioSesion
	 *            the usuarioSesion to set
	 */
	public void setUsuarioSesion(Usuario usuarioSesion) {
		this.usuarioSesion = usuarioSesion;
	}

	/**
	 * Método para seleccionar un registro de modelo de auto
	 * 
	 * @param ev
	 */
	public void seleccionarRegistro(SelectEvent ev) {
		this.modeloSel = (ModeloAuto) ev.getObject();
		this.idEspecificacion = modeloSel.getEspecficacionBateria().getId();
		this.idEspecificacionFull = modeloSel.getEspecficacionBateriaFull().getId();
		this.idMarcaAuto= modeloSel.getMarcaAuto().getId();
		this.anio = modeloSel.getAnio();
		this.descripcion = modeloSel.getDescripcion();
		this.motor = modeloSel.getMotor();
	}

	/**
	 * Método para guardar
	 */
	public void guardar() {
		try {
			EspecificacionBateria especificacion = adminEspecificacion.buscarPorId(idEspecificacion);
			modelo.setEspecficacionBateria(especificacion);
			EspecificacionBateria especificacionFull = adminEspecificacion.buscarPorId(idEspecificacionFull);
			modelo.setEspecificacionBateriaFull(especificacionFull);
			MarcaAuto marcaAuto = adminMarcaAuto.buscarPorId(idMarcaAuto);
			modelo.setMarcaAuto(marcaAuto);
			modelo.setAnio(anio);
			modelo.setDescripcion(descripcion);
			modelo.setMotor(motor);
			if (modelo.getId() > 0) {
				adminModeloAuto.actualizar(modelo);
				addInfo("Marca de auto guardada correctamente!!");
			} else {
				adminModeloAuto.guardar(modelo);
				addInfo("Marca de auto actualizada correctamente!!");
			}
			resetearFormulario();
		} catch (Exception e) {
			addError("No se pudo guardar la marca(s) de auto, revisar que el c�digo no este duplicado");
		}
	}

	/**
	 * Método para editar un producto
	 */
	public void editar() {
		try {
			if (modeloSel != null) {
				this.modelo = modeloSel;
				this.idEspecificacion = modelo.getEspecficacionBateria().getId();
				if( modelo.getEspecficacionBateriaFull() != null ) {
					this.idEspecificacionFull = modelo.getEspecficacionBateriaFull().getId();
				}else {
					this.idEspecificacionFull = 0;
				}
				this.idMarcaAuto= modelo.getMarcaAuto().getId();
				this.descripcion = modelo.getDescripcion();
				this.anio = modelo.getAnio();
				this.motor = modelo.getMotor();
			} else {
				addError("Se debe seleccionar un modelo de Auto!!");
			}
		} catch (Exception e) {
			addError("No se pudo editar un modelo de auto:" + e.getMessage());
		}
	}

	/**
	 * Método para eliminar
	 */
	public void eliminar() {
		try {
			if (modeloSel != null) {
				adminModeloAuto.eliminar(modeloSel, false);
				resetearFormulario();
				cargarModelosAutos();
				addInfo("Modelo de auto eliminado correctamente");
			} else {
				addError("Se debe seleccionar un modelo de auto!!");
			}
		} catch (Exception e) {
			addError("No se pudo eliminar el modelo de auto:" + e.getMessage());
		}
	}

	/**
	 * Método para cargar los fabricantes
	 */
	private void cargarEspecificaciones() {
		try {
			this.listaEspecificaciones.clear();
			for (EspecificacionBateria esp : adminEspecificacion.buscarTodos() ) {
				this.listaEspecificaciones.add(new SelectItem(esp.getId(), esp.getCodEspecificacion() ));
			}
		} catch (Exception e) {
			addError("No se pudo cargar las especificaciones de bateria:" + e.getMessage());
		}

	}

	/**
	 * Método para cargar los modelos de autos
	 */
	private void cargarModelosAutos() {
		try {
			this.listaModeloAuto = adminModeloAuto.buscarTodos();
		} catch (Exception e) {
			addError("No se pudo cargar los modelos de auto:" + e.getMessage());
		}
	}

	/**
	 * Método para cargar marcas de auto
	 */
	private void cargarMarcasAutos() {
		try {
			this.listaMarcaAuto.clear();
			for (MarcaAuto marca : adminMarcaAuto.buscarTodos()) {
				this.listaMarcaAuto.add(new SelectItem(marca.getId(), marca.getDescripcion() ));
			}
		} catch (Exception e) {
			addError("No se pudo cargar las marcas de autos:" + e.getMessage());
		}

	}

	/**
	 * M�todo para resetear formulario
	 */
	public void resetearFormulario() {
		this.modelo = new ModeloAuto();
		this.modeloSel = null;
		this.idEspecificacion = 0;
		this.idEspecificacionFull = 0;
		this.idMarcaAuto = 0;
		this.descripcion = "";
		this.motor= "";
		this.anio = "";
	}

	/**
	 * M�todo para inicializar el formulario
	 */
	@PostConstruct
	public void inicializar() {
		cargarModelosAutos();
		cargarEspecificaciones();
		cargarMarcasAutos();
	}

}
