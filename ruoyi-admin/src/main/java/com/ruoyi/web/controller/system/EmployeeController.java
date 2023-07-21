package com.ruoyi.web.controller.system;

import cn.hutool.core.lang.Assert;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Employee;
import com.ruoyi.system.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Author 张继升
 * @Date 2023/7/21 19:53
 */
@Api(tags = "职工管理")
@RestController
@RequestMapping("/system/employee")
@RequiredArgsConstructor
public class EmployeeController extends BaseController {

    private final EmployeeService employeeService;




    @ApiOperation(value = "查询列表")
    @PreAuthorize("@ss.hasPermi('system:employee:list')")
    @GetMapping("/list")
    public TableDataInfo list(Employee employee)
    {
        startPage();
        List<Employee> list = employeeService.queryList(employee);
        return getDataTable(list);
    }


    @ApiOperation(value = "导出")
    @PreAuthorize("@ss.hasPermi('system:employee:export')")
    @Log(title = "职工信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(Employee employee, HttpServletResponse response)
    {
        List<Employee> list = employeeService.queryList(employee);
        ExcelUtil<Employee> util = new ExcelUtil<Employee>(Employee.class);
        util.exportExcel(response,list, "职工信息");
    }

    @ApiOperation(value = "查询详情")
    @PreAuthorize("@ss.hasPermi('system:employee:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(employeeService.getById(id));
    }


    @ApiOperation(value = "新增")
    @PreAuthorize("@ss.hasPermi('system:employee:add')")
    @Log(title = "职工信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Employee employee)
    {
        Assert.notBlank(employee.getEmpName(),"姓名不可为空");
        Assert.notNull(employee.getSex(),"性别不可为空");
        Assert.notNull(employee.getAge(),"年龄不可为空");
        Assert.notNull(employee.getDeptName(),"部门不可为空");
        Assert.notNull(employee.getEmpDegreeName(),"学历不可为空");
        employee.setCreateBy(SecurityUtils.getUsername());
        employee.setCreateId(SecurityUtils.getUserId());
        employee.setCreateTime(LocalDateTime.now());
        employee.setDeleted(0);
        return toAjax(employeeService.save(employee));
    }


    @ApiOperation(value = "修改")
    @PreAuthorize("@ss.hasPermi('system:employee:edit')")
    @Log(title = "职工信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Employee employee)
    {
        Assert.notBlank(employee.getEmpName(),"姓名不可为空");
        Assert.notNull(employee.getSex(),"性别不可为空");
        Assert.notNull(employee.getAge(),"年龄不可为空");
        Assert.notNull(employee.getDeptName(),"部门不可为空");
        Assert.notNull(employee.getEmpDegreeName(),"学历不可为空");
        employee.setUpdateBy(SecurityUtils.getUsername());
        employee.setUpdateId(SecurityUtils.getUserId());
        employee.setUpdateTime(LocalDateTime.now());
        return toAjax(employeeService.updateById(employee));
    }


    @ApiOperation(value = "批量删除")
    @PreAuthorize("@ss.hasPermi('system:employee:remove')")
    @Log(title = "职工信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{studentIds}")
    public AjaxResult remove(@PathVariable Long[] studentIds)
    {
        return toAjax(employeeService.removeByIds(Arrays.asList(studentIds)));
    }










}
