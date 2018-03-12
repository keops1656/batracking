package com.matoosfe.batracking.entidad;

import com.matoosfe.batracking.modelo.Usuario;

public class ELogin {

	private String usuario;
	private String password;
	private int idUsuario;
	private String nombre;
	private String apellido;
	private int idEntidad;
	private String nombreEntidad;
	private int idEntidadPadre;
	private int idTipoEntidad;
	private String nombreTipoEntidad;
	private String token;
	
	public ELogin() {
		this.usuario = "";
		this.password = "";
		this.idUsuario= 0;
		this.nombre = "";
		this.apellido = "";
		this.idEntidad = 0;
		this.nombreEntidad = "";
		this.idTipoEntidad= 0;
		this.idEntidadPadre = 0;
		this.nombreTipoEntidad = "";
		this.token = "";
	}
	
	public ELogin(Usuario usuario){
		this.usuario = usuario.getUsuLogin();
		this.password = usuario.getUsuPassword();
		this.idUsuario= usuario.getIdUsuario();
		this.nombre = usuario.getUsuNombre();
		this.apellido = usuario.getUsuApellido();
		this.idEntidad = usuario.getEntidad().getIdEntidad();
		this.nombreEntidad = usuario.getEntidad().getEntNombre();
		try {
			this.idEntidadPadre = usuario.getEntidad().getEntidad().getIdEntidad();
		}catch(NullPointerException e){
			this.idEntidadPadre = 0;
		}
		this.idTipoEntidad = usuario.getEntidad().getTipoEntidad().getIdTipoEntidad();
		this.nombreTipoEntidad = usuario.getEntidad().getTipoEntidad().getTipentNombre();
		this.token = "";
	}
	
	public String getUsuario(){
		return this.usuario;
	}
	
	public String getPassword(){
		return this.password;
	}

	public void setUsuario(String usuario){
		this.usuario = usuario;
	}
	
	public void setPassword(String password){
		this.password = password;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(int idEntidad) {
		this.idEntidad = idEntidad;
	}

	public String getNombreEntidad() {
		return nombreEntidad;
	}

	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
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

	public String getNombreTipoEntidad() {
		return nombreTipoEntidad;
	}

	public void setNombreTipoEntidad(String nombreTipoEntidad) {
		this.nombreTipoEntidad = nombreTipoEntidad;
	}
	
		
}
