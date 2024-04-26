package OnlineWallet.controller;

import OnlineWallet.dto.OperationDTO;
import OnlineWallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;
    @PostMapping("/wallet")
    public ResponseEntity<?> updateCheckInDB(@Valid @RequestBody OperationDTO operationDTO) {
        walletService.updateCheckInDB(operationDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/wallets/{WALLET_UUID}")
    public ResponseEntity<?> getBalance(@PathVariable UUID WALLET_UUID) {
        BigDecimal balance = walletService.getBalance(WALLET_UUID);
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }
}