package com.tnt.onlinestore.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CartEntityTest {

    //Set of RoleEntities to add to UserEntity
    Set<RoleEntity> RoleSet = new HashSet<>();

    //Set of UserEntities to add to RoleEntity
    Set<UserEntity> userSet = new HashSet<>();

    //RoleEntity to add to RoleEntity Set
    RoleEntity userRole = new RoleEntity(2L,"ROLE_USER", null);

    //Initialise UserEntity for testing using RoleEntity Set
    UserEntity user = new UserEntity(1L,"name","email@e.se","abcde","address", RoleSet, new CartEntity(1L,null, null));

    //Initialise CartEntity for testing
    CartEntity cartEntity = new CartEntity(1L, user, new ArrayList<>());

    //Inistialise ManufacturerEntity for testing
    ManufacturerEntity manufacturer1 = new ManufacturerEntity(1L, "Manufacturer1", new ArrayList<>());
    ManufacturerEntity manufacturer2 = new ManufacturerEntity(2L, "Manufacturer2", new ArrayList<>());

    //Initialise ProductEntities for testing
    ProductEntity product1 = new ProductEntity(1L,"product1",100.00, manufacturer1, new ArrayList<>());
    ProductEntity product2 = new ProductEntity(2L, "product2", 200.00, manufacturer1, new ArrayList<>());
    ProductEntity product3 = new ProductEntity(2L, "product3", 300.00, manufacturer2, new ArrayList<>());

    //create list of ProductEntities to add to ManufacturerEntities
    List<ProductEntity> manufacturer1Products = new ArrayList<>();
    List<ProductEntity> manufacturer2Products = new ArrayList<>();

    //create list of ProductEntities to add to StoreEntity
    List<ProductEntity> storeProducts = new ArrayList<>();

    //Initialise StoreEntity for testing
    StoreEntity store = new StoreEntity(1L, "London", new ArrayList<>());

    @BeforeEach
    void setup() {
        //Before each test, entities must be correctly constructed so the tests can compile.

        //Add the user to a Set, and add the Set to each RoleEntity
        userSet.add(user);
        userRole.setUsers(userSet);
        user.addRole(userRole);

        //Add cart to user
        user.setCart(cartEntity);

        //Add ProductEntities to ManufacturerProducts Lists
        manufacturer1Products.add(product1);
        manufacturer1Products.add(product2);

        manufacturer2Products.add(product3);

        //Add product lists to manufacturers
        manufacturer1.setProducts(manufacturer1Products);
        manufacturer2.setProducts(manufacturer2Products);

        //Add ProductEntities to StoreProducts List
        storeProducts.add(product1);
        storeProducts.add(product2);
        storeProducts.add(product3);

        //Add StoreProducts to Store
        store.setProducts(storeProducts);

    }

    @Test
    void cartInitialisesAsEmpty() {
        //Call on user's cartEntity
        CartEntity cart = user.getCart();
        assertTrue(cart.getProducts().isEmpty());
    }

    @Test
    void addingOneProductShouldGiveSizeOf1() {
        CartEntity cart = user.getCart();
        cart.addProduct(product1);
        assertEquals(cart.getProducts().size(), 1);
    }

    @Test
    void addingTwoProductsShouldGiveSizeOf2() {
        CartEntity cart = user.getCart();
        cart.addProduct(product1);
        cart.addProduct(product3);
        assertEquals(cart.getProducts().size(), 2);
    }

    @Test
    void productsAddedShouldBeCorrect() {
        CartEntity cart = user.getCart();
        cart.addProduct(product3);
        cart.addProduct(product2);
        assertTrue((cart.getProducts().contains(product3)) && (cart.getProducts().contains(product2)) && (!cart.getProducts().contains(product1)));
    }

    @Test
    void removeProductShouldRemoveProduct() {
        CartEntity cart = user.getCart();
        cart.addProduct(product2);
        assertEquals(cart.getProducts().size(), 1);
        cart.removeProduct(product2);
        assertEquals(cart.getProducts().size(), 0);
    }

    @Test
    void removeProductShouldRemoveSpecifiedProduct() {
        CartEntity cart = user.getCart();
        cart.addProduct(product1);
        cart.addProduct(product2);
        assertTrue(cart.getProducts().contains(product1) && (cart.getProducts().contains(product2)));
        cart.removeProduct(product1);
        assertTrue(cart.getProducts().contains(product2) && !cart.getProducts().contains(product1));
    }

    @Test
    void removeAllProductsShouldReturnEmptyList() {
        CartEntity cart = user.getCart();
        cart.addProduct(product1);
        cart.addProduct(product2);
        cart.addProduct(product3);
        assertEquals(cart.getProducts().size(), 3);
        cart.removeAllProducts();
        assertEquals(cart.getProducts().size(), 0);
    }
}