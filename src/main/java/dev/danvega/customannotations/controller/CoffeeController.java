package dev.danvega.customannotations.controller;

import dev.danvega.customannotations.annotation.GET;
import dev.danvega.customannotations.annotation.POST;
import dev.danvega.customannotations.model.Coffee;
import dev.danvega.customannotations.model.Size;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/coffee")
public class CoffeeController {

    private List<Coffee> coffees = new ArrayList<>();

    @GET
    public List<Coffee> findAll() {
        return coffees;
    }

    @GET("/{id}")
    public Optional<Coffee> findOne(@PathVariable Integer id) {
        return coffees.stream().filter(coffee -> coffee.id() == id).findFirst();
    }

    @POST
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Coffee coffee) {
        coffees.add(coffee);
    }

    @PostConstruct
    private void init() {
        coffees.add(new Coffee(1,"Caffè Americano", Size.GRANDE));
        coffees.add(new Coffee(2,"Caffè Latte", Size.VENTI));
        coffees.add(new Coffee(3,"Caffè Caramel Macchiato", Size.TALL));
    }
}
