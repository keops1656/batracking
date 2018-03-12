package com.matoosfe.batracking.negocio.seguimiento;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.lang.Long;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.validator.internal.engine.messageinterpolation.parser.EscapedState;

import com.matoosfe.batracking.modelo.ClienteFinal;
import com.matoosfe.batracking.modelo.Entidad;
import com.matoosfe.batracking.modelo.Producto;
import com.matoosfe.batracking.modelo.Seguimiento;
import com.matoosfe.batracking.modelo.Usuario;
import com.matoosfe.batracking.modelo.dto.DashboardDto;
import com.matoosfe.batracking.modelo.dto.ReporteDto;
import com.matoosfe.batracking.negocio.admin.ProductoFacade;
import com.matoosfe.batracking.negocio.seguridad.RelacionEntidadFacade;
import com.matoosfe.kernel.core.negocio.AbstractFacade;

/**
 * Clase para administrar las operaciones de seguimiento del producto
 * 
 * @author MATOOSFE - Ing. Marco Toscano Freire. Msc -martosfre 6 ago. 2017-
 *         11:43:07<br>
 *         <a href="mailto:mtoscano@matoosfe.com?Subject=Soporte Batracking"
 *         target="_top">Soporte</a><br>
 *         <a href="http://www.matoosfe.com">Matoosfe</a>
 */
@Stateless
public class SeguimientoFacade extends AbstractFacade<Seguimiento> {

	@PersistenceContext(unitName = "batrackingPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public SeguimientoFacade() {
		super(Seguimiento.class);
	}

	/**
	 * Método para buscar los seguimientos de los productos de una entidad por
	 * su estado
	 * 
	 * @param entidad
	 * @param tipentNombre
	 * @return
	 */
	public List<Seguimiento> buscar(Entidad entidad, String tipentNombre) {
		TypedQuery<Seguimiento> conSeg = em.createQuery(
				"Select se from Seguimiento se " + " where se.producto.entidad =:entidad and se.segEstado =:estado",
				Seguimiento.class);
		conSeg.setParameter("entidad", entidad);
		conSeg.setParameter("estado", tipentNombre);
		return conSeg.getResultList();
	}

	/**
	 * Método para buscar los seguimientos por Producto
	 * 
	 * @param producto
	 * @return
	 */
	public List<Seguimiento> buscar(Producto producto) {
		TypedQuery<Seguimiento> conSeg = em.createQuery("Select se from Seguimiento se where se.producto =:producto",
				Seguimiento.class);
		conSeg.setParameter("producto", producto);
		return conSeg.getResultList();
	}

	/**
	 * Método para buscar el seguimiento actual del producto
	 * 
	 * @param producto
	 * @return
	 */
	public Seguimiento buscarSeguimientoActual(Producto producto) {

		TypedQuery<Seguimiento> conSeg = em.createQuery(
				"Select se from Seguimiento se where se.producto =:producto and se.segActual =:activo",
				Seguimiento.class);
		conSeg.setParameter("producto", producto);
		conSeg.setParameter("activo", 1);
		return conSeg.getSingleResult();
	}
	
	/**
	 * Método para buscar el seguimiento actual del producto
	 * 
	 * @param producto
	 * @return
	 */
	public Seguimiento buscarSeguimientoRecepcion(Producto producto, int intIdUsuario) {

		TypedQuery<Seguimiento> conSeg = em.createQuery(
				"Select se from Seguimiento se where se.producto =:producto and se.usuario.idUsuario =:usuario " +
				"and se.segFechaRefrescar IS NULL ",
				Seguimiento.class);
		conSeg.setParameter("producto", producto);
		conSeg.setParameter("usuario", intIdUsuario);
		return conSeg.getSingleResult();
	}
	
