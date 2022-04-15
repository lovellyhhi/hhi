package com.kyh.iipa.mvc.result;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultEtc<E> {
    private E etc;
}
