package com.interbank.periferiait.springrestexchangerate.infraestructure.repository;

import com.interbank.periferiait.springrestexchangerate.infraestructure.persistence.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencySqlRepository extends JpaRepository<CurrencyEntity, String> {
    Optional<CurrencyEntity> getByCode(String code);
}
