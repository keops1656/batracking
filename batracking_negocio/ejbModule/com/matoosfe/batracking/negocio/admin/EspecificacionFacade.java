package com.matoosfe.batracking.negocio.admin;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.matoosfe.batracking.modelo.Entidad;
import com.matoosfe.batracking.modelo.EspecificacionBateria;
import com.matoosfe.batracking.modelo.Producto;
import com.matoosfe.kernel.core.negocio.AbstractFacade;

/**
 * Clase para administrar las operaciones de Entidad
 * 
 * @author MATOOSFE - Ing. Marco Toscano Freire. Msc -martosfre 8 ago. 2017-
 *         23:29:56<br>
 *         <a href="mailto:mtoscano@matoosfe.com?Subject=Soporte Batracking"
 *         target="_top">Soporte</a><br>
 *         <a href="http://www.matoosfe.com">Matoosfe</a>
 */
@Stateless
public class EspecificacionFacade extends AbstractFacade<EspecificacionBateria> {
	
	@PersistenceContext(unitName = "batrackingPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public EspecificacionFacade() {
		super(EspecificacionBateria.class);
	}

	/**
	 * Metodo para listar todas las especificaciones de baterias
	 * 
	 * @return
	 */
	public List<EspecificacionBateria> listarEspecificaciones() {
		TypedQuery<EspecificacionBateria> conEspecificacion = em.createQuery(
				"SELECT esp FROM  EspecificacionBateria esp", EspecificacionBateria.class);
		return conEspecificacion.getResultList();
	}
	
	/**
	 * Método para buscar Especificacion dado código
	 * 
	 * @return
	 */
	public EspecificacionBateria buscarEspecificacionDadoCodigo(String strCodigoEspecificacion) throws Exception {
		try{ 
			TypedQuery<EspecificacionBateria> conEsp = em.createQuery(
					"SELECT esp FROM EspecificacionBateria esp " +
					"WHERE esp.codEspecificacion = :codigoEspecificacion ", EspecificacionBateria.class);
			conEsp.setParameter("codigoEspecificacion", strCodigoEspecificacion);
			return conEsp.getSingleResult();
		}catch(Exception ex) {
			throw new Exception(ex);
		}
		
		
	}
	

}
