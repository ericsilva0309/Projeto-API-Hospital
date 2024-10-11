package br.com.serratec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.entity.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long>{	

}
