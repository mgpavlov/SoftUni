package _03_Inheritance.LAB._05_Random_Array_List;

import java.util.ArrayList;
import java.util.Random;

public class RandomArrayList extends ArrayList
{
    public Object getRandomElement() {
        Random rnd = new Random();

        return super.remove(rnd.nextInt(super.size()));
    }
}
