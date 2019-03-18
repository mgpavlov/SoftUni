package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Supplier;
import org.softuni.cardealer.domain.models.service.SupplierServiceModel;
import org.softuni.cardealer.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SupplierServiceTests {
    @Autowired
    private SupplierRepository supplierRepository;
    private ModelMapper modelMapper;
    private SupplierService supplierService;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
        this.supplierService = new SupplierServiceImpl(this.supplierRepository, this.modelMapper);
    }

    //Tests with correct data:
    @Test
    public void supplierService_saveSupplier_returnsSupplierServiceModel() {
        SupplierServiceModel model = new SupplierServiceModel() {{
            setName("Misho");
            setImporter(true);
        }};
        SupplierServiceModel actual = this.supplierService.saveSupplier(model);
        SupplierServiceModel expected =
                this.modelMapper.map(this.supplierRepository.findAll().get(0), SupplierServiceModel.class);
        Assert.assertEquals("Save doesn't work!", expected.getId(), actual.getId());
        Assert.assertEquals("Save doesn't work!", expected.getName(), actual.getName());
        Assert.assertEquals("Save doesn't work!", expected.isImporter(), actual.isImporter());
    }

    @Test
    public void supplierService_editSupplier_returnsEditedSupplierServiceModel() {
        SupplierServiceModel toBeSaved = new SupplierServiceModel() {{
            setName("Pesho");
            setImporter(true);
        }};
        this.supplierRepository.saveAndFlush(this.modelMapper.map(toBeSaved, Supplier.class));
        SupplierServiceModel model = new SupplierServiceModel() {{
            setId(supplierRepository.findAll().get(0).getId());
            setName("Gosho");
            setImporter(false);
        }};
        SupplierServiceModel actual = this.supplierService.editSupplier(model);
        SupplierServiceModel expected = this.modelMapper
                .map(this.supplierRepository.findAll().get(0), SupplierServiceModel.class);
        Assert.assertEquals("Edit doesn't work!", expected.getId(), actual.getId());
        Assert.assertEquals("Edit doesn't work!", expected.getName(), actual.getName());
        Assert.assertEquals("Edit doesn't work!", expected.isImporter(), actual.isImporter());
    }

    @Test
    public void supplierService_deleteSupplier_deletesSupplierSuccessful() {
        SupplierServiceModel toBeSaved = new SupplierServiceModel() {{
            setName("Misho");
            setImporter(true);
        }};
        this.supplierRepository.saveAndFlush(this.modelMapper.map(toBeSaved, Supplier.class));
        SupplierServiceModel expected = this.modelMapper
                .map(this.supplierRepository.findAll().get(0), SupplierServiceModel.class);
        SupplierServiceModel actual = this.supplierService
                .deleteSupplier(this.supplierRepository.findAll().get(0).getId());
        Assert.assertEquals("Delete doesn't work!", expected.getId(), actual.getId());
        Assert.assertEquals("Delete doesn't work!", expected.getName(), actual.getName());
        Assert.assertEquals("Delete doesn't work!", expected.isImporter(), actual.isImporter());
    }

    @Test
    public void supplierService_findSupplierById_returnsSupplier() {
        SupplierServiceModel toBeSaved = new SupplierServiceModel() {{
            setName("Misho");
            setImporter(true);
        }};
        this.supplierRepository.saveAndFlush(this.modelMapper.map(toBeSaved, Supplier.class));
        SupplierServiceModel expected = this.modelMapper
                .map(this.supplierRepository.findAll().get(0), SupplierServiceModel.class);
        SupplierServiceModel actual = this.supplierService
                .findSupplierById(this.supplierRepository.findAll().get(0).getId());
        Assert.assertEquals("Find doesn't work!", expected.getId(), actual.getId());
        Assert.assertEquals("Find doesn't work!", expected.getName(), actual.getName());
        Assert.assertEquals("Find doesn't work!", expected.isImporter(), actual.isImporter());
    }

    //Tests with incorrect data:
    @Test(expected = Exception.class)
    public void supplierService_saveSupplierWithFieldsValuesNull_throwsException(){
        this.supplierService.saveSupplier(new SupplierServiceModel());
    }

    @Test(expected = Exception.class)
    public void supplierService_editSupplierWithIdNull_throwsException() {
        this.supplierService.editSupplier(new SupplierServiceModel());
    }

    @Test(expected = Exception.class)
    public void supplierService_deleteSupplierWithIdNull_throwsException() {
        this.supplierService.editSupplier(new SupplierServiceModel());
    }

    @Test(expected = Exception.class)
    public void supplierService_findSupplierByIdNull_throwsException() {
        SupplierServiceModel model = this.supplierService.findSupplierById(null);
    }
}
