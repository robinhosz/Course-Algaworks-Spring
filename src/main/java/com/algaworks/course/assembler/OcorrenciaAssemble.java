package com.algaworks.course.assembler;

import com.algaworks.course.domain.model.Ocorrencia;
import com.algaworks.course.dto.OcorrenciaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OcorrenciaAssemble {

    @Autowired
    private ModelMapper modelMapper;

    public OcorrenciaDTO toModel(Ocorrencia ocorrencia) {
        return modelMapper.map(ocorrencia, OcorrenciaDTO.class);

    }

    public List<OcorrenciaDTO> toCollectionModel(List<Ocorrencia> ocorrencias) {
        return ocorrencias.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
