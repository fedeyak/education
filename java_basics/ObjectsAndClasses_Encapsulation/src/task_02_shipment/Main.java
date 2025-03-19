package task_02_shipment;

public class Main {

    public static void main(String[] args) {
        Shipment shipment = new Shipment(20, 20, 20, 50,
                "Red Square, Moscow, Russia", Rotatable.ROTATABLE, "NBF221783", Fragile.FRAGILE);

        System.out.println(shipment);

        Shipment alteredShipment = shipment.alterShipmentDimensions(10, 10, 10);
        System.out.println(alteredShipment);

        Shipment alteredShipment2 =  shipment.alterShipmentDeliveryAddress("Tour Eiffel, Paris, France");
        System.out.println(alteredShipment2);

        Shipment alteredShipment3 =  shipment.alterShipmentWeight(1000);
        System.out.println(alteredShipment3);

    }
}
