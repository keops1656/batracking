package com.matoosfe.batracking.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the fotografias_seguimiento database table.
 * 
 */
@Entity
@Table(name = "fotografias_seguimiento")
@NamedQuery(name = "FotografiasSeguimiento.findAll", query = "SELECT f FROM FotografiasSeguimiento f")
public class FotografiasSeguimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_fotografia_seg")
	private int idFotografiaSeg;

	@Column(name = "fseg_nombre")
	private String fsegNombre;

	@Column(name = "fseg_path")
	private String fsegPath;

	@Lob
	@Column(name = "fseg_real")
	private byte[] fsegReal;

	@Transient
	private int idTmp;

	// bi-directional many-to-one association to Seguimiento
	@ManyToOne
	@JoinColumn(name = "id_seguimiento")
	private Seguimiento seguimiento;

	public FotografiasSeguimiento() {
	}

	public int getIdFotografiaSeg() {
		return this.idFotografiaSeg;
	}

	public void setIdFotografiaSeg(int idFotografiaSeg) {
		this.idFotografiaSeg = idFotografiaSeg;
	}

	public String getFsegNombre() {
		return this.fsegNombre;
	}

	public void setFsegNombre(String fsegNombre) {
		this.fsegNombre = fsegNombre;
	}

	public String getFsegPath() {
		return this.fsegPath;
	}

	public void setFsegPath(String fsegPath) {
		this.fsegPath = fsegPath;
	}

	public byte[] getFsegReal() {
		return this.fsegReal;
	}

	public void setFsegReal(byte[] fsegReal) {
		this.fsegReal = fsegReal;
	}

	/**
	 * @return the idTmp
	 */
	public int getIdTmp() {
		return idTmp;
	}

	/**
	 * @param idTmp
	 *            the idTmp to set
	 */
	public void setIdTmp(int idTmp) {
		this.idTmp = idTmp;
	}

	public Seguimiento getSeguimiento() {
		return this.seguimiento;
	}

	public void setSeguimiento(Seguimiento seguimiento) {
		this.seguimiento = seguimiento;
	}

}