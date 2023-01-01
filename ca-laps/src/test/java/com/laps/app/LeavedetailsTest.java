package com.laps.app;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.laps.app.helper.DateHelper;
import com.laps.app.model.LeaveDetails;
import com.laps.app.model.LeaveEventEnum;
import com.laps.app.repo.EmployeeRepository;
import com.laps.app.repo.LeaveRepository;
import com.laps.app.service.EmployeeService;
import com.laps.app.service.LeaveService;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CaLapsApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LeavedetailsTest {
	
	@Autowired
	private LeaveRepository leaveRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeService empService;
	@Autowired
	private DateHelper datehelper;
	
	private LeaveService leaveservice;
	
	//process leave request
	@Test

	void sendleaverequest() {
		int before=leaveRepository.findAll().size();
		LeaveDetails leave1= new LeaveDetails(11,01,"Annual Leave",LocalDate.of(2022, 12, 22),LocalDate.of(2022, 12, 31),9,LeaveEventEnum.APPLIED,"personal","noted","done","Overseas",123456);
		
		  LeaveDetails testleave=leaveservice.processLeaveRequest(leave1) ; 
		  
		  int after=leaveRepository.findAll().size();
		  Assertions.assertEquals(after, before+1);
	
	}
	
	void checkisholiday() {
	boolean before=true;
		Boolean day1=datehelper.isHoliday(LocalDate.of(2022,12,31)) ; 
		
		Assertions.assertEquals(day1, before);
	}
}

