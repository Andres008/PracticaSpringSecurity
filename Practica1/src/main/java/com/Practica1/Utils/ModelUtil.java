package com.Practica1.Utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ModelUtil {

	
	public static String convMD5(String cadena) throws NoSuchAlgorithmException {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(cadena.getBytes("UTF-8"), 0, cadena.length());
			byte[] bt = md.digest();
			BigInteger bi = new BigInteger(1, bt);
			String md5 = bi.toString(16);
			return md5.toUpperCase();
		} catch (UnsupportedEncodingException ex) {
		}
		return null;
	}
	
	public static void passCheck(String password) throws Exception {
		int length = 0; // Almacenamos numero de caracteres en el pass
		int numCount = 0; // Variable usada para almacenar numeros en el password
		int capCount = 0; // Variable usada para almacenar mayusculas en el password
		int lowCount = 0; // Variable usada para almacenar minusculas en el password

		for (int x = 0; x < password.length(); x++) {
			if ((password.charAt(x) > 47 && password.charAt(x) < 58)) { // Cuenta la cantidad de numero
				numCount++;
			}

			if (Character.isUpperCase(password.charAt(x))) { // Cuenta la cantidad de mayuscula
				capCount++;
			}
			if (Character.isLowerCase(password.charAt(x))) { // Cuenta la cantidad de mayuscula
				lowCount++;
			}
			length = (x + 1); // Cuenta la longitud del password
		} // Final del bucle

		if (numCount < 1) { // Revisa que el password contenga minimo 1 numero
			throw new Exception("Atención, clave no tiene suficientes números!");
		}

		if (capCount < 1) { // Revisa que el password contenga minimo 1 mayuscula
			throw new Exception("Atención, clave no tiene suficientes mayusculas el Password!");
		}

		if (lowCount < 1) { // Revisa que el password contenga minimo 1 mayuscula
			throw new Exception("Atención, clave no tiene suficientes minusculas el Password!");
		}

		if (length < 10) { // Revisa la longitud minima de 10 caracteres del password
			throw new Exception("Atención, clave no cumple con el mínimo de caracteres!");
		}

	}
	
}
