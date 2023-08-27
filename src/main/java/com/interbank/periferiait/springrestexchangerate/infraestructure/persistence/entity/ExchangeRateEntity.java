package com.interbank.periferiait.springrestexchangerate.infraestructure.persistence.entity;

import com.interbank.periferiait.springrestexchangerate.domain.model.ExchangeRate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "exchange_rate")
@Getter
@NoArgsConstructor
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

    public ExchangeRateEntity(ExchangeRate exchangeRate){
        this.id = exchangeRate.getId();
        this.currencyFrom = new CurrencyEntity(exchangeRate.getCurrencyFrom());
        this.currencyTo = new CurrencyEntity(exchangeRate.getCurrencyTo());
        this.date = exchangeRate.getDate();
        this.amount = BigDecimal.valueOf(exchangeRate.getAmount());
    }

    public ExchangeRate toDomain(){
        return new ExchangeRate(id, currencyFrom.toDomain(), currencyTo.toDomain(), date, amount.doubleValue());
    }
}
