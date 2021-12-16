package dao.impl;

import bean.Skill;
import dao.inter.AbstractDao;
import dao.inter.SkillDaoInter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SkillDaoImpl extends AbstractDao implements SkillDaoInter {


    private Skill getSkill(ResultSet rs) throws Exception{
        int id=rs.getInt("id");
        String name=rs.getString("name");
        Skill skill=new Skill(id,name);
        return skill;


    }

    @Override
    public List<Skill> getAllSkill() {
        List<Skill> result = new ArrayList<>();
        try( Connection c = connect()) {
        Statement stmt=c.createStatement();
        stmt.execute("Select * from skills");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
              Skill skill=getSkill(rs);
                result.add(skill);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}



