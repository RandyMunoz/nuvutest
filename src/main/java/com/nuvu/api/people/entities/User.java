package com.nuvu.api.people.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "usuarios")
public class User implements Serializable {

	private static final long serialVersionUID = 308122459227137252L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "username")
	@NotNull
	private String username;

	@Column(name = "password")
	@NotNull
	private String password;

	@ManyToOne
	// @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name =
	// "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
	@JoinColumn(name = "rol_id", referencedColumnName = "id")
	@NotNull
	private Rol rol;

	public User(@NotNull String username, @NotNull String password, @NotNull Rol rol) {
		this.username = username;
		this.password = password;
		this.rol = rol;
	}

	public User() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}
