package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Part;
import org.softuni.cardealer.domain.entities.Supplier;
import org.softuni.cardealer.domain.models.service.PartServiceModel;
import org.softuni.cardealer.domain.models.service.SupplierServiceModel;
import org.softuni.cardealer.repository.PartRepository;
import org.softuni.cardealer.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PartServiceTests {
    @Autowired
    private PartRepository partRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    private ModelMapper modelMapper;
    private PartService partService;
    private PartServiceModel partServiceModel;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
        this.partService = new PartServiceImpl(this.partRepository, this.modelMapper);
        SupplierServiceModel supplier = new SupplierServiceModel() {{
            setName("Misho");
            setImporter(true);
        }};
        this.supplierRepository.saveAndFlush(this.modelMapper.map(supplier, Supplier.class));

        partServiceModel = new PartServiceModel() {{
            setName("part1");
            setPrice(new BigDecimal("33.33"));
            setSupplier(modelMapper.map(supplierRepository.findAll().get(0), SupplierServiceModel.class));
        }};
    }

    //Tests with correct data:
    @Test
    public void partService_savePart_returnsCorrectPartServiceModel() {
        PartServiceModel actual = this.partService.savePart(this.partServiceModel);
        PartServiceModel expected =
                this.modelMapper.map(this.partRepository.findAll().get(0), PartServiceModel.class);
        Assert.assertEquals("Save doesn't work!", expected.getId(), actual.getId());
        Assert.assertEquals("Save doesn't work!", expected.getName(), actual.getName());
        Assert.assertEquals("Save doesn't work!", expected.getPrice(), actual.getPrice());
        Assert.assertEquals("Save doesn't work!", expected.getSupplier().getId(), actual.getSupplier().getId());
    }

    @Test
    public void partService_editPart_returnsEditedPartServiceModel() {
        this.partRepository.saveAndFlush(this.modelMapper.map(this.partServiceModel, Part.class));
        PartServiceModel edited = new PartServiceModel() {{
            setId(partRepository.findAll().get(0).getId());
            setName("part2");
            setPrice(new BigDecimal("44.44"));
        }};
        PartServiceModel actual = this.partService.editPart(edited);
        PartServiceModel expected = this.modelMapper
                .map(this.partRepository.findAll().get(0), PartServiceModel.class);
        Assert.assertEquals("Edit doesn't work!", expected.getId(), actual.getId());
        Assert.assertEquals("Edit doesn't work!", expected.getName(), actual.getName());
        Assert.assertEquals("Edit doesn't work!", expected.getPrice(), actual.getPrice());
    }

    @Test
    public void partService_deletePart_deletesEntity() {
        this.partRepository.saveAndFlush(this.modelMapper.map(this.partServiceModel, Part.class));
        PartServiceModel expected = this.modelMapper
                .map(this.partRepository.findAll().get(0), PartServiceModel.class);
        PartServiceModel actual = this.partService
                .deletePart(this.partRepository.findAll().get(0).getId());
        Assert.assertEquals("Delete doesn't work!", expected.getId(), actual.getId());
        Assert.assertEquals("Delete doesn't work!", expected.getName(), actual.getName());
        Assert.assertEquals("Delete doesn't work!", expected.getPrice(), actual.getPrice());
        Assert.assertEquals("Delete doesn't work!", expected.getSupplier().getId(), actual.getSupplier().getId());
    }

    @Test
    public void partService_findPartById_returnsSupplier() {
        this.partRepository.saveAndFlush(this.modelMapper.map(this.partServiceModel, Part.class));
        PartServiceModel expected = this.modelMapper
                .map(this.partRepository.findAll().get(0), PartServiceModel.class);
        PartServiceModel actual = this.partService
                .findPartById(this.partRepository.findAll().get(0).getId());
        Assert.assertEquals("Find doesn't work!", expected.getId(), actual.getId());
        Assert.assertEquals("Find doesn't work!", expected.getName(), actual.getName());
        Assert.assertEquals("Find doesn't work!", expected.getPrice(), actual.getPrice());
        Assert.assertEquals("Find doesn't work!", expected.getSupplier().getId(), actual.getSupplier().getId());
    }

    //Tests with incorrect data:
    @Test(expected = Exception.class)
    public void partService_savePartWithFieldsValuesNull_throwsException() {
        this.partService.savePart(new PartServiceModel());
    }

    @Test(expected = Exception.class)
    public void partService_editPartWithIdNull_throwsException() {
        this.partService.editPart(new PartServiceModel());
    }

    @Test(expected = Exception.class)
    public void partService_deletePartWithIdNull_throwsException() {
        this.partService.deletePart(null);
    }

    @Test(expected = Exception.class)
    public void partService_findPartByIdNull_throwsException() {
        PartServiceModel model = this.partService.findPartById(null);
    }

}