	/**
	 * Método para buscar el seguimiento con el último refresh de un producto
	 * 
	 * @param producto
	 * @return
	 */
	public Seguimiento buscarSeguimientoUltimoRefresh(Producto producto) {
		TypedQuery<Long> conCountSeg = em.createQuery(
				"SELECT COUNT(se.idSeguimiento) FROM Seguimiento se WHERE se.producto =:producto " +
				"AND se.segFechaRefrescar IS NOT NULL " +
				"ORDER BY se.segFecha DESC",
				Long.class);
		conCountSeg.setParameter("producto", producto);
		Long cantidad = (Long) conCountSeg.getSingleResult();
		if(cantidad > 0) {
			TypedQuery<Seguimiento> conSeg = em.createQuery(
					"SELECT se FROM Seguimiento se WHERE se.producto =:producto " +
					"AND se.segFechaRefrescar IS NOT NULL " + 
					"ORDER BY se.segFecha DESC",
					Seguimiento.class);
			conSeg.setParameter("producto", producto);
			List<Seguimiento> lstSeguimiento =  conSeg.setMaxResults(1).getResultList();
			return lstSeguimiento.get(0);
		}else {
			return null;
		}
		
	}

	/**
	 * Método para guardar el seguimiento de un producto
	 */
	@Override
	public void guardar(Seguimiento seguimiento) {
		for (Seguimiento seg : buscar(seguimiento.getProducto())) {
			seg.setSegActual(0);
			em.merge(seg);
		}
		em.persist(seguimiento);

	}

	public List<DashboardDto> contabilizarVidaUtil(int idEnt) {
		List<DashboardDto> lisDas = new ArrayList<>();
		if (idEnt > 0) {
			TypedQuery<Seguimiento> conSegEnt = em.createQuery(
					"Select se from Seguimiento se where se.usuario.entidad.idEntidad =:idEnt and se.segActual =:activo",
					Seguimiento.class);
			conSegEnt.setParameter("idEnt", idEnt);
			conSegEnt.setParameter("activo", 1);
			int numVen = 0;
			int numEqu = 0;
			int numPorVen = 0;
			List<ReporteDto> listaProductosVencidos = new ArrayList<>();
			List<ReporteDto> listaProductosAlLimite = new ArrayList<>();;
			List<ReporteDto> listaProductosPorVencer = new ArrayList<>();;
			for (Seguimiento seg : conSegEnt.getResultList()) {
				ReporteDto productoRep = new ReporteDto();
				if(!seg.getClienteFinals().isEmpty()){
					productoRep.setCliente(seg.getClienteFinals().get(0));
					productoRep.setColor(seg.getClienteFinals().get(0).getCliVehiculoColor());
				}
				productoRep.setDiasRestantesVU(seg.getSegVidaUtil());
				productoRep.setSeguimiento(seg);
				
				if (seg.getSegVidaUtil() < 0) { //Vencido
					numVen++;
					listaProductosVencidos.add(productoRep);
				} else if (seg.getSegVidaUtil() == 0) { //Al límite
					numEqu++;
					listaProductosAlLimite.add(productoRep);
				} else {//Por Vencer
					numPorVen++;
					listaProductosPorVencer.add(productoRep);
				}
			}

			DashboardDto dasVen = new DashboardDto();
			dasVen.setColor("#FF0000");
			dasVen.setEtiqueta("Ya se paso el tiempo");
			dasVen.setTitulo("Vencido");
			dasVen.setValor(numVen);
			dasVen.setListaProductosReporte(listaProductosVencidos);

			DashboardDto dasEqu = new DashboardDto();
			dasEqu.setColor("#FFFF00");
			dasEqu.setEtiqueta("<= 1 de Vida útil");
			dasEqu.setTitulo("Al Límite");
			dasEqu.setValor(numEqu);
			dasEqu.setListaProductosReporte(listaProductosAlLimite);

			DashboardDto dasPorVen = new DashboardDto();
			dasPorVen.setColor("#00FF00");
			dasPorVen.setEtiqueta("> 1 mes Vida útil");
			dasPorVen.setTitulo("Por Vencer");
			dasPorVen.setValor(numPorVen);
			dasPorVen.setListaProductosReporte(listaProductosPorVencer);

			lisDas.add(dasVen);
			lisDas.add(dasEqu);
			lisDas.add(dasPorVen);
		}
		return lisDas;
	}

