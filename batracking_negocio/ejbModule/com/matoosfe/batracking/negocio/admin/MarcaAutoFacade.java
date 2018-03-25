package com.matoosfe.batracking.negocio.admin;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.matoosfe.batracking.modelo.MarcaAuto;
import com.matoosfe.batracking.modelo.Pallet;
import com.matoosfe.batracking.modelo.Producto;
import com.matoosfe.kernel.core.negocio.AbstractFacade;

/***
 * Clase para administrar las operaciones del pallet
 * @author MATOOSFE - Ing. Marco Toscano Freire. Msc -martosfre 
 * 18 ago. 2017- 00:16:39<br>
 * <a href="mailto:mtoscano@matoosfe.com?Subject=Soporte Batracking" target="_top">Soporte</a><br>
 * <a href="http://www.matoosfe.com">Matoosfe</a>
 */
@Stateless
public class MarcaAutoFacade extends AbstractFacade<MarcaAuto> {

	@PersistenceContext(unitName = "batrackingPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public MarcaAutoFacade() {
		super(MarcaAuto.class);
	}


}
