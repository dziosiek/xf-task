package com.example.task1.object;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Meal {

    @ManyToMany(mappedBy = "orderMealList")
    List<Orders> ordersList = new LinkedList<>();
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @JoinColumn(name = "cuisine", referencedColumnName = "name")
    @ManyToOne
    private Cuisine cuisine;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "type")
    private MealType type;
    @NotNull
    private String name;
    @NotNull
    private Float price;


    public Meal(Cuisine cuisine, String name, MealType type, Float price) {
        this.cuisine = cuisine;
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name;
    }
}
