package com.algaworks.course.assembler;

import com.algaworks.course.domain.model.Entrega;
import com.algaworks.course.domain.model.input.EntregaInput;
import com.algaworks.course.dto.EntregaDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EntregaAssembler {

    private ModelMapper modelMapper;

    public EntregaDTO toModel(Entrega entrega) {
        return modelMapper.map(entrega, EntregaDTO.class);
    }

    public List<EntregaDTO> toCollectionModel(List<Entrega> entregas) {
        return entregas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Entrega toEntity(EntregaInput entregaInput) {
        return modelMapper.map(entregaInput, Entrega.class);
    }
}
