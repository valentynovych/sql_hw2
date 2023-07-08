package jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Demo {
    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) {

        //logger.trace("Entering application.");
        logger.info("Info Log");
        logger.error("Error log");
        logger.warn("Warn log");
        logger.debug("debug log");

//        UsersService usersService = new UsersService();
//        ProductsService productsService = new ProductsService();
//        ShoppingCardService cardService = new ShoppingCardService();
//        OrdersService ordersService = new OrdersService();
//
//        Users users = new Users();
//        users.setUserId(12L);
//        users.setDetailsId(12L);
//        users.setCardId(12L);
//        users.setFirstName("Nikolay");
//        users.setOrdersCount(0);
//        usersService.addUser(users);
//        Users getUser = usersService.getUserById(12L);
//        System.out.println("========================");
//        System.out.println("New User from DB");
//        System.out.println(getUser);
//        usersService.deleteUserById(12L);
//
//        List<Users> usersList = usersService.getAllUsers();
//        System.out.println("========================");
//        System.out.println("All users");
//        for (Users user : usersList){
//            System.out.println(user);
//        }
//        System.out.println("========================");
//
//
//        Products product = new Products();
//        product.setProductId(25L);
//        product.setProductName("new product");
//        product.setProductPrice(25.0);
//        product.setAmount(25);
//        productsService.addProducts(product);
//
//        System.out.println("========================");
//        System.out.println("New product from DB");
//        Products newProduct = productsService.getProductById(25L);
//        System.out.println(newProduct);
//
//        System.out.println("========================");
//        System.out.println("All products from DB");
//        List<Products> productsList = productsService.getAllProducts();
//
//        for (Products products : productsList){
//            System.out.println(products);
//        }
//
//        System.out.println("========================");
//        System.out.println("All orders from DB");
//        List<Orders> ordersList = ordersService.getAllUserOrdersById(1L);
//        for (Orders order : ordersList){
//            System.out.println(order);
//        }
//
//        ShoppingCard shoppingCard = new ShoppingCard();
//        shoppingCard.setCardId(1L);
//        shoppingCard.setProduct_id(4L);
//        cardService.addProductToCard(shoppingCard);
//        shoppingCard.setProduct_id(4L);
//        cardService.addProductToCard(shoppingCard);
//        shoppingCard.setProduct_id(5L);
//        cardService.addProductToCard(shoppingCard);
//        shoppingCard.setProduct_id(6L);
//        cardService.addProductToCard(shoppingCard);
//        Users getUserById = usersService.getUserById(1L);
//        Orders newOrder = ordersService.createOrder(getUserById);
//        System.out.println("========================");
//        System.out.println("Created order");
//        System.out.println(newOrder);
//
//        System.out.println("========================");
//        System.out.println("All orders by id");
//        List<Orders> ordersList1 = ordersService.getAllUserOrdersById(1L);
//        for (Orders orders : ordersList1) {
//            System.out.println(orders);
//        }
    }
}

