package br.com.serratec.controller;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.entity.Paciente;
import br.com.serratec.repository.PacienteRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
	
	@Autowired
	private PacienteRepository repository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Paciente inserir(@Valid @RequestBody Paciente p) {
		return repository.save(p);
	}
	
	@PostMapping("/varios")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Paciente> inserirVarios(@RequestBody List<@Valid Paciente> pacientes) {
	    return repository.saveAll(pacientes);
	}
	
	@GetMapping("/{codigo}")
    public ResponseEntity<Paciente> retornaPacientePorId(@PathVariable Long codigo) {
        Optional<Paciente> paciente = repository.findById(codigo);
        if (paciente.isPresent()) {
            return ResponseEntity.ok(paciente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	@GetMapping
	public List<Paciente> Listar(){
		return repository.findAll();
	}
	
	@DeleteMapping("{codigo}")
	public ResponseEntity<Void> apagar(@PathVariable Long codigo){
		if (repository.existsById(codigo)) {
			repository.deleteById(codigo);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{codigo}")
    public ResponseEntity<Paciente> atualizar(@Valid @PathVariable Long codigo, @Valid @RequestBody Paciente pacienteAtualizado) {
        Optional<Paciente> pacienteExistente = repository.findById(codigo);
        if (pacienteExistente.isPresent()) {
            Paciente paciente = pacienteExistente.get();
            paciente.setNome(pacienteAtualizado.getNome());
            paciente.setEmail(pacienteAtualizado.getEmail());
            paciente.setTelefone(pacienteAtualizado.getTelefone());
            
            return ResponseEntity.ok(repository.save(paciente));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
