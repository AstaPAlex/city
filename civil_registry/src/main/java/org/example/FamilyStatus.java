package org.example;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum FamilyStatus {
    NOT_MARRIED, MARRIED, DIVORSED
}
