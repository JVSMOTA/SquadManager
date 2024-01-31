package br.com.SquadManager.services;

import br.com.SquadManager.dtos.EmpresaRecordDto;
import br.com.SquadManager.models.EmpresaModel;
import br.com.SquadManager.repositories.EmpresaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
