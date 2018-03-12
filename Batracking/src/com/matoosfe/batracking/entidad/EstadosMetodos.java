package com.matoosfe.batracking.entidad;

public class EstadosMetodos {
	
	private boolean blResultado;
	private String strMensaje;
	
	public EstadosMetodos() {
		this.blResultado = false;
		this.strMensaje = "";
	}

	public EstadosMetodos(boolean blResultado, String strMensaje) {
		this.blResultado = blResultado;
		this.strMensaje = strMensaje;
	}

	public boolean isBlResultado() {
		return blResultado;
	}

	public void setBlResultado(boolean blResultado) {
		this.blResultado = blResultado;
	}

	public String getStrMensaje() {
		return strMensaje;
	}

	public void setStrMensaje(String strMensaje) {
		this.strMensaje = strMensaje;
	}
	
}
