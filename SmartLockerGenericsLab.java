import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SmartLockerGenericsLab {
    record Parcel(String id, String status) {}
    record Box<T>(T value) {}

    static <T> void display(String label, T value) {
        System.out.println(label + ": " + value);
    }

    public static void main(String[] args) {
        Box<Parcel> box = new Box<>(new Parcel("PKG-301", "READY"));
        display("Generic box", box.value());

        List<Parcel> parcels = new ArrayList<>(List.of(
                new Parcel("PKG-301", "READY"),
                new Parcel("PKG-302", "COLLECTED"),
                new Parcel("PKG-303", "STORED")));
        for (Parcel parcel : parcels) {
            display("Visit", parcel);
        }

        Iterator<Parcel> iterator = parcels.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().status().equals("COLLECTED")) {
                iterator.remove();
            }
        }
        List<Integer> lockerNumbers = new ArrayList<>();
        lockerNumbers.add(12); // int is autoboxed to Integer
        int firstLocker = lockerNumbers.get(0); // Integer is unboxed to int
        System.out.println("After safe removal: " + parcels);
        System.out.println("Unboxed locker: " + firstLocker);
    }
}