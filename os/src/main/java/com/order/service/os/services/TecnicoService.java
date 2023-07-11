package com.order.service.os.services;

import com.order.service.os.domain.Pessoa;
import com.order.service.os.domain.Tecnico;
import com.order.service.os.dtos.TecnicoDTO;
import com.order.service.os.repositories.PessoaRepository;
import com.order.service.os.repositories.TecnicoRepository;
import com.order.service.os.services.exceptions.DataIntegratyViolationException;
import com.order.service.os.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;


    public List<TecnicoDTO> findAll() {
        List<Tecnico> objs = repository.findAll();
        return objs.stream().map(TecnicoDTO::new).collect(Collectors.toList());
    }

    public TecnicoDTO findById(Integer id) {
        Tecnico obj = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Técnico não encontrado! Id: " + id + ", Tipo: " + Tecnico.class.getName()));
        return new TecnicoDTO(obj);
    }

    public TecnicoDTO create(TecnicoDTO obj) {
        if (findByCPF(obj.getCpf()) != null) {
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados! Id: " + obj.getId() + ", Tipo: " + Tecnico.class.getName());
        }
        Tecnico tecnico = repository.save(new Tecnico(null, obj.getNome(), obj.getCpf(), obj.getTelefone()));
        return new TecnicoDTO(tecnico);
    }

    public void delete(Integer id) {
        Tecnico obj = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Técnico não encontrado! Id: " + id + ", Tipo: " + Tecnico.class.getName()));
        if (obj.getList().size() > 0) {
            throw new DataIntegratyViolationException("Técnico possui Ordens de Serviço, não pode ser deletado!");
        }

        repository.deleteById(id);
    }

    public TecnicoDTO update(Integer id, @Valid TecnicoDTO obj) {
        Tecnico oldObj = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Técnico não encontrado! Id: " + id + ", Tipo: " + Tecnico.class.getName()));
        if (findByCPF(obj.getCpf()) != null && findByCPF(obj.getCpf()).getId() != id) {
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados! Id: " + obj.getId() + ", Tipo: " + Tecnico.class.getName());
        }
        oldObj.setNome(obj.getNome());
        oldObj.setCpf(obj.getCpf());
        oldObj.setTelefone(obj.getTelefone());
        Tecnico tecnico = repository.save(oldObj);
        return new TecnicoDTO(tecnico);
    }

    private Pessoa findByCPF(String cpf) {
        Pessoa obj = pessoaRepository.findByCPF(cpf);
        if (obj != null) {
            return obj;
        }
        return null;
    }
}
