package OnlineWallet.entity;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "operations")
@Data
@TypeDef(name = "operation_type", typeClass = PostgreSQLEnumType.class)
public class Operation {
    public Operation() {
        this.operationDate = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "operation", columnDefinition = "operation_type")
    @Type(type = "operation_type")
    private OperationType operation;
    private LocalDateTime operationDate;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "wallets_id", nullable = false)
    private Wallet wallet;
}