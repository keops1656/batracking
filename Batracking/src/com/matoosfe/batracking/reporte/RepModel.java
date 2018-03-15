package com.matoosfe.batracking.reporte;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class RepModel {
	
    private HttpServletResponse response;
    private FacesContext contex;
    private ByteArrayOutputStream baos;
    private InputStream stream;
	
	public RepModel() {
		    this.contex = FacesContext.getCurrentInstance();
		    this.response = (HttpServletResponse) contex.getExternalContext().getResponse();
    }
    
	
	public HttpServletResponse getResponse() {
		return response;
	}


	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}


	public FacesContext getContex() {
		return contex;
	}


	public void setContex(FacesContext contex) {
		this.contex = contex;
	}


	public ByteArrayOutputStream getBaos() {
		return baos;
	}


	public void setBaos(ByteArrayOutputStream baos) {
		this.baos = baos;
	}


	public InputStream getStream() {
		return stream;
	}


	public void setStream(InputStream stream) {
		this.stream = stream;
	}


	public void getReporteCodigo(String strRuta, int idPallet) throws Exception, ClassNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, IOException{
        System.out.println("Ruta :" + strRuta);
        System.out.println("idPallet :" + idPallet);
		Connection conexion = null;
        AccesoReportes accesoDatos = new AccesoReportes();
        conexion = accesoDatos.getConReportes();
        Map<String, Object> parametro = new HashMap<String, Object>();
        parametro.put("codigo", idPallet);
        try {
            File file = new File(strRuta);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.setContentType("application/pdf");
            httpServletResponse.addHeader("Content-Type", "application/pdf");
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(file.getPath());
            System.out.print("File:" + file.getPath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametro, conexion);
            JRExporter jrExporter = null;
            jrExporter = new JRPdfExporter();
            jrExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            jrExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, httpServletResponse.getOutputStream());
            if(jrExporter != null) {
            	try {
            		jrExporter.exportReport();
            	}catch(JRException e) {
            		e.printStackTrace();
            	}
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	if(conexion != null) {
        		try{
        			conexion.close();
        		}catch (Exception e) {
					e.printStackTrace();
				}
        	}
        }
        
	}
}
