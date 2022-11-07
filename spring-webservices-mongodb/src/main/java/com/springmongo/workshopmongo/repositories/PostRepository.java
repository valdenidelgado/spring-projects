package com.springmongo.workshopmongo.repositories;

import com.springmongo.workshopmongo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    @Query("{'title': { $regex: ?0, $option: 'i'} }")
    List<Post> searchTitle(String text);
    List<Post> findByTitleContainingIgnoreCase(String text);

    @Query("{ $and: [ {date: {$gte: ?1}}, {date: {$lte: ?2}}, { $or: [ {'title': { $regex: ?0, $option: 'i'} }, {'body': { $regex: ?0, $option: 'i'} } , {'comments.text': { $regex: ?0, $option: 'i'} }] } ] }")
    List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
