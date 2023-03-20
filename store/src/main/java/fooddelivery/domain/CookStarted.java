package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class CookStarted extends AbstractEvent {

    private Long id;
    private String status;
    private String foodId;
    private String orderId;
    private List<String> options;
    private String storeId;

    public CookStarted(FoodCooking aggregate) {
        super(aggregate);
    }

    public CookStarted() {
        super();
    }
}
