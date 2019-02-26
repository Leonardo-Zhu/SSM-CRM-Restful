package service.user;

import mapper.UserMapper;
import model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Value("${user.existent}")
    private int existent;
    @Value("${user.nonexistent}")
    private int nonexistent;


    @Override
    public int userCheck(User user) {
        User checkUser = userMapper.checkUser(user);

        if (checkUser != null) {
            if (checkUser.getUserState() == nonexistent ||
               !checkUser.getUserPassword().equals(user.getUserPassword())) {
                return nonexistent;
            }else {
                return existent;
            }
        }
        return nonexistent;
    }

    @Override
    public User userInfo(String userName) {
        return userMapper.userInfo(userName);
    }


    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
