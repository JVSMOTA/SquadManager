package br.com.SquadManager.services;

import br.com.SquadManager.dtos.ColaboradorRecordDto;
import br.com.SquadManager.models.ColaboradorModel;
import br.com.SquadManager.repositories.ColaboradorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
