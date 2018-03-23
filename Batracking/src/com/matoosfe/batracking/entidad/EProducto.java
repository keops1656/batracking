package com.matoosfe.batracking.entidad;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.matoosfe.batracking.bean.util.Fechas;
import com.matoosfe.batracking.modelo.Producto;
import com.matoosfe.batracking.modelo.Seguimiento;

public class EProducto {
	
	private int idProducto;
	private int idPallet;
	private int idEntidad;
	private String strNombreEntidad;
	private String prodCodigo;
	private String prodEspecificaciones;
	private String prodEstadoBateria;
	private String prodFechaProduccion;
	private String prodFechaVenta;	
	private String prodFechaMantenimiento;
	private String prodFechaRepone;
	private int idParametrizacion;  // par_codigo
	private int idTipoBateria;		// tipbat_codigo
	private int intContadorRefrescador; 	//prodContadorRefrescar;
	private double dblMesesVida;
	private double dblMesesVividos;
	private ArrayList<String> lstTransacciones;
	private boolean blRefresh;
	private String strDetalleRefresh;
	private String segFechaRecepecion;
	private String segFechaRefrescada;
	private String strLugar;
	private String strEspecificacionBateria;
	
	public EProducto() {
		this.idProducto = 0;
		this.idPallet = 0;
		this.idEntidad = 0;
		this.strNombreEntidad = "";
		this.prodCodigo = "";
		this.prodEspecificaciones = "";
		this.prodEstadoBateria = "";
		this.prodFechaProduccion = null;
		this.prodFechaVenta = null;
		this.prodFechaMantenimiento = null;
		this.prodFechaRepone = null;
		this.idParametrizacion = 0;
		this.idTipoBateria = 0;
		this.intContadorRefrescador = 0;
		this.dblMesesVida = 0;
		this.dblMesesVividos = 0;
		this.lstTransacciones = new ArrayList<>();
		this.blRefresh = false;
		this.strDetalleRefresh = "";
		this.segFechaRecepecion = null;
		this.segFechaRefrescada= null;
		this.strLugar = "";
		this.strEspecificacionBateria = "";
	}
	
	public EProducto(Producto producto, Seguimiento seguimientoRefresh, Seguimiento seguimientoRecepcion ) {
		if(producto.getIdProducto() > 0) {
			this.idProducto = producto.getIdProducto();
		}else {
			this.idProducto = 0;
		}
		if(producto.getPallet() != null) {
			this.idPallet = producto.getPallet().getIdPallet();
		}else {
			this.idPallet = 0;
		}
		if(seguimientoRecepcion != null) {
			this.idEntidad = seguimientoRecepcion.getUsuario().getEntidad().getIdEntidad();
		}else {
			this.idEntidad = 0;
		}
		if(seguimientoRecepcion != null) {
			this.strNombreEntidad = seguimientoRecepcion.getUsuario().getEntidad().getEntNombre();
		}else {
			this.strNombreEntidad = "";
		}
		if(producto.getProdCodigo() != null || !producto.getProdCodigo().equals("")  ) {
			this.prodCodigo =producto.getProdCodigo();
		}else {
			this.prodCodigo = "";
		}
		if(producto.getProdEspecificaciones() != null || !producto.getProdEspecificaciones().equals("")  ) {
			this.prodEspecificaciones =producto.getProdEspecificaciones();
		}else {
			this.prodEspecificaciones = "";
		}
		if(producto.getProdEstadoBateria() != null || !producto.getProdEstadoBateria().equals("")  ) {
			this.prodEstadoBateria =producto.getProdEstadoBateria();
		}else {
			this.prodEstadoBateria = "";
		}
		if(producto.getProdFechaProduccion() != null  ) {
			this.prodFechaProduccion = Fechas.convertirFechaString(producto.getProdFechaProduccion() );
		}else {
			this.prodFechaProduccion = null;
		}
		if(producto.getProdFechaVenta() != null  ) {
			this.prodFechaVenta = Fechas.convertirFechaString(producto.getProdFechaVenta() );
		}else {
			this.prodFechaVenta = null;
		}
		if(producto.getProdFechaMantenimiento() != null  ) {
			this.prodFechaMantenimiento = Fechas.convertirFechaString(producto.getProdFechaMantenimiento() );
		}else {
			this.prodFechaMantenimiento = null;
		}
		if(producto.getProdFechaRepone() != null  ) {
			this.prodFechaRepone = Fechas.convertirFechaString(producto.getProdFechaRepone() );
		}else {
			this.prodFechaRepone = null;
		}
		if(producto.getParametro() != null) {
			this.idParametrizacion = producto.getParametro().getParCodigo();
		}else {
			this.idParametrizacion = 0;
		}
		if(producto.getTipoBateria() != null) {
			this.idTipoBateria = producto.getParametro().getParCodigo();
		}else {
			this.idTipoBateria = 0;
		}
		if(producto.getProdContadorRefrescar() > 0) {
			this.intContadorRefrescador = producto.getProdContadorRefrescar();
		}else {
			this.intContadorRefrescador = 0;
		}
		
		this.blRefresh = obtenerEstadoRefrescamiento(producto, seguimientoRefresh);  // también se asigna --> this.dblMesesVida
		
		this.lstTransacciones = listarTransaccionesDisponibles(producto);
		
		if(seguimientoRecepcion != null  ) {
			this.segFechaRecepecion = Fechas.convertirFechaString(seguimientoRecepcion.getSegFecha() );
			this.strLugar = seguimientoRecepcion.getSegLugar();
		}

		if(seguimientoRefresh != null  ) {
			this.segFechaRefrescada = Fechas.convertirFechaString(seguimientoRefresh.getSegFechaRefrescar() );
		}else {
			this.segFechaRefrescada = null;
		}
		
		if( producto.getEspecficacionBateria() != null  ) {
			this.strEspecificacionBateria = producto.getEspecficacionBateria().getCodEspecificacion();
		}else {
			this.strEspecificacionBateria = null;
		}
		
		
	}
	
	
	
