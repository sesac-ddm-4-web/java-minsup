import java.util.ArrayList;
import java.util.List;

public class Cart {
    List<OrderLine> list = new ArrayList<>();

    public void add(OrderLine order){
        list.add(order);
    }

    public int subtotal(){
        int sum = 0;
        for(OrderLine order : list){
            sum += order.amount();
        }
        return sum;
    }
}
