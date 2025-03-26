//package com.evnit.hrms3.employe.components;
//
//import java.util.Calendar;
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.evnit.hrms3.employe.service.PhanxeveService;
//
//@Component
//public class ScheduledPhanxe {
//	//private static final Logger logger = Logger.getLogger(ScheduledPhanxe.class);
//
//	@Autowired
//	private PhanxeveService PhanxeveService;
//
//	// @Scheduled(fixedRate = 5000)
//
//	@Scheduled(cron = "0 0 1 * * ?")
//	public void reportCurrentTime() {
//		Date localDate = Calendar.getInstance().getTime();
//		PhanxeveService.updateTrangthaiPhanxe(localDate);
//
//	}
//}
