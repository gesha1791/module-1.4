package chaplinskiy.crud.view;

import chaplinskiy.crud.controller.PostController;
import chaplinskiy.crud.controller.RegionController;
import chaplinskiy.crud.model.Post;
import chaplinskiy.crud.model.Region;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class PostView {
    PostController postController = new PostController();
    BufferedReader bufferedReader;

    public void readMessage(String s, BufferedReader br) throws IOException {
        bufferedReader = br;
        if (s.equals("1")) {
            System.out.println("Введите сообщение");
            s = br.readLine();
            Post post = new Post();
            post.setContent(s);
            post.setCreated(LocalDateTime.now());
            post.setUpdated(LocalDateTime.now());
            postController.create(post);

            System.out.println("Сообщение создано");
            System.out.println(post.toString());
        } else if (s.equals("2")) {
            System.out.println("Введите id сообщения");
            s = br.readLine();
            Post byId = postController.getById(Long.valueOf(s));
            System.out.println("Введите новое сообщение для update");
            s = br.readLine();
            byId.setContent(s);
            byId.setUpdated(LocalDateTime.now());
            Post updatePost = postController.update(byId);
            System.out.println("Сообщение обновили: " + updatePost.toString());
        }  else if (s.equals("3")) {
            List<Post> all = postController.getAll();
            all.forEach(System.out::println);

        } else if (s.equals("4")) {
            System.out.println("Введите id поста");
            s = br.readLine();

            postController.deleteById(Long.valueOf(s));
            System.out.println("Сообщение удалено");
        } else if (s.equals("5")) {
            System.out.println("Введите id поста");
            s = br.readLine();

            Post byId = postController.getById(Long.valueOf(s));
            System.out.println(byId.toString());
        }
    }
}
