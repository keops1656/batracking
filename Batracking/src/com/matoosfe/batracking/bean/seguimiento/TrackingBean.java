package com.matoosfe.batracking.bean.seguimiento;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.matoosfe.batracking.bean.seguridad.LoginBean;
import com.matoosfe.batracking.modelo.ComentarioSeguimiento;
import com.matoosfe.batracking.modelo.Entidad;
import com.matoosfe.batracking.modelo.FotografiasSeguimiento;
import com.matoosfe.batracking.modelo.Producto;
import com.matoosfe.batracking.modelo.Seguimiento;
import com.matoosfe.batracking.modelo.Usuario;
import com.matoosfe.batracking.negocio.admin.ProductoFacade;
import com.matoosfe.batracking.negocio.seguimiento.SeguimientoFacade;
import com.matoosfe.batracking.negocio.seguridad.RelacionEntidadFacade;
import com.matoosfe.kernel.web.bean.AbstractManagedBean;

/**
 * Clase para administrar el tracking de un producto
 * 
 * @author MATOOSFE - Ing. Marco Toscano Freire. Msc -martosfre 20 ago. 2017-
 *         12:38:12<br>
 *         <a href="mailto:mtoscano@matoosfe.com?Subject=Soporte Batracking"
 *         target="_top">Soporte</a><br>
 *         <a href="http://www.matoosfe.com">Matoosfe</a>
 */
@ManagedBean
@ViewScoped
public class TrackingBean extends AbstractManagedBean {

	private Usuario usuarioSesion;
	private int idEnt;
	private List<Producto> listaProductos;
	private Producto productoSel;
	private List<Seguimiento> listaSeguimiento;
	private Seguimiento seguimiento;
	private Seguimiento seguimientoSel;
	private ComentarioSeguimiento comentario;
	private ComentarioSeguimiento comentarioSel;
	private List<ComentarioSeguimiento> listaComentarios;
	private List<FotografiasSeguimiento> listaFotografias;
	private int idTmp;
	private StreamedContent fotoDescarga;
	private TimeZone timezone;
	private String tipoBateria;
	private boolean refrescar;
	private List<String> idsFabricantes;
	private String valBusPro;

	@EJB
	private RelacionEntidadFacade adminRelacionEnt;
	@EJB
	private SeguimientoFacade adminSeguimiento;
	@EJB
	private RelacionEntidadFacade adminRelacionEntidad;
	@EJB
	private ProductoFacade adminProducto;

