package com.matoosfe.batracking.entidad;

public class EBatracking {
	
	private int idProducto;
	private int idUsuario;
	private boolean blRefrescar;
	private String tipoBateria;
	private String longitud;
	private String latitud;
	
	public EBatracking(int idProducto, int idUsuario, boolean blRefrescar, String tipoBateria, String longitud,
			String latitud) {
		this.idProducto = idProducto;
		this.idUsuario = idUsuario;
		this.blRefrescar = blRefrescar;
		this.tipoBateria = tipoBateria;
		this.longitud = longitud;
		this.latitud = latitud;
	}
	
	public EBatracking() {
		this.idProducto = 0;
		this.idUsuario = 0;
		this.blRefrescar = false;
		this.tipoBateria = "";
		this.longitud = "";
		this.latitud = "";
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public boolean isBlRefrescar() {
		return blRefrescar;
	}

	public void setBlRefrescar(boolean blRefrescar) {
		this.blRefrescar = blRefrescar;
	}

	public String getTipoBateria() {
		return tipoBateria;
	}

	public void setTipoBateria(String tipoBateria) {
		this.tipoBateria = tipoBateria;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	
	
	
	
}
