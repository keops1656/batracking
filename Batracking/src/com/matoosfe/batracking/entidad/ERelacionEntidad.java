package com.matoosfe.batracking.entidad;

import com.matoosfe.batracking.modelo.RelacionEntidad;

public class ERelacionEntidad {

	private int idEntidad;
	private int idEntidadPadre;
	private int idTipoEntidad;
	
	public ERelacionEntidad() {
		this.idEntidad = 0;
		this.idEntidadPadre = 0;
		this.idTipoEntidad = 0;
	}
	
	public ERelacionEntidad( RelacionEntidad objRelacionEntidad) {
		if( objRelacionEntidad.getIdEntidad() > 0 ) {
			this.idEntidad = objRelacionEntidad.getIdEntidad();
		}else {
			this.idEntidad = 0;
		}
		if( objRelacionEntidad.getIdEntidadPadre() > 0 ) {
			this.idEntidadPadre = objRelacionEntidad.getIdEntidadPadre();
		}else {
			this.idEntidadPadre = 0;
		}
		this.idTipoEntidad = 0;
		
	}

	public int getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(int idEntidad) {
		this.idEntidad = idEntidad;
	}

	public int getIdEntidadPadre() {
		return idEntidadPadre;
	}

	public void setIdEntidadPadre(int idEntidadPadre) {
		this.idEntidadPadre = idEntidadPadre;
	}

	public int getIdTipoEntidad() {
		return idTipoEntidad;
	}

	public void setIdTipoEntidad(int idTipoEntidad) {
		this.idTipoEntidad = idTipoEntidad;
	}
	
}
