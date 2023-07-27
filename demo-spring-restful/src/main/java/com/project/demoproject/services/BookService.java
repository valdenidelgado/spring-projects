package com.project.demoproject.services;

import com.project.demoproject.controllers.BookController;
import com.project.demoproject.exceptions.RequiredObjectIsNullException;
import com.project.demoproject.exceptions.ResourceNotFoundException;
import com.project.demoproject.mapper.MapperStruct;
import com.project.demoproject.model.Book;
import com.project.demoproject.model.dto.v1.BookDTO;
import com.project.demoproject.model.dto.v1.PersonDTO;
import com.project.demoproject.repositories.BookRepository;
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

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {

    private final BookRepository repository;

    @Autowired
    PagedResourcesAssembler<BookDTO> assembler;

    private final Logger logger = LoggerFactory.getLogger(BookService.class);

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public PagedModel<EntityModel<BookDTO>> findAll(Pageable pageable) {
        logger.info("Find all Books");
        var bookPage = repository.findAll(pageable);
        Page<BookDTO> dtoPage = bookPage.map(MapperStruct.INSTANCE::toBookDTO);
        dtoPage.map(dto -> dto.add(linkTo(methodOn(BookController.class).findById(dto.getKey())).withSelfRel()));
        Link link = linkTo(methodOn(BookController.class).findAll(pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel();
        return assembler.toModel(dtoPage, link);
    }

    public BookDTO findById(Long id) {
        logger.info("Finding one Book");
        BookDTO dto = MapperStruct.INSTANCE.toBookDTO(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID")));
        dto.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return dto;
    }

    public BookDTO create(BookDTO bookDTO) {
        if (bookDTO == null) throw new RequiredObjectIsNullException();
        logger.info("Creating a new Book");
        Book save =  repository.save(MapperStruct.INSTANCE.toBook(bookDTO));
        BookDTO dto = MapperStruct.INSTANCE.toBookDTO(save);
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getKey())).withSelfRel());
        return dto;
    }

    public BookDTO update(BookDTO bookDTO) {
        if (bookDTO == null) throw new RequiredObjectIsNullException();
        logger.info("Update Book and return DTO");
        Book entity = repository.findById(bookDTO.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        entity.setAuthor(bookDTO.getAuthor());
        entity.setLaunchDate(bookDTO.getLaunchDate());
        entity.setPrice(bookDTO.getPrice());
        entity.setTitle(bookDTO.getTitle());
        BookDTO dto = MapperStruct.INSTANCE.toBookDTO(repository.save(entity));
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getKey())).withSelfRel());
        return dto;
    }

    public void delete(Long id) {
        logger.info("Delete Book");
        Book entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }
}
