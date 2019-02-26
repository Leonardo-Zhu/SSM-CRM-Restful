package mapper;

import model.user.User;

public interface UserMapper {

    void addUser(User user);

    void retrieveUser(User user);

    User checkUser(User user);

    User userInfo(String userName);

    void deleteUser(User user);
}
