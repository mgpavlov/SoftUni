package panzer.models.miscellaneous;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import panzer.contracts.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

public class VehicleAssemblerTest {

    private Assembler vehicleAssembler;

    private AttackModifyingPart arsenal;
    private DefenseModifyingPart shell;
    private HitPointsModifyingPart endurance;


    @Before
    public void setUp() throws Exception {

        this.vehicleAssembler = new VehicleAssembler();

        this.arsenal = Mockito.mock(AttackModifyingPart.class);
        this.shell = Mockito.mock(DefenseModifyingPart.class);
        this.endurance = Mockito.mock(HitPointsModifyingPart.class);

        this.vehicleAssembler.addArsenalPart(this.arsenal);
        this.vehicleAssembler.addShellPart(this.shell);
        this.vehicleAssembler.addEndurancePart(this.endurance);
    }

    @Test
    public void getTotalWeight() {
        Mockito.when(this.arsenal.getWeight()).thenReturn(10.0);
        Mockito.when(this.shell.getWeight()).thenReturn(20.0);
        Mockito.when(this.endurance.getWeight()).thenReturn(30.0);
        double actual = this.vehicleAssembler.getTotalWeight();
        double expected = 60.0;
        Assert.assertEquals(expected, actual, 0.1);
    }

    @Test
    public void getTotalPrice() {
        Mockito.when(this.arsenal.getPrice()).thenReturn(BigDecimal.valueOf(10));
        Mockito.when(this.shell.getPrice()).thenReturn(BigDecimal.valueOf(20));
        Mockito.when(this.endurance.getPrice()).thenReturn(BigDecimal.valueOf(30));

        BigDecimal actual = this.vehicleAssembler.getTotalPrice();
        BigDecimal expected = BigDecimal.valueOf(60);
        Assert.assertEquals(expected, actual);
        Mockito.when(this.arsenal.getPrice()).thenReturn(BigDecimal.valueOf(0));
        Mockito.when(this.shell.getPrice()).thenReturn(BigDecimal.valueOf(0));
        Mockito.when(this.endurance.getPrice()).thenReturn(BigDecimal.valueOf(0));

        actual = this.vehicleAssembler.getTotalPrice();
        expected = BigDecimal.valueOf(0);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getTotalAttackModification() {
        AttackModifyingPart arsenalPart = Mockito.mock(AttackModifyingPart.class);
        this.vehicleAssembler.addArsenalPart(arsenalPart);
        Mockito.when(arsenalPart.getAttackModifier()).thenReturn(10);
        Mockito.when(this.arsenal.getAttackModifier()).thenReturn(20);

        long actual = this.vehicleAssembler.getTotalAttackModification();
        long expected = 30;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getTotalDefenseModification() {
        DefenseModifyingPart shellPart = Mockito.mock(DefenseModifyingPart.class);
        this.vehicleAssembler.addShellPart(shellPart);
        Mockito.when(shellPart.getDefenseModifier()).thenReturn(10);
        Mockito.when(this.shell.getDefenseModifier()).thenReturn(20);

        long actual = this.vehicleAssembler.getTotalDefenseModification();
        long expected = 30;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getTotalHitPointModification() {
        HitPointsModifyingPart endurancePart = Mockito.mock(HitPointsModifyingPart.class);
        this.vehicleAssembler.addEndurancePart(endurancePart);
        Mockito.when(endurancePart.getHitPointsModifier()).thenReturn(10);
        Mockito.when(this.endurance.getHitPointsModifier()).thenReturn(20);

        long actual = this.vehicleAssembler.getTotalHitPointModification();
        long expected = 30;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addArsenalPart() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        /*Class<?> rf = VehicleAssembler.class;
        Constructor constructor = rf.getConstructor();
        Field[] declaredFields = rf.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
        }
        vehicleAssembler = (VehicleAssembler) constructor.newInstance();
        AttackModifyingPart arsenalPart = Mockito.mock(AttackModifyingPart.class);

        List<Part> partsArsenal = (List<Part>) declaredFields[0].get(this.vehicleAssembler);
        int actual = partsArsenal.size();
        int expected = 0;

        Assert.assertEquals(expected, actual);
        this.vehicleAssembler.addArsenalPart(arsenalPart);
        this.vehicleAssembler.addArsenalPart(this.arsenal);
        actual = partsArsenal.size();
        expected = 2;
        Assert.assertEquals(expected, actual);*/
        AttackModifyingPart arsenalPart = Mockito.mock(AttackModifyingPart.class);
        Mockito.when(arsenalPart.getAttackModifier()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(this.arsenal.getAttackModifier()).thenReturn(Integer.MAX_VALUE);


        this.vehicleAssembler.addArsenalPart(arsenalPart);

        long actual = this.vehicleAssembler.getTotalAttackModification();
        long expected = (long) Integer.MAX_VALUE + Integer.MAX_VALUE;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addShellPart() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, InvocationTargetException {
        /*Class<?> rf = VehicleAssembler.class;
        Constructor constructor = rf.getConstructor();
        Field[] declaredFields = rf.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
        }
        vehicleAssembler = (VehicleAssembler) constructor.newInstance();
        DefenseModifyingPart shellPart = Mockito.mock(DefenseModifyingPart.class);
        this.vehicleAssembler.addShellPart(shellPart);
        this.vehicleAssembler.addShellPart(this.shell);



        List<Part> partsShell = (List<Part>) declaredFields[1].get(this.vehicleAssembler);
        int actual = partsShell.size();

        int expected = 2;

        Assert.assertEquals(expected, actual);*/
        DefenseModifyingPart shellPart = Mockito.mock(DefenseModifyingPart.class);
        Mockito.when(shellPart.getDefenseModifier()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(this.shell.getDefenseModifier()).thenReturn(Integer.MAX_VALUE);


        this.vehicleAssembler.addShellPart(shellPart);

        long actual = this.vehicleAssembler.getTotalDefenseModification();
        long expected = (long) Integer.MAX_VALUE + Integer.MAX_VALUE;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addEndurancePart() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        /*Class<?> rf = VehicleAssembler.class;
        Constructor constructor = rf.getConstructor();
        Field[] declaredFields = rf.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
        }
        vehicleAssembler = (VehicleAssembler) constructor.newInstance();
        HitPointsModifyingPart endurancePart = Mockito.mock(HitPointsModifyingPart.class);
        this.vehicleAssembler.addEndurancePart(endurancePart);
        this.vehicleAssembler.addEndurancePart(this.endurance);



        List<Part> partaEndurance = (List<Part>) declaredFields[2].get(this.vehicleAssembler);
        int actual = partaEndurance.size();

        int expected = 2;

        Assert.assertEquals(expected, actual);*/

        HitPointsModifyingPart endurancePart = Mockito.mock(HitPointsModifyingPart.class);
        Mockito.when(endurancePart.getHitPointsModifier()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(this.endurance.getHitPointsModifier()).thenReturn(Integer.MAX_VALUE);


        this.vehicleAssembler.addEndurancePart(endurancePart);

        long actual = this.vehicleAssembler.getTotalHitPointModification();
        long expected = (long) Integer.MAX_VALUE + Integer.MAX_VALUE;
        Assert.assertEquals(expected, actual);
    }
}