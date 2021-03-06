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
import com.matoosfe.batracking.modelo.Pallet;
import com.matoosfe.batracking.modelo.TipoEntidad;
import com.matoosfe.batracking.modelo.Usuario;
import com.matoosfe.batracking.negocio.admin.EntidadFacade;
import com.matoosfe.batracking.negocio.admin.ProductoFacade;
import com.matoosfe.batracking.negocio.seguridad.TipoEntidadFacade;
import com.matoosfe.kernel.web.bean.AbstractManagedBean;


@ManagedBean
@ViewScoped
public class RepCodigosBean extends AbstractManagedBean{
	
	private Usuario usuarioSesion;
	private int idPallet;
	private List<SelectItem> listaPalletsFabrica;
//	private int idEnt;
//	private List<SelectItem> listaEntidades;
	
	@EJB
	private TipoEntidadFacade adminTipoEntidad;
	@EJB
	private EntidadFacade adminEntidad;	
	@EJB
	private ProductoFacade adminProducto;	
	
	private String ruta;
	
	
	
	public RepCodigosBean() {
		this.listaPalletsFabrica = new ArrayList<>();
		this.usuarioSesion = ((LoginBean) recuperarParametroSession("loginBean")).getUsuario();
	}


	public int getIdPallet() {
		return idPallet;
	}

	

	public String getRuta() {
		return ruta;
	}


	public void setRuta(String ruta) {
		this.ruta = ruta;
	}


	public void setIdPallet(int idPallet) {
		this.idPallet = idPallet;
	}


	public List<SelectItem> getListaPalletsFabrica() {
		return listaPalletsFabrica;
	}


	public void setListaPalletsFabrica(List<SelectItem> listaTipoEntidades) {
		this.listaPalletsFabrica = listaTipoEntidades;
	}


//	public int getIdEnt() {
//		return idEnt;
//	}


//	public void setIdEnt(int idEnt) {
//		this.idEnt = idEnt;
//	}

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
		this.idPallet = 0;
	}
	
	@PostConstruct
	public void inicializar() {
		if (usuarioSesion != null) {
			cargarPalletsFabrica();
		}else {
			try {
				ExternalContext ec = getExternalContext();
				ec.redirect(ec.getRequestContextPath() + "/inicio.mat");
			} catch (IOException e) {
				throw new FacesException(e);
			}
		}

	}
	
	private void cargarPalletsFabrica() {
		try {
			this.listaPalletsFabrica.clear();
			List<Pallet> lstPallets = adminProducto.buscarPalletsDadoFabrica(usuarioSesion.getEntidad().getIdEntidad());
			for (Pallet pal : lstPallets) {
				this.listaPalletsFabrica.add(new SelectItem(pal.getIdPallet(), pal.getPallCodigo()));
			}
		} catch (Exception e) {
			addError("No se pudo cargar los pallets de la f�brica:" + e.getMessage());
		}
	}
	
	/************Reporte*************/
	public void verReporte() throws SQLException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        RepModel rep = new RepModel();
        String Ruta = servletContext.getRealPath("//pages//reportes//rQr.jasper");
        FacesContext.getCurrentInstance().responseComplete();
        
        rep.getReporteCodigo(Ruta, this.idPallet);
        System.out.println("File:" + this.idPallet);
	}
}
