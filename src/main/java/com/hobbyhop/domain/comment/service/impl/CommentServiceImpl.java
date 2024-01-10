package com.hobbyhop.domain.comment.service.impl;

import com.hobbyhop.domain.comment.dto.*;
import com.hobbyhop.domain.comment.entity.Comment;
import com.hobbyhop.domain.comment.repository.CommentRepository;
import com.hobbyhop.domain.comment.service.CommentService;
import com.hobbyhop.domain.post.entity.Post;
import com.hobbyhop.domain.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    public CommentResponseDTO postComment(CommentRequestDTO request, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow();

        Comment comment = Comment.builder()
                .content(request.getContent())
                .post(post)
                //user 넣기
                .build();
        commentRepository.save(comment);

        return CommentResponseDTO.builder()
                .content(comment.getContent())
                //.writer(comment.getUser().getUsername())
                //.like()
                .createdAt(comment.getCreatedAt())
                .build();
    }



    @Override
    @Transactional
    public void patchComment(CommentRequestDTO requestDto, Long commentId) {
        Comment comment = findById(commentId);
        comment.changeContent(requestDto.getContent());
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = findById(commentId);
        commentRepository.delete(comment);
    }

    @Override
    public CommentListResponseDTO getComments(Pageable pageable, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow();
        //List<CommentResponseDTO> commentList = commentRepository.findByPostId(postId).orElseThrow();
        Page<Comment> comments = commentRepository.findAllByPost(pageable, post);
        List<CommentResponseDTO> commentList = new ArrayList<>();
        comments.getContent().forEach((comment) -> {
            commentList.add(CommentResponseDTO.builder()
                .content(comment.getContent())
                //.writer(comment.getUser().getUsername())
                //.like()
                .createdAt(comment.getCreatedAt())
                .build());
        });
        return CommentListResponseDTO.builder()
                .page(pageable.getPageNumber())
                .totalCount(comments.getTotalPages())
                .data(commentList)
                .build();
    }

    private Comment findById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(/*NOT_FOUND_COMMENT_EXCEPTION::new*/);
    }
}