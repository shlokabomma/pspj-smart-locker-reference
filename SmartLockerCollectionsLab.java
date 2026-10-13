import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

public class SmartLockerCollectionsLab {
    public static void main(String[] args) {
        Set<String> parcelIds = new HashSet<>();
        System.out.println("Add PKG-201: " + parcelIds.add("PKG-201"));
        System.out.println("Add duplicate PKG-201: " + parcelIds.add("PKG-201"));

        Map<String, String> parcelById = new HashMap<>();
        parcelById.put("PKG-201", "Asha - READY");
        parcelById.put("PKG-202", "Ravi - STORED");
        System.out.println("Lookup PKG-202: " + parcelById.get("PKG-202"));
        System.out.println("Has PKG-999: " + parcelById.containsKey("PKG-999"));

        Map<Integer, String> lockerReport = new TreeMap<>();
        lockerReport.put(12, "PKG-202");
        lockerReport.put(5, "PKG-201");
        System.out.println("Lockers in order: " + lockerReport);

        Queue<String> pickupQueue = new ArrayDeque<>();
        pickupQueue.offer("PKG-201");
        pickupQueue.offer("PKG-202");
        System.out.println("Next pickup: " + pickupQueue.peek());
        System.out.println("Collected: " + pickupQueue.poll());
        System.out.println("Queue now: " + pickupQueue);
    }
}