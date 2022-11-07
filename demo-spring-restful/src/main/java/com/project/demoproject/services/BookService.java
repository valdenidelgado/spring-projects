package com.project.demoproject.services;

import com.project.demoproject.controllers.BookController;
import com.project.demoproject.exceptions.ResourceNotFoundException;
import com.project.demoproject.mapper.MapperStruct;
import com.project.demoproject.model.Book;
import com.project.demoproject.model.dto.v1.BookDTO;
import com.project.demoproject.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    private final Logger logger = Logger.getLogger(BookService.class.getName());

    public List<BookDTO> findAll() {
        logger.info("Find all Books");
        List<BookDTO> dtos = MapperStruct.INSTANCE.toBookDTOs(repository.findAll());
        dtos.forEach(dto -> dto.add(linkTo(methodOn(BookController.class).findById(dto.getKey())).withSelfRel()));
        return dtos;
    }

    public BookDTO findById(Long id) {
        logger.info("Findind one Book");
        BookDTO dto = MapperStruct.INSTANCE.toBookDTO(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID")));
        dto.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return dto;
    }

    public BookDTO create(BookDTO bookDTO) {
        logger.info("Creating a new Book");
        Book save =  repository.save(MapperStruct.INSTANCE.toBook(bookDTO));
        BookDTO dto = MapperStruct.INSTANCE.toBookDTO(save);
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getKey())).withSelfRel());
        return dto;
    }

    public BookDTO update(BookDTO bookDTO) {
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
