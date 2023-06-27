package com.course.dscatalog.repositories;

import com.course.dscatalog.dto.ProductDTO;
import com.course.dscatalog.entities.Product;
import com.course.dscatalog.factories.Factory;
import com.course.dscatalog.services.ProductService;
import com.course.dscatalog.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@Transactional
public class ProductRepositoryTests {

    @InjectMocks
    private ProductService service;

    @Mock
    private ProductRepository repository;

    private long existingId;
    private long nonExistingId;
    private long countTotalProducts;
    private long dependentId;
    private PageImpl<Product> page;
    private Product product;

    @BeforeEach
    void setUp() throws Exception {
        // executa antes de cada teste
        existingId = 1L;
        nonExistingId = 1000L;
        countTotalProducts = 1L;
        dependentId = 4L;
        product = Factory.createProduct();
        page = new PageImpl<>(List.of(product));


        // configura o comportamento simulado do repositório
        Mockito.when(repository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(page);
        Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(product);
        Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(product));
        Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());
        Mockito.doThrow(ResourceNotFoundException.class).when(repository).getReferenceById(nonExistingId);


        Mockito.doNothing().when(repository).deleteById(existingId);
        Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);
        Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);
    }

    @Test
    public void findAllPagedShouldReturnPage() {
//        Pageable pageable = Pageable.ofSize(10);
//        PageImpl<Product> result = (PageImpl<Product>) repository.findAll(pageable);
//        Assertions.assertNotNull(result);

        Pageable pageable = PageRequest.of(0, 10);
        Page<ProductDTO> result = service.findAllPaged(pageable);
        Assertions.assertNotNull(result);

        Mockito.verify(repository, Mockito.times(1)).findAll(pageable);
    }

    @Test
    public void findByIdShouldReturnNonEmptyOptionalWhenIdExists() {
        Optional<Product> result = repository.findById(existingId);
        // verifica se está presente
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    public void findByIdShouldReturnEmptyOptionalWhenIdDoesNotExists() {
        Optional<Product> result = repository.findById(nonExistingId);
        // verifica se está vazio
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
        Product product = Factory.createProduct();
        product.setId(null);
        product = repository.save(product);
        Assertions.assertNotNull(product.getId());
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {
        service.delete(existingId);

        ProductDTO result = service.findById(existingId);
        // verifica se está presente
        Assertions.assertNotNull(result);
    }

//    @Test
//    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {
//        Assertions.assertThrows(org.springframework.dao.EmptyResultDataAccessException.class, () -> {
//            repository.deleteById(nonExistingId);
//        });
//    }
}
