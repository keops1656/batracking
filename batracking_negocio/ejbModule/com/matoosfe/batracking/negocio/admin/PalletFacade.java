package com.matoosfe.batracking.negocio.admin;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
public class PalletFacade extends AbstractFacade<Pallet> {

	@PersistenceContext(unitName = "batrackingPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public PalletFacade() {
		super(Pallet.class);
	}

	/**
	 * Método para guardar el pallet con los productos asociados
	 * 
	 * @param pallet
	 * @param listaProductosSel
	 */
	public void guardar(Pallet pallet, List<Producto> listaProductosSel) throws Exception {
		em.persist(pallet);
		em.flush();
		for (Producto producto : listaProductosSel) {
			producto.setPallet(pallet);
			em.merge(producto);
		}

	}

	/**
	 * Método para actualizar el pallet con los productos asociados
	 * 
	 * @param pallet
	 * @param listaProductosSel
	 * @param listaProductosEli
	 * @throws Exception
	 */
	public void actualizar(Pallet pallet, List<Producto> listaProductosSel, List<Producto> listaProductosEli)
			throws Exception {
		em.merge(pallet);
		//Actualizar productos
		for (Producto producto : listaProductosSel) {
			producto.setPallet(pallet);
			em.merge(producto);
		}

		//Eliminar productos
		for (Producto producto : listaProductosEli) {
			producto.setPallet(null);
			em.merge(producto);
		}
	}

	/**
	 * Método para eliminar pallet
	 * @param palletSel
	 * @param productos
	 */
	public void eliminar(Pallet palletSel, List<Producto> productos) {
		for (Producto producto : productos) {
			producto.setPallet(null);
			em.merge(producto);
		}
		palletSel.setProductos(null);
		em.remove(em.merge(palletSel));
		
	}

}
