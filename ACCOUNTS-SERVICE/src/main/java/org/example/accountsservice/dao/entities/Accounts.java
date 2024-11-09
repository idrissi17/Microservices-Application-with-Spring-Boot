package org.example.accountsservice.dao.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Accounts extends BaseEntity {
    @Id
    @Column(name = "account_number")
    private Integer accountNumber;
    @Column(name = "account_type")
    private String accountType;
    @Column(name = "branch_address")
    private String branchAddress;
    @Column(name = "customer_id")
    private Integer customerId;

}
