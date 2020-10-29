package com.ae5.sige.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "Reuniones")
public class Reunion {
	  /**
	   * ID.
	   * 
	   * @author ae5
	   */
	  @Id
	  private String id;
	  
	  /**
	   * Titulo.
	   * 
	   * @author ae5
	   */
	 
	  private String titulo;
	  
	  /**
	   * Descripcion.
	   * 
	   * @author ae5
	   */
	 
	  private String descripcion;
	  /**
	   * Organizador.
	   * 
	   * @author ae5
	   */
	 
	  private String organizador;
	  /**
	   * Fecha.
	   * 
	   * @author ae5
	   */
	 
	  private String fecha;
	  /**
	   * Hora inicial.
	   * 
	   * @author ae5
	   */
	 
	  private String horaIni;
	  /**
	   * Hora inicial.
	   * 
	   * @author ae5
	   */
	 
	  private String horaFin;
	  /**
	   * ListaAsistentes.
	   * 
	   * @author ae5
	   */
	 
	  private ArrayList<String> listaAsistentes = new ArrayList<>();
	public Reunion(String id, String titulo, String descripcion, String organizador, String fecha, String horaIni,
			String horaFin, ArrayList<String> listaAsistentes) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.organizador = organizador;
		this.fecha = fecha;
		this.horaIni = horaIni;
		this.horaFin = horaFin;
		this.listaAsistentes = listaAsistentes;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getOrganizador() {
		return organizador;
	}
	public void setOrganizador(String organizador) {
		this.organizador = organizador;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHoraIni() {
		return horaIni;
	}
	public void setHoraIni(String horaIni) {
		this.horaIni = horaIni;
	}
	public String getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	public ArrayList<String> getListaAsistentes() {
		return listaAsistentes;
	}
	public void setListaAsistentes(ArrayList<String> listaAsistentes) {
		this.listaAsistentes = listaAsistentes;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((horaFin == null) ? 0 : horaFin.hashCode());
		result = prime * result + ((horaIni == null) ? 0 : horaIni.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((listaAsistentes == null) ? 0 : listaAsistentes.hashCode());
		result = prime * result + ((organizador == null) ? 0 : organizador.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		Reunion other = (Reunion) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (horaFin == null) {
			if (other.horaFin != null)
				return false;
		} else if (!horaFin.equals(other.horaFin))
			return false;
		if (horaIni == null) {
			if (other.horaIni != null)
				return false;
		} else if (!horaIni.equals(other.horaIni))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (listaAsistentes == null) {
			if (other.listaAsistentes != null)
				return false;
		} else if (!listaAsistentes.equals(other.listaAsistentes))
			return false;
		if (organizador == null) {
			if (other.organizador != null)
				return false;
		} else if (!organizador.equals(other.organizador))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Reunion [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", organizador="
				+ organizador + ", fecha=" + fecha + ", horaIni=" + horaIni + ", horaFin=" + horaFin
				+ ", listaAsistentes=" + listaAsistentes + "]";
	}
	

	  
}
