package LockerSystem;

public class Size implements Comparable {

    private double height;
    private double length;
    private double width;

    public Size(double height, double length, double width ) {
        this.height = height;
        this.length = length;
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public int compareTo(Object o) {
        Size sizeObject = (Size)o;

        if ((height == sizeObject.getHeight()) && (length == sizeObject.getLength()) && (width == sizeObject.getWidth())) return 0;

        if ((height >= sizeObject.getHeight()) && (length >= sizeObject.getLength()) && (width >= sizeObject.getWidth())) return 1;

        return -1;
    }
}
