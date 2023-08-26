package com.interbank.periferiait.springrestexchangerate.infraestructure.persistence.entity;

import com.interbank.periferiait.springrestexchangerate.domain.model.Currency;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;

@Entity
@Table(name = "currency")
@Getter
public class CurrencyEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "code", length = 3)
    private String code;

    @Column(name = "description", length = 100)
    private String description;

    public Currency toDomain(){
        return new Currency(id, code, description);
    }
}
