package com.matoosfe.batracking.ws;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import com.matoosfe.batracking.entidad.EProducto;
import com.matoosfe.batracking.entidad.EstadosMetodos;
import com.matoosfe.batracking.modelo.Entidad;
import com.matoosfe.batracking.modelo.EspecificacionBateria;
import com.matoosfe.batracking.modelo.Producto;
import com.matoosfe.batracking.modelo.RelacionEntidad;
import com.matoosfe.batracking.modelo.Seguimiento;
import com.matoosfe.batracking.modelo.Usuario;
import com.matoosfe.batracking.negocio.seguimiento.SeguimientoFacade;
import com.matoosfe.batracking.negocio.seguridad.RelacionEntidadFacade;
import com.matoosfe.batracking.negocio.admin.EntidadFacade;
import com.matoosfe.batracking.negocio.admin.EspecificacionFacade;
import com.matoosfe.batracking.negocio.admin.ProductoFacade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.PathParam;
//import org.json.JSONObject;

@Stateless
@Path("wsStock")
public class wsStock {
	
	@EJB
	private ProductoFacade productoEJB;
	
	@EJB
	private SeguimientoFacade adminSeguimiento;
	
	@EJB
	private RelacionEntidadFacade RelEntidadEJB;
	
	@EJB
	private EntidadFacade EntidadEJB;
	
	@EJB
	private EspecificacionFacade EspecificacionEJB;
	
	@GET
    @Produces("application/json; charset=utf-8")
    @Path("miStock/{idUsuario}")
    public ArrayList<EProducto> miStock(
    		@PathParam("idUsuario") int intIdUsuario
    ) throws Exception {
			ArrayList<EProducto> lstMiStock = new ArrayList<>();
			List<Producto> lstProductos = null;
			try {
				lstProductos = productoEJB.productosMiStock(intIdUsuario);
				if( lstProductos.size() > 0 && lstProductos != null) {
					for (Producto producto : lstProductos) {
						Seguimiento seguimientoRefresh = adminSeguimiento.buscarSeguimientoUltimoRefresh(producto);
						Seguimiento seguimientoRecepcion = null;
						if( seguimientoRefresh != null) {
							Seguimiento seguimientoActual = adminSeguimiento.buscarSeguimientoActual(producto);
							seguimientoRecepcion = adminSeguimiento.buscarSeguimientoRecepcion(producto, seguimientoActual.getUsuario().getIdUsuario());
						}else {
							seguimientoRecepcion = adminSeguimiento.buscarSeguimientoActual(producto);
						}
						EProducto objEProducto = new EProducto(producto, seguimientoRefresh, seguimientoRecepcion);
						lstMiStock.add(objEProducto);
					}
				}
				
			}catch(Exception e) {
				System.out.println( "Error  EXCEPCION >>>" + e.getMessage());
				lstMiStock.clear();
			}
		return lstMiStock;
    }	

	/*
	 * Buscar baterias en mi stock dado una especificacion de bateria
	 */
	@GET
    @Produces("application/json; charset=utf-8")
    @Path("buscarBateriaMiStock/{idEntidad}/{strCodigoEspecificacion}")
    public EstadosMetodos buscarBateriaMiStock(
    		@PathParam("idEntidad") int intIdEntidad,
    		@PathParam("strCodigoEspecificacion") String strCodigoEspecificacion
    ) throws Exception {
			EstadosMetodos objEstadoMetodo = new EstadosMetodos(false, "No disponible baterias con la especificacion " + strCodigoEspecificacion);
			try {
				EspecificacionBateria especificacion = EspecificacionEJB.buscarEspecificacionDadoCodigo(strCodigoEspecificacion);
			}catch(Exception e) {
				objEstadoMetodo.setStrMensaje("No existe el código de especificación:  " + strCodigoEspecificacion);
				return objEstadoMetodo;
			}
			try {
				Entidad objEntidad = EntidadEJB.buscarPorId( intIdEntidad );
				List<Usuario> lstUsuario = objEntidad.getUsuarios();
				if( lstUsuario.size() > 0 && lstUsuario != null ) {
					for (Usuario objUsuario : lstUsuario) {
						boolean existeBateria = productoEJB.comprobrarExistenciaBateriaDadoUsuarioyCodigoEspecificacion(objUsuario.getIdUsuario(), strCodigoEspecificacion);
						if(existeBateria) {
							objEstadoMetodo.setBlResultado(true);
							objEstadoMetodo.setStrMensaje("Bateria disponible");
							return objEstadoMetodo;
						}							
					}
				}
							
			}catch(Exception e) {
				objEstadoMetodo.setStrMensaje( e.getMessage());
			}
				
		return objEstadoMetodo;
    }
	