	/**
	 * Método para guardar transaccion vendedor
	 * 
	 * @param seguimientoSel
	 * @param clienteFinal
	 */
	public void guardarTxVendedor(Seguimiento seguimientoSel, ClienteFinal clienteFinal) {
		// Actualizar el producto
		em.merge(seguimientoSel.getProducto());
		// Registrar o actualizar cliente
		clienteFinal.setSeguimiento(seguimientoSel);
		if (clienteFinal.getIdCliente() == 0) {
			em.persist(clienteFinal);
		} else {
			em.merge(clienteFinal);
		}

	}

	/**
	 * Método para devolver el tracking
	 * 
	 * @param seguimientoActual
	 */
	public void devolver(Seguimiento seguimientoActual) {
		TypedQuery<Seguimiento> conSegAnt = em.createQuery(
				"Select se from Seguimiento se where se.producto.idProducto =:idProd order by se.segFecha desc",
				Seguimiento.class);
		conSegAnt.setParameter("idProd", seguimientoActual.getProducto().getIdProducto());
		List<Seguimiento> listaSeg = conSegAnt.getResultList();
		if (!listaSeg.isEmpty() && listaSeg.size() > 1) {
			Seguimiento seguimientoAnt = listaSeg.get(1);
			seguimientoAnt.setSegActual(1);
			em.merge(seguimientoAnt);
			em.remove(em.merge(seguimientoActual));
		}

	}

	/**
	 * Método para buscar seguimientos por usuario
	 * 
	 * @param idUsuario
	 * @return
	 */
	public List<Seguimiento> buscar(int idUsuario) {
		TypedQuery<Seguimiento> conSegUsu = em
				.createQuery("Select se from Seguimiento se where se.usuario.idUsuario =:idUsu", Seguimiento.class);
		conSegUsu.setParameter("idUsu", idUsuario);
		return conSegUsu.getResultList();
	}
	
