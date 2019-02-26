package mapper;

import model.shop.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopMapper {
    List<Item> findAllItems();

    Item findItemByItemId(Integer itemId);

    List<Cart> findCartItemsByUserId(Integer userId);

    void addItemToCart(Cart cart);

    Cart hasItemByUserId(Cart cart);

    void addCartItemNum(Cart cart);

    void removeCartItemByCart(Cart cart);

    void updateItemNum(Cart cart);

    List<Area> findAreas(Integer areaId);

    void addOrderItem(OrderItem orderItem);

    void createOrder(Order order);

    void removeCartItemByOrder(@Param("userId") Integer userId, @Param("itemId") Integer itemId);

    List<Order> findOrdersByUserId(Integer userId);

    List<OrderItem> findOrderItemsByOrderId(String orderId);

    List<Order> findUnPaidOrdersByUserId(Integer userId);

    List<Order> findUnReceivedOrdersByUserId(Integer userId);
}
