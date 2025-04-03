/*
 * Copyright (C) 2016 Pivotal Software, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.arsw.myrestaurant.restcontrollers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import edu.eci.arsw.myrestaurant.model.Order;
import edu.eci.arsw.myrestaurant.model.ProductType;
import edu.eci.arsw.myrestaurant.model.ResponseOrder;
import edu.eci.arsw.myrestaurant.model.RestaurantProduct;
import edu.eci.arsw.myrestaurant.services.OrderServicesException;
import edu.eci.arsw.myrestaurant.services.RestaurantOrderServices;
import edu.eci.arsw.myrestaurant.services.RestaurantOrderServicesStub;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import netscape.javascript.JSObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author hcadavid
 */
@RestController
@RequestMapping("/api")
public class OrdersAPIController {
    private final RestaurantOrderServices restaurantOrderServices;

    public OrdersAPIController(RestaurantOrderServices restaurantOrderServices) {
        this.restaurantOrderServices = restaurantOrderServices;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<ResponseOrder>> getOrders() throws OrderServicesException {
        List<ResponseOrder> response = new ArrayList<>();
        Set<Integer> keys = restaurantOrderServices.getTablesWithOrders();
        for( Integer k: keys) {
            Order order =  restaurantOrderServices.getTableOrder(k);
            Set<String> dishes = order.getOrderedDishes();
            int bill = restaurantOrderServices.calculateTableBill(order.getTableNumber());
            response.add(new ResponseOrder(k, dishes, bill ));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
