package fooddelivery.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fooddelivery.config.kafka.KafkaProcessor;
import fooddelivery.domain.*;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    FoodCookingRepository foodCookingRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderPlaced'"
    )
    public void wheneverOrderPlaced_OrderInfo(
        @Payload OrderPlaced orderPlaced
    ) {
        OrderPlaced event = orderPlaced;
        System.out.println(
            "\n\n##### listener OrderInfo : " + orderPlaced + "\n\n"
        );

        // Sample Logic //
        FoodCooking.orderInfo(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Paid'"
    )
    public void wheneverPaid_Updatestatus(@Payload Paid paid) {
        Paid event = paid;
        System.out.println(
            "\n\n##### listener Updatestatus : " + paid + "\n\n"
        );

        // Sample Logic //
        FoodCooking.updatestatus(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderCanceled'"
    )
    public void wheneverOrderCanceled_Updatestatus(
        @Payload OrderCanceled orderCanceled
    ) {
        OrderCanceled event = orderCanceled;
        System.out.println(
            "\n\n##### listener Updatestatus : " + orderCanceled + "\n\n"
        );

        // Sample Logic //
        FoodCooking.updatestatus(event);
    }
}
