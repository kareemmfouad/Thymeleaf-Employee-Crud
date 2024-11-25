package com.example.employeecrud.controller;

import com.example.employeecrud.entity.Employee;
import com.example.employeecrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String  listEmployees(Model model){
        model.addAttribute("employees", employeeService.findAll());
        return "employees/employee-list";
    }

    @GetMapping("/showFormForAdd")
    public String  showFormForAdd(Model model){
        model.addAttribute("employee", new Employee());
        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String  saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.save(employee);
        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String  showFormForUpdate(@RequestParam("employeeId") Integer id, Model model){
        model.addAttribute("employee", employeeService.findById(id));
        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String  deleteEmployee(@RequestParam("employeeId") Integer id){
        employeeService.deleteById(id);
        return "redirect:/employees/list";
    }
}
