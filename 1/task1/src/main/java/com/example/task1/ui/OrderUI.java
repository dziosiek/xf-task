package com.example.task1.ui;

import com.example.task1.jpa.MealRepository;
import com.example.task1.jpa.OrderRepository;
import com.example.task1.object.Meal;
import com.example.task1.object.Orders;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
@Getter
@Setter
@NoArgsConstructor
@Transactional
public class OrderUI {

    @Autowired
    MealRepository mealRepository;

    @Autowired
    OrderRepository orderRepository;

    Scanner scanner = new Scanner(System.in);

    Orders orders = new Orders();

    public void run() {

        String output;

        do {
            commandsHelp();
            output = scanner.nextLine();
            System.out.println(output);
            if (output.equals("menu")) showMenu(mealRepository.findAll());
            else if (output.equals("clear")) orders.clear();
            else if (output.contains("add drink")) addDrink();
            else if (output.contains("add lunch")) addLunch();
            else if (output.equals("order")) order();
            else if (output.equals("show orders")) showOrders();
        } while (!output.equals("exit"));
    }

    private void showOrders() {
        System.out.println("Orders:");
        orderRepository.findAll().stream().forEach(orders1 -> {
            System.out.println("Order " + orders1.getId());
            System.out.println(orders1.getOrderMealList());
            System.out.println(orders1.getAdditionalInformation());
            System.out.println("-------------------------------------");
        });
    }

    @Transactional
    private void order() {

        Orders order = orderRepository.saveAndFlush(orders);
        System.out.println("order number:" + order.getId());
        System.out.println("meals:" + order.getOrderMealList());
        System.out.println("additional informations:"+order.getAdditionalInformation());
        System.out.println("final price(PLN):" + order.getOrderMealList().
                stream().map(Meal::getPrice).reduce((aFloat, aFloat2) -> aFloat + aFloat2).orElseGet(() -> 0F));
    }

    private void addDrink() {
        List<Meal> drinks = mealRepository.findAll().stream().filter(meal -> meal.getType().getName().equals("Drink")).collect(Collectors.toList());
        showMenu(drinks);
        long drinkId = scanner.nextLong();
        if (drinks.stream().anyMatch(meal -> meal.getId().equals(drinkId))) System.out.println("Drink added");
        else {
            System.out.println("Incorrect id number");
            return;
        }

        System.out.println("with ice ? (yes/no)");
        String withIce, withLemon;
        do {
            withIce = scanner.next();
        } while (!withIce.equals("yes") && !withIce.equals("no"));
        System.out.println("with lemon ? (yes/no)");
        do {
            withLemon = scanner.next();
        } while (!withLemon.equals("yes") && !withLemon.equals("no"));

        orders.getOrderMealList().add(mealRepository.getOne(drinkId));
        orders.setAdditionalInformation(orders.getAdditionalInformation()+"\n" +
                mealRepository.getOne(drinkId).getName()+"\nwith lemon = " + withLemon + "\nwith ice = " + withIce);

    }

    private void addLunch() {

        List<Meal> lunches = mealRepository.findAll().stream().filter(meal -> meal.getType().getName().equals("Main Course")).collect(Collectors.toList());
        List<Meal> desserts = mealRepository.findAll().stream().filter(meal -> meal.getType().getName().equals("Dessert")).collect(Collectors.toList());

        showMenu(lunches);
        long lunchId = scanner.nextLong();
        if (lunches.stream().anyMatch(meal -> meal.getId().equals(lunchId))) System.out.println("Lunch added");
        else {
            System.out.println("Incorrect id number");
            return;
        }

        showMenu(desserts);
        Long dessertsId = scanner.nextLong();
        if (desserts.stream().anyMatch(meal -> meal.getId().equals(dessertsId))) System.out.println("Dessert added");
        else {
            System.out.println("Incorrect id number");
            return;
        }

        orders.getOrderMealList().add(mealRepository.getOne(lunchId));
        orders.getOrderMealList().add(mealRepository.getOne(dessertsId));

    }

    private void showMenu(List<Meal> mealList) {
        mealList.forEach(meal -> {
            System.out.println(meal.getId() + " / " + meal.getName() + " / " + meal.getPrice()
                    + " PLN / " + meal.getType().getName() + " / " + meal.getCuisine().getName());
        });
        System.out.println("Choose a number:");


    }

    private void commandsHelp(){

        System.out.println("-------------------------------------");
        System.out.print("menu - show products\nadd drink - add drink to order\n" +
                "add lunch - add lunch to order\norder - order completion\n" +
                "show orders - show all orders\n" +
                "exit - close UI\n");
        System.out.println("-------------------------------------");
        System.out.print("Type command here:");
    }
}
