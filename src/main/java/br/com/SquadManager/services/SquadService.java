package br.com.SquadManager.services;

import br.com.SquadManager.dtos.SquadRecordDto;
import br.com.SquadManager.exceptions.ColaboradorNotFoundException;
import br.com.SquadManager.exceptions.SquadNotFoundException;
import br.com.SquadManager.models.ColaboradorModel;
import br.com.SquadManager.models.SquadModel;
import br.com.SquadManager.repositories.SquadRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class SquadService {

    @Autowired
    SquadRepository squadRepository;

    @Autowired
    ColaboradorService colaboradorService;

    public SquadModel createSquad(SquadRecordDto squadRecordDto) {
        SquadModel squad = new SquadModel();
        BeanUtils.copyProperties(squadRecordDto, squad);
        squadRepository.save(squad);
        return squad;
    }

    public SquadModel adicionarColaboradorAoSquad(UUID idSquad, Long idColaborador) {
        ColaboradorModel colaborador = colaboradorService.getColaboradorById(idColaborador);
        SquadModel squad = getSquadById(idSquad);
        if (squad != null) {
            squad.getColaboradores().add(colaborador);
            colaborador.setSquad(squad);
            squadRepository.save(squad);
            return squad;
        } else {
            throw new SquadNotFoundException();
        }
    }

    public List<SquadModel> getAllSquads() {
        List<SquadModel> squadList = squadRepository.findAll();
        return squadList;
    }

    public SquadModel getSquadById(UUID idSquad) {
        SquadModel squad = squadRepository.findById(idSquad).orElseThrow(SquadNotFoundException::new);
        return squad;
    }

    public List<ColaboradorModel> listarColaboradoresDaSquad(UUID idSquad) {
        SquadModel squad = squadRepository.findById(idSquad).orElseThrow(SquadNotFoundException::new);
        return squad.getColaboradores();
    }

    public SquadModel updateSquad(UUID idSquad, SquadRecordDto squadRecordDto) {
        SquadModel squad = squadRepository.findById(idSquad).orElseThrow(SquadNotFoundException::new);
        BeanUtils.copyProperties(squadRecordDto, squad);
        squadRepository.save(squad);
        return squad;
    }

    public List<ColaboradorModel> removerColaboradorDeSquad(UUID idSquad, Long idColaborador) {
        SquadModel squad = squadRepository.findById(idSquad).orElseThrow(SquadNotFoundException::new);

        Iterator<ColaboradorModel> iterator = squad.getColaboradores().iterator();
        while (iterator.hasNext()) {
            ColaboradorModel colaborador = iterator.next();
            if (colaborador.getIdColaborador().equals(idColaborador)) {
                iterator.remove();
                colaborador.setSquad(null);
                squadRepository.save(squad);
                return squad.getColaboradores();
            }
        }

        throw new ColaboradorNotFoundException();
    }


    public void deleteSquad(UUID idSquad) {
        SquadModel squad = getSquadById(idSquad);
        if (squad != null) {
            for (ColaboradorModel colaborador : squad.getColaboradores()) {
                colaborador.setSquad(null);
            }
            squadRepository.delete(squad);
        } else {
            throw new SquadNotFoundException();
        }
    }
}
