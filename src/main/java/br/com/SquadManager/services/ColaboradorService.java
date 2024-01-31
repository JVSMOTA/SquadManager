package br.com.SquadManager.services;

import br.com.SquadManager.controllers.ColaboradorController;
import br.com.SquadManager.dtos.ColaboradorRecordDto;
import br.com.SquadManager.exceptions.ColaboradorNotFoundException;
import br.com.SquadManager.models.ColaboradorModel;
import br.com.SquadManager.repositories.ColaboradorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ColaboradorService {

    @Autowired
    ColaboradorRepository colaboradorRepository;

    public ColaboradorModel createColaborador(ColaboradorRecordDto colaboradorRecordDto) {
        ColaboradorModel colaborador = new ColaboradorModel();
        BeanUtils.copyProperties(colaboradorRecordDto, colaborador);
        colaboradorRepository.save(colaborador);
        return colaborador;
    }

    public List<ColaboradorModel> getAllColaboradores() {
        List<ColaboradorModel> colaboradorList = colaboradorRepository.findAll();
        if(!colaboradorList.isEmpty()){
            for (ColaboradorModel colaborador : colaboradorList) {
                UUID id = colaborador.getIdColaborador();
                colaborador.add(linkTo(methodOn(ColaboradorController.class).getOneColaborador(id)).withSelfRel());
            }
        }
        return colaboradorList;
    }

    public ColaboradorModel getColaboradorById(UUID id) {
        ColaboradorModel colaborador = colaboradorRepository.findById(id).orElseThrow(ColaboradorNotFoundException::new);
        colaborador.add(linkTo(methodOn(ColaboradorController.class).getAllColaboradors()).withSelfRel());
        return colaborador;
    }

    public ColaboradorModel updateColaborador(UUID id, ColaboradorRecordDto colaboradorRecordDto) {
        ColaboradorModel colaborador = colaboradorRepository.findById(id).orElseThrow(ColaboradorNotFoundException::new);
        BeanUtils.copyProperties(colaboradorRecordDto, colaborador);
        colaboradorRepository.save(colaborador);
        return colaborador;
    }

    public void deleteColaborador(UUID id) {
        ColaboradorModel colaborador = colaboradorRepository.findById(id).orElseThrow(ColaboradorNotFoundException::new);
        colaboradorRepository.delete(colaborador);
    }
}
