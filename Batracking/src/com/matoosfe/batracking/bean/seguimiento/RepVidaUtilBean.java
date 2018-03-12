package com.matoosfe.batracking.bean.seguimiento;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;

import com.matoosfe.batracking.bean.seguridad.LoginBean;
import com.matoosfe.batracking.modelo.Entidad;
import com.matoosfe.batracking.modelo.Producto;
import com.matoosfe.batracking.modelo.Seguimiento;
import com.matoosfe.batracking.modelo.TipoEntidad;
import com.matoosfe.batracking.modelo.Usuario;
import com.matoosfe.batracking.modelo.dto.DashboardDto;
import com.matoosfe.batracking.modelo.dto.ReporteDto;
import com.matoosfe.batracking.negocio.admin.EntidadFacade;
import com.matoosfe.batracking.negocio.seguimiento.SeguimientoFacade;
import com.matoosfe.batracking.negocio.seguridad.TipoEntidadFacade;
import com.matoosfe.kernel.web.bean.AbstractManagedBean;

/**
 * Clase para administrar reporte de vida útil
 * 
 * @author MATOOSFE - Ing. Marco Toscano Freire. Msc -martosfre 20 ago. 2017-
 *         12:38:12<br>
 *         <a href="mailto:mtoscano@matoosfe.com?Subject=Soporte Batracking"
 *         target="_top">Soporte</a><br>
 *         <a href="http://www.matoosfe.com">Matoosfe</a>
 */
@ManagedBean
@ViewScoped
public class RepVidaUtilBean extends AbstractManagedBean {

	private Usuario usuarioSesion;

	private List<ReporteDto> listaSeguimientoBus;
	private List<DashboardDto> panelesSumarizados;
	private int idTipEnt;
	private List<SelectItem> listaTipoEntidades;
	private int idEnt;
	private List<SelectItem> listaEntidades;
	private DashboardDto dashboardSel;
	private List<Seguimiento> detallesSeg;

	@EJB
	private SeguimientoFacade adminSeguimiento;
	@EJB
	private TipoEntidadFacade adminTipoEntidad;
	@EJB
	private EntidadFacade adminEntidad;

	public RepVidaUtilBean() {
		this.listaSeguimientoBus = new ArrayList<>();
		this.panelesSumarizados = new ArrayList<>();
		this.listaTipoEntidades = new ArrayList<>();
		this.listaEntidades = new ArrayList<>();
		this.detallesSeg = new ArrayList<>();

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
	 * @return the listaSeguimientoBus
	 */
	public List<ReporteDto> getListaSeguimientoBus() {
		return listaSeguimientoBus;
	}

	/**
	 * @param listaSeguimientoBus
	 *            the listaSeguimientoBus to set
	 */
	public void setListaSeguimientoBus(List<ReporteDto> listaSeguimientoBus) {
		this.listaSeguimientoBus = listaSeguimientoBus;
	}

	/**
	 * @return the panelesSumarizados
	 */
	public List<DashboardDto> getPanelesSumarizados() {
		return panelesSumarizados;
	}

	/**
	 * @param panelesSumarizados
	 *            the panelesSumarizados to set
	 */
	public void setPanelesSumarizados(List<DashboardDto> panelesSumarizados) {
		this.panelesSumarizados = panelesSumarizados;
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

	/**
	 * @return the dashboardSel
	 */
	public DashboardDto getDashboardSel() {
		return dashboardSel;
	}

	/**
	 * @param dashboardSel
	 *            the dashboardSel to set
	 */
	public void setDashboardSel(DashboardDto dashboardSel) {
		this.dashboardSel = dashboardSel;
	}

	/**
	 * @return the detallesSeg
	 */
	public List<Seguimiento> getDetallesSeg() {
		return detallesSeg;
	}

	/**
	 * @param detallesSeg
	 *            the detallesSeg to set
	 */
	public void setDetallesSeg(List<Seguimiento> detallesSeg) {
		this.detallesSeg = detallesSeg;
	}

	/**
	 * Método para guardar el seguimiento
	 */
	public void guardar() {

	}

	/**
	 * Método para buscar un producto
	 */
	public void buscar() {
		panelesSumarizados = adminSeguimiento.contabilizarVidaUtil(idEnt);
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
		this.listaSeguimientoBus.clear();
		this.panelesSumarizados.clear();
		TipoEntidad tipoEnt = adminTipoEntidad.buscarPorId(idTipEnt);
		for (Entidad ent : adminEntidad.buscarEntidadPorTipo(tipoEnt.getTipentNombre().toUpperCase())) {
			this.listaEntidades.add(new SelectItem(ent.getIdEntidad(), ent.getEntNombre()));
		}
	}

	/**
	 * Método para resetear el formulario
	 */
	public void resetearFormulario() {
		this.idTipEnt = 0;
		this.idEnt = 0;
	}

	/**
	 * Método para cargar los detalles del reporte
	 */
	public void cargarDetalleReporte() {
		try {
			this.listaSeguimientoBus = dashboardSel.getListaProductosReporte();
		} catch (Exception e) {
			addError("Error al cargar los productos:" + e.getMessage());
		}
	}

	/**
	 * Método para cargar los detalles del seguimiento
	 */
	public void cargarDetallesSeguimiento(Producto producto) {
		try {
			this.detallesSeg = adminSeguimiento.buscar(producto);
			RequestContext.getCurrentInstance().execute("PF('diaSegPro').show()");
		} catch (Exception e) {
			addError("Error al cargar los detalles de seguimiento:" + e.getMessage());
		}
	}

	/**
	 * Método para inicializar el formulario
	 */
	@PostConstruct
	public void inicializar() {
		if (usuarioSesion != null) {

			cargarTiposEntidad();

			this.panelesSumarizados.clear();
			DashboardDto panRepRoj = new DashboardDto();
			panRepRoj.setEtiqueta("Ya se paso el tiempo");
			panRepRoj.setValor(0);
			panRepRoj.setColor("Rojo");

			DashboardDto panRepAma = new DashboardDto();
			panRepAma.setEtiqueta("<= 1 de Vida útil");
			panRepAma.setValor(0);
			panRepAma.setColor("Amarillo");

			DashboardDto panRepVer = new DashboardDto();
			panRepVer.setEtiqueta("> 1 mes Vida Útil");
			panRepVer.setValor(0);
			panRepVer.setColor("Verde");
			this.panelesSumarizados.add(panRepRoj);
			this.panelesSumarizados.add(panRepAma);
			this.panelesSumarizados.add(panRepVer);
		} else {
			try {
				ExternalContext ec = getExternalContext();
				ec.redirect(ec.getRequestContextPath() + "/inicio.mat");
			} catch (IOException e) {
				throw new FacesException(e);
			}
		}
	}

}
