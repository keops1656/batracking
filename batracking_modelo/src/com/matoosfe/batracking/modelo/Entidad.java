package com.matoosfe.batracking.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase que representa la tabla Entidad
 * 
 * @author MATOOSFE - Ing. Marco Toscano Freire. Msc -martosfre 17 ago. 2017-
 *         22:54:20<br>
 *         <a href="mailto:mtoscano@matoosfe.com?Subject=Soporte Batracking"
 *         target="_top">Soporte</a><br>
 *         <a href="http://www.matoosfe.com">Matoosfe</a>
 */
@Entity
@Table(name = "entidad")
@NamedQuery(name = "Entidad.findAll", query = "SELECT f FROM Entidad f")
public class Entidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_entidad")
	private int idEntidad;

	@Column(name = "ent_contacto")
	private String entContacto;

	@Column(name = "ent_descripcion")
	private String entDescripcion;

	@Column(name = "ent_email")
	private String entEmail;

	@Column(name = "ent_nombre")
	private String entNombre;

	@Column(name = "ent_pagina_web")
	private String entPaginaWeb;

	@Column(name = "ent_razon_social")
	private String entRazonSocial;

	@ManyToOne
	@JoinColumn(name = "ent_codigo_padre")
	private Entidad entidad;

	@ManyToOne
	@JoinColumn(name = "id_tipo_entidad")
	private TipoEntidad tipoEntidad;

	@OneToMany(mappedBy = "entidad")
	private List<Producto> productos;

	@OneToMany(mappedBy = "entidad")
	private List<Entidad> entidades;

	@OneToMany(mappedBy = "entidad")
	private List<Usuario> usuarios;

	public Entidad() {

	}

	/**
	 * @return the idEntidad
	 */
	public int getIdEntidad() {
		return idEntidad;
	}

	/**
	 * @param idEntidad
	 *            the idEntidad to set
	 */
	public void setIdEntidad(int idEntidad) {
		this.idEntidad = idEntidad;
	}

	/**
	 * @return the entContacto
	 */
	public String getEntContacto() {
		return entContacto;
	}

	/**
	 * @param entContacto
	 *            the entContacto to set
	 */
	public void setEntContacto(String entContacto) {
		this.entContacto = entContacto;
	}

	/**
	 * @return the entDescripcion
	 */
	public String getEntDescripcion() {
		return entDescripcion;
	}

	/**
	 * @param entDescripcion
	 *            the entDescripcion to set
	 */
	public void setEntDescripcion(String entDescripcion) {
		this.entDescripcion = entDescripcion;
	}

	/**
	 * @return the entEmail
	 */
	public String getEntEmail() {
		return entEmail;
	}

	/**
	 * @param entEmail
	 *            the entEmail to set
	 */
	public void setEntEmail(String entEmail) {
		this.entEmail = entEmail;
	}

	/**
	 * @return the entNombre
	 */
	public String getEntNombre() {
		return entNombre;
	}

	/**
	 * @param entNombre
	 *            the entNombre to set
	 */
	public void setEntNombre(String entNombre) {
		this.entNombre = entNombre;
	}

	/**
	 * @return the entPaginaWeb
	 */
	public String getEntPaginaWeb() {
		return entPaginaWeb;
	}

	/**
	 * @param entPaginaWeb
	 *            the entPaginaWeb to set
	 */
	public void setEntPaginaWeb(String entPaginaWeb) {
		this.entPaginaWeb = entPaginaWeb;
	}

	/**
	 * @return the entRazonSocial
	 */
	public String getEntRazonSocial() {
		return entRazonSocial;
	}

	/**
	 * @param entRazonSocial
	 *            the entRazonSocial to set
	 */
	public void setEntRazonSocial(String entRazonSocial) {
		this.entRazonSocial = entRazonSocial;
	}

	/**
	 * @return the entidad
	 */
	public Entidad getEntidad() {
		return entidad;
	}

	/**
	 * @param entidad
	 *            the entidad to set
	 */
	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	/**
	 * @return the tipoEntidad
	 */
	public TipoEntidad getTipoEntidad() {
		return tipoEntidad;
	}

	/**
	 * @param tipoEntidad
	 *            the tipoEntidad to set
	 */
	public void setTipoEntidad(TipoEntidad tipoEntidad) {
		this.tipoEntidad = tipoEntidad;
	}

	/**
	 * @return the productos
	 */
	public List<Producto> getProductos() {
		return productos;
	}

	/**
	 * @param productos
	 *            the productos to set
	 */
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	/**
	 * @return the entidades
	 */
	public List<Entidad> getEntidades() {
		return entidades;
	}

	/**
	 * @param entidades
	 *            the entidades to set
	 */
	public void setEntidades(List<Entidad> entidades) {
		this.entidades = entidades;
	}

	/**
	 * @return the usuarios
	 */
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * @param usuarios
	 *            the usuarios to set
	 */
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return tipoEntidad.getTipentNombre() + " - " + entNombre;
	}

}