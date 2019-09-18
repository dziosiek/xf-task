package com.example.task1.object;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MealType {

    @OneToMany(mappedBy = "type")
    Set<Meal> mealList = new HashSet<>();
    @NotNull
    @Id
    @Column(name = "name")
    private String name;


    public MealType(String type) {
        this.name = type;
    }
}
