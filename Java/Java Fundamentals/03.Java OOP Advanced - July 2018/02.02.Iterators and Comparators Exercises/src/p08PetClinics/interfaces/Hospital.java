package p08PetClinics.interfaces;

public interface Hospital {
    String getName();

    int getRoomsCount();

    boolean add(Animal pet);

    boolean release();

    boolean hasEmptyRooms();

    void print();

    void printRoom(int room);
}
