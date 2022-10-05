package com.springmongo.workshopmongo.services;

import com.springmongo.workshopmongo.domain.Post;
import com.springmongo.workshopmongo.domain.User;
import com.springmongo.workshopmongo.dto.UserDTO;
import com.springmongo.workshopmongo.repositories.PostRepository;
import com.springmongo.workshopmongo.repositories.UserRepository;
import com.springmongo.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post findById(String id) {
        Optional<Post> obj = postRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found."));
    }

    public List<Post> findByTitle(String text) {
        return postRepository.findByTitleContainingIgnoreCase(text);
    }
}
