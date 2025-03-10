package id.ac.ui.cs.advprog.eshop.controller;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;

@Controller
@RequestMapping("/car")
class CarController extends ProductController {
    @Autowired
        private CarService carService;

    @GetMapping("/createCar")
    public String createCarPage (Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "createCar";
    }

    @PostMapping("/createCar")
    public String createCarPost (@Valid @ModelAttribute Car car, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "createCar";
        }
        carService.create(car);
        return "redirect:listCar";
    }

    @GetMapping("/listCar")
    public String carListPage (Model model) {
        List<Car> allCars = carService.findAll();
        model.addAttribute("cars", allCars);
        return "carList";
    }

    @GetMapping("/editCar/{carId}")
    public String editCarPage (@PathVariable("carId") String carId, Model model) {
        Car car = carService.findById(carId);
        model.addAttribute("car", car);
        return "editCar";
    }

    @PostMapping("/editCar")
    public String editCarPost (@Valid @ModelAttribute Car car, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editCar";
        }
        carService.update(car.getCarId(), car);
        return "redirect:listCar";
    }

    @PostMapping("/deleteCar")
    public String deleteCar (@RequestParam("carId") String carId) {
        carService.deleteCarById(carId);
        return "redirect:listCar";
    }
}