	/**
	 * Método para guardar el seguimiento 
	 */
	public String guardarWS( Producto productoSel, Usuario usuarioSesion, boolean refrescar, String tipoBateria, String longitud, String latitud) {
		Seguimiento seguimientoSel = new Seguimiento();
		Seguimiento seguimientoWS = new Seguimiento();
		String respuesta = "";
		try {
			if (productoSel != null) {
				seguimientoSel = buscarSeguimientoActual(productoSel);
				if (!seguimientoSel.getSegEstado()
						.equals(usuarioSesion.getEntidad().getTipoEntidad().getTipentNombre().toUpperCase())) {
					if (verificarEstadoPadre(seguimientoSel, usuarioSesion)) {
						Calendar fechaSeguimientoAnt = Calendar.getInstance();
						fechaSeguimientoAnt.setTime(seguimientoSel.getSegFecha());

						Calendar fechaSeguimientoAct = Calendar.getInstance();
						fechaSeguimientoAct.setTime(new Date());

						int tieMesTra = fechaSeguimientoAct.get(Calendar.MONTH)
								- fechaSeguimientoAnt.get(Calendar.MONTH);
						int vidaUtil = seguimientoSel.getSegVidaUtil() - tieMesTra;

						if (vidaUtil > 0) {
							
							seguimientoWS.setSegEstado(usuarioSesion.getEntidad().getTipoEntidad().getTipentNombre());
							seguimientoWS.setProducto(seguimientoSel.getProducto());
							seguimientoWS.setSegFecha(new Date());
							seguimientoWS.setSegMantenimiento(productoSel.getParametro().getParMantenimiento());
							//seguimientoWS.setComentarioSeguimientos(listaComentarios);
							//seguimientoWS.setFotografiasSeguimientos(listaFotografias);
							seguimientoWS.setUsuario(usuarioSesion);
							seguimientoWS.setSegLongitud(longitud);
							seguimientoWS.setSegLatitud(latitud);

							// Refrescamiento
							if (refrescar) {
								if (seguimientoWS.getProducto().getProdFechaVenta() == null) {
									if (seguimientoWS.getProducto().getProdContadorRefrescar() < 2) {
										seguimientoWS.getProducto().setProdContadorRefrescar(
												seguimientoWS.getProducto().getProdContadorRefrescar() + 1);
										seguimientoWS.setSegFechaRefrescar(new Date());
										if (tipoBateria.equals("REFRESCAMIENTO")) {
											seguimientoWS.setSegTiempoRefrescar(
													seguimientoWS.getProducto().getParametro().getParRefrescamiento());
											vidaUtil = vidaUtil
													+ seguimientoWS.getProducto().getParametro().getParRefrescamiento();
										} else {
											seguimientoWS.setSegTiempoRefrescar(0);
										}
									} else {
										respuesta = "El producto ya cumplio con su refrescamiento, número 2.";
									}
								} else {
									respuesta = "No se puede refrescar un producto vendido";
								}
							}
							seguimientoWS.setSegActual(1);
							seguimientoWS.setSegVidaUtil(vidaUtil);
							guardar(seguimientoWS);
							respuesta = "Bateria recibida";

//							resetearFormulario();
						} else {
							respuesta = "El producto ya no cuenta con vida útil, no se puede realizar tracking";
						}
					}
				} else {
					respuesta = "El producto ya fue hecho tracking por el estado:"
							+ usuarioSesion.getEntidad().getTipoEntidad().getTipentNombre().toUpperCase();
				}
			} else {
				respuesta = "Se debe seleccionar un producto para realizar el tracking";
			}
		} catch (Exception e) {
			respuesta =  "No se pudo registrar el tracking:" + e.getMessage();
		}
		return respuesta;

	}
	
	/**
	 * Método para verificar que el producto haya pasado por el inmediato
	 * superior en el tracking
	 * 
	 * @return
	 * @throws Exception
	 */
	@EJB
	private RelacionEntidadFacade adminRelacionEnt;
	
	public boolean verificarEstadoPadre(Seguimiento seguimientoSel, Usuario usuarioSesion) throws Exception {
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
				throw new Exception("Se debe realizar el tracking siguiendo la jerarquía, estado seguimiento:"
						+ seguimientoSel.getSegEstado());
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return valEnt;
	}
	
