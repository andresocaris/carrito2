package com.ao.juego.util;

import java.util.Date;

public class Tiempo {

	@SuppressWarnings("deprecation")
	public Date convertStrinToDate(String fecha) {
		if ( fecha.length()>19)return null;
		//"2021-09-22 20:40:14"
		String ano = fecha.substring(0,4);
		String mes = fecha.substring(5,7);
		String dia = fecha.substring(8,10);
		String hora = fecha.substring(11,13);
		String min = fecha.substring(14,16);
		String sec = fecha.substring(17,19);
		return new Date(Integer.valueOf(ano)-1900,Integer.valueOf(mes)-1
				,Integer.valueOf(dia),Integer.valueOf(hora),Integer.valueOf(min),
				Integer.valueOf(sec));
	}
}