	@GET
    @Produces("application/json; charset=utf-8")
    @Path("stockFabrica/{idEntidad}/{strCodigo}")
    public EstadosMetodos stockFabrica(
    		@PathParam("idEntidad") int intIdEntidad,
    		@PathParam("strCodigo") String strCodigoEspecificacion
    ) throws Exception {
			EstadosMetodos objEstadoMetodo = new EstadosMetodos(false, "Bateria no disponible");
			ArrayList<Integer> lstFabricas = new ArrayList<>();
			ArrayList<Integer> lstPadres = new ArrayList<>();
			ArrayList<Integer> lstAuxiliar = new ArrayList<>();
			try {
				EspecificacionBateria especificacion = EspecificacionEJB.buscarEspecificacionDadoCodigo(strCodigoEspecificacion);
			}catch(Exception e) {
				objEstadoMetodo.setStrMensaje("No existe el código de especificación:  " + strCodigoEspecificacion);
				return objEstadoMetodo;
			}
			try {
			
				List<RelacionEntidad> lstRelacionEntidad = RelEntidadEJB.obtenerRelacionEntidad(intIdEntidad);
				if(lstRelacionEntidad.size() > 0 && lstRelacionEntidad!= null) {
					for (RelacionEntidad objRelacionEntidad : lstRelacionEntidad) {
						Entidad objEntidadPadre = EntidadEJB.buscarPorId(objRelacionEntidad.getIdEntidadPadre());
						if( objEntidadPadre.getTipoEntidad().getIdTipoEntidad() != 1 ){
							lstFabricas.add(objEntidadPadre.getIdEntidad());
						}
						
						lstPadres.add(objEntidadPadre.getIdEntidad());
					}
				}
				
				int c = 0;
				while (lstPadres.size() > 0) {
					c++;
					lstAuxiliar = new ArrayList<>();
					for (int idEntidad : lstPadres) {
						lstRelacionEntidad.clear();
						lstRelacionEntidad = RelEntidadEJB.obtenerRelacionEntidad(idEntidad);
						if(lstRelacionEntidad.size() > 0 && lstRelacionEntidad!= null) {
							for (RelacionEntidad objRelacionEntidad : lstRelacionEntidad) {
								Entidad objEntidadPadre = EntidadEJB.buscarPorId( objRelacionEntidad.getIdEntidadPadre() );
								if( lstFabricas.contains(objEntidadPadre.getIdEntidad() )  == false ) {
									lstFabricas.add(objEntidadPadre.getIdEntidad());
								}
								if( objEntidadPadre.getTipoEntidad().getIdTipoEntidad() != 1 ){
									if( lstAuxiliar.contains(objEntidadPadre.getIdEntidad() )  == false ) {
										lstAuxiliar.add(objEntidadPadre.getIdEntidad());
									}
								}
							}
						}
					}
					lstPadres= new ArrayList<>();
					lstPadres = lstAuxiliar;
				}
				
				if(lstFabricas.size() > 0) {
					for (int idFabrica : lstFabricas) {
						Entidad objEntidad = EntidadEJB.buscarPorId( idFabrica );
						List<Usuario> lstUsuario = objEntidad.getUsuarios();
						if( lstUsuario.size() > 0 && lstUsuario != null ) {
							for (Usuario objUsuario : lstUsuario) {
								boolean existeBateria = productoEJB.comprobrarExistenciaBateriaDadoUsuarioyCodigoEspecificacion(objUsuario.getIdUsuario(), strCodigoEspecificacion);
								if(existeBateria) {
									objEstadoMetodo.setBlResultado(true);
									objEstadoMetodo.setStrMensaje("Bateria disponible");
									return objEstadoMetodo;
								}							
							}
						}
					}
				}
							
			}catch(Exception e) {
				objEstadoMetodo.setStrMensaje( e.getMessage());
			}
				
		return objEstadoMetodo;
    }	
	
