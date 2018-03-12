package com.matoosfe.batracking.reporte;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccesoReportes {
	public Connection con;

    public AccesoReportes() throws Exception {
        try {
            Global global = new Global();
            Class.forName(global.getDRIVER());
            con = DriverManager.getConnection(global.getURL(), global.getUSER(), global.getPASS());
        } catch (Exception ex) {
            Logger.getLogger(AccesoReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConReportes() {
        return con;
    }

    //Ejecuta una actualización sobre una tabla de base de datos
    public boolean ejecutaPreparedComando(PreparedStatement prStm) throws Exception {
        int i = -1;
        try {
            i = prStm.executeUpdate();
        } catch (SQLException exConec) {
            throw exConec;
        }
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    //Desconecta la conexión de un enlace a una base de datos
    public void desconectarAdmin() throws Exception {
        try {
            con.close();
            con = null;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
