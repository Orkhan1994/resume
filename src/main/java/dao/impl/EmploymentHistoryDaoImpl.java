package dao.impl;

import bean.EmploymentHistory;
import bean.Skill;
import bean.User;
import bean.UserSkill;
import dao.inter.AbstractDao;
import dao.inter.EmploymentHistoryDaoInter;
import dao.inter.UserSkillDaoInter;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmploymentHistoryDaoImpl extends AbstractDao implements EmploymentHistoryDaoInter {

    private EmploymentHistory getEmploymentHistory(ResultSet rs) throws Exception{
        String header=rs.getString("header");
        Date beginDate=rs.getDate("begin_date");
        Date endDate=rs.getDate("end_date");
        String jobDescription=rs.getString("job_description");
        int userId=rs.getInt("user_id");

        return new EmploymentHistory(null,header,beginDate,endDate,jobDescription,new User(userId));


    }

    @Override
    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId) {
        List<EmploymentHistory> result = new ArrayList<>();
        try( Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select * from employment_history where  user_id=?");
                stmt.setInt(1, userId);
               stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                   EmploymentHistory emp=getEmploymentHistory(rs);
                result.add(emp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }


}
