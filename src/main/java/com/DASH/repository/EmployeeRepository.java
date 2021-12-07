package com.DASH.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DASH.model.*;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}