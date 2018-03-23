package com.matoosfe.batracking.ws;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import com.matoosfe.batracking.entidad.EComentarioSeguimiento;
import com.matoosfe.batracking.entidad.EEspecificacion;
import com.matoosfe.batracking.entidad.EProducto;
import com.matoosfe.batracking.modelo.ComentarioSeguimiento;
import com.matoosfe.batracking.modelo.EspecificacionBateria;
import com.matoosfe.batracking.modelo.Producto;
import com.matoosfe.batracking.modelo.Seguimiento;
import com.matoosfe.batracking.negocio.seguimiento.ComentarioSeguimientoFacade;
import com.matoosfe.batracking.negocio.seguimiento.SeguimientoFacade;
import com.matoosfe.batracking.negocio.admin.EspecificacionFacade;
import com.matoosfe.batracking.negocio.admin.ProductoFacade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.PathParam;
//import org.json.JSONObject;

@Stateless
@Path("wsEspecificacion")
public class wsEspecificacion {
	
//	@EJB
//	private ProductoFacade productoEJB;
//	
//	@EJB
//	private SeguimientoFacade adminSeguimiento;
	
	@EJB
	private EspecificacionFacade especificacionFacade;
	
	
	
	@GET
    @Produces("application/json; charset=utf-8")
    @Path("listarEspecificaciones")
    public ArrayList<EEspecificacion> listarEspecificaciones() throws Exception {
			List<EspecificacionBateria> lstEspecificaciones = null;
			ArrayList<EEspecificacion> lstEEspecificaciones = new ArrayList<>();
			try {
				lstEspecificaciones = especificacionFacade.listarEspecificaciones();
				if(lstEspecificaciones.size() > 0 && lstEspecificaciones != null) {
					for (EspecificacionBateria objEspecificacionBateria : lstEspecificaciones) {
						System.out.println("ID ESPECIFICACION: " + objEspecificacionBateria.getId());
						EEspecificacion objEEspecificacion = new EEspecificacion(objEspecificacionBateria);
						lstEEspecificaciones.add(objEEspecificacion);
					}
				}
			}catch(Exception e) {
				System.out.println("Expecion >>" + e.getMessage());
				lstEEspecificaciones.clear();
			}
		return lstEEspecificaciones;
    }	


}
