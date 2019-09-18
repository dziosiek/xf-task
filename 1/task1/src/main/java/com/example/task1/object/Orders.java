package com.example.task1.object;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Orders {

    @Id
    @GeneratedValue
    Long id;


    @ManyToMany
    List<Meal> orderMealList = new LinkedList<>();

    String additionalInformation = "";

    public void clear() {
        orderMealList.clear();
    }

}
