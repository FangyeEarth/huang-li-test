package com.ruoyi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.system.domain.Employee;

import java.util.List;

public interface EmployeeService extends IService<Employee> {

    List<Employee> queryList(Employee employee);
}
