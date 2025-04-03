package edu.eci.arsw.myrestaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

public class ResponseOrder {

    int order;
    int total;
    Set<String> dishes;
    public ResponseOrder(int order, Set<String> dishes, int total){
        this.order = order;
        this.total = total;
        this.dishes = dishes;
    }

    public int getTotal() {
        return total;
    }


    public int getKey() {
        return order;
    }


    public Set<String> getDishes() {
        return dishes;
    }

}
