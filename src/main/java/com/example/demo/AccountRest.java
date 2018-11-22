package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/accounts")
@RequiredArgsConstructor
public class AccountRest {
    private final AccountDAO dao;
    private final AccountRepository repository;

    @GetMapping(value = "/create")
    public void create(){
        dao.create(1);
    }

    @GetMapping(value = "/find")
    public void find(@PathVariable Integer id, @RequestParam BigDecimal sum){
        dao.addBalance(id, sum.abs());
    }

    @GetMapping(value = "/checkout")
    public void checkout(@PathVariable Integer id, @RequestParam BigDecimal sum){
        dao.addBalance(id, sum.negate());
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<AccountEntity>> get(@PathVariable Integer id){
        return ResponseEntity.ok(repository.findByClientId(id));
    }
}
