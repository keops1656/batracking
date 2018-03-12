package com.matoosfe.batracking.negocio.admin;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.matoosfe.batracking.modelo.Parametro;
import com.matoosfe.kernel.core.negocio.AbstractFacade;

/**
 * Clase para administrar las operaciones de Parámetro
 * 
 * @author MATOOSFE - Ing. Marco Toscano Freire. Msc -martosfre 7 ago. 2017-
 *         23:09:25<br>
 *         <a href="mailto:mtoscano@matoosfe.com?Subject=Soporte Batracking"
 *         target="_top">Soporte</a><br>
 *         <a href="http://www.matoosfe.com">Matoosfe</a>
 */
@Stateless
public class ParametroFacade extends AbstractFacade<Parametro> {

	@PersistenceContext(unitName = "batrackingPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ParametroFacade() {
		super(Parametro.class);
	}

}
