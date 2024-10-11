package br.com.serratec.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import br.com.serratec.enums.StatusConsulta;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private LocalDate dataConsulta;
    private LocalTime horaConsulta;

    @Enumerated(EnumType.STRING)
    private StatusConsulta status;

    @ManyToMany
    @JoinTable(
        name = "paciente_consulta",
        joinColumns = @JoinColumn(name = "codigo_consulta"),
        inverseJoinColumns = @JoinColumn(name = "codigo_paciente")
    )
    private List<Paciente> pacientes = new ArrayList<>(); // Inicializa a lista

    // Getters e Setters
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    
    public List<Paciente> getPacientes() {
        return pacientes;
    }


    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public LocalTime getHoraConsulta() {
        return horaConsulta;
    }

    public void setHoraConsulta(LocalTime horaConsulta) {
        this.horaConsulta = horaConsulta;
    }

    public StatusConsulta getStatus() {
        return status;
    }

    public void setStatus(StatusConsulta status) {
        this.status = status;
    }

//    // Adiciona um paciente à consulta
//    public void addPaciente(Paciente paciente) {
//        if (!pacientes.contains(paciente)) { // Evita duplicações
//            pacientes.add(paciente);
//            paciente.addConsulta(this); // Atualiza a lista de consultas do paciente
//        }
//    }
//
//    // Remove um paciente da consulta
//    public void removePaciente(Paciente paciente) {
//        if (pacientes.remove(paciente)) {
//            paciente.removeConsulta(this); // Atualiza a lista de consultas do paciente
//        }
//    }
}
