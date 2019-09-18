package com.example.task1.object;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cuisine {

    @Id
    @NotNull
    private String name;

    @OneToMany(mappedBy = "cuisine")
    private Set<Meal> mealList = new HashSet<>();

    public Cuisine(String name) {
        this.name = name;
    }


}