	private ArrayList<String> listarTransaccionesDisponibles(Producto producto) {
		ArrayList<String> listaTransacciones = new ArrayList<>();
		if (producto.getProdFechaVenta() == null) {
			listaTransacciones.add("V");
		} else {
			listaTransacciones.add("M");
			listaTransacciones.add("G");
		}
		
		return listaTransacciones;
	}
	
	private boolean obtenerEstadoRefrescamiento(Producto producto, Seguimiento seguimientoRefresh) {
		
		int x = producto.getParametro().getParRefrescamiento();		//meses necesarios para refrescar
		
		int y = producto.getProdContadorRefrescar();			// cantidad de refrescamientos
		
		int i = producto.getParametro().getParVidaUtil();		//meses de vida util
		
		
		//FECHA ACTUAL
		Calendar calendarFActual = Calendar.getInstance();
		Date fActual = new Date();
		calendarFActual.setTime( fActual );
		System.out.println( "Fecha Actual >>> " + fActual ); // Devuelve el objeto Date con los nuevos días añadidos
		
		//FECHA PRODUCCION
		
		Calendar calendarFProduccion = Calendar.getInstance();
		System.out.println( "Fecha Produccion >>> " + producto.getProdFechaProduccion() ); // Devuelve el objeto Date con los nuevos días añadidos
		calendarFProduccion.setTime( producto.getProdFechaProduccion() ); // Configuramos la fecha que se recibe
		
		Calendar calendarFCaducidad = Calendar.getInstance();
		calendarFCaducidad.setTime( producto.getProdFechaProduccion() ); // Configuramos la fecha que se recibe
		calendarFCaducidad.add( Calendar.MONTH ,  i );  // numero de días a añadir, o restar en caso de días<0
		System.out.println( "Fecha Caducidad >>> " + calendarFCaducidad.getTime() ); // Devuelve el objeto Date con los nuevos días añadidos

		///////////////////////////////////////////////////////////////////////////////
		
		// RETURN FALSE
		
		double vidaRestante =  calendarFCaducidad.get(Calendar.MONTH) - calendarFActual.get(Calendar.MONTH);
		this.dblMesesVida = vidaRestante;
		this.dblMesesVividos = i - vidaRestante;
		
		if( y == 2 ) {
			System.out.println("False 1: Número máximo de refresh alcanzado");
			this.strDetalleRefresh= "Número máximo de refrescamiento alcanzado";
			return false;
		}
		
		if( vidaRestante <= 0 ) {
			System.out.println("False 2: Bateria sin vida útil");
			this.strDetalleRefresh= "Bateria sin vida útil";
			return false;
		}
					
		//RETURN TRUE 
		
		//REFRESCAMIENTO 1 
		
		Calendar calendarRefresh1 = Calendar.getInstance();
		calendarRefresh1.setTime( producto.getProdFechaProduccion() ); // Configuramos la fecha que se recibe
		calendarRefresh1.add( Calendar.MONTH ,  x );  // numero de días a añadir, o restar en caso de días<0
		System.out.println( "Fecha Refresh N°1 >>> " + calendarRefresh1.getTime() ); // Devuelve el objeto Date con los nuevos días añadidos
		
		if( (y == 0) && 
			( calendarRefresh1.get(Calendar.MONTH) <= calendarFActual.get(Calendar.MONTH) ) &&
			( vidaRestante > 0)
		  ) {
				
				System.out.println("TRUE REFRESH 1");
				this.strDetalleRefresh= "Refresh1";
				return true;
		  }
		
		//REFRESCAMIENTO 2
		if( seguimientoRefresh != null) {
			Calendar calendarRefresh2 = Calendar.getInstance();
			calendarRefresh2.setTime( seguimientoRefresh.getSegFechaRefrescar() ); // Configuramos la fecha que se recibe
			calendarRefresh2.add( Calendar.MONTH ,  x );  // numero de días a añadir, o restar en caso de días<0
			System.out.println( "Fecha Refresh N°2 >>> " + calendarRefresh2.getTime() ); // Devuelve el objeto Date con los nuevos días añadidos
			if( (y == 1) && 
				( calendarRefresh2.get(Calendar.MONTH) <= calendarFActual.get(Calendar.MONTH) ) &&
				( vidaRestante > 0)
			   ) {
					System.out.println("TRUE REFRESH 2");
					this.strDetalleRefresh= "Refresh2";
					return true;
			   }
		}
		
		System.out.println("False x defecto");
		this.strDetalleRefresh= "No es necesario refrescar la Bateria";
		return false;
		
	}
	
	
	//GETTERS AND SETTERS
	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getIdPallet() {
		return idPallet;
	}

