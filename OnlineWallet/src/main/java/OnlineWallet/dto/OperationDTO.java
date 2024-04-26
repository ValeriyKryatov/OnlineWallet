package OnlineWallet.dto;

import OnlineWallet.entity.OperationType;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class OperationDTO {
    @NotNull(message = "Please provide a wallet Id")
    private UUID walletId;
    @NotNull(message = "Please provide a operation type")
    private OperationType operationType;
    @NotNull(message = "Please provide an amount")
    @DecimalMin(value = "0.0", inclusive = false, message = "The amount must be positive")
    private BigDecimal amount;
}