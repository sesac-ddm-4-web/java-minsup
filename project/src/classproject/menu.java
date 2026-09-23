package task.lesson7;

public class Menu {
    // 최종 수량 및 가격
    private int totalquantity;
    private int totalprice;

    public Menu(int price, int quantity){
        this.totalprice = price;
        this.totalquantity = quantity;
    }

    // 이미 있는 메뉴를 추가할 때 수량 가격 초
    public void updateSale(int price, int quantity){
        this.totalprice += price;
        this.totalquantity += quantity;
    }

    public int getQuantity() {
        return totalquantity;
    }

    public int getPrice() {
        return totalprice;
    }
}
