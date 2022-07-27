package com.algaworks.course.repository;

import com.algaworks.course.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

     List<Cliente> findDistinctByNome(String name);

     Optional<Cliente> findByEmail(String email);

}
