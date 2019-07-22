package DeliveryManSystem.DatabaseSystem.Tables;

/**
 * This class represents the 'pickuppoints' database table
 *
 * @author Gruppo D19
 * @version 1.0.0
 */

public class PickupPointTable {

    private String id;
    private String location;
    private String ip;
    private String small_boxes;
    private String medium_boxes;
    private String large_boxes;
    private String address;

    /**
     * The constructor. It sets the pickup point ID, its
     * location, its IP address, the number of small,
     * medium and large boxes it hosts, and its address
     *
     * @param id The pickup point ID
     * @param location The pickup point location
     * @param ip The pickup point IP address
     * @param small_boxes The number of small boxes it hosts
     * @param medium_boxes The number of medium boxes it hosts
     * @param large_boxes The number of large boxes it hosts
     * @param address The pickup point address
     */

    public PickupPointTable(String id, String location, String ip, String small_boxes, String medium_boxes, String large_boxes, String address) {
        this.id = id;
        this.location = location;
        this.ip = ip;
        this.small_boxes = small_boxes;
        this.medium_boxes = medium_boxes;
        this.large_boxes = large_boxes;
        this.address = address;
    }

    /**
     * This method returns the pickup point ID
     *
     * @return The pickup point ID
     */

    public String getId() {
        return id;
    }

    /**
     * This method returns the pickup point location
     *
     * @return The pickup point location
     */

    public String getLocation() {
        return location;
    }

    /**
     * This method returns the pickup point IP address
     *
     * @return The pickup point IP address
     */

    public String getIp() {
        return ip;
    }

    /**
     * This method returns the number of small boxes the
     * pickup point hosts
     *
     * @return The number of small boxes the pickup point hosts
     */

    public String getSmall_boxes() {
        return small_boxes;
    }

    /**
     * This method returns the number of medium boxes the
     * pickup point hosts
     *
     * @return The number of medium boxes the pickup point hosts
     */

    public String getMedium_boxes() {
        return medium_boxes;
    }

    /**
     * This method returns the number of large boxes the
     * pickup point hosts
     *
     * @return The number of large boxes the pickup point hosts
     */

    public String getLarge_boxes() {
        return large_boxes;
    }

    /**
     * This method returns the pickup point address
     *
     * @return The pickup point address
     */

    public String getAddress() {
        return address;
    }
}
