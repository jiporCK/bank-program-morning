package model.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public record TransactionResponse(

        int id,
        String senderName,
        String receiverName,
        double amount,
        String timestamp

) {
}
