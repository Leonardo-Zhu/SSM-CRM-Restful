package service.user;

import model.user.User;


public interface UserService {

    int userCheck(User user);

    User userInfo(String userName);
}
