package com.order.service.os.services;

import com.order.service.os.domain.Cliente;
import com.order.service.os.domain.Pessoa;
import com.order.service.os.dtos.ClienteDTO;
import com.order.service.os.repositories.ClienteRepository;
import com.order.service.os.repositories.PessoaRepository;
import com.order.service.os.services.exceptions.DataIntegratyViolationException;
import com.order.service.os.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;


    public List<ClienteDTO> findAll() {
        List<Cliente> objs = repository.findAll();
        return objs.stream().map(ClienteDTO::new).collect(Collectors.toList());
    }

    public ClienteDTO findById(Integer id) {
        Cliente obj = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
        return new ClienteDTO(obj);
    }

    public ClienteDTO create(ClienteDTO obj) {
        if (findByCPF(obj.getCpf()) != null) {
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados! Id: " + obj.getId() + ", Tipo: " + Cliente.class.getName());
        }
        Cliente cliente = repository.save(new Cliente(null, obj.getNome(), obj.getCpf(), obj.getTelefone()));
        return new ClienteDTO(cliente);
    }

    public void delete(Integer id) {
        Cliente obj = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
        if (obj.getList().size() > 0) {
            throw new DataIntegratyViolationException("Cliente possui Ordens de Serviço, não pode ser deletado!");
        }

        repository.deleteById(id);
    }

    public ClienteDTO update(Integer id, @Valid ClienteDTO obj) {
        Cliente oldObj = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
        if (findByCPF(obj.getCpf()) != null && findByCPF(obj.getCpf()).getId() != id) {
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados! Id: " + obj.getId() + ", Tipo: " + Cliente.class.getName());
        }
        oldObj.setNome(obj.getNome());
        oldObj.setCpf(obj.getCpf());
        oldObj.setTelefone(obj.getTelefone());
        Cliente cliente = repository.save(oldObj);
        return new ClienteDTO(cliente);
    }

    private Pessoa findByCPF(String cpf) {
        Pessoa obj = pessoaRepository.findByCPF(cpf);
        if (obj != null) {
            return obj;
        }
        return null;
    }
}
