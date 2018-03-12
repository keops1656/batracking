package com.matoosfe.batracking.bean.seguimiento;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import com.matoosfe.batracking.bean.seguridad.LoginBean;
import com.matoosfe.batracking.modelo.ClienteFinal;
import com.matoosfe.batracking.modelo.Entidad;
import com.matoosfe.batracking.modelo.Seguimiento;
import com.matoosfe.batracking.modelo.Usuario;
import com.matoosfe.batracking.negocio.admin.ProductoFacade;
import com.matoosfe.batracking.negocio.seguimiento.SeguimientoFacade;
import com.matoosfe.batracking.negocio.seguridad.RelacionEntidadFacade;
import com.matoosfe.kernel.web.bean.AbstractManagedBean;

/**
 * Clase para administrar la pantalla de vendedor
 * 
 * @author MATOOSFE - Ing. Marco Toscano Freire. Msc -martosfre 04 oct. 2017-
 *         12:38:12<br>
 *         <a href="mailto:mtoscano@matoosfe.com?Subject=Soporte Batracking"
 *         target="_top">Soporte</a><br>
 *         <a href="http://www.matoosfe.com">Matoosfe</a>
 */
@ManagedBean
@ViewScoped
public class VendedorBean extends AbstractManagedBean {

	private Usuario usuarioSesion;
	private int idEnt;
	private List<Seguimiento> listaSeguimiento;
	private Seguimiento seguimientoSel;
	private TimeZone timezone;
	private String tipTra;
	private Date fechaTra;
	private ClienteFinal clienteFinal;
	private List<SelectItem> listaTransacciones;

	@EJB
	private RelacionEntidadFacade adminRelacionEnt;
	@EJB
	private SeguimientoFacade adminSeguimiento;
	@EJB
	private RelacionEntidadFacade adminRelacionEntidad;
	@EJB
	private ProductoFacade adminProducto;

	public VendedorBean() {
		this.listaSeguimiento = new ArrayList<>();
		this.timezone = TimeZone.getDefault();
		this.fechaTra = new Date();
		this.clienteFinal = new ClienteFinal();
		this.listaTransacciones = new ArrayList<>();
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
	 * @return the tipTra
	 */
	public String getTipTra() {
		return tipTra;
	}

	/**
	 * @param tipTra
	 *            the tipTra to set
	 */
	public void setTipTra(String tipTra) {
		this.tipTra = tipTra;
	}

	/**
	 * @return the fechaTra
	 */
	public Date getFechaTra() {
		return fechaTra;
	}

	/**
	 * @param fechaTra
	 *            the fechaTra to set
	 */
	public void setFechaTra(Date fechaTra) {
		this.fechaTra = fechaTra;
	}

	/**
	 * @return the clienteFinal
	 */
	public ClienteFinal getClienteFinal() {
		return clienteFinal;
	}

	/**
	 * @param clienteFinal
	 *            the clienteFinal to set
	 */
	public void setClienteFinal(ClienteFinal clienteFinal) {
		this.clienteFinal = clienteFinal;
	}

	/**
	 * @return the listaTransacciones
	 */
	public List<SelectItem> getListaTransacciones() {
		return listaTransacciones;
	}

	/**
	 * @param listaTransacciones
	 *            the listaTransacciones to set
	 */
	public void setListaTransacciones(List<SelectItem> listaTransacciones) {
		this.listaTransacciones = listaTransacciones;
	}

	/**
	 * Método para guardar la tx del vendedor
	 */
	public void guardar() {
		try {
			if (seguimientoSel != null) {
				if (tipTra.equals("V")) {
					seguimientoSel.getProducto().setProdFechaVenta(fechaTra);
					seguimientoSel.getProducto().setProdEstadoBateria("VENDIDO");
				} else if (tipTra.equals("M")) {
					Calendar fechaVenta = Calendar.getInstance();
					fechaVenta.setTime(seguimientoSel.getProducto().getProdFechaVenta());

					Calendar fechaMantenimiento = Calendar.getInstance();
					fechaMantenimiento.setTime(fechaTra);
					fechaMantenimiento.add(Calendar.MONTH,
							seguimientoSel.getProducto().getParametro().getParMantenimiento());
					if (fechaTra.compareTo(fechaMantenimiento.getTime()) < 0) {
						seguimientoSel.getProducto().setProdFechaMantenimiento(fechaTra);
						seguimientoSel.getProducto().setProdEstadoBateria("MANTENIMIENTO");
					} else {
						throw new Exception("Ya paso la fecha de mantenimiento:" + fechaMantenimiento.getTime().toString());
					}
				} else {
					seguimientoSel.getProducto().setProdFechaRepone(fechaTra);
					seguimientoSel.getProducto().setProdEstadoBateria("GARANTÏA");
				}
				adminSeguimiento.guardarTxVendedor(seguimientoSel, clienteFinal);
				addInfo("Transacción guardada correctamente");
				resetearFormulario();
			} else {
				addError("Se debe seleccionar un producto para realizar la transacción");
			}
		} catch (Exception e) {
			addError("No se pudo registrar la transacción:" + e.getMessage());
		}

	}

	/**
	 * MÃ©todo para seleccionar un producto
	 * 
	 * @param ev
	 */
	public void seleccionarProducto(SelectEvent ev) {
		try {
			this.seguimientoSel = (Seguimiento) ev.getObject();
			this.seguimientoSel = adminSeguimiento.buscarPorId(seguimientoSel.getIdSeguimiento());
			// Cargar datos cliente, vehÃ­culo
			if (!seguimientoSel.getClienteFinals().isEmpty()) {
				clienteFinal = seguimientoSel.getClienteFinals().get(0);
			}
			// Cargar transacciones vendedor segÃºn seguimiento
			this.listaTransacciones.clear();
			if (seguimientoSel.getProducto().getProdFechaVenta() == null) {
				this.listaTransacciones.add(new SelectItem("V", "Vendido"));
			} else {
				this.listaTransacciones.add(new SelectItem("M", "Mantenimiento"));
				this.listaTransacciones.add(new SelectItem("G", "Garantía"));
			}
		} catch (Exception e) {
			addError("No se pudo cargar el producto:" + e.getMessage());
		}
	}

	/**
	 * MÃ©todo para resetear el formulario
	 */
	public void resetearFormulario() {
		this.idEnt = 0;
		this.seguimientoSel = null;
		this.listaSeguimiento.clear();
	}

	/**
	 * MÃ©todo para inicializar el formulario
	 */
	@PostConstruct
	public void inicializar() {
		if (usuarioSesion != null) {
			try {
				// Recupero las entidades asociadas al usuario
				List<String> idsFabricantes = new ArrayList<String>();
				idsFabricantes.add("0");
				idsFabricantes.addAll(recuperarFabricantes(usuarioSesion.getEntidad()));
				// Busco los productos de las entidades asociadas
				listaSeguimiento = adminSeguimiento.buscar(usuarioSesion.getIdUsuario());
			} catch (Exception e) {
				addError("No se pudo cargar los productos:" + e.getMessage());
			}
		} else {
			try {
				ExternalContext ec = getExternalContext();
				ec.redirect(ec.getRequestContextPath() + "/inicio.mat");
			} catch (IOException e) {
				throw new FacesException(e);
			}
		}
	}

	/**
	 * MÃ©todo para recuperar los ids fabricantes que se encuentra asociado el
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

}
