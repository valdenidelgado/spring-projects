package com.project.demoproject.services;

import com.project.demoproject.controllers.PersonController;
import com.project.demoproject.exceptions.RequiredObjectIsNullException;
import com.project.demoproject.exceptions.ResourceNotFoundException;
import com.project.demoproject.mapper.MapperStruct;
import com.project.demoproject.model.Person;
import com.project.demoproject.model.dto.v1.PersonDTO;
import com.project.demoproject.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonService {

    private final PersonRepository repository;

    @Autowired
    PagedResourcesAssembler<PersonDTO> assembler;

    private final Logger logger = LoggerFactory.getLogger(PersonService.class);

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public PagedModel<EntityModel<PersonDTO>> findAll(Pageable pageable) {
        logger.info("Find all persons");
        var personPage = repository.findAll(pageable);
        Page<PersonDTO> dtoPage = personPage.map(MapperStruct.INSTANCE::toPersonDTO);
        dtoPage.map(dto -> dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel()));
        Link link = linkTo(methodOn(PersonController.class).findAll(pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel();
        return assembler.toModel(dtoPage, link);
    }

    public PagedModel<EntityModel<PersonDTO>> findPersonsByName(String firstName, Pageable pageable) {
        logger.info("Find all persons");
        var personPage = repository.findPersonsByName(firstName, pageable);
        Page<PersonDTO> dtoPage = personPage.map(MapperStruct.INSTANCE::toPersonDTO);
        dtoPage.map(dto -> dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel()));
        Link link = linkTo(methodOn(PersonController.class).findAll(pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel();
        return assembler.toModel(dtoPage, link);
    }

    public PersonDTO findById(Long id) {
        logger.info("Finding one person");
        PersonDTO dto = MapperStruct.INSTANCE.toPersonDTO(repository.findById(id)
                                                                  .orElseThrow(() -> new ResourceNotFoundException(
                                                                          "No records found for this ID")));
        dto.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return dto;
    }

    public PersonDTO create(PersonDTO personDTO) {
        if (personDTO == null) throw new RequiredObjectIsNullException();
        logger.info("Creating a new person");
        Person save = repository.save(MapperStruct.INSTANCE.toPerson(personDTO));
        PersonDTO dto = MapperStruct.INSTANCE.toPersonDTO(save);
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());
        return dto;
    }

    public PersonDTO update(PersonDTO personDTO) {
        if (personDTO == null) throw new RequiredObjectIsNullException();
        logger.info("Update person and return DTO");
        Person entity = repository.findById(personDTO.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
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
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }

    @Transactional
    public PersonDTO disablePerson(Long id) {
        logger.info("Disabling one person");
        repository.disablePerson(id);
        PersonDTO dto = MapperStruct.INSTANCE.toPersonDTO(repository.findById(id)
                                                                  .orElseThrow(() -> new ResourceNotFoundException(
                                                                          "No records found for this ID")));
        dto.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return dto;
    }
}
