package cars.services;

import cars.dto.bindings.CarImportDto;
import cars.dto.views.car.CarToyotaViewDto;
import cars.dto.views.car.CarWithPartsJsonViewDto;
import cars.dto.views.car.CarWithPartsXmlViewDto;
import cars.entities.Car;

import java.util.List;

public interface CarService {

    List<Car> findAll();

    Car findById(long id);

    List<CarToyotaViewDto> getCarsFromMakeToyota(String make);

    List<CarWithPartsXmlViewDto> getCarsWithPartsXml();

    List<CarWithPartsJsonViewDto> getCarsWithPartsJson();

    Car createOne(Car car);

    List<Car> createMany(List<CarImportDto> carImportDtos);

    Car updateOne(Car car);

    List<Car> updateMany(Iterable<Car> cars);

    void deleteById(long id);

    void deleteByCar(Car car);
}
