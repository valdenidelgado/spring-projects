package com.order.service.os.services;

import com.order.service.os.domain.Cliente;
import com.order.service.os.domain.OS;
import com.order.service.os.domain.Tecnico;
import com.order.service.os.domain.enums.Prioridade;
import com.order.service.os.domain.enums.Status;
import com.order.service.os.dtos.OSDTO;
import com.order.service.os.dtos.TecnicoDTO;
import com.order.service.os.repositories.ClienteRepository;
import com.order.service.os.repositories.OSRepository;
import com.order.service.os.repositories.TecnicoRepository;
import com.order.service.os.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OSService {

    @Autowired
    private OSRepository repository;

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;


    public OSDTO findById(Integer id) {
        OS obj = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("OS não encontrada ! Id: " + id + ", Tipo: " + OS.class.getName()));
        return new OSDTO(obj);
    }

    public List<OSDTO> findAll() {
        List<OS> objs = repository.findAll();
        return objs.stream().map(OSDTO::new).collect(Collectors.toList());
    }

    public OSDTO create(@Valid OSDTO obj) {
        return new OSDTO(fromDTO(obj));
    }

    public OSDTO update(@Valid OSDTO obj) {
        repository.findById(obj.getId())
                .orElseThrow(() -> new ObjectNotFoundException(
                        "OS não encontrada ! Id: " + obj.getId() + ", Tipo: " + OS.class.getName()));
        return new OSDTO(fromDTO(obj));
    }

    private OS fromDTO(OSDTO objDTO) {
        OS newObj = new OS();
        newObj.setId(objDTO.getId());
        newObj.setObservacoes(objDTO.getObservacoes());
        newObj.setPrioridade(Prioridade.toEnum(objDTO.getPrioridade()));
        newObj.setStatus(Status.toEnum(objDTO.getStatus()));

        Tecnico tec = tecnicoRepository.findById(objDTO.getTecnico()).orElseThrow(() -> new ObjectNotFoundException("Técnico não encontrado ! Id: " + objDTO.getTecnico() + ", Tipo: " + Tecnico.class.getName()));
        Cliente cliente = clienteRepository.findById(objDTO.getCliente()).orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado ! Id: " + objDTO.getCliente() + ", Tipo: " + Cliente.class.getName()));

        newObj.setTecnico(tec);
        newObj.setCliente(cliente);

        if (newObj.getStatus().getCod().equals(2)) {
            newObj.setDataFechamento(LocalDateTime.now());
        }

        return repository.save(newObj);
    }

}
