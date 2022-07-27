package com.algaworks.course.service;

import com.algaworks.course.domain.model.Cliente;
import com.algaworks.course.domain.model.Entrega;
import com.algaworks.course.domain.model.StatusEntrega;
import com.algaworks.course.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;

@Service
public class SolicitacaoEntregaService {

    @Autowired
    CatalagoClienteService catalagoClienteService;
    @Autowired
    EntregaRepository entregaRepository;

    @Transactional
    public Entrega solicitar(Entrega entrega) {

        Cliente cliente = catalagoClienteService.buscar(entrega.getCliente().getId());

        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());

        return entregaRepository.save(entrega);
    }
}
