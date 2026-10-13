import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class SmartLockerOptionalFileStreamLab {
    record Parcel(String id, String status, String zone) {}

    public static void main(String[] args) throws Exception {
        Path file = Path.of("data", "co6-parcels.csv");
        Files.createDirectories(file.getParent());
        Files.write(file, List.of("PKG-601,READY,A", "bad-row", "PKG-602,STORED,B", "PKG-603,READY,A"));

        List<Parcel> parcels;
        try (Stream<String> lines = Files.lines(file)) {
            parcels = lines.map(String::trim).filter(line -> line.split(",", -1).length == 3)
                    .map(line -> line.split(",", -1))
                    .map(p -> new Parcel(p[0], p[1], p[2])).toList();
        }
        Optional<Parcel> found = parcels.stream().filter(p -> p.id().equals("PKG-603")).findFirst();
        Optional<Parcel> missing = parcels.stream().filter(p -> p.id().equals("PKG-999")).findFirst();
        long loopCount = 0;
        for (Parcel parcel : parcels) if (parcel.status().equals("READY")) loopCount++;
        long streamCount = parcels.stream().filter(p -> p.status().equals("READY")).count();

        System.out.println("Valid parcels: " + parcels.size());
        found.ifPresent(p -> System.out.println("Found: " + p));
        System.out.println("Missing fallback: " + missing.map(Parcel::id).orElse("NOT FOUND"));
        System.out.println("Loop READY: " + loopCount);
        System.out.println("Stream READY: " + streamCount);
        System.out.println("Results agree: " + (loopCount == streamCount));
    }
}s