	/**
	 * Método para refrescar una bateria - seguimiento 
	 */
	public String refrescarBateria( Producto productoSel, Usuario usuarioSesion, boolean refrescar, String tipoBateria, String longitud, String latitud) {
		Seguimiento seguimientoSel = new Seguimiento();
		Seguimiento seguimientoWS = new Seguimiento();
		String respuesta = "";
		try {
			if (productoSel != null) {
				seguimientoSel = buscarSeguimientoActual(productoSel);
//				if (!seguimientoSel.getSegEstado()
//						.equals(usuarioSesion.getEntidad().getTipoEntidad().getTipentNombre().toUpperCase())) {
//					if (verificarEstadoPadre(seguimientoSel, usuarioSesion)) {
				Calendar fechaSeguimientoAnt = Calendar.getInstance();
				fechaSeguimientoAnt.setTime(seguimientoSel.getSegFecha());

				Calendar fechaSeguimientoAct = Calendar.getInstance();
				fechaSeguimientoAct.setTime(new Date());

				int tieMesTra = fechaSeguimientoAct.get(Calendar.MONTH)
						- fechaSeguimientoAnt.get(Calendar.MONTH);
				int vidaUtil = seguimientoSel.getSegVidaUtil() - tieMesTra;

				if (vidaUtil > 0) {
					
					seguimientoWS.setSegEstado(usuarioSesion.getEntidad().getTipoEntidad().getTipentNombre());
					seguimientoWS.setProducto(seguimientoSel.getProducto());
					seguimientoWS.setSegFecha(new Date());
					seguimientoWS.setSegMantenimiento(productoSel.getParametro().getParMantenimiento());
					//seguimientoWS.setComentarioSeguimientos(listaComentarios);
					//seguimientoWS.setFotografiasSeguimientos(listaFotografias);
					seguimientoWS.setUsuario(usuarioSesion);
					seguimientoWS.setSegLongitud(longitud);
					seguimientoWS.setSegLatitud(latitud);

					// Refrescamiento
					if (refrescar) {
						if (seguimientoWS.getProducto().getProdFechaVenta() == null) {
							if (seguimientoWS.getProducto().getProdContadorRefrescar() < 2) {
								seguimientoWS.getProducto().setProdContadorRefrescar(
										seguimientoWS.getProducto().getProdContadorRefrescar() + 1);
								seguimientoWS.setSegFechaRefrescar(new Date());
								if (tipoBateria.equals("REFRESCAMIENTO")) {
									seguimientoWS.setSegTiempoRefrescar(
											seguimientoWS.getProducto().getParametro().getParRefrescamiento());
									vidaUtil = vidaUtil
											+ seguimientoWS.getProducto().getParametro().getParRefrescamiento();
								} else {
									seguimientoWS.setSegTiempoRefrescar(0);
								}
							} else {
								respuesta = "El producto ya cumplio con su refrescamiento, número 2.";
							}
						} else {
							respuesta = "No se puede refrescar un producto vendido";
						}
					}
					seguimientoWS.setSegActual(1);
					seguimientoWS.setSegVidaUtil(vidaUtil);
					guardar(seguimientoWS);
					respuesta = "Bateria refrescada";
					
				} else {
					respuesta = "La bateria ya no cuenta con vida útil, no se puede realizar el refrescamiento";
				}
					
			} else {
				respuesta = "Se debe seleccionar una bateria para refrescar";
			}
		} catch (Exception e) {
			respuesta =  "No se pudo refrescar la bateria: " + e.getMessage();
		}
		return respuesta;
	}
	
	
	@EJB
	private ProductoFacade productoEJB;
	
	@EJB
	private SeguimientoFacade adminSeguimiento;
	
	@EJB
	private FotografiasSeguimientoFacade fotosEJB;
	
