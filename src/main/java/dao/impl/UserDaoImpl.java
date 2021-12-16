package dao.impl;

import bean.Country;
import bean.User;
import dao.inter.AbstractDao;
import dao.inter.UserDaoInter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDao implements UserDaoInter {

    private User getUser(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        int nationalityId = rs.getInt("nationality_id");
        int birthplaceId = rs.getInt("birthplace_id");
        String nationalityStr = rs.getString("nationality");
        String birthpalceStr = rs.getString("name");
        Date birthDate = rs.getDate("birthdate");

        Country nationality = new Country(nationalityId, null, nationalityStr);
        Country birthplace = new Country(birthplaceId, birthpalceStr, null);

        return new User(id, name, surname, email, phone, birthplace, nationality, null);

    }

    @Override
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("Select " +
                    "u.*," +
                    "n.nationality," +
                    "c.name as birthplace" +
                    "from user u" +
                    "left join country n on u.nationality_id=n.id" +
                    "left join country c on u.birthplace_id=c.id");

            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                User u = getUser(rs);
                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public User getUserById(int userId) {
        User result = null;
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("Select " +
                    "u.*," +
                    "n.nationality," +
                    "c.name as birthplace" +
                    "from user u" +
                    "left join country n on u.nationality_id=n.id" +
                    "left join country c on u.birthplace_id=c.id where u.id" + userId);

            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                result = getUser(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addUser(User user) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into user(name,surname,email,phone,profile_description" +
                    ",address,birthdate,birthplace_id,nationality_id) " +
                    "values (?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhone());
            stmt.setString(5, user.getProfileDescription());
            stmt.setString(6, user.getAddress());
            stmt.setDate(7, user.getBirthDate());
            stmt.setString(8, String.valueOf(user.getBirthPlace()));
            stmt.setString(9, String.valueOf(user.getNationality()));
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean updateUser(User user) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update user set name=?,surname=?,email=?,phone=?,profile_description=?," +
                    "address=?,birthdate=?,birthplace_id=?,nationality_id=? where id=?");
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhone());
            stmt.setString(5, user.getProfileDescription());
            stmt.setString(6, user.getAddress());
            stmt.setDate(7, user.getBirthDate());
            stmt.setString(8, String.valueOf(user.getBirthPlace()));
            stmt.setString(9, String.valueOf(user.getNationality()));
            stmt.setInt(10,user.getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean remove(int id) {
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            return stmt.execute("delete from user where id= " + id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
