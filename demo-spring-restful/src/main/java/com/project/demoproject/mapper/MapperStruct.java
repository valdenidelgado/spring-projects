package com.project.demoproject.mapper;

import com.project.demoproject.model.Book;
import com.project.demoproject.model.Person;
import com.project.demoproject.model.dto.v1.BookDTO;
import com.project.demoproject.model.dto.v1.PersonDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapperStruct {

    MapperStruct INSTANCE = Mappers.getMapper(MapperStruct.class);

    @Mapping(target = "key", source = "id")
    PersonDTO toPersonDTO(Person person);

    List<PersonDTO> toPersonDTOs(List<Person> persons);

    @Mapping(target = "id", source = "key")
    @InheritInverseConfiguration
    Person toPerson(PersonDTO personDTO);

    @Mapping(target = "key", source = "id")
    BookDTO toBookDTO(Book book);

    List<BookDTO> toBookDTOs(List<Book> books);

    @Mapping(target = "id", source = "key")
    @InheritInverseConfiguration
    Book toBook(BookDTO bookDTO);
}
