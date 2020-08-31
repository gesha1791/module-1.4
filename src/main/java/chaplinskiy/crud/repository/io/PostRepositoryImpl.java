package chaplinskiy.crud.repository.io;

import chaplinskiy.crud.model.Post;
import chaplinskiy.crud.model.Region;
import chaplinskiy.crud.repository.PostRepository;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import static java.nio.file.Files.exists;
import static java.nio.file.Files.readAllLines;

public class PostRepositoryImpl implements PostRepository {

    private final Gson mapper = new Gson();

    @Override
    public Post create(Post post) {
        Long id = generateId();

        post.setId(id);

        try (FileWriter fileWriter = new FileWriter("post.json", true)) {
            String postJsonGormat = mapper.toJson(post);

            fileWriter.write(postJsonGormat);
            fileWriter.write("\n");
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return post;
    }

    private Long generateId() {
        boolean exists = exists(Paths.get("post.json"));
        Long id = null;
        List<Post> posts = new ArrayList<>();
        if (exists){
            try {
                List<String> strings = readAllLines(Paths.get("post.json"));

                strings.stream().forEach(s -> {
                    posts.add(mapper.fromJson(s, Post.class));
                });
                if (posts.size() == 0) {
                    id = 1L;
                } else {
                    Long idMax = posts.stream().max(Comparator.comparing(Post::getId))
                            .orElseThrow(NoSuchElementException::new).getId();
                    id = idMax + 1;
                }

            }catch (IOException e){
                System.out.println(e);
            }
        } else {
            id = 1L;
        }
        return id;
    }

    @Override
    public Post update(Post post) {
        List<Post> posts = getAll();

        Post postRemove = new Post();
        for(Post p : posts){
            if (p.getId().longValue() == post.getId().longValue()){
                postRemove = p;
            }
        }
        posts.remove(postRemove);

        posts.add(post);

        try (FileWriter fileWriter = new FileWriter("post.json")) {
            posts.forEach(p ->{
                String postJsonGormat = mapper.toJson(p);
                try {
                    fileWriter.write(postJsonGormat);
                    fileWriter.write("\n");
                } catch (IOException e) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return post;
    }

    @Override
    public Post getById(Long aLong) {
        List<Post> posts = getAll();

        Post post = new Post();
        for(Post p : posts){
            if (p.getId().longValue() == aLong.longValue()){
                post = p;
            }
        }
        return post;
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        try {
            List<String> strings = readAllLines(Paths.get("post.json"));
            strings.stream().forEach(s -> {
                posts.add(mapper.fromJson(s, Post.class));
            });

        }catch (IOException e){
            System.out.println(e);
        }
        return posts;
    }

    @Override
    public void deleteById(Long aLong) {
        List<Post> posts = getAll();

        Post post = new Post();
        for(Post p : posts){
            if (p.getId().longValue() == aLong.longValue()){
                post = p;
            }
        }
        posts.remove(post);

        try (FileWriter fileWriter = new FileWriter("post.json")) {
            posts.forEach(p ->{
                String postJsonGormat = mapper.toJson(p);
                try {
                    fileWriter.write(postJsonGormat);
                    fileWriter.write("\n");
                } catch (IOException e) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
