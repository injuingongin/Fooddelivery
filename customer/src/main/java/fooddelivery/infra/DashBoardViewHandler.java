package fooddelivery.infra;

import fooddelivery.config.kafka.KafkaProcessor;
import fooddelivery.domain.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class DashBoardViewHandler {

    @Autowired
    private DashBoardRepository dashBoardRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderPlaced_then_CREATE_1(
        @Payload OrderPlaced orderPlaced
    ) {
        try {
            if (!orderPlaced.validate()) return;

            // view 객체 생성
            DashBoard dashBoard = new DashBoard();
            // view 객체에 이벤트의 Value 를 set 함
            dashBoard.setId(orderPlaced.getId());
            dashBoard.setStatus("주문됨");
            // view 레파지 토리에 save
            dashBoardRepository.save(dashBoard);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaid_then_UPDATE_1(@Payload Paid paid) {
        try {
            if (!paid.validate()) return;
            // view 객체 조회
            Optional<DashBoard> dashBoardOptional = dashBoardRepository.findById(
                paid.getOrderId()
            );

            if (dashBoardOptional.isPresent()) {
                DashBoard dashBoard = dashBoardOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                dashBoard.setStatus("결재됨");
                // view 레파지 토리에 save
                dashBoardRepository.save(dashBoard);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderAccepted_then_UPDATE_2(
        @Payload OrderAccepted orderAccepted
    ) {
        try {
            if (!orderAccepted.validate()) return;
            // view 객체 조회
            Optional<DashBoard> dashBoardOptional = dashBoardRepository.findById(
                orderAccepted.getOrderId()
            );

            if (dashBoardOptional.isPresent()) {
                DashBoard dashBoard = dashBoardOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                dashBoard.setStatus("접수됨");
                // view 레파지 토리에 save
                dashBoardRepository.save(dashBoard);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderRejected_then_UPDATE_3(
        @Payload OrderRejected orderRejected
    ) {
        try {
            if (!orderRejected.validate()) return;
            // view 객체 조회
            Optional<DashBoard> dashBoardOptional = dashBoardRepository.findById(
                orderRejected.getOrderId()
            );

            if (dashBoardOptional.isPresent()) {
                DashBoard dashBoard = dashBoardOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                dashBoard.setStatus("거부됨");
                // view 레파지 토리에 save
                dashBoardRepository.save(dashBoard);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
