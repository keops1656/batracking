package com.matoosfe.batracking.entidad;

import java.util.ArrayList;

import com.matoosfe.batracking.modelo.EspecificacionBateria;
import com.matoosfe.batracking.modelo.ModeloAuto;

public class EEspecificacion {
	
	int intIdEspecificacion;
	String strCodEspecificacion;
	ArrayList<EModeloAuto> lstModeloAuto;
	
	public EEspecificacion() {
		this.intIdEspecificacion = 0;
		this.strCodEspecificacion = "";
		this.lstModeloAuto = new ArrayList<>();
	}
	
	public EEspecificacion(EspecificacionBateria objEspecificacionBateria) {
		
		if(objEspecificacionBateria.getId() >  0) {
			this.intIdEspecificacion = objEspecificacionBateria.getId() ;
		}else {
			this.intIdEspecificacion = 0;
		}
		
		if(objEspecificacionBateria.getCodEspecificacion() != null || !objEspecificacionBateria.getCodEspecificacion().equals("")  ) {
			this.strCodEspecificacion = objEspecificacionBateria.getCodEspecificacion() ;
		}else {
			this.strCodEspecificacion = "";
		}
		this.lstModeloAuto = new ArrayList<>();
		if( objEspecificacionBateria.getModeloAutos().size() > 0 ) {
			for (ModeloAuto objModeloAuto : objEspecificacionBateria.getModeloAutos()) {
				EModeloAuto objEModeloAuto = new EModeloAuto(objModeloAuto);
				lstModeloAuto.add(objEModeloAuto);			
			}
		}
		if( objEspecificacionBateria.getModeloAutos_Full().size() > 0 ) {
			for (ModeloAuto objModeloAuto : objEspecificacionBateria.getModeloAutos_Full()) {
				EModeloAuto objEModeloAuto = new EModeloAuto(objModeloAuto);
				lstModeloAuto.add(objEModeloAuto);			
			}
		}
		
	}

	public int getIntIdEspecificacion() {
		return intIdEspecificacion;
	}

	public void setIntIdEspecificacion(int intIdEspecificacion) {
		this.intIdEspecificacion = intIdEspecificacion;
	}

	public String getStrCodEspecificacion() {
		return strCodEspecificacion;
	}

	public void setStrCodEspecificacion(String strCodEspecificacion) {
		this.strCodEspecificacion = strCodEspecificacion;
	}

	public ArrayList<EModeloAuto> getLstModeloAuto() {
		return lstModeloAuto;
	}

	public void setLstModeloAuto(ArrayList<EModeloAuto> lstModeloAuto) {
		this.lstModeloAuto = lstModeloAuto;
	}
	
	
}
