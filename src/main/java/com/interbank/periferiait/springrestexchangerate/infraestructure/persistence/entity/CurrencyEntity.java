package com.interbank.periferiait.springrestexchangerate.infraestructure.persistence.entity;

import com.interbank.periferiait.springrestexchangerate.domain.model.Currency;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "currency")
@Getter
@Setter
@NoArgsConstructor
public class CurrencyEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "code", length = 3)
    private String code;

    @Column(name = "description", length = 100)
    private String description;

    public CurrencyEntity(Currency currency){
        this.id = currency.getId();
        this.code = currency.getCode();
        this.description = currency.getDescription();
    }

    public Currency toDomain(){
        return new Currency(id, code, description);
    }
}
