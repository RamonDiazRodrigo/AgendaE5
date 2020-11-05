package com.ae5.sige.encryption;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.ae5.sige.model.Usuario;

public class Encriptacion {

	/**
	   * Método para encriptar texto.
	   * 
	   * @author e3corp
	   */
	  public static String encriptar(final String texto) {

	    final String secretKey = "ae5sige"; // llave para encriptar datos
	    String base64EncryptedString = "";

	    try {

	      final MessageDigest md = MessageDigest.getInstance("MD5");
	      final byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
	      final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

	      final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
	      final Cipher cipher = Cipher.getInstance("DESede");
	      cipher.init(Cipher.ENCRYPT_MODE, key);

	      final byte[] plainTextBytes = texto.getBytes("utf-8");
	      final byte[] buf = cipher.doFinal(plainTextBytes);
	      final byte[] base64Bytes = Base64.encodeBase64(buf);
	      base64EncryptedString = new String(base64Bytes);

	    } catch (Exception ex) {
	    }
	    return base64EncryptedString;
	  }
	  
	  /**
	   * Método para desencriptar texto.
	   * 
	   * @author ae5
	   */

	  public static String desencriptar(final String textoEncriptado) throws Exception {

	    final String secretKey = "ae5sige"; // llave para desencriptar datos
	    String base64EncryptedString = "";

	    try {
	      final byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
	      final MessageDigest md = MessageDigest.getInstance("MD5");
	      final byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
	      final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
	      final SecretKey key = new SecretKeySpec(keyBytes, "DESede");

	      final Cipher decipher = Cipher.getInstance("DESede");
	      decipher.init(Cipher.DECRYPT_MODE, key);

	      final byte[] plainText = decipher.doFinal(message);

	      base64EncryptedString = new String(plainText, "UTF-8");

	    } catch (Exception ex) {
	    }
	    return base64EncryptedString;
	  }
	  
	  /**
	   * Método para desencriptar un  optional usuario.
	   * 
	   * @author ae5
	   */
	  public static Optional<Usuario> desencriptarOptionalUsuario(final Optional<Usuario> user) {

			try {
				user.get().setContrasena(desencriptar(user.get().getContrasena()));
				user.get().setNombre(desencriptar(user.get().getNombre()));
				user.get().setApellidos(desencriptar(user.get().getApellidos()));
				user.get().setDni(desencriptar(user.get().getDni()));
				user.get().setTelefono(desencriptar(user.get().getTelefono()));
				user.get().setCorreo(desencriptar(user.get().getCorreo()));

				return user;
			} catch (Exception ex) {

				return null;
			}

		}

		  /**
		   * Método para desencriptar usuario.
		   * 
		   * @author ae5
		   */
		  public static Usuario desencriptarUsuario(final Usuario user) {

		    try {

		    	user.setContrasena(desencriptar(user.getContrasena()));
				user.setNombre(desencriptar(user.getNombre()));
				user.setApellidos(desencriptar(user.getApellidos()));
				user.setDni(desencriptar(user.getDni()));
				user.setTelefono(desencriptar(user.getTelefono()));
				user.setCorreo(desencriptar(user.getCorreo()));
		      return user;
		    } catch (Exception ex) {

		      return null;
		    }

		  }

}
