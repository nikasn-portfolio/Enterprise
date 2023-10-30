package com.knits.enterprise.repository.company;

import com.knits.enterprise.analyticsModel.EmployeesCountByBusinessUnit;
import com.knits.enterprise.analyticsModel.EmployeesCountByDepartment;
import com.knits.enterprise.analyticsModel.EmployeesCountByJobTitle;
import com.knits.enterprise.view.*;
import com.knits.enterprise.dto.common.EmployeesCountByGenderDto;
import com.knits.enterprise.analyticsModel.EmployeesCountByOffice;
import com.knits.enterprise.model.company.Employee;
import com.knits.enterprise.repository.common.ActiveEntityRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends ActiveEntityRepository<Employee> {

    @Query("select e from Employee  e  where e.id IN (:ids) AND e.active = true")
    Set<Employee> findAllById(@Param("ids") Set<Long> ids);

    @Query("SELECT " +
            "CASE " +
            "    WHEN EXTRACT(YEAR FROM current_date) - EXTRACT(YEAR FROM e.birthDate) BETWEEN 20 AND 24 THEN '20-24' " +
            "    WHEN EXTRACT(YEAR FROM current_date) - EXTRACT(YEAR FROM e.birthDate) BETWEEN 25 AND 29 THEN '25-29' " +
            "    WHEN EXTRACT(YEAR FROM current_date) - EXTRACT(YEAR FROM e.birthDate) BETWEEN 30 AND 34 THEN '30-34' " +
            "    WHEN EXTRACT(YEAR FROM current_date) - EXTRACT(YEAR FROM e.birthDate) BETWEEN 35 AND 39 THEN '35-39' " +
            "    ELSE '40+' " +
            "  END as ageGroup, COUNT(e) as employeesCount" +
            "  FROM Employee e where EXTRACT(YEAR FROM current_date) - EXTRACT(YEAR FROM e.birthDate) >= 20 GROUP BY ageGroup")
    List<AgeGroupCountView> countEmployeesByAgeGroup();

    @Query("SELECT CASE " +
            "WHEN EXTRACT(YEAR FROM current_date) - EXTRACT(YEAR FROM e.startDate) = 1 THEN '1'" +
            "WHEN EXTRACT(YEAR FROM current_date) - EXTRACT(YEAR FROM e.startDate) = 2 THEN '2'" +
            "WHEN EXTRACT(YEAR FROM current_date) - EXTRACT(YEAR FROM e.startDate) = 5 THEN '5'" +
            "WHEN EXTRACT(YEAR FROM current_date) - EXTRACT(YEAR FROM e.startDate) >= 10 THEN '10+'" +
            "END as experienceGroup, COUNT(e) as employeesCount FROM Employee e " +
            "WHERE e.endDate IS NULL AND (EXTRACT(YEAR FROM current_date) - EXTRACT(YEAR FROM e.startDate) IN (1,2,5,10) OR EXTRACT(YEAR FROM current_date) - EXTRACT(YEAR FROM e.startDate) > 10) " +
            "GROUP BY experienceGroup")
    List<EmployeesCountByExperienceView> countEmployeesByExperience();

    @Query("SELECT new com.knits.enterprise.dto.common.EmployeesCountByGenderDto(e.gender , COUNT(e)) FROM Employee e GROUP BY e.gender")
    List<EmployeesCountByGenderDto> countEmployeesByGender();

    @Query("SELECT new com.knits.enterprise.analyticsModel.EmployeesCountByOffice(e.office , COUNT(e)) FROM Employee e GROUP BY e.office")
    List<EmployeesCountByOffice> countEmployeesByOffice();

    @Query("SELECT new com.knits.enterprise.analyticsModel.EmployeesCountByBusinessUnit(e.businessUnit , COUNT(e)) FROM Employee e GROUP BY e.businessUnit")
    List<EmployeesCountByBusinessUnit> countEmployeesByBusinessUnit();

    @Query("SELECT new com.knits.enterprise.analyticsModel.EmployeesCountByJobTitle(e.jobTitle , COUNT(e)) FROM Employee e GROUP BY e.jobTitle")
    List<EmployeesCountByJobTitle> countEmployeesByJobTitle();

    @Query("SELECT new com.knits.enterprise.analyticsModel.EmployeesCountByDepartment(e.department , COUNT(e)) FROM Employee e GROUP BY e.department")
    List<EmployeesCountByDepartment> countEmployeesByDepartment();

    @Query("SELECT EXTRACT(YEAR FROM e.startDate) as yearNumber, count(e) as employeesCount FROM Employee e WHERE EXTRACT(YEAR FROM CURRENT_DATE) - EXTRACT(YEAR FROM e.startDate) <= 15 " +
            " GROUP BY yearNumber ORDER BY EXTRACT(YEAR FROM e.startDate) ASC")
    List<EmployeesCountByYearView> countHiredEmployeesByYear();

    @Query("SELECT EXTRACT(YEAR FROM e.endDate) as yearNumber, count(e) as employeesCount FROM Employee e WHERE EXTRACT(YEAR FROM CURRENT_DATE) - EXTRACT(YEAR FROM e.endDate) <= 15" +
            " and e.endDate is not null " +
            " GROUP BY yearNumber ORDER BY EXTRACT(YEAR FROM e.endDate) ASC")
    List<EmployeesCountByYearView> countLeftEmployeesByYear();

    @Query(value = "SELECT e.businessUnit as businessUnit, count(e) as employeesCount " +
            " FROM Employee e join e.businessUnit b where e.businessUnit is not null " +
            " GROUP BY b, e.businessUnit ORDER BY employeesCount DESC")
    List<EmployeesCountInBusinessUnitView> findMaxEmployeeCountByBusinessUnit(PageRequest limit);

    @Query(value = "SELECT e.jobTitle as jobTitle, count(e) as employeesCount " +
            " FROM Employee e join e.jobTitle b where e.jobTitle is not null " +
            " GROUP BY b, e.jobTitle ORDER BY employeesCount DESC")
    List<EmployeesCountInJobTitleView> findMaxEmployeeCountByJobTitle(PageRequest limit);

    @Query(value = "SELECT e.department as department, count(e) as employeesCount " +
            " FROM Employee e join e.department b where e.department is not null " +
            " GROUP BY b, e.department ORDER BY employeesCount DESC")
    List<EmployeesCountInDepartmentView> findMaxEmployeeCountByDepartment(PageRequest limit);


}
