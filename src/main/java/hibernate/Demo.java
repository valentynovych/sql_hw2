package hibernate;


import hibernate.entity.Product;
import hibernate.entity.User;
import hibernate.entity.UserDetails;
import hibernate.service.OrderService;
import hibernate.service.ProductService;
import hibernate.service.UserDetailsService;
import hibernate.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {


        UserService userService = new UserService();
        ProductService productService = new ProductService();
//        User user = userService.getUserById(2L);
//        List<Product> productList = new ArrayList<>();
//        productList.add(productService.getProductById(1L));
//        productList.add(productService.getProductById(2L));
//        productList.add(productService.getProductById(3L));
//        productList.add(productService.getProductById(4L));
//
//        user.setProducts(productList);
//        userService.updateUserById(user);

        User user = userService.getUserById(1L);
        OrderService orderService = new OrderService();
        //orderService.createOrder(user);

        UserDetailsService userDetailsService = new UserDetailsService();
        UserDetails userDetails = new UserDetails();
        userDetails.setDetailsId(26L);
        UserDetails get = userDetailsService.getUsersDetailById(userDetails);

        System.out.println(get);
    }

}
