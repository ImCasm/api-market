package com.casm.apimarket.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Purchase {
    private int purchaseId;
    private String customerId;
    private LocalDateTime date;
    private Character paymentMethod;
    private String comment;
    private Character state;
    private List<PurchaseItem> items;
}
