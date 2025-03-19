package task_02_shipment;

public class Shipment {

    private final Dimensions dimensions;
    private final int weight;
    private final String deliveryAddress;
    private final Rotatable rotatable;
    private final String regNumber;
    private final Fragile fragile;

    public Shipment(int width, int height, int length, int weight, String deliveryAddress, Rotatable rotatable, String regNumber, Fragile fragile) {
        this.dimensions = new Dimensions(width, height, length);
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.rotatable = rotatable;
        this.regNumber = regNumber;
        this.fragile = fragile;
    }

    public Shipment(Dimensions dimensions, int weight, String deliveryAddress, Rotatable rotatable, String regNumber, Fragile fragile) {
        this.dimensions = dimensions;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.rotatable = rotatable;
        this.regNumber = regNumber;
        this.fragile = fragile;
    }

    public Shipment alterShipmentDeliveryAddress(String deliveryAddress) {
        return new Shipment(dimensions, weight, deliveryAddress, rotatable, regNumber, fragile);
    }

    public Shipment alterShipmentDimensions(int width, int height, int length) {
        Dimensions dimensions = new Dimensions(width, height, length);
        return new Shipment(dimensions, weight, deliveryAddress, rotatable, regNumber, fragile);
    }

    public Shipment alterShipmentWeight(int weight) {
        return new Shipment(dimensions, weight, deliveryAddress, rotatable, regNumber, fragile);
    }

    @Override
    public String toString() {

        return "\nVolume: " + dimensions.getVolume() + " sq.sm." +
                ";\nWeight: " + weight + " kg" +
                ";\nDelivery address: " + deliveryAddress +
                ";\nRotatable: " + rotatable +
                ";\nReg. number: " + regNumber +
                ";\nFragile:" + fragile;
    }
}




