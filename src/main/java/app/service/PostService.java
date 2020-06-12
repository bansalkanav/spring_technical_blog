package app.service;

import app.model.Post;
import app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public List<Post> getAllPosts() {
        return repository.getAllPosts();

    }

    public Post getOnePost(){
        return repository.getLatestPost();
    }

    public void createPost(Post newPost){
        newPost.setDate(new Date());
        repository.createPost(newPost);
    }

    public Post getPost(Integer postId){
        return repository.getPost(postId);
    }

     public void updatePost(Post updatedPost){
        updatedPost.setDate(new Date());
        repository.updatePost(updatedPost);
     }

     public void deletePost(Integer postId){
         System.out.println("****** Delete Service ******");
         repository.deletePost(postId);
     }

}
