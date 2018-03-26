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
import com.matoosfe.batracking.modelo.Parametro;
import com.matoosfe.batracking.modelo.Producto;
import com.matoosfe.batracking.modelo.Seguimiento;
import com.matoosfe.batracking.modelo.TipoBateria;
import com.matoosfe.batracking.modelo.Usuario;
import com.matoosfe.batracking.negocio.admin.EntidadFacade;
import com.matoosfe.batracking.negocio.admin.EspecificacionFacade;
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
public class ProductoBean extends AbstractManagedBean {
	private Producto producto;
	private Producto productoSel;
	private List<Producto> listaProductos;
	private List<Producto> listaProductosSel;
	private List<SelectItem> listaFabricante;
	private List<SelectItem> listaEspecificaciones;
	private int idEspecificacion;
	
	private int idFab;
	private int idFabLot;
	private String semillaLot;
	private int inicioCodLot;
	private int finCodLot;
	private String modeloLot;
	private Date fechaProLot;

	private String valorBusquedaProd;
	private List<Producto> listaProductosBus;
	private int idPara;
	private int idParaLot;
	private List<SelectItem> listaParametrizacion;
	private int idTipBat;
	private List<SelectItem> listaTipoBaterias;

	private Usuario usuarioSesion;

	@EJB
	private ProductoFacade adminProducto;
	@EJB
	private EntidadFacade adminFabricante;
	@EJB
	private ParametroFacade adminParametro;
	@EJB
	private EspecificacionFacade adminEspecificacion;

	public ProductoBean() {
		this.producto = new Producto();
		this.listaProductos = new ArrayList<>();
		this.listaFabricante = new ArrayList<>();
		this.listaProductosBus = new ArrayList<>();
		this.listaParametrizacion = new ArrayList<>();
		this.listaTipoBaterias = new ArrayList<>();
		this.listaEspecificaciones = new ArrayList<>();

		this.usuarioSesion = ((LoginBean) recuperarParametroSession("loginBean")).getUsuario();
	}

	/**
	 * @return the producto
	 */
	public Producto getProducto() {
		return producto;
	}

	/**
	 * @param producto
	 *            the producto to set
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	/**
	 * @return the productoSel
	 */
	public Producto getProductoSel() {
		return productoSel;
	}

	/**
	 * @param productoSel
	 *            the productoSel to set
	 */
	public void setProductoSel(Producto productoSel) {
		this.productoSel = productoSel;
	}

	/**
	 * @return the listaProductos
	 */
	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	/**
	 * @param listaProductos
	 *            the listaProductos to set
	 */
	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	/**
	 * @return the listaProductosSel
	 */
	public List<Producto> getListaProductosSel() {
		return listaProductosSel;
	}

	/**
	 * @param listaProductosSel
	 *            the listaProductosSel to set
	 */
	public void setListaProductosSel(List<Producto> listaProductosSel) {
		this.listaProductosSel = listaProductosSel;
	}

	/**
	 * @return the listaFabricante
	 */
	public List<SelectItem> getListaFabricante() {
		return listaFabricante;
	}

	/**
	 * @param listaFabricante
	 *            the listaFabricante to set
	 */
	public void setListaFabricante(List<SelectItem> listaFabricante) {
		this.listaFabricante = listaFabricante;
	}

	/**
	 * @return the listaProductosBus
	 */
	public List<Producto> getListaProductosBus() {
		return listaProductosBus;
	}

	/**
	 * @param listaProductosBus
	 *            the listaProductosBus to set
	 */
	public void setListaProductosBus(List<Producto> listaProductosBus) {
		this.listaProductosBus = listaProductosBus;
	}

	/**
	 * @return the idPara
	 */
	public int getIdPara() {
		return idPara;
	}

	/**
	 * @param idPara
	 *            the idPara to set
	 */
	public void setIdPara(int idPara) {
		this.idPara = idPara;
	}

	/**
	 * @return the idParaLot
	 */
	public int getIdParaLot() {
		return idParaLot;
	}

