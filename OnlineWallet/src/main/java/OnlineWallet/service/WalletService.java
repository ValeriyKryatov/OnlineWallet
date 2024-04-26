package OnlineWallet.service;

import OnlineWallet.dto.OperationDTO;

import java.math.BigDecimal;
import java.util.UUID;

public interface WalletService {
    BigDecimal getBalance(UUID WALLET_UUID);

    void updateCheckInDB(OperationDTO operationDTO);
}