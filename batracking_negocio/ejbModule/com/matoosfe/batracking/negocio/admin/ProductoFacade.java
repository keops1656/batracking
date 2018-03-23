package com.matoosfe.batracking.negocio.admin;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.matoosfe.batracking.modelo.ComentarioSeguimiento;
import com.matoosfe.batracking.modelo.Pallet;
import com.matoosfe.batracking.modelo.Producto;
import com.matoosfe.batracking.modelo.RelacionEntidad;
import com.matoosfe.batracking.modelo.Seguimiento;
import com.matoosfe.batracking.modelo.TipoBateria;
import com.matoosfe.kernel.core.negocio.AbstractFacade;

/**
 * Clase para administrar las operaciones de producto
 * 
 * @author MATOOSFE - Ing. Marco Toscano Freire. Msc -martosfre 6 ago. 2017-
 *         11:43:07<br>
 *         <a href="mailto:mtoscano@matoosfe.com?Subject=Soporte Batracking"
 *         target="_top">Soporte</a><br>
 *         <a href="http://www.matoosfe.com">Matoosfe</a>
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> {

	@PersistenceContext(unitName = "batrackingPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ProductoFacade() {
		super(Producto.class);
	}

	/**
	 * Método para guardar un lote
	 * 
	 * @param listaProLote
	 */
	public void guardarLote(List<Producto> listaProLote) throws Exception {
		for (Producto producto : listaProLote) {
			em.persist(producto);
		}

	}

	/**
	 * Método para buscar los productos sin pallet
	 * 
	 * @return
	 */
	public List<Producto> buscarProductosSinPallet() throws Exception {
		TypedQuery<Producto> conProSinPal = em.createQuery("Select pro from Producto pro where pro.pallet is null",
				Producto.class);
		return conProSinPal.getResultList();
	}

	/**
	 * Método para buscar los productos sin pallet por una lista de cÃ³digos
	 * 
	 * @param codigos
	 * @return
	 */
	public List<Producto> buscarProductosSinPallet(List<String> codigos) throws Exception {
		TypedQuery<Producto> conProSinPal = em
				.createQuery("Select pro from Producto pro where pro.prodCodigo in :codigos and pro.pallet is null", Producto.class);
		conProSinPal.setParameter("codigos", codigos);
		return conProSinPal.getResultList();
	}

	/**
	 * Método para guardar un producto con su seguimiento inicial.
	 * @param producto
	 * @param seguimiento
	 */
	public void guardar(Producto producto, Seguimiento seguimiento) {
		em.persist(producto);
		em.flush();
		em.persist(seguimiento);
		
	}

	/**
	 * Método para buscar los productos de los fabricantes
	 * @param idsFabricantes
	 * @param valorBusqueda
	 * @return
	 */
	public List<Producto> buscarProductos(List<String> idsFabricantes, String valorBusqueda) {
		TypedQuery<Producto> conProSinPal = em
				.createQuery("Select pro from Producto pro where pro.entidad.idEntidad in :codigos "
						+ " and (pro.prodCodigo like :valorBus or pro.prodEspecificaciones like :valorBus)", Producto.class);
		conProSinPal.setParameter("codigos", idsFabricantes);
		conProSinPal.setParameter("valorBus", "%" + valorBusqueda + "%");
		return conProSinPal.getResultList();
	}
	
	/**
	 * Método para buscar los tipos de bateria
	 * @return
	 */
	public List<TipoBateria> buscarTiposBateria(){
		TypedQuery<TipoBateria> conTipBat = em.createQuery("Select tipBat from TipoBateria tipBat", TipoBateria.class);
		return conTipBat.getResultList();
	}

	/**
	 * Método para buscar el tipo de bateria por su identificador
	 * @param idTipBat
	 * @return
	 */
	public TipoBateria buscarTipoBateria(int idTipBat) {
		return em.find(TipoBateria.class, idTipBat);
	}
	
	/**
	 * Método para buscar producto dado código
	 * 
	 * @return
	 */
	public Producto buscarProductoDadoCodigo(String strCodigoProducto) throws Exception {
		try{ 
			TypedQuery<Producto> conPro = em.createQuery(
				"SELECT pro FROM Producto pro WHERE pro.prodCodigo = :codigoProducto", Producto.class);
			conPro.setParameter("codigoProducto", strCodigoProducto);
			return conPro.getSingleResult();
		}catch(Exception ex) {
			throw new Exception(ex);
		}
		
		
	}
	
	/**
	 * Método para buscar pallets dado idEntidad
	 * 
	 * @return
	 */
	public List<Pallet> buscarPalletsDadoFabrica(int idFabrica) throws Exception {
		try{ 
			TypedQuery<Pallet> conPro = em.createQuery(
				"SELECT DISTINCT pal FROM Pallet pal " +
				"INNER JOIN Producto pro ON pro.pallet.idPallet = pal.idPallet " +
				"WHERE pro.pallet.idPallet IS NOT NULL " +
				"AND pro.entidad.idEntidad = :fabrica", Pallet.class);
			conPro.setParameter("fabrica", idFabrica);
			return conPro.getResultList();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			throw new Exception(ex);
		}	
		
	}
	
	/**
	 * Método para enlistar productos en stock Local
	 * @return
	 */
	public List<Producto> productosMiStock( int intIdUsuario){
		TypedQuery<Producto> conMiStock = em.createQuery(
				"SELECT pro FROM Producto pro " +
				"INNER JOIN Seguimiento seg ON seg.producto.idProducto = pro.idProducto " +
				"WHERE seg.segActual = 1 " +
				"AND seg.usuario.idUsuario = :usuarioID " +
				"AND pro.prodEstadoBateria = 'EN FABRICA' " +
				"ORDER BY pro.prodFechaProduccion, pro.prodCodigo ASC",
				Producto.class);
		conMiStock.setParameter("usuarioID", intIdUsuario);
		return conMiStock.getResultList(); 
	}
	
	/**
	 * Método para buscar existencia de una bateria en una entidad superior dado una entidad
	 * 
	 * @param idEntidad
	 * @return
	 */
	public boolean comprobrarExistenciaBateriaMiStock (int idUsuario , String codProducto) throws Exception {
		Long intCountExistencia = null;
		try{
			TypedQuery<Long> countEntRel = em
					.createQuery("SELECT COUNT(seg.idSeguimiento) FROM Seguimiento seg WHERE seg.producto.prodCodigo =:cod " + 
								"AND seg.usuario.idUsuario =:idUsu " +  
								"AND seg.segActual =1", Long.class);
			countEntRel.setParameter("cod", codProducto);
			countEntRel.setParameter("idUsu", idUsuario);
			intCountExistencia = (Long) countEntRel.getSingleResult();
		}catch(Exception ex) {
			System.out.println("Excepcion EJB >>>> " + ex.getMessage());
		}
		if( intCountExistencia == 1 ) {
			return true;
		}else {
			return false;
		}
		
	}
	
	/**
	 * Método para buscar existencia de una bateria en una entidad superior dado una entidad
	 * 
	 * @param idEntidad
	 * @return
	 */
	public boolean comprobrarExistenciaBateriaDadoUsuarioyCodigoEspecificacion (int idUsuario , String codEspecificacion) throws Exception {
		Long intCountExistencia = null;
		try{
			TypedQuery<Long> countEntRel = em
					.createQuery(	"SELECT COUNT(seg.idSeguimiento) FROM Seguimiento seg " +
									"WHERE seg.producto.especificacionBateria.codEspecificacion =:cod " + 
								"AND seg.usuario.idUsuario =:idUsu " +  
								"AND seg.segActual =1", Long.class);
			countEntRel.setParameter("cod", codEspecificacion);
			countEntRel.setParameter("idUsu", idUsuario);
			intCountExistencia = (Long) countEntRel.getSingleResult();
		}catch(Exception ex) {
			System.out.println("Excepcion EJB >>>> " + ex.getMessage());
		}
		if( intCountExistencia == 1 ) {
			return true;
		}else {
			return false;
		}
		
	}
	
	/**
	 * Método para enlistar productos dado Especificacion
	 * @return
	 */
	public List<Producto> bateriasDadoEspecificacion( String codEspecificacion){
		TypedQuery<Producto> conBaterias = em.createQuery(
				"SELECT pro FROM Producto pro " +
				"INNER JOIN EspecificacionBateria esp ON esp = pro.especificacionBateria " +
				"WHERE esp.codEspecificacion = :codEsp AND pro.prodEstadoBateria = 'EN FABRICA'",
				Producto.class);
		conBaterias.setParameter("codEsp", codEspecificacion);
		return conBaterias.getResultList(); 
	}

}
