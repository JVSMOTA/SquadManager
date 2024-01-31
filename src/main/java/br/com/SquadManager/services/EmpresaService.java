package br.com.SquadManager.services;

import br.com.SquadManager.controllers.EmpresaController;
import br.com.SquadManager.dtos.EmpresaRecordDto;
import br.com.SquadManager.exceptions.EmpresaNotFoundException;
import br.com.SquadManager.models.EmpresaModel;
import br.com.SquadManager.repositories.EmpresaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class EmpresaService {

    @Autowired
    EmpresaRepository empresaRepository;

    public EmpresaModel createEmpresa(EmpresaRecordDto empresaRecordDto) {
        EmpresaModel empresa = new EmpresaModel();
        BeanUtils.copyProperties(empresaRecordDto, empresa);
        empresaRepository.save(empresa);
        return empresa;
    }

    public List<EmpresaModel> getAllEmpresas() {
        List<EmpresaModel> empresaList = empresaRepository.findAll();
        if(!empresaList.isEmpty()){
            for (EmpresaModel empresa : empresaList) {
                UUID id = empresa.getIdEmpresa();
                empresa.add(linkTo(methodOn(EmpresaController.class).getOneEmpresa(id)).withSelfRel());
            }
        }
        return empresaList;
    }

    public EmpresaModel getEmpresaById(UUID id) {
        EmpresaModel empresa = empresaRepository.findById(id).orElseThrow(EmpresaNotFoundException::new);
        empresa.add(linkTo(methodOn(EmpresaController.class).getAllEmpresas()).withSelfRel());
        return empresa;
    }

    public EmpresaModel updateEmpresa(UUID id, EmpresaRecordDto empresaRecordDto) {
        EmpresaModel empresa = empresaRepository.findById(id).orElseThrow(EmpresaNotFoundException::new);
        BeanUtils.copyProperties(empresaRecordDto, empresa);
        empresaRepository.save(empresa);
        return empresa;
    }

    public void deleteEmpresa(UUID id) {
        EmpresaModel empresa = empresaRepository.findById(id).orElseThrow(EmpresaNotFoundException::new);
        empresaRepository.delete(empresa);
    }

}
