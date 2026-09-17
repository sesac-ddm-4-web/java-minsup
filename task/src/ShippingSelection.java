package lesoon5;

public class ShippingSelection {
   public static int total(int subtotal, ShippingMethod method){
       return subtotal + method.fee(subtotal);
   }

   public static void main(String[] args) {
       ShippingSelection.total(20000,ShippingMethod.PICKUP);
       ShippingSelection.total(20000,ShippingMethod.DELIVERY);
       ShippingSelection.total(20000,ShippingMethod.EXPRESS);
   }
}