	/*
	 *
	 *
	 */
	@GET
    @Produces("application/json; charset=utf-8")
    @Path("buscarBateriaMiRed/{idEntidad}/{strCodigoEspecificacion}")
    public EstadosMetodos stockMiRed(
    		@PathParam("idEntidad") int intIdEntidad,
    		@PathParam("strCodigoEspecificacion") String strCodigoEspecificacion
    ) throws Exception {
			EstadosMetodos objEstadoMetodo = new EstadosMetodos(false, "Bateria no disponible");
			ArrayList<Integer> lstVendedor = new ArrayList<>();
			ArrayList<Integer> lstHijas = new ArrayList<>();
			ArrayList<Integer> lstAuxiliar = new ArrayList<>();
			try {
				EspecificacionBateria especificacion = EspecificacionEJB.buscarEspecificacionDadoCodigo(strCodigoEspecificacion);
			}catch(Exception e) {
				objEstadoMetodo.setStrMensaje("No existe el código de especificación:  " + strCodigoEspecificacion);
				return objEstadoMetodo;
			}
			try {
			
				List<RelacionEntidad> lstRelacionEntidad = RelEntidadEJB.obtenerRelacionEntidadParaHijas(intIdEntidad);
				if(lstRelacionEntidad.size() > 0 && lstRelacionEntidad!= null) {
					for (RelacionEntidad objRelacionEntidad : lstRelacionEntidad) {
						Entidad objEntidadHija = EntidadEJB.buscarPorId(objRelacionEntidad.getIdEntidad());
						if( objEntidadHija.getTipoEntidad().getIdTipoEntidad() < 7 ){
							lstVendedor.add(objEntidadHija.getIdEntidad());
						}
						
						lstHijas.add(objEntidadHija.getIdEntidad());
					}
				}
				
				int c = 0;
				while (lstHijas.size() > 0) {
					c++;
					lstAuxiliar = new ArrayList<>();
					for (int idEntidad : lstHijas) {
						System.out.println("ID entidad Hija >>> " + idEntidad);
						lstRelacionEntidad.clear();
						lstRelacionEntidad = RelEntidadEJB.obtenerRelacionEntidadParaHijas(idEntidad);
						if(lstRelacionEntidad.size() > 0 && lstRelacionEntidad!= null) {
							for (RelacionEntidad objRelacionEntidad : lstRelacionEntidad) {
								Entidad objEntidadHija = EntidadEJB.buscarPorId( objRelacionEntidad.getIdEntidad() );
								if( lstVendedor.contains(objEntidadHija.getIdEntidad() )  == false ) {
									lstVendedor.add(objEntidadHija.getIdEntidad());
								}
								if( objEntidadHija.getTipoEntidad().getIdTipoEntidad() < 7 ){
									if( lstAuxiliar.contains(objEntidadHija.getIdEntidad() )  == false ) {
										lstAuxiliar.add(objEntidadHija.getIdEntidad());
									}
								}
							}
						}
					}
					lstHijas= new ArrayList<>();
					lstHijas = lstAuxiliar;
				}
				
				if(lstVendedor.size() > 0) {
					for (int idEntidad : lstVendedor) {
						Entidad objEntidad = EntidadEJB.buscarPorId( idEntidad );
						List<Usuario> lstUsuario = objEntidad.getUsuarios();
						if( lstUsuario.size() > 0 && lstUsuario != null ) {
							for (Usuario objUsuario : lstUsuario) {
								boolean existeBateria = productoEJB.comprobrarExistenciaBateriaDadoUsuarioyCodigoEspecificacion(objUsuario.getIdUsuario(), strCodigoEspecificacion);
								if(existeBateria) {
									objEstadoMetodo.setBlResultado(true);
									objEstadoMetodo.setStrMensaje("Bateria disponible");
									return objEstadoMetodo;
								}							
							}
						}
					}
				}
							
			}catch(Exception e) {
				System.out.println("Excepcion ws >>>> " + e.getMessage());
				objEstadoMetodo.setStrMensaje( e.getMessage());
			}
				
		return objEstadoMetodo;
    }	


	
	
	
	
	
	
