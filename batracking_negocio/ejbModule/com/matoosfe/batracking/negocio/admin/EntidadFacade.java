package com.matoosfe.batracking.negocio.admin;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.matoosfe.batracking.modelo.Entidad;
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
public class EntidadFacade extends AbstractFacade<Entidad> {

	@PersistenceContext(unitName = "batrackingPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public EntidadFacade() {
		super(Entidad.class);
	}

	/**
	 * MÃ©todo para buscar entidades por tipo de entidad
	 * 
	 * @param tipoEnt
	 * @return
	 */
	public List<Entidad> buscarEntidadPorTipo(String tipoEnt) {
		TypedQuery<Entidad> conEntPorTipEnt = em.createQuery(
				"Select ent from Entidad ent where ent.tipoEntidad.tipentNombre =:nomTipEnt", Entidad.class);
		conEntPorTipEnt.setParameter("nomTipEnt", tipoEnt);
		return conEntPorTipEnt.getResultList();
	}
	
	/**
	 * Método para buscar tipo Entidad dado id Entidad
	 * 
	 * @param idEntidad
	 * @return
	 */
	public int buscarTipoEntidad(int idEntidad) {
		TypedQuery<Entidad> conTipEnt = em.createQuery(
				"SELECT ent FROM Entidad ent WHERE ent.idEntidad =:entidadID", Entidad.class);
		conTipEnt.setParameter("entidadID", idEntidad);
		Entidad objEntidad = (Entidad) conTipEnt.getSingleResult();
		return objEntidad.getTipoEntidad().getTipentOrden();
	}

}
