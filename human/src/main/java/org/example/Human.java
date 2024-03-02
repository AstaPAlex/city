package org.example;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.exceptions.EqualsGenderParentsException;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.capitalize;

@Getter
@EqualsAndHashCode(exclude = {"father", "mother", "children"})
@ToString(of = {"name", "surname", "patronymic", "gender"})
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Human {
    String name;
    String surname;
    String patronymic;
    Gender gender;
    Human father;
    Human mother;
    List<Human> children = new ArrayList<>();

    public Human(@NonNull String name, @NonNull String surname, @NonNull String patronymic, @NonNull Gender gender) {
        this.name = capitalize(name);
        this.surname = capitalize(surname);
        this.patronymic = capitalize(patronymic);
        this.gender = gender;
    }

    private void addParents(Human father,Human mother) {
        if (father.getGender().equals(mother.getGender())) {
            throw new IllegalArgumentException("Родители должны быть разного пола");
        }
        this.father = father;
        this.mother = mother;

        father.getChildren().add(this);
        mother.getChildren().add(this);
    }

    private void addChild(Human child) {
        children.add(child);
    }

    public Human createChild(@NonNull String name, @NonNull String surname, @NonNull String patronymic,
                             @NonNull Gender gender,@NonNull Human parent) throws EqualsGenderParentsException {
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

    public String getFullName() {
        return String.format("%s %s %s", surname, name, patronymic);
    }
}