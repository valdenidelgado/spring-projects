package com.order.service.os.services;

import com.order.service.os.domain.Tecnico;
import com.order.service.os.dtos.TecnicoDTO;
import com.order.service.os.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;


    public List<TecnicoDTO> findAll() {
        return repository.findAll();
    }

    public TecnicoDTO findById(Integer id) {
        Tecnico obj = repository.findById(id).orElseThrow(() -> new RuntimeException("Técnico não encontrado"));
        return new TecnicoDTO(obj);
    }

    public TecnicoDTO create(TecnicoDTO obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

}
