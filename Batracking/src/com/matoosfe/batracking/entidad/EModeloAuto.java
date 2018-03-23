package com.matoosfe.batracking.entidad;

import com.matoosfe.batracking.modelo.ModeloAuto;

public class EModeloAuto {
	
	int intIdModelo;
	String strModelo;
	int intIdMarca;
	String strMarca;
	String strMotor;
	String strAnio;
	int idEspecificacion;
	int idEspecificacionFull;
	
	public EModeloAuto() {
		this.intIdModelo = 0;
		this.strModelo = "";
		this.intIdMarca = 0;
		this.strMarca = "";
		this.strMotor = "";
		this.strAnio = "";
		this.idEspecificacion = 0;
		this.idEspecificacionFull = 0;
	}
	
	public EModeloAuto( ModeloAuto objModeloAuto) {
		
		if( objModeloAuto.getId() > 0) {
			this.intIdModelo = objModeloAuto.getId();
		}else{
			this.intIdModelo = 0;
		}
		
//		System.out.println("Modelo paso x aqui 1");
		
		if( objModeloAuto.getDescripcion() != null && !objModeloAuto.getDescripcion().equals("")) {
			this.strModelo = objModeloAuto.getDescripcion();
		}else{
			this.strModelo = "";
		}
		
//		System.out.println("Modelo paso x aqui 2");
		
		if( objModeloAuto.getMarcaAuto().getId() > 0 ) {
			this.intIdMarca = objModeloAuto.getMarcaAuto().getId() ;
		}else{
			this.intIdMarca = 0;
		}
		
//		System.out.println("Modelo paso x aqui 3");
		
		if( objModeloAuto.getMarcaAuto() != null ) {
			this.strMarca = objModeloAuto.getMarcaAuto().getDescripcion();
		}else{
			this.strMarca = "";
		}
		
//		System.out.println("Modelo paso x aqui 4");
		
		if( objModeloAuto.getMotor() != null && !objModeloAuto.getMotor().equals("")) {
			this.strMotor = objModeloAuto.getDescripcion();
		}else{
			this.strMotor = "";
		}
		
//		System.out.println("Modelo paso x aqui 5");
		
		if( objModeloAuto.getAnio() != null && !objModeloAuto.getAnio().equals("")) {
			this.strAnio = objModeloAuto.getAnio();
		}else{
			this.strAnio = "";
		}
		
//		System.out.println("Modelo paso x aqui 6");
		
		if( objModeloAuto.getEspecficacionBateria() != null) {
			this.idEspecificacion = objModeloAuto.getEspecficacionBateria().getId();
		}else{
			this.idEspecificacion = 0;
		}
		
//		System.out.println("Modelo paso x aqui 7");
		
		if( objModeloAuto.getEspecficacionBateriaFull() != null) {
			this.idEspecificacionFull = objModeloAuto.getEspecficacionBateriaFull().getId();
//			System.out.println("paso x aqui");
		}else{
			this.idEspecificacionFull = 0;
		}
//		System.out.println("Modelo paso x aqui 8");
		
	}

	public int getIntIdModelo() {
		return intIdModelo;
	}

	public void setIntIdModelo(int intIdModelo) {
		this.intIdModelo = intIdModelo;
	}

	public String getStrModelo() {
		return strModelo;
	}

	public void setStrModelo(String strModelo) {
		this.strModelo = strModelo;
	}

	public int getIntIdMarca() {
		return intIdMarca;
	}

	public void setIntIdMarca(int intIdMarca) {
		this.intIdMarca = intIdMarca;
	}

	public String getStrMarca() {
		return strMarca;
	}

	public void setStrMarca(String strMarca) {
		this.strMarca = strMarca;
	}

	public String getStrMotor() {
		return strMotor;
	}

	public void setStrMotor(String strMotor) {
		this.strMotor = strMotor;
	}

	public String getStrAnio() {
		return strAnio;
	}

	public void setStrAnio(String strAnio) {
		this.strAnio = strAnio;
	}

	public int getIdEspecificacion() {
		return idEspecificacion;
	}

	public void setIdEspecificacion(int idEspecificacion) {
		this.idEspecificacion = idEspecificacion;
	}

	public int getIdEspecificacionFull() {
		return idEspecificacionFull;
	}

	public void setIdEspecificacionFull(int idEspecificacionFull) {
		this.idEspecificacionFull = idEspecificacionFull;
	}
	
}
