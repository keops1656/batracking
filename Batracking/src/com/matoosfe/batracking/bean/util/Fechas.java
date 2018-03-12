package com.matoosfe.batracking.bean.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Fechas {
	
	public static String convertirFechaString(Date date){
	   return new SimpleDateFormat("dd/MM/yyyy").format(date);	
	}

}
