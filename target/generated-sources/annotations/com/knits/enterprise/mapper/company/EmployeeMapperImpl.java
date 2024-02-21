package com.knits.enterprise.mapper.company;

import com.knits.enterprise.dto.company.EmployeeDto;
import com.knits.enterprise.dto.company.OfficeDto;
import com.knits.enterprise.mapper.common.OrganizationMapper;
import com.knits.enterprise.model.company.Employee;
import com.knits.enterprise.model.location.Location;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-19T23:37:40+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Autowired
    private OrganizationMapper organizationMapper;
    @Autowired
    private DivisionMapper divisionMapper;
    @Autowired
    private BusinessUnitMapper businessUnitMapper;
    @Autowired
    private JobTitleMapper jobTitleMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    private final DateTimeFormatter dateTimeFormatter_dd_MM_yyyy_0650712384 = DateTimeFormatter.ofPattern( "dd/MM/yyyy" );

    @Override
    public List<EmployeeDto> toDtos(List<Employee> entityList) {
        if ( entityList == null ) {
            return new ArrayList<EmployeeDto>();
        }

        List<EmployeeDto> list = new ArrayList<EmployeeDto>( entityList.size() );
        for ( Employee employee : entityList ) {
            list.add( toDto( employee ) );
        }

        return list;
    }

    @Override
    public void partialUpdate(Employee entity, EmployeeDto dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        entity.setActive( dto.isActive() );
        if ( dto.getFirstName() != null ) {
            entity.setFirstName( dto.getFirstName() );
        }
        if ( dto.getLastName() != null ) {
            entity.setLastName( dto.getLastName() );
        }
        if ( dto.getEmail() != null ) {
            entity.setEmail( dto.getEmail() );
        }
        if ( dto.getBirthDate() != null ) {
            entity.setBirthDate( LocalDate.parse( dto.getBirthDate() ) );
        }
        if ( dto.getGender() != null ) {
            entity.setGender( dto.getGender() );
        }
        if ( dto.getStartDate() != null ) {
            entity.setStartDate( LocalDate.parse( dto.getStartDate() ) );
        }
        if ( dto.getEndDate() != null ) {
            entity.setEndDate( LocalDate.parse( dto.getEndDate() ) );
        }
        if ( dto.getCompanyPhone() != null ) {
            entity.setCompanyPhone( dto.getCompanyPhone() );
        }
        if ( dto.getCompanyMobileNumber() != null ) {
            entity.setCompanyMobileNumber( dto.getCompanyMobileNumber() );
        }
        if ( dto.getBusinessUnit() != null ) {
            entity.setBusinessUnit( businessUnitMapper.toEntity( dto.getBusinessUnit() ) );
        }
        if ( dto.getOrganization() != null ) {
            entity.setOrganization( organizationMapper.toEntity( dto.getOrganization() ) );
        }
        if ( dto.getOffice() != null ) {
            if ( entity.getOffice() == null ) {
                entity.setOffice( Location.builder().build() );
            }
            officeDtoToLocation( dto.getOffice(), entity.getOffice() );
        }
        if ( dto.getJobTitle() != null ) {
            entity.setJobTitle( jobTitleMapper.toEntity( dto.getJobTitle() ) );
        }
        if ( dto.getDepartment() != null ) {
            entity.setDepartment( departmentMapper.toEntity( dto.getDepartment() ) );
        }
        if ( dto.getDivision() != null ) {
            entity.setDivision( divisionMapper.toEntity( dto.getDivision() ) );
        }
        if ( dto.getSolidLineManager() != null ) {
            entity.setSolidLineManager( toEntity( dto.getSolidLineManager() ) );
        }
    }

    @Override
    public void update(Employee entity, EmployeeDto dto) {
        if ( dto == null ) {
            return;
        }

        entity.setId( dto.getId() );
        entity.setActive( dto.isActive() );
        entity.setFirstName( dto.getFirstName() );
        entity.setLastName( dto.getLastName() );
        entity.setEmail( dto.getEmail() );
        if ( dto.getBirthDate() != null ) {
            entity.setBirthDate( LocalDate.parse( dto.getBirthDate() ) );
        }
        else {
            entity.setBirthDate( null );
        }
        entity.setGender( dto.getGender() );
        if ( dto.getStartDate() != null ) {
            entity.setStartDate( LocalDate.parse( dto.getStartDate() ) );
        }
        else {
            entity.setStartDate( null );
        }
        if ( dto.getEndDate() != null ) {
            entity.setEndDate( LocalDate.parse( dto.getEndDate() ) );
        }
        else {
            entity.setEndDate( null );
        }
        entity.setCompanyPhone( dto.getCompanyPhone() );
        entity.setCompanyMobileNumber( dto.getCompanyMobileNumber() );
        entity.setBusinessUnit( businessUnitMapper.toEntity( dto.getBusinessUnit() ) );
        entity.setOrganization( organizationMapper.toEntity( dto.getOrganization() ) );
        if ( dto.getOffice() != null ) {
            if ( entity.getOffice() == null ) {
                entity.setOffice( Location.builder().build() );
            }
            officeDtoToLocation( dto.getOffice(), entity.getOffice() );
        }
        else {
            entity.setOffice( null );
        }
        entity.setJobTitle( jobTitleMapper.toEntity( dto.getJobTitle() ) );
        entity.setDepartment( departmentMapper.toEntity( dto.getDepartment() ) );
        entity.setDivision( divisionMapper.toEntity( dto.getDivision() ) );
        entity.setSolidLineManager( toEntity( dto.getSolidLineManager() ) );
    }

    @Override
    public EmployeeDto toDto(Employee entity) {
        if ( entity == null ) {
            return null;
        }

        EmployeeDto.EmployeeDtoBuilder<?, ?> employeeDto = EmployeeDto.builder();

        if ( entity.getBirthDate() != null ) {
            employeeDto.birthDate( dateTimeFormatter_dd_MM_yyyy_0650712384.format( entity.getBirthDate() ) );
        }
        if ( entity.getStartDate() != null ) {
            employeeDto.startDate( dateTimeFormatter_dd_MM_yyyy_0650712384.format( entity.getStartDate() ) );
        }
        if ( entity.getEndDate() != null ) {
            employeeDto.endDate( dateTimeFormatter_dd_MM_yyyy_0650712384.format( entity.getEndDate() ) );
        }
        employeeDto.active( entity.isActive() );
        employeeDto.id( entity.getId() );
        employeeDto.firstName( entity.getFirstName() );
        employeeDto.lastName( entity.getLastName() );
        employeeDto.email( entity.getEmail() );
        employeeDto.gender( entity.getGender() );
        employeeDto.companyPhone( entity.getCompanyPhone() );
        employeeDto.companyMobileNumber( entity.getCompanyMobileNumber() );

        return employeeDto.build();
    }

    @Override
    public Employee toEntity(EmployeeDto dto) {
        if ( dto == null ) {
            return null;
        }

        Employee.EmployeeBuilder<?, ?> employee = Employee.builder();

        if ( dto.getBirthDate() != null ) {
            employee.birthDate( LocalDate.parse( dto.getBirthDate(), dateTimeFormatter_dd_MM_yyyy_0650712384 ) );
        }
        if ( dto.getStartDate() != null ) {
            employee.startDate( LocalDate.parse( dto.getStartDate(), dateTimeFormatter_dd_MM_yyyy_0650712384 ) );
        }
        if ( dto.getEndDate() != null ) {
            employee.endDate( LocalDate.parse( dto.getEndDate(), dateTimeFormatter_dd_MM_yyyy_0650712384 ) );
        }
        employee.id( dto.getId() );
        employee.active( dto.isActive() );
        employee.firstName( dto.getFirstName() );
        employee.lastName( dto.getLastName() );
        employee.email( dto.getEmail() );
        employee.gender( dto.getGender() );
        employee.companyPhone( dto.getCompanyPhone() );
        employee.companyMobileNumber( dto.getCompanyMobileNumber() );

        return employee.build();
    }

    @Override
    public List<Employee> toEntities(List<EmployeeDto> entityList) {
        if ( entityList == null ) {
            return new ArrayList<Employee>();
        }

        List<Employee> list = new ArrayList<Employee>( entityList.size() );
        for ( EmployeeDto employeeDto : entityList ) {
            list.add( toEntity( employeeDto ) );
        }

        return list;
    }

    protected void officeDtoToLocation(OfficeDto officeDto, Location mappingTarget) {
        if ( officeDto == null ) {
            return;
        }

        mappingTarget.setId( officeDto.getId() );
        mappingTarget.setName( officeDto.getName() );
    }
}
