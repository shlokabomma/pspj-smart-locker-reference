import java.util.ArrayList;
import java.util.List;

public class SmartLockerArrayListLab {
    record Parcel(String id, String status) {}

    static void show(List<Parcel> parcels, String label) {
        System.out.println(label + ": " + parcels);
    }

    public static void main(String[] args) {
        List<Parcel> parcels = new ArrayList<>();
        parcels.add(new Parcel("PKG-101", "READY"));
        parcels.add(new Parcel("PKG-103", "STORED"));
        parcels.add(1, new Parcel("PKG-102", "READY"));
        show(parcels, "After add");

        System.out.println("Index 1: " + parcels.get(1));
        parcels.set(2, new Parcel("PKG-103", "READY"));
        System.out.println("Removed: " + parcels.remove(0));
        show(parcels, "Final");
        System.out.println("Size: " + parcels.size() + ", empty: " + parcels.isEmpty());

        int requestedIndex = 5;
        if (requestedIndex >= 0 && requestedIndex < parcels.size()) {
            System.out.println(parcels.get(requestedIndex));
        } else {
            System.out.println("Index 5 is invalid; valid range is 0 to " + (parcels.size() - 1));
        }
    }
}