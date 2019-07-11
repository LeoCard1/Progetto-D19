package PickupPointSystem.DatabaseSystem.Tables;

/**
 * This class is the representation of the pickup point database table
 * @author Andrea Stella
 * @version 1.0
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
     * The constructor. Initializes the pickup point id, the location, the ip,
     * the small, medium and large boxes, and the address
     * @param id the pickup point id
     * @param location the pickup point location
     * @param ip the pickup point server ip
     * @param small_boxes the small boxes
     * @param medium_boxes the medium boxes
     * @param large_boxes the large boxes
     * @param address the pickup point address
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
     * @return the pickup point id
     */

    public String getId() {
        return id;
    }

    /**
     * @return the pickup point location
     */

    public String getLocation() {
        return location;
    }

    /**
     * @return the pickup point server ip
     */

    public String getIp() {
        return ip;
    }

    /**
     * @return the small boxes
     */

    public String getSmall_boxes() {
        return small_boxes;
    }

    /**
     * @return the medium boxes
     */

    public String getMedium_boxes() {
        return medium_boxes;
    }

    /**
     * @return the large boxes
     */

    public String getLarge_boxes() {
        return large_boxes;
    }

    /**
     * @return the pickup point address
     */

    public String getAddress() {
        return address;
    }
}
