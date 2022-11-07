package com.project.demoproject.services;

import com.project.demoproject.controllers.PersonController;
import com.project.demoproject.exceptions.ResourceNotFoundException;
import com.project.demoproject.mapper.MapperStruct;
import com.project.demoproject.model.Person;
import com.project.demoproject.model.dto.v1.PersonDTO;
import com.project.demoproject.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    public List<PersonDTO> findAll() {
        logger.info("Find all persons");
        List<PersonDTO> dtos = MapperStruct.INSTANCE.toPersonDTOs(repository.findAll());
        dtos.forEach(dto -> dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel()));
        return dtos;
    }

    public PersonDTO findById(Long id) {
        logger.info("Findind one person");
        PersonDTO dto = MapperStruct.INSTANCE.toPersonDTO(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID")));
        dto.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return dto;
    }

    public PersonDTO create(PersonDTO personDTO) {
        logger.info("Creating a new person");
        Person save =  repository.save(MapperStruct.INSTANCE.toPerson(personDTO));
        PersonDTO dto = MapperStruct.INSTANCE.toPersonDTO(save);
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());
        return dto;
    }

    public PersonDTO update(PersonDTO personDTO) {
        logger.info("Update person and return DTO");
        Person entity = repository.findById(personDTO.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        entity.setFirstName(personDTO.getFirstName());
        entity.setLastName(personDTO.getLastName());
        entity.setAddress(personDTO.getAddress());
        entity.setGender(personDTO.getGender());
        PersonDTO dto = MapperStruct.INSTANCE.toPersonDTO(repository.save(entity));
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());
        return dto;
    }

    public void delete(Long id) {
        logger.info("Delete person");
        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }
}
