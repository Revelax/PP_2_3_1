package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    List<User> listUsers();

    User listUserId(int id);

    void deleteUserById(int id);

    void editUser(int id, User userNew);

}
