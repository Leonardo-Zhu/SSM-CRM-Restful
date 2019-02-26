package service.shop;

import model.shop.*;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ShopService {
    List<Item> findAllItems();

    Item findItemById(Integer itemId);

    List<Cart> findCartItemsByUserId(Integer userId);

    void addItemToCart(Cart cart);

    void removeCartItem(Cart cart);

    void updateItemNum(Cart cart);

    List<Area> findAreasByAreaId(Integer areaId);

    void createOrder(Order order, HttpSession session);

    List<Order> findOrdersByUserId(Integer userId);

    List<Order> findUnPaidOrdersByUserId(Integer userId);

    List<Order> findUnReceivedOrdersByUserId(Integer userId);
}
