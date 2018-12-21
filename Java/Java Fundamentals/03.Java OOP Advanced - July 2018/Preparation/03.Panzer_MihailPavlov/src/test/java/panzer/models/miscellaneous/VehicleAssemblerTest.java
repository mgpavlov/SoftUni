package panzer.models.miscellaneous;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import panzer.contracts.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

public class VehicleAssemblerTest {
    private Assembler assembler;

    @Before
    public void setUp() throws Exception {
        assembler = new VehicleAssembler();

        Part part1 = Mockito.mock(AttackModifyingPart.class);
        Part part2 = Mockito.mock(DefenseModifyingPart.class);
        Part part3 = Mockito.mock(HitPointsModifyingPart.class);

        Mockito.when(part1.getPrice()).thenReturn(BigDecimal.valueOf(Long.MAX_VALUE));
        Mockito.when(part2.getPrice()).thenReturn(BigDecimal.valueOf(Long.MAX_VALUE));
        Mockito.when(part3.getPrice()).thenReturn(BigDecimal.valueOf(Long.MAX_VALUE));

        Mockito.when(part1.getWeight()).thenReturn(10.00);
        Mockito.when(part2.getWeight()).thenReturn(11.00);
        Mockito.when(part3.getWeight()).thenReturn(12.00);

        Mockito.when(part1.getModel()).thenReturn("A");
        Mockito.when(part2.getModel()).thenReturn("B");
        Mockito.when(part3.getModel()).thenReturn("C");

        Mockito.when(((AttackModifyingPart) part1).getAttackModifier()).thenReturn(10);
        Mockito.when(((DefenseModifyingPart) part2).getDefenseModifier()).thenReturn(10);
        Mockito.when(((HitPointsModifyingPart) part3).getHitPointsModifier()).thenReturn(10);


        this.assembler.addArsenalPart(part1);
        this.assembler.addShellPart(part2);
        this.assembler.addEndurancePart(part3);


    }


    @Test
    public void getTotalWeight() {
        Assert.assertEquals(this.assembler.getTotalWeight(), 33, 0.1);
        Assert.assertEquals(this.assembler.getTotalPrice(), BigDecimal.valueOf(Long.MAX_VALUE).multiply(BigDecimal.valueOf(3)));
        Assert.assertEquals(this.assembler.getTotalAttackModification(), 10);
        Assert.assertEquals(this.assembler.getTotalDefenseModification(), 10);
        Assert.assertEquals(this.assembler.getTotalHitPointModification(), 10);
    }

    @Test
    public void getTotalPrice() {
    }

    @Test
    public void getTotalAttackModification() {
    }

    @Test
    public void getTotalDefenseModification() {
    }

    @Test
    public void getTotalHitPointModification() {
    }

    @Test
    public void addArsenalPart() throws NoSuchFieldException, IllegalAccessException {
        Field arsenalPartsField = this.assembler.getClass().getDeclaredField("arsenalParts");
        arsenalPartsField.setAccessible(true);
        List<AttackModifyingPart> arsenalParts = (List<AttackModifyingPart>) arsenalPartsField.get(this.assembler);

        Assert.assertEquals(1, arsenalParts.size());
    }

    @Test
    public void addShellPart() throws NoSuchFieldException, IllegalAccessException {
        Field shellPartsField = this.assembler.getClass().getDeclaredField("shellParts");
        shellPartsField.setAccessible(true);
        List<DefenseModifyingPart> shellParts = (List<DefenseModifyingPart>) shellPartsField.get(this.assembler);

        Assert.assertEquals(1, shellParts.size());
    }

    @Test
    public void addEndurancePart() throws NoSuchFieldException, IllegalAccessException {
        Field endurancePartsField = this.assembler.getClass().getDeclaredField("enduranceParts");
        endurancePartsField.setAccessible(true);
        List<AttackModifyingPart> enduranceParts = (List<AttackModifyingPart>) endurancePartsField.get(this.assembler);

        Assert.assertEquals(1, enduranceParts.size());
    }

    @Test
    public void addArsenalPart2() throws NoSuchFieldException, IllegalAccessException {
        AttackModifyingPart mockAttack = Mockito.mock(AttackModifyingPart.class);
        Mockito.when(mockAttack.getAttackModifier()).thenReturn(Integer.MAX_VALUE);
        this.assembler.addArsenalPart(mockAttack);
        Assert.assertEquals((10L + Integer.MAX_VALUE), this.assembler.getTotalAttackModification());
    }

    @Test
    public void addShellPart2() throws NoSuchFieldException, IllegalAccessException {
        DefenseModifyingPart defenseModifyingPart = Mockito.mock(DefenseModifyingPart.class);
        Mockito.when(defenseModifyingPart.getDefenseModifier()).thenReturn(Integer.MAX_VALUE);
        this.assembler.addShellPart(defenseModifyingPart);
        Assert.assertEquals(10L + Integer.MAX_VALUE, this.assembler.getTotalDefenseModification());
    }

    @Test
    public void addEndurancePart2() throws NoSuchFieldException, IllegalAccessException {
        HitPointsModifyingPart hitPointsModifyingPart = Mockito.mock(HitPointsModifyingPart.class);
        Mockito.when(hitPointsModifyingPart.getHitPointsModifier()).thenReturn(Integer.MAX_VALUE);
        this.assembler.addEndurancePart(hitPointsModifyingPart);
        Assert.assertEquals(10L + Integer.MAX_VALUE, this.assembler.getTotalHitPointModification());
    }
}