/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matoosfe.batracking.negocio.seguridad;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.matoosfe.batracking.modelo.TipoEntidad;
import com.matoosfe.kernel.core.negocio.AbstractFacade;

/**
 * Clase para administrar las operaciones de tipo entidad
 * 
 * @author MATOOSFE - Ing. Marco Toscano Freire. Msc -martosfre 9 ago. 2017-
 *         00:04:42<br>
 *         <a href="mailto:mtoscano@matoosfe.com?Subject=Soporte Batracking"
 *         target="_top">Soporte</a><br>
 *         <a href="http://www.matoosfe.com">Matoosfe</a>
 */
@Stateless
public class TipoEntidadFacade extends AbstractFacade<TipoEntidad> {

	@PersistenceContext(unitName = "batrackingPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public TipoEntidadFacade() {
		super(TipoEntidad.class);
	}

	/**
	 * MÃ©todo para buscar los tipos de entidad
	 * 
	 * @return
	 */
	public List<TipoEntidad> buscarTipoUsuarios() {
		TypedQuery<TipoEntidad> conTipEnt = em.createQuery("Select tipEnt from TipoEntidad tipEnt", TipoEntidad.class);
		return conTipEnt.getResultList();
	}

	/**
	 * Método para buscar los tipos de entidad
	 * 
	 * @param codigoJerarquia
	 * @return
	 */
	public List<TipoEntidad> buscarJerarquia(int codigoJerarquia) {
		TypedQuery<TipoEntidad> conTipEnt = em.createQuery(
				"Select tipEnt from TipoEntidad tipEnt where tipEnt.tipentOrden <:codigoJerarquia", TipoEntidad.class);
		conTipEnt.setParameter("codigoJerarquia", codigoJerarquia);
		return conTipEnt.getResultList();
	}
	

}
