package com.matoosfe.batracking.negocio.seguimiento;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.matoosfe.batracking.modelo.ClienteFinal;
import com.matoosfe.batracking.modelo.FotografiasSeguimiento;
import com.matoosfe.batracking.modelo.Seguimiento;
import com.matoosfe.kernel.core.negocio.AbstractFacade;
import javax.xml.bind.DatatypeConverter;
//import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

@Stateless
public class FotografiasSeguimientoFacade extends AbstractFacade<FotografiasSeguimiento> {

	@PersistenceContext(unitName = "batrackingPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public FotografiasSeguimientoFacade() {
		super(FotografiasSeguimiento.class);
	}

	/**
	 * Método para guardar foto de venta Bateria
	 * 
	 * @param seguimientoSel
	 * @param clienteFinal
	 */
	public void guardarFotoVendedor(Seguimiento seguimientoSel, String strImage64) {
		FotografiasSeguimiento foto = new FotografiasSeguimiento();
		foto.setSeguimiento(seguimientoSel);
		foto.setFsegNombre("");
		foto.setFsegPath("");
		
//		byte[] imageBLOB = Base64.decode(strImage64);
//		java.util.Base64.Decoder.decode(strImage64, 0);
		byte[] imageBLOB = DatatypeConverter.parseBase64Binary(strImage64);
		
		foto.setFsegReal(imageBLOB);
		em.persist(foto);
		em.flush();

	}

	
	
}
