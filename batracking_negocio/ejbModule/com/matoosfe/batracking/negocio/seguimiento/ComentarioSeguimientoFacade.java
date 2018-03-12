
package com.matoosfe.batracking.negocio.seguimiento;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.validator.internal.engine.messageinterpolation.parser.EscapedState;

import com.matoosfe.batracking.modelo.ComentarioSeguimiento;

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
public class ComentarioSeguimientoFacade extends AbstractFacade<ComentarioSeguimiento> {

	@PersistenceContext(unitName = "batrackingPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ComentarioSeguimientoFacade() {
		super(ComentarioSeguimiento.class);
	}
	
	/**
	 * Método para enlistar comentarios de un producto
	 * @return
	 */
	public List<ComentarioSeguimiento> comentariosProducto( String strCodigoProducto){
		System.out.println(strCodigoProducto);
		TypedQuery<ComentarioSeguimiento> conComSeg = em.createQuery(
				"SELECT com FROM ComentarioSeguimiento com " +
				"INNER JOIN Seguimiento seg ON com.seguimiento.idSeguimiento = seg.idSeguimiento " +
				"INNER JOIN Producto pro ON seg.producto.idProducto = pro.idProducto " +
				"WHERE com.seguimiento.idSeguimiento IS NOT NULL AND pro.prodCodigo = :codigo",
				ComentarioSeguimiento.class);
		conComSeg.setParameter("codigo", strCodigoProducto);
		return conComSeg.getResultList(); 
	}

}