	public TrackingBean() {
		this.listaProductos = new ArrayList<>();
		this.seguimiento = new Seguimiento();
		this.listaSeguimiento = new ArrayList<>();
		this.comentario = new ComentarioSeguimiento();
		this.listaComentarios = new ArrayList<>();
		this.listaFotografias = new ArrayList<>();
		this.timezone = TimeZone.getDefault();
		this.idsFabricantes = new ArrayList<>();

		this.usuarioSesion = ((LoginBean) recuperarParametroSession("loginBean")).getUsuario();
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
	 * @return the listaSeguimiento
	 */
	public List<Seguimiento> getListaSeguimiento() {
		return listaSeguimiento;
	}

	/**
	 * @param listaSeguimiento
	 *            the listaSeguimiento to set
	 */
	public void setListaSeguimiento(List<Seguimiento> listaSeguimiento) {
		this.listaSeguimiento = listaSeguimiento;
	}

	/**
	 * @return the seguimiento
	 */
	public Seguimiento getSeguimiento() {
		return seguimiento;
	}

	/**
	 * @param seguimiento
	 *            the seguimiento to set
	 */
	public void setSeguimiento(Seguimiento seguimiento) {
		this.seguimiento = seguimiento;
	}

	/**
	 * @return the seguimientoSel
	 */
	public Seguimiento getSeguimientoSel() {
		return seguimientoSel;
	}

	/**
	 * @param seguimientoSel
	 *            the seguimientoSel to set
	 */
	public void setSeguimientoSel(Seguimiento seguimientoSel) {
		this.seguimientoSel = seguimientoSel;
	}

	/**
	 * @return the comentario
	 */
	public ComentarioSeguimiento getComentario() {
		return comentario;
	}

	/**
	 * @param comentario
	 *            the comentario to set
	 */
	public void setComentario(ComentarioSeguimiento comentario) {
		this.comentario = comentario;
	}

	/**
	 * @return the comentarioSel
	 */
	public ComentarioSeguimiento getComentarioSel() {
		return comentarioSel;
	}

	/**
	 * @param comentarioSel
	 *            the comentarioSel to set
	 */
	public void setComentarioSel(ComentarioSeguimiento comentarioSel) {
		this.comentarioSel = comentarioSel;
	}

	/**
	 * @return the listaComentarios
	 */
	public List<ComentarioSeguimiento> getListaComentarios() {
		return listaComentarios;
	}

	/**
	 * @param listaComentarios
	 *            the listaComentarios to set
	 */
	public void setListaComentarios(List<ComentarioSeguimiento> listaComentarios) {
		this.listaComentarios = listaComentarios;
	}

	/**
	 * @return the listaFotografias
	 */
	public List<FotografiasSeguimiento> getListaFotografias() {
		return listaFotografias;
	}

	/**
	 * @param listaFotografias
	 *            the listaFotografias to set
	 */
	public void setListaFotografias(List<FotografiasSeguimiento> listaFotografias) {
		this.listaFotografias = listaFotografias;
	}

	/**
	 * @return the fotoDescarga
	 */
	public StreamedContent getFotoDescarga() {
		return fotoDescarga;
	}

	/**
	 * @param fotoDescarga
	 *            the fotoDescarga to set
	 */
	public void setFotoDescarga(StreamedContent fotoDescarga) {
		this.fotoDescarga = fotoDescarga;
	}

	/**
	 * @return the timezone
	 */
	public TimeZone getTimezone() {
		return timezone;
	}

	/**
	 * @param timezone
	 *            the timezone to set
	 */
	public void setTimezone(TimeZone timezone) {
		this.timezone = timezone;
	}

	/**
	 * @return the tipoBateria
	 */
	public String getTipoBateria() {
		return tipoBateria;
	}

	/**
	 * @param tipoBateria
	 *            the tipoBateria to set
	 */
	public void setTipoBateria(String tipoBateria) {
		this.tipoBateria = tipoBateria;
	}

	/**
	 * @return the refrescar
	 */
	public boolean isRefrescar() {
		return refrescar;
	}

	/**
	 * @param refrescar
	 *            the refrescar to set
	 */
	public void setRefrescar(boolean refrescar) {
		this.refrescar = refrescar;
	}

	/**
	 * @return the valBusPro
	 */
	public String getValBusPro() {
		return valBusPro;
	}

	/**
	 * @param valBusPro
	 *            the valBusPro to set
	 */
	public void setValBusPro(String valBusPro) {
		this.valBusPro = valBusPro;
	}

	/**
	 * MÈtodo para guardar el seguimiento
	 */
	public void guardar() {
		try {
			if (productoSel != null) {
				seguimientoSel = adminSeguimiento.buscarSeguimientoActual(productoSel);
				if (!seguimientoSel.getSegEstado()
						.equals(usuarioSesion.getEntidad().getTipoEntidad().getTipentNombre().toUpperCase())) {
					if (verificarEstadoPadre()) {
						Calendar fechaSeguimientoAnt = Calendar.getInstance();
						fechaSeguimientoAnt.setTime(seguimientoSel.getSegFecha());

						Calendar fechaSeguimientoAct = Calendar.getInstance();
						fechaSeguimientoAct.setTime(new Date());

						int tieMesTra = fechaSeguimientoAct.get(Calendar.MONTH)
								- fechaSeguimientoAnt.get(Calendar.MONTH);
						int vidaUtil = seguimientoSel.getSegVidaUtil() - tieMesTra;

						if (vidaUtil > 0) {
							seguimiento.setSegEstado(usuarioSesion.getEntidad().getTipoEntidad().getTipentNombre());
							seguimiento.setProducto(seguimientoSel.getProducto());
							seguimiento.setSegFecha(new Date());
							seguimiento.setSegMantenimiento(productoSel.getParametro().getParMantenimiento());
							seguimiento.setComentarioSeguimientos(listaComentarios);
							seguimiento.setFotografiasSeguimientos(listaFotografias);
							seguimiento.setUsuario(usuarioSesion);

							// Refrescamiento
							if (refrescar) {
								if (seguimiento.getProducto().getProdFechaVenta() == null) {
									if (seguimiento.getProducto().getProdContadorRefrescar() < 2) {
										seguimiento.getProducto().setProdContadorRefrescar(
												seguimiento.getProducto().getProdContadorRefrescar() + 1);
										seguimiento.setSegFechaRefrescar(new Date());
										if (tipoBateria.equals("REFRESCAMIENTO")) {
											seguimiento.setSegTiempoRefrescar(
													seguimiento.getProducto().getParametro().getParRefrescamiento());
											vidaUtil = vidaUtil
													+ seguimiento.getProducto().getParametro().getParRefrescamiento();
										} else {
											seguimiento.setSegTiempoRefrescar(0);
										}
									} else {
										throw new Exception("El producto ya cumplio con su refrescamiento, n˙mero 2.");
									}
								} else {
									throw new Exception("No se puede refrescar un producto vendido");
								}
							}
							seguimiento.setSegActual(1);
							seguimiento.setSegVidaUtil(vidaUtil);
							adminSeguimiento.guardar(seguimiento);

							resetearFormulario();
						} else {
							throw new Exception(
									"El producto ya no cuenta con vida ˙til, no se puede realizar tracking");
						}
					}
				} else {
					throw new Exception("El producto ya fue hecho tracking por el estado:"
							+ usuarioSesion.getEntidad().getTipoEntidad().getTipentNombre().toUpperCase());
				}
			} else {
				throw new Exception("Se debe seleccionar un producto para realizar el tracking");
			}
		} catch (Exception e) {
			addError("No se pudo registrar el tracking:" + e.getMessage());
		}

	}

	/**
	 * MÈtodo para verificar que el producto haya pasado por el inmediato
	 * superior en el tracking
	 * 
	 * @return
	 * @throws Exception
	 */
	private boolean verificarEstadoPadre() throws Exception {
		boolean valEnt = false;
		try {
			List<Entidad> entidadesAsociadas = adminRelacionEnt
					.buscarEntidadesAsociadas(usuarioSesion.getEntidad().getIdEntidad());
			for (Entidad entidad : entidadesAsociadas) {
				if (seguimientoSel.getSegEstado().equals(entidad.getTipoEntidad().getTipentNombre().toUpperCase())) {
					valEnt = true;
					break;
				}
			}
			if (!valEnt) {
				throw new Exception("Se debe realizar el tracking siguiendo la jerarquÌa, estado seguimiento:"
						+ seguimientoSel.getSegEstado());
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return valEnt;
	}

	/**
	 * MÈtodo para seleccionar un producto
	 * 
	 * @param ev
	 */
	public void seleccionarProducto(SelectEvent ev) {
		try {
			this.productoSel = (Producto) ev.getObject();
			this.listaSeguimiento = adminSeguimiento.buscar(productoSel);
			// Cargar los refrescamientos
			this.tipoBateria = productoSel.getTipoBateria().getTipbatNombre();
		} catch (Exception e) {
			addError("No se pudo cargar el tracking del producto seleccionado:" + e.getMessage());
		}
	}

	/**
	 * MÈtodo para seleccionar un tracking
	 * 
	 * @param ev
	 */
	public void seleccionarTracking(SelectEvent ev) {
		this.seguimientoSel = (Seguimiento) ev.getObject();
	}

	/**
	 * MÈtodo para seleccionar un comentario
	 * 
	 * @param ev
	 */
	public void seleccionarComentario(SelectEvent ev) {
		this.comentarioSel = (ComentarioSeguimiento) ev.getObject();
	}

	/**
	 * MÈtodo para procesar la descarga
	 * 
	 * @param fotoSel
	 */
	public void procesarDescarga(FotografiasSeguimiento fotoSel) {
		String[] conAr = fotoSel.getFsegNombre().split("\\.");
		String mimeType = "image/png";
		if (conAr[1].equals("jpg")) {
			mimeType = "image/jpg";
		} else {
			mimeType = "image/png";
		}
		InputStream stream = FacesContext.getCurrentInstance().getExternalContext()
				.getResourceAsStream(fotoSel.getFsegPath());
		this.fotoDescarga = new DefaultStreamedContent(stream, mimeType, fotoSel.getFsegNombre());
	}

	/**
	 * MÈtodo para eliminar una fotografia
	 * 
	 * @param fotoSel
	 */
	public void eliminarFotografia(FotografiasSeguimiento fotoSel) {
		this.listaFotografias.remove(fotoSel);
	}

	/**
	 * MÈtodo para a√±adir comentario
	 */
	public void anadirComentario() {
		comentario.setIdTmp(++idTmp);
		this.listaComentarios.add(comentario);
		comentario = new ComentarioSeguimiento();
	}

	/**
	 * MÈtodo para eliminar comentario
	 */
	public void eliminarComentario() {
		if (comentarioSel != null) {
			this.listaComentarios.remove(comentarioSel);
		}

	}

	/**
	 * MÈtodo para subir un archivo de tracking
	 * 
	 * @param event
	 */
	public void subirArchivo(FileUploadEvent event) {
		FacesMessage msg = new FacesMessage("Archivo: " + event.getFile().getFileName() + " cargado correctamente.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		try {
			File archivoReunion = guardaBlobEnFicheroTemporal(event.getFile().getFileName(),
					event.getFile().getInputstream());
			FotografiasSeguimiento archivo = new FotografiasSeguimiento();
			archivo.setIdTmp(++idTmp);
			archivo.setFsegNombre(archivoReunion.getName().length() >= 50
					? archivoReunion.getName().substring(0, 47).concat("...") : archivoReunion.getName());
			archivo.setFsegPath("/resources/img/tmp/" + archivoReunion.getName());
			archivo.setSeguimiento(seguimiento);
			archivo.setFsegReal(event.getFile().getContents());
			this.listaFotografias.add(archivo);
		} catch (IOException e) {
			addError(e.getMessage());
		} catch (Exception e) {
			addError(e.getMessage());
		}
	}

	/**
	 * MÈtodo para guardar el fichero en un archivo temporal
	 * 
	 * @param archivo
	 * @return
	 * @throws Exception
	 */
	private File guardaBlobEnFicheroTemporal(String nombreArchivo, InputStream datosArchivo) throws Exception {
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
				.getContext();
		String path = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "img"
				+ File.separator + "tmp" + File.separator + nombreArchivo;

		File f = new File(path);
		FileOutputStream out = new FileOutputStream(f.getAbsolutePath());

		int read = 0;
		byte[] bytes = new byte[1024];

		while ((read = datosArchivo.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		out.flush();
		out.close();
		return f;
	}

	/**
	 * MÈtodo para buscar los productos por un valor de b√∫squeda
	 */
	public void buscarProductos() {
		try {
			this.idsFabricantes.clear();
			idsFabricantes.add("0");
			idsFabricantes.addAll(recuperarFabricantes(usuarioSesion.getEntidad()));
			// Busco los productos de las entidades asociadas
			this.listaProductos = adminProducto.buscarProductos(idsFabricantes, valBusPro);
		} catch (Exception e) {
			addError("No se pudo cargar los productos:" + e.getMessage());
		}
	}

	/**
	 * MÈtodo para recuperar los ids fabricantes que se encuentra asociado el
	 * usuario
	 * 
	 * @param entidad
	 * @param idsFabricantes
	 * @return
	 */
	private List<String> recuperarFabricantes(Entidad entidad) {
		List<String> idEnt = new ArrayList<>();
		try {
			if (!entidad.getTipoEntidad().getTipentNombre().equals("FABRICA")) {
				for (Entidad entTmp : adminRelacionEntidad.buscarEntidadesAsociadas(entidad.getIdEntidad())) {
					idEnt.addAll(recuperarFabricantes(entTmp));
				}
			} else {
				idEnt.add(String.valueOf(entidad.getIdEntidad()));
			}
		} catch (Exception e) {
			addError("Error al recuperar fabricantes:" + e.getMessage());
		}

		return idEnt;
	}

	/**
	 * MÈtodo para devolver el tracking al estado anterior
	 */
	public void devolver() {
		try {
			if (seguimientoSel != null) {

				// Verificar si el estado del tracking est√° en el estado del
				// usuario
				if (seguimientoSel.getSegEstado()
						.equals(usuarioSesion.getEntidad().getTipoEntidad().getTipentNombre())) {
					if (seguimientoSel.getSegActual() == 1) {
						// Eliminar el tracking y actualizar el anterior como
						// actual
						adminSeguimiento.devolver(seguimientoSel);
						// Actualizar la lista
						this.listaSeguimiento = adminSeguimiento.buscar(productoSel);

						addInfo("Se ha realizado la devoluci√≥n satisfactoriamente");
					} else {
						throw new Exception("El tracking a devolver no es el actual");
					}
				} else {
					throw new Exception("El tracking seleccionado no le pertenece, su rol es:"
							+ usuarioSesion.getEntidad().getTipoEntidad().getTipentNombre());
				}
			} else {
				addError("Se debe seleccionar un tracking!!");
			}
		} catch (Exception e) {
			addError("Error Devolver:" + e.getMessage());
		}
	}

	/**
	 * MÈtodo para resetear el formulario
	 */
	public void resetearFormulario() {
		this.idEnt = 0;
		this.seguimiento = new Seguimiento();
		this.productoSel = null;
		this.seguimientoSel = null;
		this.comentario = new ComentarioSeguimiento();
		this.comentarioSel = null;
		this.idTmp = 0;
		this.listaComentarios.clear();
		this.listaFotografias.clear();
		this.listaSeguimiento.clear();
		this.refrescar = false;
		this.valBusPro = null;
		this.listaProductos.clear();
	}

	/**
	 * MÈtodo para inicializar el formulario
	 */
	@PostConstruct
	public void inicializar() {
		if (usuarioSesion == null) {
			try {
				ExternalContext ec = getExternalContext();
				ec.redirect(ec.getRequestContextPath() + "/inicio.mat");
			} catch (IOException e) {
				throw new FacesException(e);
			}
		}
	}
	
}
