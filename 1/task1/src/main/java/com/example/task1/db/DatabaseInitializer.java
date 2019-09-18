package com.example.task1.db;

import com.example.task1.jpa.CuisineRepository;
import com.example.task1.jpa.MealRepository;
import com.example.task1.jpa.MealTypeRepository;
import com.example.task1.object.Cuisine;
import com.example.task1.object.Meal;
import com.example.task1.object.MealType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseInitializer implements InitializingBean {

    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private CuisineRepository cuisineRepository;

    @Autowired
    private MealTypeRepository mealTypeRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    private void init() {


        MealType drink = mealTypeRepository.save(new MealType("Drink"));
        MealType mainCourse = mealTypeRepository.save(new MealType("Main Course"));
        MealType dessert = mealTypeRepository.save(new MealType("Dessert"));

        Cuisine mexican = cuisineRepository.save(new Cuisine("Mexican"));
        Cuisine polish = cuisineRepository.save(new Cuisine("Polish"));
        Cuisine italian = cuisineRepository.save(new Cuisine("Italian"));
        Cuisine general = cuisineRepository.save(new Cuisine("General"));

        mealRepository.save(new Meal(polish, "Golabki", mainCourse, 10.50f));
        mealRepository.save(new Meal(polish, "Bigos", mainCourse, 20f));

        mealRepository.save(new Meal(mexican, "Burrito", mainCourse, 12f));

        mealRepository.save(new Meal(italian, "Margherita", mainCourse, 20f));
        mealRepository.save(new Meal(italian, "Spaghetti", mainCourse, 15f));

        mealRepository.save(new Meal(italian, "Biscuit Tortoni", dessert, 11f));
        mealRepository.save(new Meal(italian, "Bomboloni", dessert, 10f));

        mealRepository.save(new Meal(polish, "Kremówka", dessert, 10f));
        mealRepository.save(new Meal(polish, "Makowiec", dessert, 12f));

        mealRepository.save(new Meal(mexican, "Churro Dump Cake", dessert, 12f));


        mealRepository.save(new Meal(general, "Mojito", drink, 8f));
        mealRepository.save(new Meal(general, "Orange Juice", drink, 6f));
        mealRepository.save(new Meal(general, "Water", drink, 4f));


    }
}
