package br.com.serratec.controller;

import java.util.List;
import java.util.Optional;

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

import br.com.serratec.entity.Medico;
import br.com.serratec.repository.MedicoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
	
	@Autowired
	private MedicoRepository repository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Medico inserir(@Valid @RequestBody Medico m) {
		return repository.save(m);
	}
	
	@PostMapping("/varios")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Medico> inserirVarios(@RequestBody List<@Valid Medico> medicos) {
	    return repository.saveAll(medicos);
	}
	
	@GetMapping("/{codigo}")
    public ResponseEntity<Medico> retornaMedicoPorId(@PathVariable Long codigo) {
        Optional<Medico> medico = repository.findById(codigo);
        if (medico.isPresent()) {
            return ResponseEntity.ok(medico.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	@GetMapping
	public List<Medico> Listar(){
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
    public ResponseEntity<Medico> atualizar(@Valid @PathVariable Long codigo, @Valid @RequestBody Medico medicoAtualizado) {
        Optional<Medico> medicoExistente = repository.findById(codigo);
        if (medicoExistente.isPresent()) {
            Medico medico = medicoExistente.get();
            medico.setNome(medicoAtualizado.getNome());
            medico.setEmail(medicoAtualizado.getEmail());
            medico.setTelefone(medicoAtualizado.getTelefone());
            
            return ResponseEntity.ok(medico);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
