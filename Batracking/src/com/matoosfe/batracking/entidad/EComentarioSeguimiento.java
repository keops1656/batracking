package com.matoosfe.batracking.entidad;

import com.matoosfe.batracking.bean.util.Fechas;
import com.matoosfe.batracking.modelo.ComentarioSeguimiento;

public class EComentarioSeguimiento {

	private int intIdObservacionSeg;
	private String strOsegDescripcion;
	private int intIdSeguimiento;
	private String fecha;
	private String strEntidad;
	
	public EComentarioSeguimiento() {
		this.intIdObservacionSeg = 0;
		this.strOsegDescripcion = "";
		this.intIdSeguimiento = 0;
		this.fecha = "";
		this.strEntidad = "";
	}
	
	public EComentarioSeguimiento(ComentarioSeguimiento comentario) {
		if(comentario.getIdObservacionSeg() > 0 )  {
			this.intIdObservacionSeg = comentario.getIdObservacionSeg();
		}else {
			this.intIdObservacionSeg = 0;
		}
		if(comentario.getOsegDescripcion() != null || !comentario.getOsegDescripcion().equals("")  )  {
			this.strOsegDescripcion = comentario.getOsegDescripcion();
		}else {
			this.strOsegDescripcion = "";
		}
		if(comentario.getSeguimiento() != null  )  {
			this.intIdSeguimiento = comentario.getSeguimiento().getIdSeguimiento();
		}else {
			this.intIdSeguimiento = 0;
		}
		if(comentario.getSeguimiento().getSegFecha() != null  )  {
			this.fecha = Fechas.convertirFechaString(comentario.getSeguimiento().getSegFecha());
		}else {
			this.fecha = "";
		}
		if(comentario.getSeguimiento().getUsuario().getEntidad() != null || !comentario.getSeguimiento().getUsuario().getEntidad().equals("") )  {
			this.strEntidad = comentario.getSeguimiento().getUsuario().getEntidad().getEntNombre();
		}else {
			this.strEntidad = "";
		}
		
	}
	

	public int getIntIdObservacionSeg() {
		return intIdObservacionSeg;
	}

	public void setIntIdObservacionSeg(int intIdObservacionSeg) {
		this.intIdObservacionSeg = intIdObservacionSeg;
	}

	public String getStrOsegDescripcion() {
		return strOsegDescripcion;
	}

	public void setStrOsegDescripcion(String strOsegDescripcion) {
		this.strOsegDescripcion = strOsegDescripcion;
	}

	public int getIntIdSeguimiento() {
		return intIdSeguimiento;
	}

	public void setIntIdSeguimiento(int intIdSeguimiento) {
		this.intIdSeguimiento = intIdSeguimiento;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getStrEntidad() {
		return strEntidad;
	}

	public void setStrEntidad(String strEntidad) {
		this.strEntidad = strEntidad;
	}
	 
	
}
