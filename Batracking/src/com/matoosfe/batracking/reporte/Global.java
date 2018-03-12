package com.matoosfe.batracking.reporte;

public class Global {
	java.util.ResourceBundle Configuracion = java.util.ResourceBundle.getBundle("com.matoosfe.batracking.reporte.BaseDatos");
    private String URL=Configuracion.getString("url");
    private String DRIVER = Configuracion.getString("driver");
    private String USER = Configuracion.getString("user");
    private String PASS = Configuracion.getString("password");
    
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getDRIVER() {
		return DRIVER;
	}
	public void setDRIVER(String dRIVER) {
		DRIVER = dRIVER;
	}
	public String getUSER() {
		return USER;
	}
	public void setUSER(String uSER) {
		USER = uSER;
	}
	public String getPASS() {
		return PASS;
	}
	public void setPASS(String pASS) {
		PASS = pASS;
	}       
}
