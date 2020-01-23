package com.celfocus.training;

import com.celfocus.training.controller.ItemInfoController;
import com.celfocus.training.controller.ShoppingCartController;
import com.celfocus.training.controller.UserController;
import com.celfocus.training.domain.ItemInfo;
import com.celfocus.training.domain.ShoppingCart;
import com.celfocus.training.domain.ShoppingCartItem;
import com.celfocus.training.domain.User;
import com.celfocus.training.dto.UserDTO;
import com.celfocus.training.repository.iteminfo.inmemory.InMemoryItemInfoRepository;
import com.celfocus.training.repository.shoppingcart.inmemory.InMemoryShoppingCartRepository;
import com.celfocus.training.repository.user.inmemory.InMemoryUserRepository;
import com.celfocus.training.view.IView;
import com.celfocus.training.view.shoppingcart.ShoppingCartIViewXML;
import com.celfocus.training.view.user.UserIViewHTML;

import java.util.Date;
import java.util.Optional;

/**
 * Temos 4 entidades em nosso projeto User, ShoppingCart, ShoppingCartItem e ItemInfo
 */
public class ShoppingCartOrchestrator {

    private UserController userController;
    private ShoppingCartController shoppingCartController;
    private ItemInfoController itemInfoController;


    public void run() {

        initControllers();



        User user = createOrUpdateUser("Sousa", "01/01/2000", "true");
        User user2 = createOrUpdateUser("Marco", "01/01/2020", "true");
        IView<User> userView = new UserIViewHTML();
        System.out.println(userView.render(user));

        ShoppingCart shoppingCart = createOrUpdateShoppingCart(user2);
        IView<ShoppingCart> shoppingCartView = new ShoppingCartIViewXML();
        System.out.println(shoppingCartView.render(shoppingCart));

        deleteUser(user.getUsername());

        addItemToUserShoppingCart("Marco", "monitor", 2);

        removeItemFromUserShoppingCart("Marco", "monitor");

        createItemIfNotExists("monitor", 21.2);

        addItemToUserShoppingCart("Marco", "monitor", 2);

        removeItemFromUserShoppingCart("Marco", "monitor");

    }

    private void initControllers() {
        userController = new UserController(new InMemoryUserRepository());
        shoppingCartController = new ShoppingCartController(new InMemoryShoppingCartRepository());
        itemInfoController = new ItemInfoController(new InMemoryItemInfoRepository());
    }


    /**
     * Cria ou atualizar utilizador
     *
     * @param username,   username do utilizador
     * @param birthDate,  data de aniversário do utilizador
     * @param isLegalAge, informa se utilizador é maior de idade ou não
     */
    public User createOrUpdateUser(String username, String birthDate, String isLegalAge) {


        UserDTO userDTO = new UserDTO(username, birthDate, isLegalAge);

        return userController.saveOrUpdateUser(userDTO);
    }

    /**
     * Cria ou atualizar um carrinho
     *
     * @param user, utilizador a associar ao carrinho
     */
    private ShoppingCart createOrUpdateShoppingCart(User user) {


        Optional<ShoppingCart> optionalShoppingCart = shoppingCartController.findShoppingCartByUser(user);

        ShoppingCart shoppingCart;

        shoppingCart = optionalShoppingCart.orElseGet(() -> shoppingCartController.createShoppingCart(user));

        return shoppingCart;
    }

    /**
     * Remover Usuario
     */
    public boolean deleteUser(String username) {
        return userController.deleteUserByUsername(username);
    }

    /**
     * Adicionar item ao carrinho
     */
    public void addItemToUserShoppingCart(String username, String itemName, int quantity) {

        itemName = itemName.toLowerCase().concat("_item");

        Optional<User> optionalUser = userController.findUser(username);

        if (optionalUser.isEmpty()){
            System.out.println("username + \" not found\" = " + username + " not found");
            return;
        }

        User user = optionalUser.get();

        Optional<ShoppingCart> optionalShoppingCart = shoppingCartController.findShoppingCartByUser(user);


        if (optionalShoppingCart.isEmpty()){
            System.out.println("shoppingCarts of user = " + username + " not found");
            return;
        }

        ShoppingCart shoppingCart = optionalShoppingCart.get();

        Optional<ShoppingCartItem> optionalShoppingCartItem = shoppingCart.findShoppingCartItemByItemName(itemName);

        if (optionalShoppingCartItem.isPresent()){
            ShoppingCartItem shoppingCartItem = optionalShoppingCartItem.get();

            shoppingCartItem.addQuantity(quantity);
        } else {

            Optional<ItemInfo> optionalItemInfo = itemInfoController.findItem(itemName);

            if (optionalItemInfo.isEmpty()){
                System.out.println("infoItem with name = " + itemName + " not found");
                return;
            }

            ItemInfo itemInfo = optionalItemInfo.get();

            //TODO Strategy
            double discount = user.hasLegalAge() && (new Date().getYear() - user.getBirthDate().getYear() < 80) ? 0.2 : 0.1;


            // TODO Save??????
            ShoppingCartItem shoppingCartItem = new ShoppingCartItem(itemInfo, quantity, discount);

        }
    }

    /**
     * Adicionar item ao carrinho
     */
    public void removeItemFromUserShoppingCart(String username, String itemName) {

        Optional<User> optionalUser = userController.findUser(username);

        if (optionalUser.isEmpty()){
            System.out.println("username + \" not found\" = " + username + " not found");
            return;
        }

        User user = optionalUser.get();

        Optional<ShoppingCart> optionalShoppingCart = shoppingCartController.findShoppingCartByUser(user);

        if (!optionalShoppingCart.isPresent()){
            System.out.println("shoppingCarts of user = " + username + " not found");
            return;
        }

        ShoppingCart shoppingCart = optionalShoppingCart.get();

        Optional<ShoppingCartItem> optionalShoppingCartItem = shoppingCart.findShoppingCartItemByItemName(itemName);

        if (!optionalShoppingCartItem.isPresent()){
            System.out.println("shoppingC cart item with item name = " + itemName + " not found");
            return;
        }

        ShoppingCartItem shoppingCartItem = optionalShoppingCartItem.get();

        //TODO Should return boolean??????
        shoppingCart.remove(shoppingCartItem);

    }

    public void createItemIfNotExists(String itemName, double value) {

        Optional<ItemInfo> optionalItemInfo = itemInfoController.findItem(itemName);

        if(optionalItemInfo.isPresent()){
            return;
        }

        ItemInfo itemInfo = new ItemInfo(itemName, value);

        itemInfoController.addItemInfo(itemInfo);
    }

}