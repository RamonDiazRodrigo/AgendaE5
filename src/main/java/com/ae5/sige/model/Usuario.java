package com.ae5.sige.model;

import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

@Document(collection = "Usuarios")
public class Usuario {
	/**
	 * ID.
	 * 
	 * @author ae5
	 */
	@Id
	private String id;
	/**
	 * Contrasena.
	 * 
	 * @author ae5
	 */
	@NonNull
	private String contrasena;
	/**
	 * nombre.
	 * 
	 * @author ae5
	 */
	private String nombre;
	/**
	 * apellidos.
	 * 
	 * @author ae5
	 */
	private String apellidos;
	/**
	 * dni.
	 * 
	 * @author ae5
	 */
	@NonNull
	private String dni;
	/**
	 * telefono.
	 * 
	 * @author ae5
	 */
	private String telefono;
	/**
	 * correo.
	 * 
	 * @author ae5
	 */
	private String correo;
	/**
	 * tipo.
	 * 
	 * @author ae5
	 */
	private String tipo;
	/**
	 * listaReuniones.
	 * 
	 * @author ae5
	 */
	private List<String> listaReuniones;
	/**
	 * listaReunionesNuevas.
	 * 
	 * @author ae5
	 */
	private List<String> listaReunionesNuevas;

	public Usuario( @NonNull String contrasena, String nombre, String apellidos,
			@NonNull String dni, String telefono, String correo, String tipo, List<String> listaReuniones,
			List<String> listaReunionesNuevas) {
		super();
		this.id = UUID.randomUUID().toString();
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.telefono = telefono;
		this.correo = correo;
		this.tipo = tipo;
		this.listaReuniones = listaReuniones;
		this.listaReunionesNuevas = listaReunionesNuevas;
	}


	public Usuario() {
		
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getnombre() {
		return nombre;
	}

	public void setnombre(String nombre) {
		this.nombre = nombre;
	}

	public String getapellidos() {
		return apellidos;
	}

	public void setapellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getdni() {
		return dni;
	}

	public void setdni(String dNI) {
		dni = dNI;
	}

	public String gettelefono() {
		return telefono;
	}

	public void settelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getcorreo() {
		return correo;
	}

	public void setcorreo(String correo) {
		this.correo = correo;
	}

	public String gettipo() {
		return tipo;
	}

	public void settipo(String tipo) {
		this.tipo = tipo;
	}

	public List<String> getlistaReuniones() {
		return listaReuniones;
	}

	public void setlistaReuniones(List<String> listaReuniones) {
		this.listaReuniones = listaReuniones;
	}

	public List<String> getlistaReunionesNuevas() {
		return listaReunionesNuevas;
	}

	public void setlistaReunionesNuevas(List<String> listaReunionesNuevas) {
		this.listaReunionesNuevas = listaReunionesNuevas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((contrasena == null) ? 0 : contrasena.hashCode());
		result = prime * result + ((correo == null) ? 0 : correo.hashCode());
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((listaReuniones == null) ? 0 : listaReuniones.hashCode());
		result = prime * result + ((listaReunionesNuevas == null) ? 0 : listaReunionesNuevas.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (contrasena == null) {
			if (other.contrasena != null)
				return false;
		} else if (!contrasena.equals(other.contrasena))
			return false;
		if (correo == null) {
			if (other.correo != null)
				return false;
		} else if (!correo.equals(other.correo))
			return false;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (listaReuniones == null) {
			if (other.listaReuniones != null)
				return false;
		} else if (!listaReuniones.equals(other.listaReuniones))
			return false;
		if (listaReunionesNuevas == null) {
			if (other.listaReunionesNuevas != null)
				return false;
		} else if (!listaReunionesNuevas.equals(other.listaReunionesNuevas))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", Contrasena=" + contrasena + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", dni=" + dni + ", telefono=" + telefono + ", correo=" + correo + ", tipo=" + tipo
				+ ", listaReuniones=" + listaReuniones + ", listaReunionesNuevas=" + listaReunionesNuevas + "]";
	}

	
}
