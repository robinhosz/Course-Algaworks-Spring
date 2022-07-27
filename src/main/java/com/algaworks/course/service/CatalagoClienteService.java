package com.algaworks.course.service;

import com.algaworks.course.domain.model.Cliente;
import com.algaworks.course.domain.model.exception.NegocioException;
import com.algaworks.course.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CatalagoClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar(Cliente cliente) {
       boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
               .stream()
               .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
       if(emailEmUso) {
           throw new NegocioException("Já existe um cliente cadastrado com esse e-mail");
       }
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluir(Long id) {
        clienteRepository.deleteById(id);
    }

    public Cliente buscar(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
    }

}
