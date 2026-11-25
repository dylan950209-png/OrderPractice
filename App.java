import java.util.ArrayList;
import java.util.List;

class Order{
    private int id;
    private String customerName;
    private int amount;
    private String status;
    //1.建構子:建立物件時把資料填進去
    public Order(int id,String customerName,int amount,String status){
        this.id = id;
        this.customerName = customerName;
        this.amount = amount;
        this.status = status;
    }

    //2.Getter:讓外部可以取得欄位的值
    public int getId(){
        return  id;
    }
    public String getCustomerName(){
        return customerName;
    }
    public int getAmount(){
        return amount;
    }
    public String getStatus(){
        return status;
    }
    //3.toString:決定印出來的樣子

    @Override 
    public String toString(){
        return "訂單#" + id + "[" + customerName + "]" + amount + "(" + status + ")";
    }
}


public class App {
    public static void main (String[] argv){
        //4.建立一個空的清單
        List<Order> orders = new ArrayList<>();

        //塞進資料
        orders.add(new Order(1 ,"小明" , 500 ,"PAID"));
        orders.add(new Order(2, "小華", 1200, "CANCELLED"));
        orders.add(new Order(3, "小明", 800, "PAID"));
        orders.add(new Order(4, "俊宏", 400, "PAID"));
        orders.add(new Order(5, "新尾", 800, "CANCELLED"));

    
        /* 
        int total = 0;   
        //用 for-each 和if迴圈只列出PAID
        for(Order o : orders){
            if("PAID".equals(o.getStatus())){
                total += o.getAmount();
            }
        }
        System.out.println(total);
        */

        int streamTotal = orders.stream()          //1.把訂單清單放上「輸送帶」
        .filter(o -> "PAID".equals(o.getStatus())) //2.過濾關卡：只留狀態是 PAID 的
        .mapToInt(o -> o.getAmount())              //3.轉換關卡：只抽出每筆訂單的「金額(int)」
        .sum();                                    //4.結算關卡：全部加起來！
        System.out.println("Stream 算出的總營業餘額:" + streamTotal);

        int streamTotal2 = orders.stream()
        .filter(o ->"CANCELLED".equals(o.getStatus()))
        .mapToInt(o -> o.getAmount())
        .sum();
        System.out.println("Stream 算出的總營業餘額:" + streamTotal2);
    }
}
