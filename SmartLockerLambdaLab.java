import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class SmartLockerLambdaLab {
    record Parcel(String id, String status, int priority) {}
    @FunctionalInterface interface Rule<T> { boolean check(T value); }
    static void printParcel(Parcel parcel) { System.out.println("Display: " + parcel); }

    public static void main(String[] args) {
        List<Parcel> parcels = List.of(
                new Parcel("PKG-401", "READY", 2),
                new Parcel("PKG-402", "STORED", 1),
                new Parcel("PKG-403", "READY", 1));
        Rule<Parcel> valid = p -> !p.id().isBlank() && p.priority() > 0;
        Predicate<Parcel> ready = p -> p.status().equals("READY");
        Predicate<Parcel> urgentReady = ready.and(p -> p.priority() == 1);
        Function<Parcel, String> label = p -> p.id() + "-" + p.status();
        Consumer<Parcel> display = SmartLockerLambdaLab::printParcel;
        Supplier<String> nextId = () -> "PKG-404";
        Comparator<Parcel> order = Comparator.comparingInt(Parcel::priority).thenComparing(Parcel::id);

        System.out.println("Valid first: " + valid.check(parcels.get(0)));
        parcels.stream().filter(urgentReady).sorted(order).forEach(display);
        System.out.println("Label: " + label.apply(parcels.get(0)));
        System.out.println("Next ID: " + nextId.get());
    }
}