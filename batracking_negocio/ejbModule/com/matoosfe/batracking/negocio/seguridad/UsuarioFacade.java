/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matoosfe.batracking.negocio.seguridad;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.matoosfe.batracking.modelo.Usuario;
import com.matoosfe.kernel.core.negocio.AbstractFacade;

/**
 * Clase para administrar las operaciones de usuario
 *
 * @author Ing Marco Toscano Freire - martosfre - matoosfe
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

	@PersistenceContext(unitName = "batrackingPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public UsuarioFacade() {
		super(Usuario.class);
	}

	/**
	 * Método para validar un usuario tomando en cuenta el usuario y su clave
	 *
	 * @param usuUsuario
	 * @param usuClave
	 * @return
	 * @throws Exception
	 */
	public Usuario validarUsuario(String usuUsuario, String usuClave) throws Exception {

		Usuario usuario = null;

		try {
			Query conUsu = em.createQuery(
					"Select usu from Usuario usu" + " where usu.usuLogin =:usuario and usu.usuPassword =:clave");
			conUsu.setParameter("usuario", usuUsuario);
			conUsu.setParameter("clave", usuClave);
			usuario = (Usuario) conUsu.getSingleResult();
		} catch (Exception e) {
			throw new Exception("Credenciales Incorrectas");
		}
		return usuario;
	}

}
