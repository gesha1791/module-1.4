package chaplinskiy.crud.view;


import chaplinskiy.crud.controller.PostController;
import chaplinskiy.crud.controller.RegionController;
import chaplinskiy.crud.controller.UserController;
import chaplinskiy.crud.model.Post;
import chaplinskiy.crud.model.Region;
import chaplinskiy.crud.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserView {
    UserController userController = new UserController();
    RegionController regionController  = new RegionController();
    PostController postController = new PostController();
    BufferedReader bufferedReader;

    public void readMessage(String s, BufferedReader br) throws IOException {
        bufferedReader = br;
        if (s.equals("1")) {
            User user = new User();
            System.out.println("Введите имя: ");
            s = br.readLine();
            user.setFirstName(s);

            System.out.println("Введите фамилию: ");
            s = br.readLine();
            user.setLastName(s);

            System.out.println("Введите регион: ");
            s = br.readLine();
            Region region = regionController.checkRegion(s);
            user.setRegion(region);

            List<Post> all = postController.getAll();
            all.forEach(System.out::println);
            System.out.println("Введите номера сообщений, например 3,4");

            s = br.readLine();

            String[] split = s.split(",");
            List<Post> messagesUser = new ArrayList<>();

            for (int i = 0; i < split.length; i++) {

                Long aLong = Long.valueOf(split[i]);
                for(Post p : all){
                    if (p.getId().longValue() == aLong.longValue()){
                        messagesUser.add(p);
                    }

                }
            }
            user.setPosts(messagesUser);

            User newUser = userController.create(user);



            System.out.println("Юзер создан");
            System.out.println(newUser.toString());


        } else if (s.equals("2")) {
            User user = new User();
            System.out.println("Введите id: ");
            s = br.readLine();
            user.setId(Long.valueOf(s));

            System.out.println("Введите имя: ");
            s = br.readLine();
            user.setFirstName(s);

            System.out.println("Введите фамилию: ");
            s = br.readLine();
            user.setLastName(s);

            System.out.println("Введите регион: ");
            s = br.readLine();
            Region region = regionController.checkRegion(s);
            user.setRegion(region);

            List<Post> all = postController.getAll();
            all.forEach(System.out::println);
            System.out.println("Введите номера сообщений, например 3,4");

            s = br.readLine();

            String[] split = s.split(",");
            List<Post> messagesUser = new ArrayList<>();

            for (int i = 0; i < split.length; i++) {

                Long aLong = Long.valueOf(split[i]);
                for(Post p : all){
                    if (p.getId().longValue() == aLong.longValue()){
                        messagesUser.add(p);
                    }

                }
            }
            user.setPosts(messagesUser);

            User update = userController.update(user);

            System.out.println("Юзер обновлен: " + update.toString());

        }  else if (s.equals("3")) {
            List<User> all = userController.getAll();

            System.out.println("Все юзеры");

            if (all.size() == 0){
                System.out.println("Нет созданных юзеров\n");
            }

            all.forEach(System.out::println);
        } else if (s.equals("4")) {
            System.out.println("Введите id юзера для удаления");

            s = br.readLine();

            userController.deleteById(Long.valueOf(s));
            System.out.println("Юзер удален успешно");
        } else if (s.equals("5")) {
            System.out.println("Введите id юзера");

            s = br.readLine();

            User getUserById = userController.getById(Long.valueOf(s));

            System.out.println(getUserById.toString());

        }
    }


}
