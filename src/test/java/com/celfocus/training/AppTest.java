package com.celfocus.training;

import com.celfocus.training.item.ItemRepositoryMemory;
import com.celfocus.training.shoppingcart.ShoppingCartRepositoryMemory;
import com.celfocus.training.user.UserRepositoryMemory;
import com.celfocus.training.user.UserService;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Date;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        UserRepositoryMemory userRepositoryMemory = new UserRepositoryMemory();
        ShoppingCartRepositoryMemory shoppingCartRepositoryMemory = new ShoppingCartRepositoryMemory();
        ItemRepositoryMemory itemRepositoryMemory = new ItemRepositoryMemory();
        UserService saver = new UserService(userRepositoryMemory, shoppingCartRepositoryMemory, itemRepositoryMemory);
        saver.saveOrUpdateUser("teste", new Date(), false);
        saver.saveOrUpdateUser("teste2", new Date(), false);
        saver.saveOrUpdateUser("teste", new Date(), true);
        assertTrue(saver.findUser("teste").get().isOlder);
        assertEquals(2, userRepositoryMemory.count());

        assertTrue(saver.userExists("teste"));
        saver.deleteUser("teste");
        assertFalse(saver.userExists("teste"));
        assertTrue(saver.userExists("teste2"));

        saver.createItemIfNotExists("item1", 10);
        saver.createItemIfNotExists("item2", 10);
        assertEquals(2, itemRepositoryMemory.count());
        saver.createItemIfNotExists("item1", 10);
        assertEquals(2, itemRepositoryMemory.count());

        saver.addItemToShoppingCart("teste2", "item1", 3);
        saver.addItemToShoppingCart("teste2", "item2", 1);
        assertEquals(2, saver.findShoppingCart(saver.findUser("teste2").get()).get().itens.size());
        assertEquals(3, saver.findShoppingCart(saver.findUser("teste2").get()).get().itens.get(0).qt);
        saver.addItemToShoppingCart("teste2", "item1", 5);
        assertEquals(8, saver.findShoppingCart(saver.findUser("teste2").get()).get().itens.get(0).qt);
        saver.removeItemFromShoppingCart("teste2", "item1");
        assertEquals(1, saver.findShoppingCart(saver.findUser("teste2").get()).get().itens.size());

    }
}
