package com.book.store.bookstore.services;

import com.book.store.bookstore.domain.Categoria;
import com.book.store.bookstore.domain.Livro;
import com.book.store.bookstore.dto.LivroDTO;
import com.book.store.bookstore.repositories.LivroRepository;
import com.book.store.bookstore.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaService categoriaService;

    public Livro findById(Integer id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        "Objeto não encontrado! Id: " + id + ", Tipo: " + Livro.class.getName()));
    }

    public List<LivroDTO> findAll(Integer idCategoria) {
        categoriaService.findById(idCategoria);
        List<Livro> livros = livroRepository.findAllByCategoria(idCategoria);
        return livros.stream().map(LivroDTO::new).collect(Collectors.toList());
    }

    public Livro create(Integer idCategoria, Livro livro) {
        livro.setId(null);
        Categoria categoria = categoriaService.findById(idCategoria);
        livro.setCategoria(categoria);
        return livroRepository.save(livro);
    }

    public Livro update(Integer id, Livro livro) {
        Livro obj = findById(id);
        updateData(obj, livro);
        return livroRepository.save(obj);
    }

    public void delete(Integer id) {
        findById(id);
        livroRepository.deleteById(id);

    }

    private void updateData(Livro obj, Livro livro) {
        obj.setTitulo(livro.getTitulo());
        obj.setNomeAutor(livro.getNomeAutor());
        obj.setTexto(livro.getTexto());
    }
}
