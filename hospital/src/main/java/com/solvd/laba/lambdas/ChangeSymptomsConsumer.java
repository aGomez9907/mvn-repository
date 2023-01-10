package com.solvd.laba.lambdas;

import com.solvd.laba.exceptions.PersonNotFoundException;

@FunctionalInterface
public interface ChangeSymptomsConsumer<T> {
    void accept(T t) throws PersonNotFoundException;
}
