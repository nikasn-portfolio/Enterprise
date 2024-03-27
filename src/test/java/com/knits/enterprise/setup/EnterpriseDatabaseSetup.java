package com.knits.enterprise.setup;

import com.knits.enterprise.model.common.Address;
import com.knits.enterprise.model.common.Contact;
import com.knits.enterprise.model.common.Organization;
import com.knits.enterprise.model.company.*;
import com.knits.enterprise.model.location.Location;
import com.knits.enterprise.repository.common.AddressRepository;
import com.knits.enterprise.repository.common.ContactRepository;
import com.knits.enterprise.repository.company.*;
import com.knits.enterprise.repository.location.LocationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:postgresql://localhost:5432/EEI",
        "spring.datasource.username=YOUR_DB_USER",
        "spring.datasource.password=YOUR_DB_PW",
        //"spring.jpa.hibernate.ddl-auto=update",
})
public class EnterpriseDatabaseSetup {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private BusinessUnitRepository businessUnitRepository;

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private JobTitleRepository jobTitleRepository;

    @Autowired
    private CostCenterRepository costCenterRepository;

    @Autowired
    private DivisionRepository divisionRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private LocationRepository locationRepository;

    /**
     * This assumes that
     *  1) Database structure has been created already
     *  2) Data in tables have been entered manually or with liquibase
     *  3) Only Connection between tables are missing
     */
    @Test
    @Transactional
    @Rollback(false)
    public void setupDatabase(){
        List<Employee> employees =employeeRepository.findAllActive();

        organizationWithConnections();
        locationWithConnections();

        employeeToBusinessUnits(employees);
        employeeToDivision(employees);
        employeeToJobTitles(employees);
        employeeToDepartments(employees);
        employeeToCostCenters(employees);
        employeeToGroups(employees);
        employeeToTeams(employees);
        employeeToOrganizations(employees);
        employeeToOffice(employees);
    }




    private void employeeToBusinessUnits (List<Employee> employees) {

        List<BusinessUnit> businessUnit=  businessUnitRepository.findAllActive();
        int bunitCounter=0;
        for (Employee employee : employees){
            if (bunitCounter==businessUnit.size()){
                bunitCounter=0;
            }
            employee.setBusinessUnit(businessUnit.get(bunitCounter));
            bunitCounter++;
            employeeRepository.save(employee);
        }
    }

    private void employeeToDivision (List<Employee> employees) {

        List<Division> divisions=  divisionRepository.findAllActive();
        int counter=0;
        for (Employee employee : employees){
            if (counter==divisions.size()){
                counter=0;
            }
            employee.setDivision(divisions.get(counter));
            counter++;
            employeeRepository.save(employee);
        }
    }

    private void employeeToJobTitles (List<Employee> employees) {

        List<JobTitle> jobTitles= jobTitleRepository.findAllActive();
        int counter=0;
        for (Employee employee : employees){
            if (counter==jobTitles.size()){
                counter=0;
            }
            employee.setJobTitle(jobTitles.get(counter));
            counter++;
            employeeRepository.save(employee);
        }
    }

    private void employeeToDepartments (List<Employee> employees) {

        List<Department> departments= departmentRepository.findAllActive();
        int counter=0;
        for (Employee employee : employees){
            if (counter==departments.size()){
                counter=0;
            }
            employee.setDepartment(departments.get(counter));
            counter++;
            employeeRepository.save(employee);
        }
    }

    private void employeeToOrganizations (List<Employee> employees) {

        List<Organization> organizations= organizationRepository.findAllActive();
        int counter=0;
        for (Employee employee : employees){
            if (counter==organizations.size()){
                counter=0;
            }
            employee.setOrganization(organizations.get(counter));
            counter++;
            employeeRepository.save(employee);
        }
    }

    private void employeeToCostCenters (List<Employee> employees) {

        List<CostCenter> costCenters= costCenterRepository.findAllActive();
        int counter=0;
        for (Employee employee : employees){
            if (counter==costCenters.size()){
                counter=0;
            }
            employee.setCostCenter(costCenters.get(counter));
            counter++;
            employeeRepository.save(employee);
        }
    }

    private void employeeToTeams(List<Employee> employees) {

        List<Team> teams= teamRepository.findAllActive();
        int counter=0;
        for (Employee employee : employees){
            if (counter==teams.size()){
                counter=0;
            }
            employee.setTeam(teams.get(counter));
            counter++;
            employeeRepository.save(employee);
        }
    }

    private void employeeToGroups(List<Employee> employees) {

        List<Group> groups= groupRepository.findAllActive();
        int counter=0;

        for (Group group : groups){
            if (counter==employees.size()){
                counter=0;
            }
            Set<Employee> employeeSet=new HashSet<>();
            employeeSet.add(employees.get(counter));
            group.setEmployees(employeeSet);
            counter++;
            groupRepository.save(group);
        }
    }

    private void organizationWithConnections () {

        List<Organization> organizations=  organizationRepository.findAllActive();
        List<Address> addresses=addressRepository.findAllActive();
        List<Contact> contacts=contactRepository.findAllActive();

        int counter=0;
        int rotation=(addresses.size()>contacts.size())? contacts.size() : addresses.size();
        for (Organization organization : organizations){
            if (counter==rotation){
                counter=0;
            }
            Address address =addresses.get(counter);
            organization.setLegalAddress(address);
            organization.setContactPerson(contacts.get(counter));
            organization.setTaxRegistrationCountry(address.getCountry());
            counter++;
            organizationRepository.save(organization);
        }
    }

    private void locationWithConnections () {
        List<Address> addresses=addressRepository.findAllActive();
        List<Location> locations = locationRepository.findAllActive();

        int counter=0;

        for (Location location : locations){
            if (counter==addresses.size()){
                counter=0;
            }
            location.setAddress(addresses.get(counter));
            counter++;
            locationRepository.save(location);
        }
    }

    private void employeeToOffice(List<Employee> employees) {
        List<Location> locations= locationRepository.findAllActive();
        int counter=0;
        for (Employee employee : employees){
            if (counter==locations.size()){
                counter=0;
            }
            employee.setOffice(locations.get(counter));
            counter++;
            employeeRepository.save(employee);
        }
    }

}
