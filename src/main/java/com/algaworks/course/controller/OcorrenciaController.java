package com.algaworks.course.controller;

import com.algaworks.course.assembler.OcorrenciaAssemble;
import com.algaworks.course.domain.model.Entrega;
import com.algaworks.course.domain.model.Ocorrencia;
import com.algaworks.course.domain.model.input.OcorrenciaInput;
import com.algaworks.course.dto.OcorrenciaDTO;
import com.algaworks.course.service.BuscaEntregaService;
import com.algaworks.course.service.RegistroOcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

    @Autowired
    private BuscaEntregaService buscaEntregaService;
    @Autowired
    private RegistroOcorrenciaService registroOcorrenciaService;
    @Autowired
    private OcorrenciaAssemble ocorrenciaAssemble;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaDTO registrar(@PathVariable Long entregaId, @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
       Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService.registrar(entregaId, ocorrenciaInput.getDescricao());

       return ocorrenciaAssemble.toModel(ocorrenciaRegistrada);
    }
    @GetMapping
    public List<OcorrenciaDTO> listar(@PathVariable Long entregaId) {
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        return ocorrenciaAssemble.toCollectionModel(entrega.getOcorrencias());
    }

}
