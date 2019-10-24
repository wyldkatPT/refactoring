package com.celfocus.training.shoppingcart;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingCartRepositoryMemory implements ShoppingCartRepository {

    private List<ShoppingCart> shoppingCarts = new ArrayList<>();

    @Override
    public Optional<ShoppingCart> findById(String id) {
        return shoppingCarts.stream().filter(s -> s.user.name.equals(id)).findFirst();
    }

    @Override
    public void deleteById(String id) {
        shoppingCarts.removeIf(shoppingCart -> shoppingCart.user.name == id);
    }

    @Override
    public void add(ShoppingCart value) {
        if(!findById(value.user.name).isPresent()) {
            shoppingCarts.add(value);
        }
    }

    @Override
    public int count() {
        return shoppingCarts.size();
    }
}
