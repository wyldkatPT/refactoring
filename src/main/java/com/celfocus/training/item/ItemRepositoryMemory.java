package com.celfocus.training.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemRepositoryMemory implements ItemRepository {

    private List<ItemInfo> itens = new ArrayList<>();

    @Override
    public Optional<ItemInfo> findById(String id) {
        return itens.stream().filter(item -> item.name.equals(id)).findFirst();
    }

    @Override
    public void deleteById(String id) {
        itens.removeIf(item -> item.name.equals(id));
    }

    @Override
    public void add(ItemInfo value) {
        if(!findById(value.name).isPresent()) {
            itens.add(value);
        }
    }

    @Override
    public int count() {
        return itens.size();
    }
}
