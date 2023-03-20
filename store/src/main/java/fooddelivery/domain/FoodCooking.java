package fooddelivery.domain;

import fooddelivery.StoreApplication;
import fooddelivery.domain.CookFinished;
import fooddelivery.domain.CookStarted;
import fooddelivery.domain.OrderRejected;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "FoodCooking_table")
@Data
public class FoodCooking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String status;

    private String foodId;

    private String orderId;

    @ElementCollection
    private List<String> options;

    private String storeId;

    @PostPersist
    public void onPostPersist() {
        CookStarted cookStarted = new CookStarted(this);
        cookStarted.publishAfterCommit();

        CookFinished cookFinished = new CookFinished(this);
        cookFinished.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate() {
        OrderRejected orderRejected = new OrderRejected(this);
        orderRejected.publishAfterCommit();
    }

    public static FoodCookingRepository repository() {
        FoodCookingRepository foodCookingRepository = StoreApplication.applicationContext.getBean(
            FoodCookingRepository.class
        );
        return foodCookingRepository;
    }

    public void accept() {
        OrderAccepted orderAccepted = new OrderAccepted(this);
        orderAccepted.publishAfterCommit();
    }

    public static void orderInfo(OrderPlaced orderPlaced) {
        /** Example 1:  new item 
        FoodCooking foodCooking = new FoodCooking();
        repository().save(foodCooking);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(foodCooking->{
            
            foodCooking // do something
            repository().save(foodCooking);


         });
        */

    }

    public static void updatestatus(Paid paid) {
        /** Example 1:  new item 
        FoodCooking foodCooking = new FoodCooking();
        repository().save(foodCooking);

        */

        /** Example 2:  finding and process
        
        repository().findById(paid.get???()).ifPresent(foodCooking->{
            
            foodCooking // do something
            repository().save(foodCooking);


         });
        */

    }

    public static void updatestatus(OrderCanceled orderCanceled) {
        /** Example 1:  new item 
        FoodCooking foodCooking = new FoodCooking();
        repository().save(foodCooking);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderCanceled.get???()).ifPresent(foodCooking->{
            
            foodCooking // do something
            repository().save(foodCooking);


         });
        */

    }
}
