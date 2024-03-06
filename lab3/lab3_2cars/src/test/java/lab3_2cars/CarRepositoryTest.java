package lab3_2cars;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import lab3_2cars.Car;
import lab3_2cars.CarRepository;

@DataJpaTest
public class CarRepositoryTest {
    

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CarRepository carRepository;


    @Test
    public void testFindCarById(){
        Car car = new Car("Skoda","Superb");

        testEntityManager.persistAndFlush(car);

        Car foundCar = carRepository.findByCarId(car.getCarId());

        assertThat(foundCar).isNotNull();
        assertThat(foundCar.getCarId()).isEqualTo(car.getCarId());

        
    }

    @Test
    public void testFindCarByIdNull(){
        
        Car foundCar = carRepository.findByCarId(2L);

        assertThat(foundCar).isNull();
    }


    @Test
    public void testFindAllCars(){
        Car car1 = new Car("Skoda","Superb");
        Car car2 = new Car("Alfa Romeo", "Giulia");
        Car car3 = new Car ("Mclaren", "765LT");


        testEntityManager.persistAndFlush(car1);
        testEntityManager.persistAndFlush(car2);
        testEntityManager.persistAndFlush(car3);

        List<Car> cars = carRepository.findAll();


        assertThat(cars).isNotNull();
        assertThat(cars).hasSize(3).extracting(Car::getMaker).containsOnly(car1.getMaker(), car2.getMaker(), car3.getMaker());
    }
}
