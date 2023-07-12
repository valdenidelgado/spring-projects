package com.book.store.bookstore.repositories;

import com.book.store.bookstore.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
