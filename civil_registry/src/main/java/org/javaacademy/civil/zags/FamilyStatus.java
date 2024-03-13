package org.javaacademy.civil.zags;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum FamilyStatus {
    NOT_MARRIED, MARRIED, DIVORCED;

}
