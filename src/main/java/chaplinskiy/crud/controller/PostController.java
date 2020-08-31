package chaplinskiy.crud.controller;

import chaplinskiy.crud.model.Post;
import chaplinskiy.crud.repository.PostRepository;
import chaplinskiy.crud.repository.io.PostRepositoryImpl;

import java.util.List;

public class PostController {
    PostRepository postRepository = new PostRepositoryImpl();

    public PostController(){
    }

    public Post getById(Long id){
        return postRepository.getById(id);
    }

    public List<Post> getAll() {
        return postRepository.getAll();
    }

    public Post create(Post post) {
        return postRepository.create(post);
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    public Post update(Post post) {
        return postRepository.update(post);
    }


}
