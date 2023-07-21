package com.ruoyi.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.system.domain.Employee;
import com.ruoyi.system.mapper.EmployeeMapper;
import com.ruoyi.system.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author 张继升
 * @Date 2023/7/21 18:51
 */
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    private final EmployeeMapper employeeMapper;


    @Override
    public List<Employee> queryList(Employee employee) {
        return employeeMapper.selectList(Wrappers.<Employee>lambdaQuery()
                .eq(Employee::getDeleted,0)
                .eq(ObjectUtil.isNotNull(employee.getDeptName()),Employee::getDeptName,employee.getDeptName())
                .eq(ObjectUtil.isNotNull(employee.getEmpDegreeName()),Employee::getEmpDegreeName,employee.getEmpDegreeName())
                .like(ObjectUtil.isNotNull(employee.getEmpName()),Employee::getEmpName,employee.getEmpName())
                .orderByDesc(Employee::getCreateTime)
        );
    }
}
