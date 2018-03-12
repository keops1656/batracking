package com.matoosfe.batracking.negocio.seguridad;

import java.util.ArrayList;
import java.util.List;
import java.lang.Long;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import com.matoosfe.batracking.modelo.Entidad;
import com.matoosfe.batracking.modelo.RelacionEntidad;
import com.matoosfe.kernel.core.negocio.AbstractFacade;

/**
 * Clase para administrar las operaciones de Relaci√≥n Entidad
 * 
 * @author MATOOSFE - Ing. Marco Toscano Freire. Msc -martosfre 8 ago. 2017-
 *         23:29:56<br>
 *         <a href="mailto:mtoscano@matoosfe.com?Subject=Soporte Batracking"
 *         target="_top">Soporte</a><br>
 *         <a href="http://www.matoosfe.com">Matoosfe</a>
 */
@Stateless
public class RelacionEntidadFacade extends AbstractFacade<RelacionEntidad> {

	@PersistenceContext(unitName = "batrackingPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public RelacionEntidadFacade() {
		super(RelacionEntidad.class);
	}

	/**
	 * M√©todo para guardar la relaci√≥n de entidades
	 * 
	 * @param listaRelacionEnt
	 */
	public void guardar(List<RelacionEntidad> listaRelacionEnt, int idEnt) throws Exception {
		// Eliminar permisos anteriores
		Query conEliEnt = em.createNativeQuery("delete from relacion_entidad where id_entidad = ?1");
		conEliEnt.setParameter(1, idEnt);
		conEliEnt.executeUpdate();
		em.flush();

		for (RelacionEntidad relacionEntidad : listaRelacionEnt) {
			em.persist(relacionEntidad);
		}

	}

	/**
	 * MÈtodo para buscar entidades asociadas
	 * 
	 * @param idEntidad
	 * @return
	 */
	public List<Entidad> buscarEntidadesAsociadas(int idEntidad) throws Exception {
		List<Entidad> listaEntidadesAso = new ArrayList<>();
		TypedQuery<RelacionEntidad> conEntAso = em
				.createQuery("Select re from RelacionEntidad re where re.idEntidad =:idEnt", RelacionEntidad.class);
		conEntAso.setParameter("idEnt", idEntidad);
		List<RelacionEntidad> relacionesEnt = conEntAso.getResultList();
		for (RelacionEntidad relEntTmp : relacionesEnt) {
			TypedQuery<Entidad> conEnt = em.createQuery("Select e from Entidad e where e.idEntidad =:idEnt",
					Entidad.class);
			conEnt.setParameter("idEnt", relEntTmp.getIdEntidadPadre());
			Entidad entidad = conEnt.getSingleResult();
			listaEntidadesAso.add(entidad);
		}
		return listaEntidadesAso;
	}
	
	/**
	 * MÈtodo para buscar las entidades hijas de una entidad
	 * 
	 * @param idEntidad
	 * @return
	 */
	public List<RelacionEntidad> buscarEntidadesHijas(int idEntidad) throws Exception {
		List<RelacionEntidad> relacionesEnt = null;
		Long intCountEntidad = null;
		try{
			TypedQuery<Long> countEntRel = em
					.createQuery("SELECT COUNT(re.idEntidad) FROM RelacionEntidad re WHERE re.idEntidadPadre =:idEnt", Long.class);
			countEntRel.setParameter("idEnt", idEntidad);
			intCountEntidad = (Long) countEntRel.getSingleResult();
			System.out.println("Numero de hijos >>>>>>>>>>>>>>>> " + intCountEntidad);
			if(intCountEntidad > 0 && intCountEntidad != null) {
				TypedQuery<RelacionEntidad> conEntRel = em
						.createQuery("SELECT re FROM RelacionEntidad re WHERE re.idEntidadPadre =:idEnt", RelacionEntidad.class);
				conEntRel.setParameter("idEnt", idEntidad);
				relacionesEnt = conEntRel.getResultList();
			}
		}catch(Exception ex) {
			System.out.println("Excepcion EJB >>>> " + ex.getMessage());
			relacionesEnt.clear();
		}
		
		return relacionesEnt;
	}

	
	/**
	 * MÈtodo para buscar la relacion entidad dado id de la entidad
	 * 
	 * @param idEntidad
	 * @return
	 */
	public List<RelacionEntidad> obtenerRelacionEntidad(int idEntidad) throws Exception {
		List<RelacionEntidad> relacionesEnt =  new ArrayList<>();
		Long intCountEntidad = null;
		try{
			TypedQuery<Long> countEntRel = em
					.createQuery("SELECT COUNT(re.idEntidad) FROM RelacionEntidad re WHERE re.idEntidad =:idEnt", Long.class);
			countEntRel.setParameter("idEnt", idEntidad);
			intCountEntidad = (Long) countEntRel.getSingleResult();
			System.out.println("N˙mero de padres >>>>>>>>>>>>>>>> " + intCountEntidad);
			if(intCountEntidad > 0 && intCountEntidad != null) {
				TypedQuery<RelacionEntidad> conEntRel = em
						.createQuery("SELECT re FROM RelacionEntidad re WHERE re.idEntidad =:idEnt", RelacionEntidad.class);
				conEntRel.setParameter("idEnt", idEntidad);
				relacionesEnt = conEntRel.getResultList();
			}
		}catch(Exception ex) {
			System.out.println("Excepcion EJB >>>> " + ex.getMessage());
			relacionesEnt.clear();
		}
		
		return relacionesEnt;
	}
	
	/**
	 * MÈtodo para buscar la relacion entidad dado id de la entidad
	 * 
	 * @param idEntidad
	 * @return
	 */
	public List<RelacionEntidad> obtenerRelacionEntidadParaHijas(int idEntidad) throws Exception {
		List<RelacionEntidad> relacionesEnt = new ArrayList<>();
		Long intCountEntidad = null;
		try{
			TypedQuery<Long> countEntRel = em
					.createQuery("SELECT COUNT(re.idEntidad) FROM RelacionEntidad re WHERE re.idEntidadPadre =:idEnt", Long.class);
			countEntRel.setParameter("idEnt", idEntidad);
			intCountEntidad = (Long) countEntRel.getSingleResult();
			System.out.println("N˙mero de hijos >>>>>>>>>>>>>>>> " + intCountEntidad);
			if(intCountEntidad > 0 && intCountEntidad != null) {
				TypedQuery<RelacionEntidad> conEntRel = em
						.createQuery("SELECT re FROM RelacionEntidad re WHERE re.idEntidadPadre =:idEnt", RelacionEntidad.class);
				conEntRel.setParameter("idEnt", idEntidad);
				relacionesEnt = conEntRel.getResultList();
			}
		}catch(Exception ex) {
			relacionesEnt.clear();
		}
		return relacionesEnt;
	}

}
