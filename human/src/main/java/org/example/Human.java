package org.example;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.example.exceptions.EqualsGenderParentsException;

import java.util.HashSet;
import java.util.Set;
@Getter
@EqualsAndHashCode(exclude = {"father", "mother", "children"})
@ToString(of = {"name", "surname", "patronymic", "gender"})
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Human {
    final String name;
    final String surname;
    final String patronymic;
    final Gender gender;
    Human father;
    Human mother;
    final Set<Human> children = new HashSet<>();

    public Human(@NonNull String name, @NonNull String surname, @NonNull String patronymic, @NonNull Gender gender) {
        this.name = StringUtils.capitalize(name);
        this.surname = StringUtils.capitalize(surname);
        this.patronymic = StringUtils.capitalize(patronymic);
        this.gender = gender;
    }

    private void addParents(Human parent1,Human parent2) {
        if (parent1.getGender().equals(Gender.MAN)) {
            father = parent1;
            mother = parent2;
        } else {
            father = parent2;
            mother = parent1;
        }
    }

    private void addChild(Human child) {
        children.add(child);
    }

    public Human createChild(@NonNull String name, @NonNull String surname, @NonNull String patronymic,
                             @NonNull Gender gender,@NonNull Human parent){
        if (!this.getGender().equals(parent.getGender())) {
            Human child = new Human(name, surname, patronymic, gender);
            child.addParents(this, parent);
            this.addChild(child);
            parent.addChild(child);
            return child;
        } else {
            throw new EqualsGenderParentsException("Parents have the same gender");
        }
    }

}
