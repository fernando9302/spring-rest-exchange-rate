package com.interbank.periferiait.springrestexchangerate.infraestructure.generic;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class GenericResponse<T> {

    private T response;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
    private Boolean process;
    private List<String> errors;

    public GenericResponse(){
        date = LocalDateTime.now();
        process = true;
        errors = new ArrayList<>();
    }

    public GenericResponse(T response){
        this();
        this.response = response;
    }
}
