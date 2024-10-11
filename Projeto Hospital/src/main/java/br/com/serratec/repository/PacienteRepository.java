package br.com.serratec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{	

}
