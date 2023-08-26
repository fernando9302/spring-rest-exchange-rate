package com.interbank.periferiait.springrestexchangerate.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Currency {
    private String id;
    private String code;
    private String description;
}
