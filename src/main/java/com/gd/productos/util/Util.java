package com.gd.productos.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Util {
	private Util(){}
    public static String imprimirStackTrace(Throwable ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString().trim();
    }
  
  
}
