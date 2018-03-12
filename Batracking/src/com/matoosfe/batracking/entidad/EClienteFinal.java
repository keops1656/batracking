package com.matoosfe.batracking.entidad;

import com.matoosfe.batracking.modelo.ClienteFinal;

public class EClienteFinal {

	private int idCliente;
	private String cliVehiculoMarca;
	private String cliVehiculoModelo;
	private String cliVehiculoPlaca;
	private String cliVehiculoColor;
	private String cliNombres;
	private String cliApellidos;
	private String cliTelefono;
	private String cliEmail;
	private String cliObservacion; //por defecto null
	private String cliFotoAuto;	
	
	
	public EClienteFinal() {
		this.idCliente = 0;
		this.cliVehiculoMarca = "";
		this.cliVehiculoModelo = "";
		this.cliVehiculoPlaca = "";
		this.cliVehiculoColor = "";
		this.cliNombres = "";
		this.cliApellidos = "";
		this.cliTelefono = "";
		this.cliEmail = "";
		this.cliObservacion = "";
		this.cliFotoAuto = "";
	}
	
	public EClienteFinal( ClienteFinal clienteFinal) {
		if(clienteFinal.getIdCliente() > 0) {
			this.idCliente = clienteFinal.getIdCliente();
		}else {
			this.idCliente = 0;
		}
		if(clienteFinal.getCliVehiculoMarca() != null ) {
			this.cliVehiculoMarca = clienteFinal.getCliVehiculoMarca();
		}else {
			this.cliVehiculoMarca = "";
		}
		if(clienteFinal.getCliVehiculoModelo() != null ) {
			this.cliVehiculoModelo = clienteFinal.getCliVehiculoModelo();
		}else {
			this.cliVehiculoModelo = "";
		}
		if(clienteFinal.getCliVehiculoPlaca() != null ) {
			this.cliVehiculoPlaca = clienteFinal.getCliVehiculoPlaca();
		}else {
			this.cliVehiculoPlaca = "";
		}
		if(clienteFinal.getCliVehiculoColor() != null ) {
			this.cliVehiculoColor = clienteFinal.getCliVehiculoColor();
		}else {
			this.cliVehiculoColor = "";
		}
		if(clienteFinal.getCliNombres() != null ) {
			this.cliNombres = clienteFinal.getCliNombres();			
		}else {
			this.cliNombres = "";			
		}
		if(clienteFinal.getCliApellidos() != null ) {
			this.cliApellidos = clienteFinal.getCliApellidos();
		}else {
			this.cliApellidos = "";
		}
		if(clienteFinal.getCliTelefono() != null ) {
			this.cliTelefono = clienteFinal.getCliTelefono();			
		}else {
			this.cliTelefono = "";			
		}
		if(clienteFinal.getCliEmail() != null ) {
			this.cliEmail = clienteFinal.getCliEmail();
		}else {
			this.cliEmail = "";
		}
		if(clienteFinal.getCliObservacion() != null ) {
			this.cliObservacion = clienteFinal.getCliObservacion();
		}else {
			this.cliObservacion = "";
		}
//		if(clienteFinal.getCliEmail() != null ) {
//			this.cliFotoAuto = clienteFinal.getCliEmail();
//		}else {
//			this.cliFotoAuto = "";
//		}
	}

	//GETTERS AND SETTERS
	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getCliVehiculoMarca() {
		return cliVehiculoMarca;
	}

	public void setCliVehiculoMarca(String cliVehiculoMarca) {
		this.cliVehiculoMarca = cliVehiculoMarca;
	}

	public String getCliVehiculoModelo() {
		return cliVehiculoModelo;
	}

	public void setCliVehiculoModelo(String cliVehiculoModelo) {
		this.cliVehiculoModelo = cliVehiculoModelo;
	}

	public String getCliVehiculoPlaca() {
		return cliVehiculoPlaca;
	}

	public void setCliVehiculoPlaca(String cliVehiculoPlaca) {
		this.cliVehiculoPlaca = cliVehiculoPlaca;
	}

	public String getCliVehiculoColor() {
		return cliVehiculoColor;
	}

	public void setCliVehiculoColor(String cliVehiculoColor) {
		this.cliVehiculoColor = cliVehiculoColor;
	}

	public String getCliNombres() {
		return cliNombres;
	}

	public void setCliNombres(String cliNombres) {
		this.cliNombres = cliNombres;
	}

	public String getCliApellidos() {
		return cliApellidos;
	}

	public void setCliApellidos(String cliApellidos) {
		this.cliApellidos = cliApellidos;
	}

	public String getCliTelefono() {
		return cliTelefono;
	}

	public void setCliTelefono(String cliTelefono) {
		this.cliTelefono = cliTelefono;
	}

	public String getCliEmail() {
		return cliEmail;
	}

	public void setCliEmail(String cliEmail) {
		this.cliEmail = cliEmail;
	}

	public String getCliObservacion() {
		return cliObservacion;
	}

	public void setCliObservacion(String cliObservacion) {
		this.cliObservacion = cliObservacion;
	}

	public String getCliFotoAuto() {
		return cliFotoAuto;
	}

	public void setCliFotoAuto(String cliFotoAuto) {
		this.cliFotoAuto = cliFotoAuto;
	}	
	
}