	/**
	 * Método para guardar la tx del vendedor
	 */
	public String guardarTransaccion(int idUsuario, int idProducto, String tipoTransaccion, ClienteFinal clienteFinal, String strImage64) {
		String respuesta = "";
		Date fechaTra = new Date();//Fecha de la transacción
		Seguimiento seguimientoSel = new Seguimiento();
		try {
			Producto productoSel = productoEJB.buscarPorId(idProducto);
			seguimientoSel = buscarSeguimientoActual(productoSel);
			if (seguimientoSel != null) {
				if(seguimientoSel.getUsuario().getIdUsuario() == idUsuario){
					
					if (tipoTransaccion.equals("V")) {
						if(productoSel.getProdEstadoBateria().equals("VENDIDO")) {
							return "El producto ya fue vendido";
						}else {
							seguimientoSel.getProducto().setProdFechaVenta(fechaTra);
							seguimientoSel.getProducto().setProdEstadoBateria("VENDIDO");
						}
					} else if (tipoTransaccion.equals("M")) {
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
					fotosEJB.guardarFotoVendedor(seguimientoSel, strImage64);
					respuesta= "Transacción guardada correctamente";
				}else{
					respuesta= "La bateria no se encuentra en mi Stock";
				}
			} else {
				respuesta= "Se debe seleccionar un producto para realizar la transacción";
			}
		} catch (Exception e) {
			respuesta = "No se pudo registrar la transacción:" + e.getMessage();
		}
		return respuesta;
	}
	
	/**
	 * Método para comprobar existencia de una bateria en stock Vendedor dado usuario y código de la bateria
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Seguimiento buscarBateriaStockVendedor(int idUsuario, Producto objProducto) {
		Seguimiento seguimiento = null;
		TypedQuery<Seguimiento> conSegUsu = em
				.createQuery("SELECT se FROM Seguimiento se WHERE se.usuario.idUsuario =:idUsu " +
				"AND se.segActual =:activo AND se.producto =:producto", Seguimiento.class);
		conSegUsu.setParameter("idUsu", idUsuario);
		conSegUsu.setParameter("activo", 1);
		conSegUsu.setParameter("producto", objProducto);
		seguimiento = conSegUsu.getSingleResult();
		return seguimiento;
	}
	
	/**
	 * Método para verificar garantía y mantenimiento de un producto
	 */
	public boolean verificarGarantiaMantenimiento(int idUsuario, Producto producto, String tipoTransaccion) throws Exception {
		Date fechaTra = new Date();//Fecha de la transacción
		Seguimiento seguimientoSel = new Seguimiento();
		int mesesGarantia = 12;
		try {
			seguimientoSel = buscarSeguimientoActual(producto);
			if (seguimientoSel != null) {
				if(seguimientoSel.getUsuario().getIdUsuario() == idUsuario) {
				
					if (tipoTransaccion.equals("M")) {
						Calendar fechaVenta = Calendar.getInstance();
						fechaVenta.setTime(seguimientoSel.getProducto().getProdFechaVenta());
						Calendar fechaMantenimiento = Calendar.getInstance();
						fechaMantenimiento.setTime(fechaTra);
						fechaMantenimiento.add(Calendar.MONTH,
								seguimientoSel.getProducto().getParametro().getParMantenimiento());
						if (fechaTra.compareTo(fechaMantenimiento.getTime()) < 0) {
							return true;
						} else {
							throw new Exception("Se ha vencido el tiempo para acceder al mantenimiento: " + seguimientoSel.getProducto().getParametro().getParMantenimiento() +" meses");
						}
					}
					
					if (tipoTransaccion.equals("G")) {
						Calendar fechaVenta = Calendar.getInstance();
						fechaVenta.setTime(seguimientoSel.getProducto().getProdFechaVenta());
						Calendar fechaActual = Calendar.getInstance();
						fechaActual.setTime(new Date());
						Calendar fechaFinGarantia = Calendar.getInstance();
						fechaFinGarantia.setTime( fechaVenta.getTime() );
						fechaFinGarantia.add(Calendar.MONTH,mesesGarantia);
						Calendar fechaMantenimiento = Calendar.getInstance();
						fechaMantenimiento.setTime(fechaTra);
						fechaMantenimiento.add(Calendar.MONTH,
								seguimientoSel.getProducto().getParametro().getParMantenimiento());
						if (fechaActual.compareTo(fechaVenta) > 0 && fechaActual.compareTo(fechaMantenimiento) <= 0 ) {  //CUANDO SE PIDE LA GARANTIA A PESAR DE QUE NO TIENE MANTENIMIENTO PORQUE AUN NO CUMPLE CON EL PARAMETRO DE MANTENIMIENTO
							return true;
						}
						if (fechaActual.compareTo(fechaVenta) > 0 && fechaActual.compareTo(fechaFinGarantia) <= 0
								&& seguimientoSel.getProducto().getProdFechaMantenimiento() != null) {  //SI HIZO MANTENIMIENTO Y AUN NO SE TERMINA LA FECHA DE GARANTIA
							return true;
						}
						return false;
					}
				}else {
					throw new Exception("La bateria no se encuentra en mi Stock");
				}
				
			} else {
				throw new Exception("Se debe seleccionar un producto para realizar la transacción");
			}
		} catch (Exception e) {
			throw new Exception("No se pudo registrar la transacción: " + e.getMessage());
		}
		return false;
	}



}
