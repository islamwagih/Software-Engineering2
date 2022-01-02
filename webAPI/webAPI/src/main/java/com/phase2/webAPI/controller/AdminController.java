package com.phase2.webAPI.controller;

import com.phase2.webAPI.entity.Driver;
import com.phase2.webAPI.entity.Event;
import com.phase2.webAPI.service.AdminService;
import com.phase2.webAPI.service.DriverService;
import com.phase2.webAPI.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    EventService eventService;

    @Autowired
    private DriverService driverService;

    @GetMapping(value = "/admin/listAllDriverRequests")
    public List<Driver> listAllDriverRequests() {
        return adminService.listAllDriverRequests();
    }

    @GetMapping(value = "/readDrivers")
    public List<Driver> getDrivers() {
        return adminService.allDrivers();
    }

    @PutMapping(value = "/admin/suspendDriver")
    public String suspendDriver(@RequestBody int id) {
        return adminService.suspendDriver(id);
    }

    @PutMapping(value = "/admin/suspendUser")
    public String suspendUser(@RequestBody int id) {
        return adminService.suspendUser(id);
    }

    @PutMapping(value = "/admin/activateDriver")
    public String activateDriver(@RequestBody int id) {
        return adminService.activateDriver(id);
    }

    @PutMapping(value = "/admin/activateUser")
    public String activateUser(@RequestBody int id) {
        return adminService.activateUser(id);
    }

    @GetMapping(value = "/admin/events")
    public List<Event> displayEvent (){
        return eventService.displayEvent();
    }

    @PostMapping(value = "/admin/addDiscount")
    public String addDiscount(@RequestBody String area){

        return "Discount added";
    }

}