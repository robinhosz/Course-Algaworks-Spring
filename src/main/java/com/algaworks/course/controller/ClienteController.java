package com.algaworks.course.controller;

import com.algaworks.course.domain.model.Cliente;
import com.algaworks.course.repository.ClienteRepository;
import com.algaworks.course.service.CatalagoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/clientes")
@RestController
public class ClienteController {

    @Autowired
    private CatalagoClienteService service;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listar() {

        return clienteRepository.findAll();

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {

        return clienteRepository.findById(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody @Valid Cliente cliente) {

        return service.salvar(cliente);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id,  @RequestBody @Valid Cliente cliente) {
        if(!clienteRepository.existsById(id)) {
           return ResponseEntity.notFound().build();
        }
        cliente.setId(id);
        cliente = service.salvar(cliente);

        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
       if (!clienteRepository.existsById(id)) {
           return ResponseEntity.notFound().build();
       }
       service.excluir(id);

       return ResponseEntity.noContent().build();
    }
}
