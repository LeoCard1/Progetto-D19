package PickupPointSystem.DatabaseSystem.Tables;

public class PickupPointTable {

    private String id;
    private String location;
    private String ip;
    private String small_boxes;
    private String medium_boxes;
    private String large_boxes;
    private String address;

    public PickupPointTable(String id, String location, String ip, String small_boxes, String medium_boxes, String large_boxes, String address) {

        this.id = id;
        this.location = location;
        this.ip = ip;
        this.small_boxes = small_boxes;
        this.medium_boxes = medium_boxes;
        this.large_boxes = large_boxes;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getIp() {
        return ip;
    }

    public String getSmall_boxes() {
        return small_boxes;
    }

    public String getMedium_boxes() {
        return medium_boxes;
    }

    public String getLarge_boxes() {
        return large_boxes;
    }

    public String getAddress() {
        return address;
    }
}