	/**
	 * @param idParaLot
	 *            the idParaLot to set
	 */
	public void setIdParaLot(int idParaLot) {
		this.idParaLot = idParaLot;
	}

	/**
	 * @return the listaParametrizacion
	 */
	public List<SelectItem> getListaParametrizacion() {
		return listaParametrizacion;
	}

	/**
	 * @param listaParametrizacion
	 *            the listaParametrizacion to set
	 */
	public void setListaParametrizacion(List<SelectItem> listaParametrizacion) {
		this.listaParametrizacion = listaParametrizacion;
	}

	/**
	 * @return the idTipBat
	 */
	public int getIdTipBat() {
		return idTipBat;
	}

	/**
	 * @param idTipBat
	 *            the idTipBat to set
	 */
	public void setIdTipBat(int idTipBat) {
		this.idTipBat = idTipBat;
	}

	/**
	 * @return the listaTipoBaterias
	 */
	public List<SelectItem> getListaTipoBaterias() {
		return listaTipoBaterias;
	}

	/**
	 * @param listaTipoBaterias
	 *            the listaTipoBaterias to set
	 */
	public void setListaTipoBaterias(List<SelectItem> listaTipoBaterias) {
		this.listaTipoBaterias = listaTipoBaterias;
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
	 * @return the idFab
	 */
	public int getIdFab() {
		return idFab;
	}

	/**
	 * @param idFab
	 *            the idFab to set
	 */
	public void setIdFab(int idFab) {
		this.idFab = idFab;
	}

	/**
	 * @return the idFabLot
	 */
	public int getIdFabLot() {
		return idFabLot;
	}

	/**
	 * @param idFabLot
	 *            the idFabLot to set
	 */
	public void setIdFabLot(int idFabLot) {
		this.idFabLot = idFabLot;
	}

	/**
	 * @return the semillaLot
	 */
	public String getSemillaLot() {
		return semillaLot;
	}

	/**
	 * @param semillaLot
	 *            the semillaLot to set
	 */
	public void setSemillaLot(String semillaLot) {
		this.semillaLot = semillaLot;
	}

	/**
	 * @return the inicioCodLot
	 */
	public int getInicioCodLot() {
		return inicioCodLot;
	}

	/**
	 * @param inicioCodLot
	 *            the inicioCodLot to set
	 */
	public void setInicioCodLot(int inicioCodLot) {
		this.inicioCodLot = inicioCodLot;
	}

	/**
	 * @return the finCodLot
	 */
	public int getFinCodLot() {
		return finCodLot;
	}

	/**
	 * @param finCodLot
	 *            the finCodLot to set
	 */
	public void setFinCodLot(int finCodLot) {
		this.finCodLot = finCodLot;
	}

	/**
	 * @return the modeloLot
	 */
	public String getModeloLot() {
		return modeloLot;
	}

	/**
	 * @param modeloLot
	 *            the modeloLot to set
	 */
	public void setModeloLot(String modeloLot) {
		this.modeloLot = modeloLot;
	}

	/**
	 * @return the fechaProLot
	 */
	public Date getFechaProLot() {
		return fechaProLot;
	}

	/**
	 * @param fechaProLot
	 *            the fechaProLot to set
	 */
	public void setFechaProLot(Date fechaProLot) {
		this.fechaProLot = fechaProLot;
	}

	/**
	 * @return the valorBusquedaProd
	 */
	public String getValorBusquedaProd() {
		return valorBusquedaProd;
	}

	/**
	 * @param valorBusquedaProd
	 *            the valorBusquedaProd to set
	 */
	public void setValorBusquedaProd(String valorBusquedaProd) {
		this.valorBusquedaProd = valorBusquedaProd;
	}
	
	public int getIdEspecificacion() {
		return idEspecificacion;
	}

	public void setIdEspecificacion(int idEspecificacion) {
		this.idEspecificacion = idEspecificacion;
	}
	
	public List<SelectItem> getListaEspecificaciones() {
		return listaEspecificaciones;
	}

	public void setListaEspecificaciones(List<SelectItem> listaEspecificaciones) {
		this.listaEspecificaciones = listaEspecificaciones;
	}

	/**
	 * Método para seleccionar un registro de producto
	 * 
	 * @param ev
	 */
	public void seleccionarRegistro(SelectEvent ev) {
		this.productoSel = (Producto) ev.getObject();
	}

	/**
	 * Método para guardar
	 */
	public void guardar() {
		try {
			Parametro parametro = adminParametro.buscarPorId(idPara);
			producto.setParametro(parametro);
			Entidad fabricante = adminFabricante.buscarPorId(idFab);
			producto.setEntidad(fabricante);
			EspecificacionBateria especificacion = adminEspecificacion.buscarPorId(idEspecificacion);
			producto.setEspecficacionBateria(especificacion);
			TipoBateria tipoBateria = adminProducto.buscarTipoBateria(idTipBat);
			producto.setTipoBateria(tipoBateria);
			producto.setProdEstadoBateria("FABRICA");
			if (producto.getIdProducto() > 0) {
				adminProducto.actualizar(producto);
			} else {
				Seguimiento seguimiento = new Seguimiento();
				seguimiento.setProducto(producto);
				seguimiento.setSegFecha(new Date());
				seguimiento.setSegLatitud("N/A");
				seguimiento.setSegLongitud("N/A");
				seguimiento.setSegEstado("FABRICA");
				seguimiento.setSegLugar("FABRICA");
				seguimiento.setSegVidaUtil(parametro.getParVidaUtil());
				seguimiento.setSegMantenimiento(parametro.getParMantenimiento());
				seguimiento.setUsuario(usuarioSesion);
				seguimiento.setSegActual(1);

				adminProducto.guardar(producto, seguimiento);
			}
			resetearFormulario();
			cargarProductos();
			addInfo("Producto guardado correctamente!!");
		} catch (Exception e) {
			addError("No se pudo guardar el producto(s), revisar que el código no este duplicado");
		}
	}

	/**
	 * Método para editar un producto
	 */
	public void editar() {
		try {
			if (productoSel != null) {
				this.producto = productoSel;
				this.idFab = producto.getEntidad().getIdEntidad();
				this.idPara = producto.getParametro().getParCodigo();
				this.idTipBat = producto.getTipoBateria().getTipbatCodigo();
				this.idEspecificacion = producto.getEspecficacionBateria().getId();
			} else {
				addError("Se debe seleccionar un producto!!");
			}
		} catch (Exception e) {
			addError("No se pudo editar un producto:" + e.getMessage());
		}
	}

	/**
	 * Método para eliminar
	 */
	public void eliminar() {
		try {
			if (productoSel != null) {
				adminProducto.eliminar(productoSel, false);
				resetearFormulario();
				cargarProductos();
				addInfo("Producto eliminado correctamente");
			} else {
				addError("Se debe seleccionar un producto!!");
			}
		} catch (Exception e) {
			addError("No se pudo eliminar el producto: " + e.getMessage());
		}
	}

	/**
	 * Método para generar los productos en Lote
	 */
	public void generarLote() {
		try {
			List<Producto> listaProLote = new ArrayList<>();
			Entidad fabricante = adminFabricante.buscarPorId(idFabLot);
			Parametro parametro = adminParametro.buscarPorId(idParaLot);
			for (int i = inicioCodLot; i <= finCodLot; i++) {
				List<Seguimiento> listaSeguimiento = new ArrayList<>();
				
				TipoBateria tipoBateria = new TipoBateria();
				tipoBateria.setTipbatCodigo(1);

				Producto prodTmp = new Producto();
				prodTmp.setProdCodigo(semillaLot + i);
				prodTmp.setEntidad(fabricante);
				prodTmp.setProdEspecificaciones(modeloLot);
				prodTmp.setProdEstadoBateria("EN FABRICA");
				prodTmp.setProdFechaProduccion(fechaProLot);
				prodTmp.setParametro(parametro);
				prodTmp.setTipoBateria(tipoBateria);

				Seguimiento seguimiento = new Seguimiento();
				seguimiento.setProducto(prodTmp);
				seguimiento.setSegFecha(new Date());
				seguimiento.setSegLatitud("N/A");
				seguimiento.setSegLongitud("N/A");
				seguimiento.setSegEstado("FABRICA");
				seguimiento.setSegLugar("FABRICA");
				seguimiento.setSegVidaUtil(parametro.getParVidaUtil());
				seguimiento.setSegMantenimiento(parametro.getParMantenimiento());
				seguimiento.setUsuario(usuarioSesion);
				seguimiento.setSegActual(1);

				listaSeguimiento.add(seguimiento);
				prodTmp.setSeguimientos(listaSeguimiento);

				listaProLote.add(prodTmp);
			}

			if (!listaProLote.isEmpty()) {
				adminProducto.guardarLote(listaProLote);

				addInfo("Lote registrado correctamente");
				cargarProductos();
				resetearFormulario();
			}
		} catch (Exception e) {
			addError("No se pudo registar el lote, revisar que los códigos seán únicos");
		}
	}

	/**
	 * Método para buscar un producto
	 */
	public void buscar() {

	}

	/**
	 * Método para generar el reporte de producto
	 */
	public void generarReporte() {

	}

	/**
	 * Método para cargar los fabricantes
	 */
	private void cargarFabricantes() {
		try {
			this.listaFabricante.clear();
			for (Entidad fab : adminFabricante.buscarEntidadPorTipo("FABRICA")) {
				this.listaFabricante.add(new SelectItem(fab.getIdEntidad(), fab.getEntNombre()));
			}
		} catch (Exception e) {
			addError("No se pudo cargar los tipos de usuarios:" + e.getMessage());
		}

	}

	/**
	 * Método para cargar los productos
	 */
	private void cargarProductos() {
		try {
			this.listaProductos = adminProducto.buscarTodos();
		} catch (Exception e) {
			addError("No se pudo cargar los productos:" + e.getMessage());
		}
	}

	/**
	 * Método para cargar las parametrizaciones
	 */
	private void cargarParametrizaciones() {
		this.listaParametrizacion.clear();
		for (Parametro par : adminParametro.buscarTodos()) {
			this.listaParametrizacion.add(new SelectItem(par.getParCodigo(), par.toString()));
		}
	}

	/**
	 * Método para cargar tipos de bateria
	 */
	private void cargarTiposBateria() {
		try {
			this.listaTipoBaterias.clear();
			for (TipoBateria tipBat : adminProducto.buscarTiposBateria()) {
				this.listaTipoBaterias.add(new SelectItem(tipBat.getTipbatCodigo(), tipBat.getTipbatNombre()));
			}
		} catch (Exception e) {
			addError("No se pudo cargar los tipos de baterias:" + e.getMessage());
		}

	}

	/**
	 * Método para cargar las especificaciones de bateria
	 */
	private void cargarEspecificaciones() {
		try {
			this.listaEspecificaciones.clear();
			for (EspecificacionBateria especificacion : adminEspecificacion.buscarTodos() ) {
				this.listaEspecificaciones.add(new SelectItem(especificacion.getId(), especificacion.getCodEspecificacion() ));
			}
		} catch (Exception e) {
			addError("No se pudo cargar las especificaciones de bateria:" + e.getMessage());
		}

	}

	/**
	 * Método para resetear formulario
	 */
	public void resetearFormulario() {
		this.producto = new Producto();
		this.productoSel = null;
		this.idFab = 0;
		this.idPara = 0;
		this.idParaLot = 0;
		this.idFabLot = 0;
		this.idTipBat = 0;
		this.idEspecificacion = 0;
	}

	/**
	 * Método para inicializar el formulario
	 */
	@PostConstruct
	public void inicializar() {
		cargarProductos();
		cargarFabricantes();
		cargarParametrizaciones();
		cargarTiposBateria();
		cargarEspecificaciones();
	}

}
