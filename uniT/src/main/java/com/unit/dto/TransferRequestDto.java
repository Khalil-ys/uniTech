package com.unit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TransferRequestDto {

    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
}
