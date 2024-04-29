package com.example.creditservice.wrappers;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataWrap<E> {
    private E data;
}
