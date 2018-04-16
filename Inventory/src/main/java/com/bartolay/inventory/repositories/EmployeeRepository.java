package com.bartolay.inventory.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    
	public Employee findByUserName(String userName);
	
//    public ObservableList<Employee> getEmployees();
//    public Employee getEmployee(long id);
//    public String getEmployeeType(String username);
//    public void saveEmployee(Employee employee);
//    public void updateEmployee(Employee employee);
//    public void deleteEmployee(Employee employee);
//    public boolean checkPassword(String username,String password);
//    public boolean checkUser(String username);
}
