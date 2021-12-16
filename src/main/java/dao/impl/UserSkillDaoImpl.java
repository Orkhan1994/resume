package dao.impl;

import bean.Skill;
import bean.User;
import bean.UserSkill;
import dao.inter.AbstractDao;
import dao.inter.UserSkillDaoInter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserSkillDaoImpl extends AbstractDao implements UserSkillDaoInter {

    private UserSkill getUserSkill(ResultSet rs) throws Exception{
        int userId=rs.getInt("user_id");
        int skillId=rs.getInt("skill_id");
        String skillName=rs.getString("skill_name");
        int power=rs.getInt("power");

        return new UserSkill(null,new User(userId),new Skill(skillId,skillName),power);
    }

    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {
        List<UserSkill> result = new ArrayList<>();
        try( Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select " +
                    "u.*, " +
                    "us.skill_id ," +
                    "s.NAME as skill_name," +
                    "us.power" +
                    "from " +
                    "user_skill us" +
                    "left join user u on us.user_id=u.id" +
                    "left join skill s on us.skill_id=s.id" +
                    "where us.user_id=?");
                stmt.setInt(1, userId);
               stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                UserSkill u=getUserSkill(rs);
                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }


}
