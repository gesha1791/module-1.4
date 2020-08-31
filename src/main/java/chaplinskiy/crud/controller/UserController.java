package chaplinskiy.crud.controller;

import chaplinskiy.crud.model.User;
import chaplinskiy.crud.repository.UserRepository;
import chaplinskiy.crud.repository.UserRepositoryImpl;

import java.util.List;

public class UserController {
    private UserRepository userRepository = new UserRepositoryImpl();;

    public UserController() {
    }

    public User getById(Long id){
        return userRepository.getById(id);
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }


    public User create(User user) {
        return userRepository.create(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User update(User user) {
        return userRepository.update(user);
    }
}
