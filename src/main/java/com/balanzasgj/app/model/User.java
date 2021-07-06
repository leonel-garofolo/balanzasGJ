package com.balanzasgj.app.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "usuarios")
public class User {
	public final static String P_ADMINISTRADOR = "ADMINISTRADOR";
	public final static String P_SUPERVISOR = "SUPERVISOR";
	public final static String P_OPERADOR = "OPERADOR";
	
	
	private static String usuarioLogeado;
	private static String perfilLogeado;
	
	@DatabaseField(id = true)
	private Long id;
	
	@DatabaseField(canBeNull = false, columnName = "id_perfil")
	private Long idPerfil;
	
	@DatabaseField(canBeNull = true)
	private String nombre;
	
	@DatabaseField(canBeNull = false)
	private String clave;
	
	public Long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(long idPerfil) {
		this.idPerfil = idPerfil;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public static String getUsuarioLogeado() {
		return usuarioLogeado;
	}
	public static void setUsuarioLogeado(String usuarioLogeado) {
		User.usuarioLogeado = usuarioLogeado;
	}
	public static String getPerfilLogeado() {
		return perfilLogeado;
	}
	public static void setPerfilLogeado(String perfilLogeado) {
		User.perfilLogeado = perfilLogeado;
	}
}
