package com.celfocus.training.domain;

import java.util.Objects;

public class ItemInfo {

    private String name;
    private double value;

    public ItemInfo(String name, double value){
        this.setName(name);
        this.setValue(value);
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setValue(double value) {
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public double getValue(){
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemInfo itemInfo = (ItemInfo) o;
        return Objects.equals(name, itemInfo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
