package com.ae5.sige.encryption;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
	 * Método para desencriptar un optional usuario.
	 * 
	 * @author ae5
	 */
	public static Optional<Usuario> desencriptarOptionalUsuario(final Optional<Usuario> usuario) {

		try {
			usuario.get().setContrasena(desencriptar(usuario.get().getContrasena()));
			usuario.get().setNombre(desencriptar(usuario.get().getNombre()));
			usuario.get().setApellidos(desencriptar(usuario.get().getApellidos()));
			usuario.get().setDni(desencriptar(usuario.get().getDni()));
			usuario.get().setTelefono(desencriptar(usuario.get().getTelefono()));
			usuario.get().setCorreo(desencriptar(usuario.get().getCorreo()));

			return usuario;
		} catch (Exception ex) {

			return null;
		}

	}

	/**
	 * Método para desencriptar usuario.
	 * 
	 * @author ae5
	 */
	public static Usuario desencriptarUsuario(final Usuario usuario) {

		try {

			usuario.setContrasena(desencriptar(usuario.getContrasena()));
			usuario.setNombre(desencriptar(usuario.getNombre()));
			usuario.setApellidos(desencriptar(usuario.getApellidos()));
			usuario.setDni(desencriptar(usuario.getDni()));
			usuario.setTelefono(desencriptar(usuario.getTelefono()));
			usuario.setCorreo(desencriptar(usuario.getCorreo()));
			return usuario;
		} catch (Exception ex) {

			return null;
		}

	}

	/**
	 * Método para desencriptar una optional lista de usuarios.
	 * 
	 * @author ae5
	 */
	public static List<Usuario> desencriptarListaUsuarios(final Optional<List<Usuario>> usuarios) {

		final List<Usuario> usuariosDesencriptados = new ArrayList<Usuario>();
		for (int i = 0; i < usuarios.get().size(); i++) {
			final Usuario usuario = usuarios.get().get(i);
			usuariosDesencriptados.add(desencriptarUsuario(usuario));

		}

		return usuariosDesencriptados;
	}

	/**
	 * Método para desencriptar una lista de usuarios.
	 * 
	 * @author ae5
	 */
	public static List<Usuario> desencriptarUsuarios(final List<Usuario> usuarios) {

		final List<Usuario> usuariosDesencriptados = new ArrayList<Usuario>();

		for (int i = 0; i < usuarios.size(); i++) {
			final Usuario usuario = usuarios.get(i);

			usuariosDesencriptados.add(desencriptarUsuario(usuario));

		}

		return usuariosDesencriptados;
	}

}
