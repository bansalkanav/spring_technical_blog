package app.repository;

import app.model.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class PostRepository {

    @PersistenceUnit(unitName = "techblog")
    private EntityManagerFactory emf;

    public List<Post> getAllPosts(){
        EntityManager em = emf.createEntityManager();

        TypedQuery<Post> query = em.createQuery("SELECT p FROM Post p", Post.class);
        List<Post> resultList = query.getResultList();

        return resultList;
    }

    public Post getLatestPost(){
        EntityManager em = emf.createEntityManager();
        return em.find(Post.class, 3);
    }

    public Post createPost(Post newPost){
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(newPost);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }

        return newPost;
    }

    public Post getPost(Integer postId){
        EntityManager em = emf.createEntityManager();
        return em.find(Post.class, postId);
    }

    public void updatePost(Post updatedPost){
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(updatedPost);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
    }

    public void deletePost(Integer postId){
        System.out.println("****** Delete Repository ******");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Post post = em.find(Post.class, postId);
            em.remove(post);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
    }
}