	/*
	 *
	 *
	 */
	@GET
	@Produces("application/json; charset=utf-8")
	@Path("stockBateriasMiRed/{idEntidad}")
	public ArrayList<EProducto> stockMiRed(
		@PathParam("idEntidad") int intIdEntidad
	) throws Exception {
			ArrayList<EProducto> lstProductosMiRed = new ArrayList<>();
			ArrayList<Integer> lstVendedor = new ArrayList<>();
			ArrayList<Integer> lstHijas = new ArrayList<>();
			ArrayList<Integer> lstAuxiliar = new ArrayList<>();
			try {
				List<RelacionEntidad> lstRelacionEntidad = RelEntidadEJB.obtenerRelacionEntidadParaHijas(intIdEntidad);
				if(lstRelacionEntidad.size() > 0 && lstRelacionEntidad!= null) {
					for (RelacionEntidad objRelacionEntidad : lstRelacionEntidad) {
						Entidad objEntidadHija = EntidadEJB.buscarPorId(objRelacionEntidad.getIdEntidad());
						if( objEntidadHija.getTipoEntidad().getIdTipoEntidad() < 7 ){
							lstVendedor.add(objEntidadHija.getIdEntidad());
						}
						
						lstHijas.add(objEntidadHija.getIdEntidad());
					}
				}
				
				int c = 0;
				while (lstHijas.size() > 0) {
					c++;
					lstAuxiliar = new ArrayList<>();
					for (int idEntidad : lstHijas) {
						System.out.println("ID entidad Hija >>> " + idEntidad);
						lstRelacionEntidad.clear();
						lstRelacionEntidad = RelEntidadEJB.obtenerRelacionEntidadParaHijas(idEntidad);
						if(lstRelacionEntidad.size() > 0 && lstRelacionEntidad!= null) {
							for (RelacionEntidad objRelacionEntidad : lstRelacionEntidad) {
								Entidad objEntidadHija = EntidadEJB.buscarPorId( objRelacionEntidad.getIdEntidad() );
								if( lstVendedor.contains(objEntidadHija.getIdEntidad() )  == false ) {
									lstVendedor.add(objEntidadHija.getIdEntidad());
								}
								if( objEntidadHija.getTipoEntidad().getIdTipoEntidad() < 7 ){
									if( lstAuxiliar.contains(objEntidadHija.getIdEntidad() )  == false ) {
										lstAuxiliar.add(objEntidadHija.getIdEntidad());
									}
								}
							}
						}
					}
					lstHijas= new ArrayList<>();
					lstHijas = lstAuxiliar;
				}
				
				if(lstVendedor.size() > 0) {
					for (int idEntidad : lstVendedor) {
						System.out.println("ID Entidad >>> " + idEntidad);
					}
				}
				
				if(lstVendedor.size() > 0) {
					for (int idEntidad : lstVendedor) {
						System.out.println("ID Entidad >>>>>><<<<<< >>>" + idEntidad);
						Entidad objEntidad = EntidadEJB.buscarPorId( idEntidad );
						List<Usuario> lstUsuario = objEntidad.getUsuarios();
						if( lstUsuario.size() > 0 && lstUsuario != null ) {
							for (Usuario objUsuario : lstUsuario) {
								System.out.println("ID Usuario >>>" + objUsuario.getIdUsuario());
								List<Producto> lstProductos = productoEJB.productosMiStock(objUsuario.getIdUsuario());
								if( lstProductos.size() > 0 && lstProductos != null) {
									for (Producto producto : lstProductos) {
										Seguimiento seguimientoRefresh = adminSeguimiento.buscarSeguimientoUltimoRefresh(producto);
										Seguimiento seguimientoRecepcion = null;
										if( seguimientoRefresh != null) {
											Seguimiento seguimientoActual = adminSeguimiento.buscarSeguimientoActual(producto);
											seguimientoRecepcion = adminSeguimiento.buscarSeguimientoRecepcion(producto, seguimientoActual.getUsuario().getIdUsuario());
										}else {
											seguimientoRecepcion = adminSeguimiento.buscarSeguimientoActual(producto);
										}
										EProducto objEProducto = new EProducto(producto, seguimientoRefresh, seguimientoRecepcion);
										lstProductosMiRed.add(objEProducto);
									}
								}
							}
						}
					}
				}
							
			}catch(Exception e) {
				System.out.println("Excepcion ws >>>> " + e.getMessage());
				lstProductosMiRed.clear();
			}
				
		return lstProductosMiRed;
   }	

	
}
