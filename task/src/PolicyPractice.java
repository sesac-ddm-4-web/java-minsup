public class PolicyPractice {
    public static void main(String[] args) {
        ShippingPolicy[] policies = {new PickupPolicy(), new DeliverPolicy(), new ExpressPolicy()};
        for(ShippingPolicy policy : policies){
            System.out.println(ShippingDemo.total(20000, policy));
        }
    }
}
