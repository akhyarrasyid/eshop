package id.ac.ui.cs.advprog.eshop.repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import id.ac.ui.cs.advprog.eshop.model.Car;

@Repository
public class CarRepository {
    static int id = 0;
    private List<Car> carData = new ArrayList<>();

    public Car create(Car car) {
        if (car.getCarId() == null) {
            car.setCarId(generateId());
        }
        carData.add(car);
        return car;
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    public Iterator<Car> findAll() {
        return carData.iterator();
    }

    public Car findById(String id) {
        for (Car car : carData) {
            if (car.getCarId().equals(id)) {
                return car;
            }
        }
        return null;
    }

    public Car update(String id, Car updatedCar) {
        Car car = findById(id);
        if (car != null) {
            updateCarProperties(car, updatedCar);
            return car;
        }
        return null;
    }

    private void updateCarProperties(Car car, Car updatedCar) {
        car.setCarName(updatedCar.getCarName());
        car.setCarColor(updatedCar.getCarColor());
        car.setCarQuantity(updatedCar.getCarQuantity());
    }

    public void delete(String id) { carData.removeIf(car -> car.getCarId().equals(id)); }
}