package com.ae5.sige.model;

import java.util.ArrayList;
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
	 * Contraseña.
	 * 
	 * @author ae5
	 */
	@NonNull
	private String Contraseña;
	/**
	 * Nombre.
	 * 
	 * @author ae5
	 */
	private String Nombre;
	/**
	 * Apellidos.
	 * 
	 * @author ae5
	 */
	private String Apellidos;
	/**
	 * DNI.
	 * 
	 * @author ae5
	 */
	@NonNull
	private String DNI;
	/**
	 * Telefono.
	 * 
	 * @author ae5
	 */
	private String Telefono;
	/**
	 * Correo.
	 * 
	 * @author ae5
	 */
	private String Correo;
	/**
	 * Tipo.
	 * 
	 * @author ae5
	 */
	private String Tipo;
	/**
	 * ListaReuniones.
	 * 
	 * @author ae5
	 */
	private ArrayList<Object> ListaReuniones = new ArrayList<Object>();
	/**
	 * ListaReunionesNuevas.
	 * 
	 * @author ae5
	 */
	private ArrayList<Object> ListaReunionesNuevas = new ArrayList<Object>();

	public Usuario( @NonNull String contraseña, String nombre, String apellidos,
			@NonNull String dNI, String telefono, String correo, String tipo, ArrayList<Object> listaReuniones,
			ArrayList<Object> listaReunionesNuevas) {
		super();
		this.id = UUID.randomUUID().toString();
		Contraseña = contraseña;
		Nombre = nombre;
		Apellidos = apellidos;
		DNI = dNI;
		Telefono = telefono;
		Correo = correo;
		Tipo = tipo;
		ListaReuniones = listaReuniones;
		ListaReunionesNuevas = listaReunionesNuevas;
	}

	public Usuario() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getContraseña() {
		return Contraseña;
	}

	public void setContraseña(String contraseña) {
		Contraseña = contraseña;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellidos() {
		return Apellidos;
	}

	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public String getCorreo() {
		return Correo;
	}

	public void setCorreo(String correo) {
		Correo = correo;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public ArrayList<Object> getListaReuniones() {
		return ListaReuniones;
	}

	public void setListaReuniones(ArrayList<Object> listaReuniones) {
		ListaReuniones = listaReuniones;
	}

	public ArrayList<Object> getListaReunionesNuevas() {
		return ListaReunionesNuevas;
	}

	public void setListaReunionesNuevas(ArrayList<Object> listaReunionesNuevas) {
		ListaReunionesNuevas = listaReunionesNuevas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Apellidos == null) ? 0 : Apellidos.hashCode());
		result = prime * result + ((Contraseña == null) ? 0 : Contraseña.hashCode());
		result = prime * result + ((Correo == null) ? 0 : Correo.hashCode());
		result = prime * result + ((DNI == null) ? 0 : DNI.hashCode());
		result = prime * result + ((ListaReuniones == null) ? 0 : ListaReuniones.hashCode());
		result = prime * result + ((ListaReunionesNuevas == null) ? 0 : ListaReunionesNuevas.hashCode());
		result = prime * result + ((Nombre == null) ? 0 : Nombre.hashCode());
		result = prime * result + ((Telefono == null) ? 0 : Telefono.hashCode());
		result = prime * result + ((Tipo == null) ? 0 : Tipo.hashCode());
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
		if (Apellidos == null) {
			if (other.Apellidos != null)
				return false;
		} else if (!Apellidos.equals(other.Apellidos))
			return false;
		if (Contraseña == null) {
			if (other.Contraseña != null)
				return false;
		} else if (!Contraseña.equals(other.Contraseña))
			return false;
		if (Correo == null) {
			if (other.Correo != null)
				return false;
		} else if (!Correo.equals(other.Correo))
			return false;
		if (DNI == null) {
			if (other.DNI != null)
				return false;
		} else if (!DNI.equals(other.DNI))
			return false;
		if (ListaReuniones == null) {
			if (other.ListaReuniones != null)
				return false;
		} else if (!ListaReuniones.equals(other.ListaReuniones))
			return false;
		if (ListaReunionesNuevas == null) {
			if (other.ListaReunionesNuevas != null)
				return false;
		} else if (!ListaReunionesNuevas.equals(other.ListaReunionesNuevas))
			return false;
		if (Nombre == null) {
			if (other.Nombre != null)
				return false;
		} else if (!Nombre.equals(other.Nombre))
			return false;
		if (Telefono == null) {
			if (other.Telefono != null)
				return false;
		} else if (!Telefono.equals(other.Telefono))
			return false;
		if (Tipo == null) {
			if (other.Tipo != null)
				return false;
		} else if (!Tipo.equals(other.Tipo))
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
		return "Usuario [id=" + id + ", Contraseña=" + Contraseña + ", Nombre=" + Nombre + ", Apellidos=" + Apellidos
				+ ", DNI=" + DNI + ", Telefono=" + Telefono + ", Correo=" + Correo + ", Tipo=" + Tipo
				+ ", ListaReuniones=" + ListaReuniones + ", ListaReunionesNuevas=" + ListaReunionesNuevas + "]";
	}

	
}
