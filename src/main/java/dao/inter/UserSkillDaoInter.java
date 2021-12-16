package dao.inter;

import bean.UserSkill;

import java.util.List;

public interface UserSkillDaoInter {

    List<UserSkill> getAllSkillByUserId(int userId);
}
