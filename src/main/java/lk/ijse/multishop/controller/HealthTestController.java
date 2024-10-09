package lk.ijse.multishop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/healthTest")
@RequiredArgsConstructor
public class HealthTestController {

    @GetMapping
    public String healthChecker(){ return "MultiShop App Running Successfully !! ";}

}
