package cars.services;

import cars.dto.bindings.CarImportDto;
import cars.dto.utilities.PartIdDto;
import cars.dto.views.car.*;
import cars.entities.Car;
import cars.entities.Part;
import cars.repositories.CarRepository;
import cars.repositories.PartRepository;
import cars.utilities.MapperConverter;
import cars.utilities.RandomNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final PartRepository partRepository;
    private final MapperConverter mapperConverter;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository, MapperConverter mapperConverter) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.mapperConverter = mapperConverter;
    }

    @Override
    public List<Car> findAll() {
        return this.carRepository.findAll();
    }

    @Override
    public Car findById(long id) {
        return this.carRepository.findById(id).orElse(null);
    }

    @Override
    public List<CarToyotaViewDto> getCarsFromMakeToyota(String make) {
        List<Car> cars = this.carRepository.getCarsByMakeOrderByModelAscTravelledDistanceDesc(make);
        return Arrays.asList(this.mapperConverter.convert(cars, CarToyotaViewDto[].class));
    }

    @Override
    public List<CarWithPartsXmlViewDto> getCarsWithPartsXml() {
        List<Car> cars = this.carRepository.findAll();
        CarViewAllInfoDto[] carViewAllInfoDtos = this.mapperConverter.convert(cars, CarViewAllInfoDto[].class);
        List<CarWithPartsXmlViewDto> carWithPartsViewDtos = new ArrayList<>();

        for (CarViewAllInfoDto carViewAllInfoDto : carViewAllInfoDtos) {
            CarWithPartsXmlViewDto carWithPartsViewDto = new CarWithPartsXmlViewDto();

            carWithPartsViewDto.setMake(carViewAllInfoDto.getMake());
            carWithPartsViewDto.setModel(carViewAllInfoDto.getModel());
            carWithPartsViewDto.setTravelledDistance(carViewAllInfoDto.getTravelledDistance());
            carWithPartsViewDto.setParts(carViewAllInfoDto.getParts());

            carWithPartsViewDtos.add(carWithPartsViewDto);
        }
        return carWithPartsViewDtos;
    }

    @Override
    public List<CarWithPartsJsonViewDto> getCarsWithPartsJson() {
        List<Car> cars = this.carRepository.findAll();
        CarViewAllInfoDto[] carViewAllInfoDtos = this.mapperConverter.convert(cars, CarViewAllInfoDto[].class);
        List<CarWithPartsJsonViewDto> carWithPartsViewDtos = new ArrayList<>();

        for (CarViewAllInfoDto carViewAllInfoDto : carViewAllInfoDtos) {
            CarWithPartsJsonViewDto carWithPartsViewDto = new CarWithPartsJsonViewDto();
            CarViewDto carViewDto = new CarViewDto();

            carViewDto.setMake(carViewAllInfoDto.getMake());
            carViewDto.setModel(carViewAllInfoDto.getModel());
            carViewDto.setTravelledDistance(carViewAllInfoDto.getTravelledDistance());

            carWithPartsViewDto.setCar(carViewDto);
            carWithPartsViewDto.setParts(carViewAllInfoDto.getParts());

            carWithPartsViewDtos.add(carWithPartsViewDto);
        }
        return carWithPartsViewDtos;
    }

    @Override
    public Car createOne(Car car) {
        return this.carRepository.save(car);
    }

    @Override
    public List<Car> createMany(List<CarImportDto> carImportDtos) {
        List<Part> parts = this.partRepository.findAll();
        PartIdDto[] partIdDtos = this.mapperConverter.convert(parts, PartIdDto[].class);

        for (CarImportDto carImportDto : carImportDtos) {
            Set<PartIdDto> partIdDtosInner = new HashSet<>();
            for (int i = 0; i < RandomNumber.getRandomNumber(10, 20); i++) {
                partIdDtosInner.add(partIdDtos[RandomNumber.getRandomNumber(partIdDtos.length - 1)]);
            }
            carImportDto.setParts(partIdDtosInner);
        }

        Car[] cars = this.mapperConverter.convert(carImportDtos, Car[].class);
        for (Car car : cars) {
            this.carRepository.save(car);
        }
        return Arrays.asList(cars);
    }

    @Override
    public Car updateOne(Car car) {
        return this.carRepository.save(car);
    }

    @Override
    public List<Car> updateMany(Iterable<Car> cars) {
        for (Car car : cars) {
            this.carRepository.save(car);
        }
        return (List<Car>)cars;
    }

    @Override
    @Modifying
    public void deleteById(long id) {
        this.carRepository.deleteById(id);
    }

    @Override
    @Modifying
    public void deleteByCar(Car car) {
        this.carRepository.delete(car);
    }

}
