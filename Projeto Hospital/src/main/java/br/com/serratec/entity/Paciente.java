package br.com.serratec.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Paciente extends Pessoa {

    @ManyToMany
    @JoinTable(
        name = "paciente_consulta",
        joinColumns = @JoinColumn(name = "codigo_paciente"),
        inverseJoinColumns = @JoinColumn(name = "codigo_consulta")
    )
    private List<Consulta> consultas = new ArrayList<>(); // Inicializa a lista

    // Getter para consultas
    public List<Consulta> getConsultas() {
        return consultas;
    }

    // Setter para consultas
    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    // Adiciona uma consulta ao paciente
//    public void addConsulta(Consulta consulta) {
//        if (!consultas.contains(consulta)) { // Evita duplicações
//            consultas.add(consulta);
//            consulta.addPaciente(this); // Atualiza a lista de pacientes da consulta
//        }
//    }
//
//    // Remove uma consulta do paciente
//    public void removeConsulta(Consulta consulta) {
//        if (consultas.remove(consulta)) {
//            consulta.removePaciente(this); // Atualiza a lista de pacientes da consulta
//        }
//    }
}
