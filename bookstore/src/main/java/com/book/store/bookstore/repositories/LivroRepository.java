package com.book.store.bookstore.repositories;

import com.book.store.bookstore.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Integer> {

    @Query("SELECT obj FROM Livro obj WHERE obj.categoria.id = :idCategoria ORDER BY titulo")
    List<Livro> findAllByCategoria(@Param(value = "idCategoria") Integer idCategoria);
}
