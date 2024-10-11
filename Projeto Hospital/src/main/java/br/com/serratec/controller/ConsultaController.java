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

import br.com.serratec.entity.Consulta;
import br.com.serratec.entity.Paciente;
import br.com.serratec.repository.ConsultaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    
    @Autowired
    private ConsultaRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Consulta inserir(@Valid @RequestBody Consulta c) {
        return repository.save(c);
    }
    
    @PostMapping("/varios")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Consulta> inserirVarios(@RequestBody List<@Valid Consulta> consultas) {
        return repository.saveAll(consultas);
    }
    
    @GetMapping("/{codigo}")
    public ResponseEntity<Consulta> retornaConsultaPorId(@PathVariable Long codigo) {
        Optional<Consulta> consulta = repository.findById(codigo);
        if (consulta.isPresent()) {
            return ResponseEntity.ok(consulta.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping
    public List<Consulta> listar() {
        return repository.findAll();
    }
    
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> apagar(@PathVariable Long codigo) {
        if (repository.existsById(codigo)) {
            repository.deleteById(codigo);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{codigo}")
    public ResponseEntity<Consulta> atualizar(@Valid @PathVariable Long codigo, @Valid @RequestBody Consulta consultaAtualizado) {
        Optional<Consulta> consultaExistente = repository.findById(codigo);
        if (consultaExistente.isPresent()) {
            Consulta consulta = consultaExistente.get();
            consulta.setDataConsulta(consultaAtualizado.getDataConsulta());
            consulta.setHoraConsulta(consultaAtualizado.getHoraConsulta());
            consulta.setStatus(consultaAtualizado.getStatus());
            // Salva a consulta atualizada no repositório
            repository.save(consulta); // Adicionando esta linha para persistir as alterações

            return ResponseEntity.ok(consulta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
