package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class CarControllerTest {

    @Mock
    private CarServiceImpl carService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private CarController carController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCarPage() {
        String viewName = carController.createCarPage(model);
        
        verify(model).addAttribute(eq("car"), any(Car.class));
        assertEquals("createCar", viewName);
    }

    @Test
    void testCreateCarPostSuccess() {
        Car car = new Car();
        car.setCarName("Honda Civic");
        car.setCarColor("Blue");
        car.setCarQuantity(3);
        
        when(bindingResult.hasErrors()).thenReturn(false);
        
        String viewName = carController.createCarPost(car, bindingResult, model);
        
        verify(carService).create(car);
        assertEquals("redirect:listCar", viewName);
    }

    @Test
    void testCreateCarPostWithErrors() {
        Car car = new Car();
        
        when(bindingResult.hasErrors()).thenReturn(true);
        
        String viewName = carController.createCarPost(car, bindingResult, model);
        
        verify(carService, never()).create(any(Car.class));
        assertEquals("createCar", viewName);
    }

    @Test
    void testCarListPage() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car());
        
        when(carService.findAll()).thenReturn(cars);
        
        String viewName = carController.carListPage(model);
        
        verify(model).addAttribute("cars", cars);
        assertEquals("carList", viewName);
    }

    @Test
    void testEditCarPage() {
        String carId = "car-123";
        Car car = new Car();
        car.setCarId(carId);
        
        when(carService.findById(carId)).thenReturn(car);
        
        String viewName = carController.editCarPage(carId, model);
        
        verify(model).addAttribute("car", car);
        assertEquals("editCar", viewName);
    }

    @Test
    void testEditCarPostSuccess() {
        Car car = new Car();
        car.setCarId("car-123");
        car.setCarName("Updated Honda");
        car.setCarColor("Green");
        car.setCarQuantity(5);
        
        when(bindingResult.hasErrors()).thenReturn(false);
        
        String viewName = carController.editCarPost(car, bindingResult);
        
        verify(carService).update(car.getCarId(), car);
        assertEquals("redirect:listCar", viewName);
    }

    @Test
    void testEditCarPostWithErrors() {
        Car car = new Car();
        car.setCarId("car-123");
        
        when(bindingResult.hasErrors()).thenReturn(true);
        
        String viewName = carController.editCarPost(car, bindingResult);
        
        verify(carService, never()).update(anyString(), any(Car.class));
        assertEquals("editCar", viewName);
    }

    @Test
    void testDeleteCar() {
        String carId = "car-123";
        
        String viewName = carController.deleteCar(carId);
        
        verify(carService).deleteCarById(carId);
        assertEquals("redirect:listCar", viewName);
    }
}