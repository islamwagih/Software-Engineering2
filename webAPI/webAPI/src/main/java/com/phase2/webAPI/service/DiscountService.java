package com.phase2.webAPI.service;

import com.phase2.webAPI.entity.Discounts;
import com.phase2.webAPI.entity.Ride;
import com.phase2.webAPI.entity.User;
import com.phase2.webAPI.repositories.DiscountsRepository;
import com.phase2.webAPI.repositories.RideRepository;
import com.phase2.webAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DiscountService {

    @Autowired
    private DiscountsRepository discountsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RideRepository rideRepository;

    public ArrayList<Double> checkDiscounts(Ride ride){
        ArrayList<Double> discounts = new ArrayList<>();
        User user = userRepository.findAllByUserName(ride.getUser());
        discounts.add(checkFirstRide(user.getUserName()));
        discounts.add(checkAreaDiscount(ride.getDestination()));
        discounts.add(checkPassengers(ride));
        discounts.add(checkPublicHolidays(java.time.LocalDate.now()));
        discounts.add(checkBirthday(user));
        return discounts;
    }

    public Double checkFirstRide(String user){
        if (!rideRepository.existsByUser(user)){
            return 0.1;
        }
        return 0.0;
    }

    public double checkAreaDiscount(String area){
        if(discountsRepository.existsByArea(area)){
            return 0.1;
        }
        return 0.0;
    }

    public double checkPassengers(Ride ride){
        if (ride.getPassengersNum() > 1){
            return 0.05;
        }
        return 0.0;
    }

    public double checkPublicHolidays(LocalDate date){
        if(discountsRepository.existsByTime(date)){
            return 0.05;
        }
        return 0.0;
    }

    public double checkBirthday(User user){
        if (user.getBirthDate().equals(java.time.LocalDate.now())){
            return 0.1;
        }
        return 0.0;
    }

    public Double getDiscoundByDate(LocalDate date) {
        double discount = 0;
        List<Discounts> found = discountsRepository.findAllByTime(date);
        for (Discounts discounts : found) {
            discount += discounts.getDiscounts();
        }
        return discount / found.size();
    }

    public Double getDiscountByArea(String area) {
        double discount = 0;
        List<Discounts> found = discountsRepository.findAllByArea(area);
        for (Discounts discounts : found) {
            discount += discounts.getDiscounts();
        }
        return discount / found.size();
    }

    public Double getAllDiscounts(LocalDate date, String area) {
        return (this.getDiscountByArea(area) + this.getDiscoundByDate(date));
    }

    public String addDiscountByDate(Discounts discounts) {
        discountsRepository.save(discounts);
        return "discount by date added";
    }

    public String addDiscountByArea(Discounts discounts) {
        discountsRepository.save(discounts);
        return "discount by time added";
    }
}