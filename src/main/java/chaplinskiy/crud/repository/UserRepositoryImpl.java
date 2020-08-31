package chaplinskiy.crud.repository;

import chaplinskiy.crud.model.Region;
import chaplinskiy.crud.model.User;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static java.nio.file.Files.exists;
import static java.nio.file.Files.readAllLines;

public class UserRepositoryImpl implements UserRepository {

    private final Gson mapper = new Gson();

    @Override
    public User create(User user) {
        Long id = generateId();
        user.setId(id);

        try (FileWriter fileWriter = new FileWriter("user.json", true)) {
            String userJsonGormat = mapper.toJson(user);

            fileWriter.write(userJsonGormat);
            fileWriter.write("\n");
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    private Long generateId() {
        boolean exists = exists(Paths.get("user.json"));
        Long id = null;
        List<Region> regions = new ArrayList<>();
        if (exists){
            try {
                List<String> strings = readAllLines(Paths.get("user.json"));

                strings.stream().forEach(s -> {
                    regions.add(mapper.fromJson(s, Region.class));
                });
                if (regions.size() == 0) {
                    id = 1L;
                } else {
                    Long idMax = regions.stream().max(Comparator.comparing(Region::getId)).orElseThrow(NoSuchElementException::new).getId();
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
    public User update(User user) {
        List<User> users = getAll();

        User userRemove = new User();
        for(User u : users){
            if (u.getId().longValue() == user.getId().longValue()){
                userRemove = u;
            }
        }

        users.remove(userRemove);

        users.add(user);

        List<User> usersSortById = users.stream().sorted(Comparator.comparing(User::getId)).collect(Collectors.toList());

        try (FileWriter fileWriter = new FileWriter("user.json")) {
            usersSortById.forEach(r ->{
                String userJsonFormat = mapper.toJson(r);
                try {
                    fileWriter.write(userJsonFormat);
                    fileWriter.write("\n");
                } catch (IOException e) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


        return user;
    }

    @Override
    public User getById(Long aLong) {
        List<User> users = new ArrayList<>();
        try {
            List<String> strings = readAllLines(Paths.get("user.json"));
            strings.stream().forEach(s -> {
                users.add(mapper.fromJson(s, User.class));
            });

        }catch (IOException e){
            System.out.println(e);
        }

        User user = new User();
        for(User u : users){
            if (u.getId().longValue() == aLong.longValue()){
                user = u;
            }
        }
        return user;
    }

    @Override
    public List<User> getAll() {

        List<User> users = new ArrayList<>();
        try {
            List<String> strings = readAllLines(Paths.get("user.json"));
            strings.stream().forEach(s -> {
                users.add(mapper.fromJson(s, User.class));
            });

        }catch (IOException e){
            System.out.println(e);
        }
        return users;
    }

    @Override
    public void deleteById(Long aLong) {
        List<User> users = getAll();

        User user = new User();
        for(User u : users){
            if (u.getId().longValue() == aLong.longValue()){
                user = u;
            }
        }

        users.remove(user);

        try (FileWriter fileWriter = new FileWriter("user.json")) {
            users.forEach(r ->{
                String userJsonFormat = mapper.toJson(r);
                try {
                    fileWriter.write(userJsonFormat);
                    fileWriter.write("\n");
                } catch (IOException e) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