	public void setIdPallet(int idPallet) {
		this.idPallet = idPallet;
	}

	public int getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(int idEntidad) {
		this.idEntidad = idEntidad;
	}

	public String getProdCodigo() {
		return prodCodigo;
	}

	public void setProdCodigo(String prodCodigo) {
		this.prodCodigo = prodCodigo;
	}

	public String getProdEspecificaciones() {
		return prodEspecificaciones;
	}

	public void setProdEspecificaciones(String prodEspecificaciones) {
		this.prodEspecificaciones = prodEspecificaciones;
	}

	public String getProdEstadoBateria() {
		return prodEstadoBateria;
	}

	public void setProdEstadoBateria(String prodEstadoBateria) {
		this.prodEstadoBateria = prodEstadoBateria;
	}

	public String getProdFechaProduccion() {
		return prodFechaProduccion;
	}

	public void setProdFechaProduccion(String prodFechaProduccion) {
		this.prodFechaProduccion = prodFechaProduccion;
	}

	public String getProdFechaVenta() {
		return prodFechaVenta;
	}

	public void setProdFechaVenta(String prodFechaVenta) {
		this.prodFechaVenta = prodFechaVenta;
	}

	public String getProdFechaMantenimiento() {
		return prodFechaMantenimiento;
	}

	public void setProdFechaMantenimiento(String prodFechaMantenimiento) {
		this.prodFechaMantenimiento = prodFechaMantenimiento;
	}

	public String getProdFechaRepone() {
		return prodFechaRepone;
	}

	public void setProdFechaRepone(String prodFechaRepone) {
		this.prodFechaRepone = prodFechaRepone;
	}

	public int getIdParametrizacion() {
		return idParametrizacion;
	}

	public void setIdParametrizacion(int idParametrizacion) {
		this.idParametrizacion = idParametrizacion;
	}

	public int getIdTipoBateria() {
		return idTipoBateria;
	}

	public void setIdTipoBateria(int idTipoBateria) {
		this.idTipoBateria = idTipoBateria;
	}

	public int getIntContadorRefrescador() {
		return intContadorRefrescador;
	}

	public void setIntContadorRefrescador(int intContadorRefrescador) {
		this.intContadorRefrescador = intContadorRefrescador;
	}
	
	public double getDblMesesVida() {
		return dblMesesVida;
	}

	public void setDblMesesVida(double dblMesesVida) {
		this.dblMesesVida = dblMesesVida;
	}
	
	public ArrayList<String> getLstTransacciones() {
		return lstTransacciones;
	}

	public void setLstTransacciones(ArrayList<String> lstTransacciones) {
		this.lstTransacciones = lstTransacciones;
	}

	public boolean isBlRefresh() {
		return blRefresh;
	}

	public void setBlRefresh(boolean blRefresh) {
		this.blRefresh = blRefresh;
	}

	public String getStrDetalleRefresh() {
		return strDetalleRefresh;
	}

	public void setStrDetalleRefresh(String strDetalleRefresh) {
		this.strDetalleRefresh = strDetalleRefresh;
	}

	public double getDblMesesVividos() {
		return dblMesesVividos;
	}

	public void setDblMesesVividos(double dblMesesVividos) {
		this.dblMesesVividos = dblMesesVividos;
	}

	public String getSegFechaRecepecion() {
		return segFechaRecepecion;
	}

	public void setSegFechaRecepecion(String segFechaRecepecion) {
		this.segFechaRecepecion = segFechaRecepecion;
	}

	public String getSegFechaRefrescada() {
		return segFechaRefrescada;
	}

	public void setSegFechaRefrescada(String segFechaRefrescada) {
		this.segFechaRefrescada = segFechaRefrescada;
	}

	public String getStrLugar() {
		return strLugar;
	}

	public void setStrLugar(String strLugar) {
		this.strLugar = strLugar;
	}

	public String getStrNombreEntidad() {
		return strNombreEntidad;
	}

	public void setStrNombreEntidad(String strNombreEntidad) {
		this.strNombreEntidad = strNombreEntidad;
	}
	
	public String getStrEspecificacionBateria() {
		return strEspecificacionBateria;
	}

	public void setStrEspecificacionBateria(String strEspecificacionBateria) {
		this.strEspecificacionBateria = strEspecificacionBateria;
	}

	
		
}
