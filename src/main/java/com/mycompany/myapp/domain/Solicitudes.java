package com.mycompany.myapp.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Solicitudes.
 */
@Entity
@Table(name = "solicitudes")
public class Solicitudes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_solicitudes")
    private String id_solicitudes;

    @NotNull
    @Column(name = "nombre_corto", nullable = false)
    private String nombre_corto;

    @NotNull
    @Column(name = "apellido", nullable = false)
    private String apellido;

    @NotNull
    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fecha_Registro;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getId_solicitudes() {
        return id_solicitudes;
    }

    public Solicitudes id_solicitudes(String id_solicitudes) {
        this.id_solicitudes = id_solicitudes;
        return this;
    }

    public void setId_solicitudes(String id_solicitudes) {
        this.id_solicitudes = id_solicitudes;
    }

    public String getNombre_corto() {
        return nombre_corto;
    }

    public Solicitudes nombre_corto(String nombre_corto) {
        this.nombre_corto = nombre_corto;
        return this;
    }

    public void setNombre_corto(String nombre_corto) {
        this.nombre_corto = nombre_corto;
    }

    public String getApellido() {
        return apellido;
    }

    public Solicitudes apellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFecha_Registro() {
        return fecha_Registro;
    }

    public Solicitudes fecha_Registro(LocalDate fecha_Registro) {
        this.fecha_Registro = fecha_Registro;
        return this;
    }

    public void setFecha_Registro(LocalDate fecha_Registro) {
        this.fecha_Registro = fecha_Registro;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Solicitudes solicitudes = (Solicitudes) o;
        if (solicitudes.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), solicitudes.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Solicitudes{" +
            "id=" + getId() +
            ", id_solicitudes='" + getId_solicitudes() + "'" +
            ", nombre_corto='" + getNombre_corto() + "'" +
            ", apellido='" + getApellido() + "'" +
            ", fecha_Registro='" + getFecha_Registro() + "'" +
            "}";
    }
}
