package com.task.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.task.model.Department;
import com.task.model.Employee;
import com.task.service.EmployeeService;
import com.task.util.DepartmentEditor;


@RestController
@SessionAttributes("emp")
@RequestMapping("/ems")
@CrossOrigin(origins = {"http://localhost:4200"})
// ref - addEmployeeFom
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	private Validator validator;

	public EmployeeController() {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Department.class, new DepartmentEditor());
	}

//	@RequestMapping(method = RequestMethod.GET)
//	public ModelAndView anythingThanLogin() {
//		return new ModelAndView("redirect:/viewAllEmployees");
//	}
//	@RequestMapping(value = "/viewAllEmployees", method = RequestMethod.GET)
	
	@GetMapping("departments")
	public ResponseEntity<HashMap<String,Object>> getDepartment() {
		List<Department> deptList = null;
		HashMap<String,Object> hm = new HashMap<String,Object>();
		try {
			deptList = employeeService.getAllDepartment();
			if(deptList != null) {
				hm.put("data", deptList);
			} else {
				hm.put("errorMsg", "Record does not exists");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ResponseEntity<HashMap<String,Object>>(hm, HttpStatus.OK);
	}
	
	@GetMapping("login")
	public ResponseEntity<HashMap<String,Object>> login(@RequestParam("userId") String userId, @RequestParam("password") String password) {
 		Employee user = employeeService.login(userId,password);
		HashMap<String,Object> hm = new HashMap<String,Object>();
		if(user != null) {
			hm.put("data", user);
		} else {
			hm.put("errorMsg", "Record does not exists");
		}
		return new ResponseEntity<HashMap<String,Object>>(hm, HttpStatus.OK);
	}
	
//	@RequestMapping(
//		    value = "/register", 
//		    method = RequestMethod.POST,
//		    consumes = "application/json")
	@PostMapping("register")
	public ResponseEntity<HashMap<String,Object>> register(@RequestBody Employee employeee) {
		HashMap<String,Object> hm = new HashMap<String,Object>();
		try {
			boolean status = employeeService.register(employeee);
			if(status){
				hm.put("data", "User created Successfully");
			} else {
				hm.put("data", "Ooops, Something went wrong");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return new ResponseEntity<HashMap<String,Object>>(hm, HttpStatus.CREATED);
	}
	
	@GetMapping("viewAllEmployees")
	public ResponseEntity<HashMap<String,Object>> getAllEmployees(@RequestParam("userId") String userId, @RequestParam("password") String password) {
		
		
		List<Employee> list = employeeService.getAllEmployees();
		HashMap<String,Object> hm = new HashMap<String,Object>();
		hm.put("msg", "");
		hm.put("data", list);
		
		ResponseEntity<HashMap<String,Object>> re = new ResponseEntity<HashMap<String,Object>>(hm, HttpStatus.OK);
		return re;
	}

	@RequestMapping(value = "/editEmpDetails/{id}", method = RequestMethod.GET)
	public ModelAndView getAllEmployeesDetailsForEdit(@PathVariable int id, Model model) {
		// System.out.println("Id ; "+id);
		ModelAndView mav = new ModelAndView("employeeEditForm");
		Employee emp = employeeService.getEmployeeById(id);
//		model.addAttribute("emp", new Employee());
		model.addAttribute("emp", emp);
		List<Department> depts = employeeService.getAllDepartments();
		mav.addObject(model);
		mav.addObject("department", depts);
		return mav;
//		return new ModelAndView("employeeEditForm", "employee", emp);
	}

	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
	public ModelAndView editSave(@ModelAttribute("emp") @Valid Employee emp, BindingResult result, Model model) {
		// System.out.println(emp);
		Set<ConstraintViolation<Employee>> violations = validator.validate(emp);
		for (ConstraintViolation<Employee> violation : violations) {
			String propertyPath = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			System.out.println(message);
//			result.addError(new FieldError("emp", propertyPath, "Invalid " + propertyPath + "(" + message + ")"));
		}
		
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("employeeEditForm");
			List<Department> depts = employeeService.getAllDepartments();
			mav.addObject("department", depts);
			return mav;
		}
		
		boolean status = employeeService.updateEmployeeById(emp);
		if (status) {
			return new ModelAndView("redirect:/viewAllEmployees");
		}
		return new ModelAndView("errorpage");
	}

	@RequestMapping(value = "/addEmployeeForm", method = RequestMethod.GET)
	public ModelAndView addNewEmployee(Model model) {
		List<Department> depts = employeeService.getAllDepartments();
		ModelAndView mav = new ModelAndView("addNewEmployee");
		mav.addObject("emp", new Employee());
		mav.addObject("department", depts);
		return mav;
//		return new ModelAndView("addNewEmployee", "department", depts);
	}

	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
	public ModelAndView addEmployee(@ModelAttribute("emp") @Valid Employee emp, BindingResult result, SessionStatus status) {
		// System.out.println(emp);

		Set<ConstraintViolation<Employee>> violations = validator.validate(emp);
		for (ConstraintViolation<Employee> violation : violations) {
			String propertyPath = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			System.out.println(message);
//			result.addError(new FieldError("emp", propertyPath, "Invalid " + propertyPath + "(" + message + ")"));
		}
		
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("addNewEmployee");
			List<Department> depts = employeeService.getAllDepartments();
//			mav.addObject("emp", new Employee());
			mav.addObject("department", depts);
			return mav;
//			return new ModelAndView("redirect:/addEmployeeForm");
		}

		boolean flag = employeeService.addEmployee(emp);
		if (flag) {
			status.setComplete();
			return new ModelAndView("redirect:/viewAllEmployees");
		}

		return new ModelAndView("errorpage");
	}

	@RequestMapping(value = "/deleteEmployee/{id}", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(@PathVariable int id, Model model) {
		boolean status = employeeService.deleteEmployeeById(id);
		if (status) {
			return new ModelAndView("redirect:/viewAllEmployees");
		}
		return new ModelAndView("errorpage");
	}

	@RequestMapping(value = "/editEmpDetails", method = RequestMethod.POST)
	public ModelAndView getAllEmployeesDetailsForEdit1(@RequestParam("selectedEmpId") String idStr, Model model) {
		int id = Integer.parseInt(idStr);
		// System.out.println("Id : "+id);
		Employee emp = employeeService.getEmployeeById(id);
		model.addAttribute("empModel", new Employee());
		return new ModelAndView("employeeEditForm", "employee", emp);
	}

	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.POST)
	public ModelAndView deleteEmployee1(@RequestParam("selectedEmpId") String idStr, Model model) {
		// System.out.println(idStr);
		String[] words = idStr.split(",");
		List<Integer> ids = new ArrayList<>();
		for (int i = 0; i < words.length; i++) {
			ids.add(Integer.parseInt(words[i]));
		}
		boolean status = employeeService.deleteEmployeeByIds(ids);
		if (status) {
			return new ModelAndView("redirect:/viewAllEmployees");
		}
		return new ModelAndView("errorpage");
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView searchEmployee(@RequestParam("searchCriteria") String searchCriteria,
	      @RequestParam("searchdropdown") String searchBy, Model model) {
		int empId = 0;
		// System.out.println(searchBy);
		// System.out.println(searchCriteria);
		if (searchBy.equals("byEmpId")) {
			empId = Integer.parseInt(searchCriteria);
			List<Employee> list = employeeService.searchByEmpId(empId);
			if (null != list && !list.isEmpty()) {
				return new ModelAndView("employeeList", "employees", list);
			}
			return new ModelAndView("norecordfound");
		}
		List<Employee> list = employeeService.searchByCriteria(searchCriteria);
		if (null != list && !list.isEmpty()) {
			return new ModelAndView("employeeList", "employees", list);
		}
		return new ModelAndView("norecordfound");
	}

	@RequestMapping(value = "/search1", method = RequestMethod.POST)
	public ModelAndView searchEmployeeByNameEmail(@RequestParam("firstName") String firstName,
	      @RequestParam("lastName") String lastName, @RequestParam("email") String email,
	      final RedirectAttributes redirectAttributes) {

		List<Employee> list = employeeService.searchEmployeeByNameEmail(firstName, lastName, email);
		if (null != list && !list.isEmpty()) {
			return new ModelAndView("employeeList", "employees", list);
		} else {
			redirectAttributes.addFlashAttribute("message", "No result Found.");
			return new ModelAndView("redirect:/viewAllEmployees");
		}
	}

}
