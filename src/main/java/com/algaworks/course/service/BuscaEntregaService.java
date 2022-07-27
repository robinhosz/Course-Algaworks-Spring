package com.algaworks.course.service;

import com.algaworks.course.domain.model.Entrega;
import com.algaworks.course.domain.model.exception.EntidadeNaoEncontradaException;
import com.algaworks.course.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscaEntregaService {
    @Autowired
    private EntregaRepository entregaRepository;

    public Entrega buscar(Long entregaId) {
        return entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
    }
}
