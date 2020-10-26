package com.nuvu.api.people.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "personas")
public class People implements Serializable {

	private static final long serialVersionUID = -1690698086927730686L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nombre")
	@NotNull
	private String nombre;

	@Column(name = "apellidos")
	@NotNull
	private String apellidos;

	@Column(name = "edad")
	@NotNull
	private Long edad;

	@Column(name = "telefono")
	@NotNull
	private Long telefono;

	@Column(name = "direccion")
	@NotNull
	private String direccion;

	@Column(name = "email")
	@NotNull
	private String email;

	@Column(name = "banco")
	@NotNull
	private String banco;

	@Column(name = "tipo_cuenta")
	@NotNull
	private String tipoCuenta;

	@Column(name = "num_tarjeta")
	@NotNull
	private Long numTarjeta;

	@Column(name = "fecha_vencimiento")
	@NotNull
	private String fechaVencimiento;

	@Column(name = "cvc")
	@NotNull
	private Long cvc;

	@Column(name = "created_at")
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = "modified_at")
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Long getEdad() {
		return edad;
	}

	public void setEdad(Long edad) {
		this.edad = edad;
	}

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public Long getNumTarjeta() {
		return numTarjeta;
	}

	public void setNumTarjeta(Long numTarjeta) {
		this.numTarjeta = numTarjeta;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Long getCvc() {
		return cvc;
	}

	public void setCvc(Long cvc) {
		this.cvc = cvc;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	@Override
	public String toString() {
		return "People [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", edad=" + edad + ", telefono="
				+ telefono + ", direccion=" + direccion + ", email=" + email + ", banco=" + banco + ", tipoCuenta="
				+ tipoCuenta + ", numTarjeta=" + numTarjeta + ", fechaVencimiento=" + fechaVencimiento + ", cvc=" + cvc
				+ ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt + "]";
	}

	@PrePersist
	public void prePersist() {
		this.createdAt = new Date();
		preUpdate();
	}

	@PreUpdate
	public void preUpdate() {
		this.modifiedAt = new Date();
	}

}
