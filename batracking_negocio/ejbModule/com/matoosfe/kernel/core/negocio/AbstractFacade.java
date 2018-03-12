package com.matoosfe.kernel.core.negocio;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class AbstractFacade<T> {
	private Class<T> entityClass;

	public AbstractFacade(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected abstract EntityManager getEntityManager();

	public void guardar(T entity) {
		getEntityManager().persist(entity);
	}

	public void actualizar(T entity) {
		getEntityManager().merge(entity);
	}

	public void eliminar(T entity, boolean logica) {
		if (logica) {
			getEntityManager().merge(entity);
		} else {
			getEntityManager().remove(getEntityManager().merge(entity));
		}
	}

	public T buscarPorId(Object id) {
		return (T) getEntityManager().find(this.entityClass, id);
	}

	public List<T> buscarTodos() {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(this.entityClass));
		return getEntityManager().createQuery(cq).getResultList();
	}

	public List<T> buscarTodos(String columnaActiva, int valor) {
		Query conTod = getEntityManager().createQuery(
				"Select t from " + this.entityClass.getSimpleName() + " t " + "where t." + columnaActiva + "=:valor");

		return conTod.setParameter("valor", Integer.valueOf(valor)).getResultList();
	}

	public List<T> buscarPorRango(int[] range) {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(this.entityClass));
		Query q = getEntityManager().createQuery(cq);
		q.setMaxResults(range[1] - range[0]);
		q.setFirstResult(range[0]);
		return q.getResultList();
	}

	public int contarRegistros() {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		Root<T> rt = cq.from(this.entityClass);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		Query q = getEntityManager().createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}
}
