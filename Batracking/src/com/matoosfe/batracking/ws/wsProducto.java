package com.matoosfe.batracking.ws;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import com.matoosfe.batracking.entidad.EComentarioSeguimiento;
import com.matoosfe.batracking.entidad.EProducto;
import com.matoosfe.batracking.modelo.ComentarioSeguimiento;
import com.matoosfe.batracking.modelo.Producto;
import com.matoosfe.batracking.modelo.Seguimiento;
import com.matoosfe.batracking.negocio.seguimiento.ComentarioSeguimientoFacade;
import com.matoosfe.batracking.negocio.seguimiento.SeguimientoFacade;
import com.matoosfe.batracking.negocio.admin.ProductoFacade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.PathParam;
//import org.json.JSONObject;

@Stateless
@Path("wsProducto")
public class wsProducto {
	
	@EJB
	private ProductoFacade productoEJB;
	
	@EJB
	private SeguimientoFacade adminSeguimiento;
	
	@EJB
	private ComentarioSeguimientoFacade comSegEJB;
	
	/*Detalle de un producto dado Código*/
	@GET
    @Produces("application/json; charset=utf-8")
    @Path("buscarProductoDadoCodigo/{codigoProducto}")
    public EProducto buscarProductoDadoCodigo(
    		@PathParam("codigoProducto") String codigoProducto
    ) throws Exception {
			EProducto objEProducto = new EProducto();
			Producto producto = new Producto();
			try {
				producto = productoEJB.buscarProductoDadoCodigo(codigoProducto);
				if(producto != null) {
					/*
					 * Consulto si hay un seguimiento com un regresh de una bateria
					 * */
					Seguimiento seguimientoRefresh = adminSeguimiento.buscarSeguimientoUltimoRefresh(producto);
					System.out.println("ACDC 1");
					Seguimiento seguimientoRecepcion = null;
					if( seguimientoRefresh != null) {
						Seguimiento seguimientoActual = adminSeguimiento.buscarSeguimientoActual(producto);
						System.out.println("ACDC 2 A");
						seguimientoRecepcion = adminSeguimiento.buscarSeguimientoRecepcion(producto, seguimientoActual.getUsuario().getIdUsuario());
						System.out.println("ACDC 3");
					}else {
						System.out.println("ACDC 2 B");
						seguimientoRecepcion = adminSeguimiento.buscarSeguimientoActual(producto);
					}
					System.out.println("ACDC 5");
					objEProducto = new EProducto(producto, seguimientoRefresh, seguimientoRecepcion);
				}
			}catch(Exception e) {
				System.out.println("Expecion >>" + e.getMessage());
				objEProducto.setIdProducto(-1);
			}
		return objEProducto;
    }	
	
	@GET
    @Produces("application/json; charset=utf-8")
    @Path("obtenerComentariosProductoDadoCodigo/{codigoProducto}")
    public ArrayList<EComentarioSeguimiento> obtenerComentariosProductoDadoCodigo(
    		@PathParam("codigoProducto") String codigoProducto
    ) throws Exception {
			List<ComentarioSeguimiento> lstComentarios = null;
			ArrayList<EComentarioSeguimiento> lstEComentarios = new ArrayList<>();
			try {
				lstComentarios = comSegEJB.comentariosProducto(codigoProducto);
				if(lstComentarios.size() > 0 && lstComentarios != null) {
					for (ComentarioSeguimiento objComentarioSeguimiento : lstComentarios) {
						EComentarioSeguimiento objEComSeg = new EComentarioSeguimiento(objComentarioSeguimiento);
						lstEComentarios.add(objEComSeg);
					}
				}
			}catch(Exception e) {
				System.out.println("Expecion >>" + e.getMessage());
				lstEComentarios.clear();
			}
		return lstEComentarios;
    }	

	
	@GET
    @Produces("application/json; charset=utf-8")
    @Path("obtenerBateriasDadoEspecificacion/{codEspecificacion}")
    public ArrayList<EProducto> obtenerBateriasDadoEspecificacion(
    		@PathParam("codEspecificacion") String codEspecificacion
    ) throws Exception {
			List<Producto> lstProductos = null;
			ArrayList<EProducto> lstEProductos = new ArrayList<>();
			try {
				lstProductos = productoEJB.bateriasDadoEspecificacion(codEspecificacion);
				if(lstProductos.size() > 0 && lstProductos != null) {
					for (Producto objProducto : lstProductos) {
						EProducto objEProducto = new EProducto(objProducto);
						lstEProductos.add(objEProducto);
					}
				}
			}catch(Exception e) {
				System.out.println("Expecion >>" + e.getMessage());
				lstEProductos.clear();
			}
		return lstEProductos;
    }	


}
