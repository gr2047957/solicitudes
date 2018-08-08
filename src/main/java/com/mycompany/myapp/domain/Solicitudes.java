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
    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fecha_Registro;

    @NotNull
    @Column(name = "solicitante", nullable = false)
    private String solicitante;

    @NotNull
    @Column(name = "tipo_solicitud", nullable = false)
    private String tipoSolicitud;

    @NotNull
    @Column(name = "persona_a_sustituir", nullable = false)
    private String personaASustituir;

    @NotNull
    @Column(name = "direccion", nullable = false)
    private String direccion;

    @NotNull
    @Column(name = "unidad_negocio", nullable = false)
    private String unidadNegocio;

    @NotNull
    @Column(name = "ceco", nullable = false)
    private String ceco;

    @NotNull
    @Column(name = "reporta_a", nullable = false)
    private String reportaA;

    @NotNull
    @Column(name = "servicio", nullable = false)
    private String servicio;

    @NotNull
    @Column(name = "periodos_asignacion", nullable = false)
    private String periodosAsignacion;

    @NotNull
    @Column(name = "nombre_candidato_propuesto", nullable = false)
    private String nombreCandidatoPropuesto;

    @NotNull
    @Column(name = "apellidos_candidato_propuesto", nullable = false)
    private String apellidosCandidatoPropuesto;

    @NotNull
    @Column(name = "proveedor_candidato_propuesto", nullable = false)
    private String proveedorCandidatoPropuesto;

    @NotNull
    @Column(name = "disponibilidad_viajar", nullable = false)
    private Boolean disponibilidadViajar;

    @NotNull
    @Column(name = "experiencia_sector_bancario", nullable = false)
    private Boolean experienciaSectorBancario;

    @NotNull
    @Column(name = "nivel_bancario", nullable = false)
    private String nivelBancario;

    @NotNull
    @Column(name = "prioridad", nullable = false)
    private Integer prioridad;

    @NotNull
    @Column(name = "tipo_recurso", nullable = false)
    private String tipoRecurso;

    @NotNull
    @Column(name = "experiencia_bancaria", nullable = false)
    private String experienciaBancaria;

    @NotNull
    @Column(name = "skill_tecnico", nullable = false)
    private String skillTecnico;

    @NotNull
    @Column(name = "obligatorio", nullable = false)
    private String obligatorio;

    @NotNull
    @Column(name = "experiencia", nullable = false)
    private String experiencia;

    @NotNull
    @Column(name = "soft_skill", nullable = false)
    private String softSkill;

    @NotNull
    @Column(name = "nivel_soft_skill", nullable = false)
    private String nivelSoftSkill;

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

    public String getSolicitante() {
        return solicitante;
    }

    public Solicitudes solicitante(String solicitante) {
        this.solicitante = solicitante;
        return this;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getTipoSolicitud() {
        return tipoSolicitud;
    }

    public Solicitudes tipoSolicitud(String tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
        return this;
    }

    public void setTipoSolicitud(String tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public String getPersonaASustituir() {
        return personaASustituir;
    }

    public Solicitudes personaASustituir(String personaASustituir) {
        this.personaASustituir = personaASustituir;
        return this;
    }

    public void setPersonaASustituir(String personaASustituir) {
        this.personaASustituir = personaASustituir;
    }

    public String getDireccion() {
        return direccion;
    }

    public Solicitudes direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUnidadNegocio() {
        return unidadNegocio;
    }

    public Solicitudes unidadNegocio(String unidadNegocio) {
        this.unidadNegocio = unidadNegocio;
        return this;
    }

    public void setUnidadNegocio(String unidadNegocio) {
        this.unidadNegocio = unidadNegocio;
    }

    public String getCeco() {
        return ceco;
    }

    public Solicitudes ceco(String ceco) {
        this.ceco = ceco;
        return this;
    }

    public void setCeco(String ceco) {
        this.ceco = ceco;
    }

    public String getReportaA() {
        return reportaA;
    }

    public Solicitudes reportaA(String reportaA) {
        this.reportaA = reportaA;
        return this;
    }

    public void setReportaA(String reportaA) {
        this.reportaA = reportaA;
    }

    public String getServicio() {
        return servicio;
    }

    public Solicitudes servicio(String servicio) {
        this.servicio = servicio;
        return this;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getPeriodosAsignacion() {
        return periodosAsignacion;
    }

    public Solicitudes periodosAsignacion(String periodosAsignacion) {
        this.periodosAsignacion = periodosAsignacion;
        return this;
    }

    public void setPeriodosAsignacion(String periodosAsignacion) {
        this.periodosAsignacion = periodosAsignacion;
    }

    public String getNombreCandidatoPropuesto() {
        return nombreCandidatoPropuesto;
    }

    public Solicitudes nombreCandidatoPropuesto(String nombreCandidatoPropuesto) {
        this.nombreCandidatoPropuesto = nombreCandidatoPropuesto;
        return this;
    }

    public void setNombreCandidatoPropuesto(String nombreCandidatoPropuesto) {
        this.nombreCandidatoPropuesto = nombreCandidatoPropuesto;
    }

    public String getApellidosCandidatoPropuesto() {
        return apellidosCandidatoPropuesto;
    }

    public Solicitudes apellidosCandidatoPropuesto(String apellidosCandidatoPropuesto) {
        this.apellidosCandidatoPropuesto = apellidosCandidatoPropuesto;
        return this;
    }

    public void setApellidosCandidatoPropuesto(String apellidosCandidatoPropuesto) {
        this.apellidosCandidatoPropuesto = apellidosCandidatoPropuesto;
    }

    public String getProveedorCandidatoPropuesto() {
        return proveedorCandidatoPropuesto;
    }

    public Solicitudes proveedorCandidatoPropuesto(String proveedorCandidatoPropuesto) {
        this.proveedorCandidatoPropuesto = proveedorCandidatoPropuesto;
        return this;
    }

    public void setProveedorCandidatoPropuesto(String proveedorCandidatoPropuesto) {
        this.proveedorCandidatoPropuesto = proveedorCandidatoPropuesto;
    }

    public Boolean isDisponibilidadViajar() {
        return disponibilidadViajar;
    }

    public Solicitudes disponibilidadViajar(Boolean disponibilidadViajar) {
        this.disponibilidadViajar = disponibilidadViajar;
        return this;
    }

    public void setDisponibilidadViajar(Boolean disponibilidadViajar) {
        this.disponibilidadViajar = disponibilidadViajar;
    }

    public Boolean isExperienciaSectorBancario() {
        return experienciaSectorBancario;
    }

    public Solicitudes experienciaSectorBancario(Boolean experienciaSectorBancario) {
        this.experienciaSectorBancario = experienciaSectorBancario;
        return this;
    }

    public void setExperienciaSectorBancario(Boolean experienciaSectorBancario) {
        this.experienciaSectorBancario = experienciaSectorBancario;
    }

    public String getNivelBancario() {
        return nivelBancario;
    }

    public Solicitudes nivelBancario(String nivelBancario) {
        this.nivelBancario = nivelBancario;
        return this;
    }

    public void setNivelBancario(String nivelBancario) {
        this.nivelBancario = nivelBancario;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public Solicitudes prioridad(Integer prioridad) {
        this.prioridad = prioridad;
        return this;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public Solicitudes tipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
        return this;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public String getExperienciaBancaria() {
        return experienciaBancaria;
    }

    public Solicitudes experienciaBancaria(String experienciaBancaria) {
        this.experienciaBancaria = experienciaBancaria;
        return this;
    }

    public void setExperienciaBancaria(String experienciaBancaria) {
        this.experienciaBancaria = experienciaBancaria;
    }

    public String getSkillTecnico() {
        return skillTecnico;
    }

    public Solicitudes skillTecnico(String skillTecnico) {
        this.skillTecnico = skillTecnico;
        return this;
    }

    public void setSkillTecnico(String skillTecnico) {
        this.skillTecnico = skillTecnico;
    }

    public String getObligatorio() {
        return obligatorio;
    }

    public Solicitudes obligatorio(String obligatorio) {
        this.obligatorio = obligatorio;
        return this;
    }

    public void setObligatorio(String obligatorio) {
        this.obligatorio = obligatorio;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public Solicitudes experiencia(String experiencia) {
        this.experiencia = experiencia;
        return this;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getSoftSkill() {
        return softSkill;
    }

    public Solicitudes softSkill(String softSkill) {
        this.softSkill = softSkill;
        return this;
    }

    public void setSoftSkill(String softSkill) {
        this.softSkill = softSkill;
    }

    public String getNivelSoftSkill() {
        return nivelSoftSkill;
    }

    public Solicitudes nivelSoftSkill(String nivelSoftSkill) {
        this.nivelSoftSkill = nivelSoftSkill;
        return this;
    }

    public void setNivelSoftSkill(String nivelSoftSkill) {
        this.nivelSoftSkill = nivelSoftSkill;
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
            ", fecha_Registro='" + getFecha_Registro() + "'" +
            ", solicitante='" + getSolicitante() + "'" +
            ", tipoSolicitud='" + getTipoSolicitud() + "'" +
            ", personaASustituir='" + getPersonaASustituir() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", unidadNegocio='" + getUnidadNegocio() + "'" +
            ", ceco='" + getCeco() + "'" +
            ", reportaA='" + getReportaA() + "'" +
            ", servicio='" + getServicio() + "'" +
            ", periodosAsignacion='" + getPeriodosAsignacion() + "'" +
            ", nombreCandidatoPropuesto='" + getNombreCandidatoPropuesto() + "'" +
            ", apellidosCandidatoPropuesto='" + getApellidosCandidatoPropuesto() + "'" +
            ", proveedorCandidatoPropuesto='" + getProveedorCandidatoPropuesto() + "'" +
            ", disponibilidadViajar='" + isDisponibilidadViajar() + "'" +
            ", experienciaSectorBancario='" + isExperienciaSectorBancario() + "'" +
            ", nivelBancario='" + getNivelBancario() + "'" +
            ", prioridad=" + getPrioridad() +
            ", tipoRecurso='" + getTipoRecurso() + "'" +
            ", experienciaBancaria='" + getExperienciaBancaria() + "'" +
            ", skillTecnico='" + getSkillTecnico() + "'" +
            ", obligatorio='" + getObligatorio() + "'" +
            ", experiencia='" + getExperiencia() + "'" +
            ", softSkill='" + getSoftSkill() + "'" +
            ", nivelSoftSkill='" + getNivelSoftSkill() + "'" +
            "}";
    }
}
