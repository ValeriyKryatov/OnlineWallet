package OnlineWallet.service;

import OnlineWallet.dto.OperationDTO;
import OnlineWallet.entity.Operation;
import OnlineWallet.entity.OperationType;
import OnlineWallet.entity.Wallet;
import OnlineWallet.exception.InsufficientFundsException;
import OnlineWallet.exception.UnsupportedOperationTypeException;
import OnlineWallet.exception.WalletNotFoundException;
import OnlineWallet.repository.OperationRepository;
import OnlineWallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final OperationRepository operationRepository;
    private final WalletRepository walletRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public BigDecimal getBalance(UUID WALLET_UUID) {
        Wallet wallet = walletRepository.findById(WALLET_UUID).orElseThrow(() -> new WalletNotFoundException("Wallet not found"));
        return wallet.getBalance();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void updateCheckInDB(OperationDTO operationDto) {
        Wallet wallet = walletRepository.findById(operationDto.getWalletId())
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found!"));
        Operation operation = new Operation();
        operation.setWallet(wallet);
        switch (operationDto.getOperationType()) {
            case DEPOSIT -> {
                operation.setOperation(OperationType.DEPOSIT);
                operation.setAmount(operationDto.getAmount());
                wallet.setBalance(wallet.getBalance().add(operationDto.getAmount()));
            }
            case WITHDRAW -> {
                BigDecimal balance = wallet.getBalance().subtract(operationDto.getAmount());
                if (balance.compareTo(BigDecimal.ZERO) < 0) {
                    throw new InsufficientFundsException("Insufficient funds!");
                }
                operation.setOperation(OperationType.WITHDRAW);
                operation.setAmount(operationDto.getAmount());
                wallet.setBalance(balance);
            }
            default -> throw new UnsupportedOperationTypeException("Unsupported operation type!");
        }
        operationRepository.save(operation);
        walletRepository.save(wallet);
    }
}