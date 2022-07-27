package com.algaworks.course.controller;

import com.algaworks.course.assembler.EntregaAssembler;
import com.algaworks.course.domain.model.Entrega;
import com.algaworks.course.domain.model.input.EntregaInput;
import com.algaworks.course.dto.EntregaDTO;
import com.algaworks.course.repository.EntregaRepository;
import com.algaworks.course.service.FinalizacaoEntregaService;
import com.algaworks.course.service.SolicitacaoEntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/entregas")
public class EntregaController {

    @Autowired
    private EntregaAssembler entregaAssembler;
    @Autowired
    SolicitacaoEntregaService service;
    @Autowired
    EntregaRepository entregaRepository;

    @Autowired
    private FinalizacaoEntregaService finalizacaoEntregaService;

    @PutMapping(value = "/{entregaId}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long entregaId) {
        finalizacaoEntregaService.finalizar(entregaId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaDTO solicitar(@RequestBody @Valid EntregaInput entregaInput) {
        Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);
        Entrega entregaSolicitada = service.solicitar(novaEntrega);
        return entregaAssembler.toModel(entregaSolicitada);
    }

    @GetMapping
    public List<EntregaDTO> listar() {
        return entregaAssembler.toCollectionModel(entregaRepository.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EntregaDTO> buscarId(@PathVariable Long id) {
        return entregaRepository.findById(id)
                .map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega)))
                .orElse(ResponseEntity.notFound().build());
    }

}
