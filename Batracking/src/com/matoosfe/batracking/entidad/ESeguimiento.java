package com.matoosfe.batracking.entidad;

import com.matoosfe.batracking.bean.util.Fechas;
import com.matoosfe.batracking.modelo.Seguimiento;

public class ESeguimiento {

	private int idSeguimiento;
	private int idUsuario;
	private int idProducto;
	private String segFecha;
	private String segLongitud;
	private String segLatitud;
	private String segEstado;
	private Integer segVidaUtil;
	private String segLugar;
	private Integer segMantenimiento;
	private int segActual;
	private int segTiempoRefrescar;
	private String segFechaRefrescar;
	
	public ESeguimiento() {
		this.idSeguimiento = 0;
		this.idUsuario = 0;
		this.idProducto = 0;
		this.segFecha = null;
		this.segLongitud = "";
		this.segLatitud = "";
		this.segEstado = "";
		this.segVidaUtil = 0;
		this.segLugar = "";
		this.segMantenimiento = 0;
		this.segActual = 0;
		this.segTiempoRefrescar = 0;
		this.segFechaRefrescar = null;
	}
	
	public ESeguimiento(Seguimiento seguimiento) {
		if(seguimiento.getIdSeguimiento() > 0) {
			this.idSeguimiento = seguimiento.getIdSeguimiento();
		}else {
			this.idSeguimiento = 0;
		}
		if(seguimiento.getUsuario().getIdUsuario() > 0) {
			this.idUsuario = seguimiento.getUsuario().getIdUsuario();
		}else {
			this.idUsuario = 0;
		}
		if(seguimiento.getProducto().getIdProducto() > 0) {
			this.idProducto = seguimiento.getProducto().getIdProducto();
		}else {
			this.idProducto = 0;
		}
		if(seguimiento.getSegFecha() != null  ) {
			this.segFecha = Fechas.convertirFechaString(seguimiento.getSegFecha() );
		}else {
			this.segFecha = null;
		}
		if(seguimiento.getSegLongitud() != null || !seguimiento.getSegLongitud().equals("")  ) {
			this.segLongitud = seguimiento.getSegLongitud();
		}else {
			this.segLongitud = "";
		}
		if(seguimiento.getSegLatitud() != null || !seguimiento.getSegLatitud().equals("")  ) {
			this.segLatitud = seguimiento.getSegLatitud();
		}else {
			this.segLatitud = "";
		}
		if(seguimiento.getSegEstado() != null || !seguimiento.getSegEstado().equals("")  ) {
			this.segEstado = seguimiento.getSegEstado();
		}else {
			this.segEstado = "";
		}
		if(seguimiento.getSegVidaUtil() > 0) {
			this.segVidaUtil = seguimiento.getSegVidaUtil();
		}else {
			this.segVidaUtil = 0;
		}
		if(seguimiento.getSegLugar() != null || !seguimiento.getSegLugar().equals("")  ) {
			this.segLugar = seguimiento.getSegLugar();
		}else {
			this.segLugar = "";
		}
		if(seguimiento.getSegMantenimiento() > 0) {
			this.segMantenimiento = seguimiento.getSegMantenimiento();
		}else {
			this.segMantenimiento = 0;
		}
		if(seguimiento.getSegActual() > 0) {
			this.segActual = seguimiento.getSegActual();
		}else {
			this.segActual = 0;
		}
		if(seguimiento.getSegTiempoRefrescar() > 0) {
			this.segTiempoRefrescar = seguimiento.getSegTiempoRefrescar();
		}else {
			this.segTiempoRefrescar = 0;
		}
		if(seguimiento.getSegFechaRefrescar() != null  ) {
			this.segFechaRefrescar = Fechas.convertirFechaString(seguimiento.getSegFechaRefrescar() );
		}else {
			this.segFechaRefrescar = null;
		}
	}
	
	public ESeguimiento(int idSeguimiento, int idUsuario, int idProducto, String segFecha, String segLongitud,
			String segLatitud, String segEstado, Integer segVidaUtil, String segLugar, Integer segMantenimiento,
			int segActual, int segTiempoRefrescar, String segFechaRefrescar) {
		this.idSeguimiento = idSeguimiento;
		this.idUsuario = idUsuario;
		this.idProducto = idProducto;
		this.segFecha = segFecha;
		this.segLongitud = segLongitud;
		this.segLatitud = segLatitud;
		this.segEstado = segEstado;
		this.segVidaUtil = segVidaUtil;
		this.segLugar = segLugar;
		this.segMantenimiento = segMantenimiento;
		this.segActual = segActual;
		this.segTiempoRefrescar = segTiempoRefrescar;
		this.segFechaRefrescar = segFechaRefrescar;
	}

	public int getIdSeguimiento() {
		return idSeguimiento;
	}

	public void setIdSeguimiento(int idSeguimiento) {
		this.idSeguimiento = idSeguimiento;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getSegFecha() {
		return segFecha;
	}

	public void setSegFecha(String segFecha) {
		this.segFecha = segFecha;
	}

	public String getSegLongitud() {
		return segLongitud;
	}

	public void setSegLongitud(String segLongitud) {
		this.segLongitud = segLongitud;
	}

	public String getSegLatitud() {
		return segLatitud;
	}

	public void setSegLatitud(String segLatitud) {
		this.segLatitud = segLatitud;
	}

	public String getSegEstado() {
		return segEstado;
	}

	public void setSegEstado(String segEstado) {
		this.segEstado = segEstado;
	}

	public Integer getSegVidaUtil() {
		return segVidaUtil;
	}

	public void setSegVidaUtil(Integer segVidaUtil) {
		this.segVidaUtil = segVidaUtil;
	}

	public String getSegLugar() {
		return segLugar;
	}

	public void setSegLugar(String segLugar) {
		this.segLugar = segLugar;
	}

	public Integer getSegMantenimiento() {
		return segMantenimiento;
	}

	public void setSegMantenimiento(Integer segMantenimiento) {
		this.segMantenimiento = segMantenimiento;
	}

	public int getSegActual() {
		return segActual;
	}

	public void setSegActual(int segActual) {
		this.segActual = segActual;
	}

	public int getSegTiempoRefrescar() {
		return segTiempoRefrescar;
	}

	public void setSegTiempoRefrescar(int segTiempoRefrescar) {
		this.segTiempoRefrescar = segTiempoRefrescar;
	}

	public String getSegFechaRefrescar() {
		return segFechaRefrescar;
	}

	public void setSegFechaRefrescar(String segFechaRefrescar) {
		this.segFechaRefrescar = segFechaRefrescar;
	}	
	
}
