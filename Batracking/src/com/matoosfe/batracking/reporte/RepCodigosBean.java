package com.matoosfe.batracking.reporte;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;

import com.matoosfe.batracking.bean.seguridad.LoginBean;
import com.matoosfe.batracking.modelo.Entidad;
import com.matoosfe.batracking.modelo.TipoEntidad;
import com.matoosfe.batracking.modelo.Usuario;
import com.matoosfe.batracking.negocio.admin.EntidadFacade;
import com.matoosfe.batracking.negocio.seguridad.TipoEntidadFacade;
import com.matoosfe.kernel.web.bean.AbstractManagedBean;


@ManagedBean
@ViewScoped
public class RepCodigosBean extends AbstractManagedBean{
	
	private Usuario usuarioSesion;
	private int idTipEnt;
	private List<SelectItem> listaTipoEntidades;
	private int idEnt;
	private List<SelectItem> listaEntidades;
	
	@EJB
	private TipoEntidadFacade adminTipoEntidad;
	@EJB
	private EntidadFacade adminEntidad;	
	
	private String ruta;
	
	
	
	public RepCodigosBean() {
		this.listaTipoEntidades = new ArrayList<>();
		this.listaEntidades = new ArrayList<>();
		this.usuarioSesion = ((LoginBean) recuperarParametroSession("loginBean")).getUsuario();
	}


	public int getIdTipEnt() {
		return idTipEnt;
	}

	

	public String getRuta() {
		return ruta;
	}


	public void setRuta(String ruta) {
		this.ruta = ruta;
	}


	public void setIdTipEnt(int idTipEnt) {
		this.idTipEnt = idTipEnt;
	}


	public List<SelectItem> getListaTipoEntidades() {
		return listaTipoEntidades;
	}


	public void setListaTipoEntidades(List<SelectItem> listaTipoEntidades) {
		this.listaTipoEntidades = listaTipoEntidades;
	}


	public int getIdEnt() {
		return idEnt;
	}


	public void setIdEnt(int idEnt) {
		this.idEnt = idEnt;
	}


	public List<SelectItem> getListaEntidades() {
		return listaEntidades;
	}


	public void setListaEntidades(List<SelectItem> listaEntidades) {
		this.listaEntidades = listaEntidades;
	}


	public TipoEntidadFacade getAdminTipoEntidad() {
		return adminTipoEntidad;
	}


	public void setAdminTipoEntidad(TipoEntidadFacade adminTipoEntidad) {
		this.adminTipoEntidad = adminTipoEntidad;
	}


	public EntidadFacade getAdminEntidad() {
		return adminEntidad;
	}


	public void setAdminEntidad(EntidadFacade adminEntidad) {
		this.adminEntidad = adminEntidad;
	}

	
	
	public Usuario getUsuarioSesion() {
		return usuarioSesion;
	}


	public void setUsuarioSesion(Usuario usuarioSesion) {
		this.usuarioSesion = usuarioSesion;
	}


	public void resetearFormulario() {
		this.idTipEnt = 0;
		this.idEnt = 0;
	}
	
	@PostConstruct
	public void inicializar() {
		if (usuarioSesion != null) {
			cargarTiposEntidad();
		}else {
			try {
				ExternalContext ec = getExternalContext();
				ec.redirect(ec.getRequestContextPath() + "/inicio.mat");
			} catch (IOException e) {
				throw new FacesException(e);
			}
		}

	}
	
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
	
	public void cargarEntidadesPadre() {
		this.listaEntidades.clear();
		TipoEntidad tipoEnt = adminTipoEntidad.buscarPorId(idTipEnt);
		for (Entidad ent : adminEntidad.buscarEntidadPorTipo(tipoEnt.getTipentNombre().toUpperCase())) {
			this.listaEntidades.add(new SelectItem(ent.getIdEntidad(), ent.getEntNombre()));
		}
	}	
	
	/************Reporte*************/
	public void verReporte() throws SQLException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        RepModel rep = new RepModel();
        String Ruta = servletContext.getRealPath("//pages//reportes//rQr.jasper");
        FacesContext.getCurrentInstance().responseComplete();
        
        rep.getReporteCodigo(Ruta, this.idEnt);
        System.out.print("File:" + this.idEnt);
	}
}
