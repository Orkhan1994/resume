package dao.inter;

import bean.EmploymentHistory;

import java.util.List;

public interface EmploymentHistoryDaoInter {

    List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId);
}
