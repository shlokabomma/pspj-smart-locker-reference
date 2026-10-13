import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SmartLockerStreamsLab {
    record Parcel(String id, String status, String zone, double weight) {}

    public static void main(String[] args) {
        List<Parcel> parcels = List.of(
                new Parcel("PKG-501", "READY", "A", 2.5),
                new Parcel("PKG-502", "STORED", "B", 1.0),
                new Parcel("PKG-503", "READY", "A", 3.5),
                new Parcel("PKG-504", "READY", "B", 2.0));

        List<String> readyIds = parcels.stream()
                .filter(p -> p.status().equals("READY"))
                .sorted((a, b) -> Double.compare(b.weight(), a.weight()))
                .map(Parcel::id)
                .toList();
        long readyCount = parcels.stream().filter(p -> p.status().equals("READY")).count();
        double readyWeight = parcels.stream().filter(p -> p.status().equals("READY"))
                .map(Parcel::weight).reduce(0.0, Double::sum);
        Map<String, Long> byZone = parcels.stream()
                .collect(Collectors.groupingBy(Parcel::zone, Collectors.counting()));
        String joined = parcels.stream().map(Parcel::id).collect(Collectors.joining(", "));

        System.out.println("Ready ranked: " + readyIds);
        System.out.println("Ready count: " + readyCount);
        System.out.println("Ready weight: " + readyWeight);
        System.out.println("By zone: " + byZone);
        System.out.println("All IDs: " + joined);
        System.out.println("Source size unchanged: " + parcels.size());
    }
}