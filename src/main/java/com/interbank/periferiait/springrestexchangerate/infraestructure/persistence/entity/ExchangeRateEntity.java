package com.interbank.periferiait.springrestexchangerate.infraestructure.persistence.entity;

import com.interbank.periferiait.springrestexchangerate.domain.model.ExchangeRate;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "exchange_rate")
@Getter
public class ExchangeRateEntity {

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "currency_id_from")
    private CurrencyEntity currencyFrom;

    @ManyToOne
    @JoinColumn(name = "currency_id_to")
    private CurrencyEntity currencyTo;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "amount", precision = 10, scale = 3)
    private BigDecimal amount;

    public ExchangeRate toDomain(){
        return new ExchangeRate(id, currencyFrom.getCode(), currencyTo.getCode(), date, amount.doubleValue());
    }
}
