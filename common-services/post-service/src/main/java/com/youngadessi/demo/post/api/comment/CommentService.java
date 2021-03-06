package com.youngadessi.demo.post.api.comment;

import com.youngadessi.demo.post.model.comment.Comment;
import com.youngadessi.demo.post.model.comment.CommentDTO;
import com.youngadessi.demo.post.model.comment.CommentMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    private static final CommentMapper COMMENT_MAPPER = Mappers.getMapper(CommentMapper.class);


    public Boolean update(Long id, CommentDTO commentDTO){

        Comment comment = commentRepository.findById(id).orElseThrow(RuntimeException::new);
        commentRepository.save(COMMENT_MAPPER.dtoToEntity(comment, commentDTO));

        return Boolean.TRUE;
    }

    public void deleteById(Long id){
        commentRepository.deleteById(id);
    }

}
