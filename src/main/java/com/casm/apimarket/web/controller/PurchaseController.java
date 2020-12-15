package com.casm.apimarket.web.controller;

import com.casm.apimarket.domain.Purchase;
import com.casm.apimarket.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping(value = {"", "/{date}"})
    public ResponseEntity<List<Purchase>> getAll(@PathVariable Optional<LocalDateTime> date) {
        return date.map(localDateTime -> purchaseService.getByDate(localDateTime)
                .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)))
                .orElseGet(() -> new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<Purchase>> getByCustomer(@PathVariable("id") String customerId) {
        return purchaseService.getByCustomer(customerId)
                .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public Purchase save(@RequestBody Purchase purchase){
        return purchaseService.save(purchase);
    }
}
