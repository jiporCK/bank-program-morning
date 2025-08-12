package model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public record TransferRequest(

        int fromAccountId,
        int toAccountId,
        double amount

) {
}
