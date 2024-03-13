package org.javaacademy.civil.zags;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import lombok.NonNull;
import lombok.Value;

@Value
public class WriterTypeRegistry {
    LocalDate date;
    TypeRegistry typeRegistry;
    Set<Citizen> listCitizen;

    public WriterTypeRegistry(@NonNull LocalDate date, @NonNull TypeRegistry typeRegistry,
                              @NonNull Citizen... citizens) {
        this.date = date;
        this.typeRegistry = typeRegistry;
        this.listCitizen = new HashSet<>(Arrays.asList(citizens));
    }
}
