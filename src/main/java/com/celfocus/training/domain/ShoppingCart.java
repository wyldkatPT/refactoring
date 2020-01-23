package com.celfocus.training.domain;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ShoppingCart {

        private User user;

        private List<ShoppingCartItem> shoppingCartItemList;

        public ShoppingCart(User user, List<ShoppingCartItem> shoppingCartItemList){
                this.setUser(user);
                this.setShoppingCartItemList(shoppingCartItemList);
        }

        public void setUser(User user) {
                this.user = user;
        }

        public void setShoppingCartItemList(List<ShoppingCartItem> shoppingCartItemList) {
                this.shoppingCartItemList = shoppingCartItemList;
        }

        public User getUser(){
                return this.user;
        }

        public List<ShoppingCartItem> getShoppingCartItemList(){
                return this.shoppingCartItemList;
        }

        public Optional<ShoppingCartItem> findShoppingCartItemByItemName(String itemName){
                return shoppingCartItemList.stream()
                        .filter(shoppingCartItem -> shoppingCartItem.getItemInfo().getName().equals(itemName))
                        .findFirst();
        }

        public void remove(ShoppingCartItem shoppingCartItem) {
                this.shoppingCartItemList.remove(shoppingCartItem);
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                ShoppingCart that = (ShoppingCart) o;
                return user.equals(that.user);
        }

        @Override
        public int hashCode() {
                return Objects.hash(user);
        }
}
