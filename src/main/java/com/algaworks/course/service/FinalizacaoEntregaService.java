package com.algaworks.course.service;

import com.algaworks.course.domain.model.Entrega;
import com.algaworks.course.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FinalizacaoEntregaService {

    @Autowired
    private EntregaRepository entregaRepository;
    @Autowired
    private BuscaEntregaService buscaEntregaService;

    @Transactional
    public void finalizar(Long entregaId) {
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        entrega.finalizar();

        entregaRepository.save(entrega);
    }
}
