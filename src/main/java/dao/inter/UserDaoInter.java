package dao.inter;

import bean.User;
import bean.UserSkill;

import java.util.List;

public interface UserDaoInter {
    List<User> getAllUsers();

    User getUserById(int id);

    boolean addUser(User user);

    boolean updateUser(User user);

    boolean remove(int id);


}
