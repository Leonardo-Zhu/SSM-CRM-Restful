package service.shop;

import mapper.ShopMapper;
import model.shop.*;
import model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    private ShopMapper shopMapper;

    @Override
    public List<Item> findAllItems() {
        return shopMapper.findAllItems();
    }

    @Override
    public Item findItemById(Integer itemId) {
        return shopMapper.findItemByItemId(itemId);
    }

    @Override
    public List<Cart> findCartItemsByUserId(Integer userId) {
        return shopMapper.findCartItemsByUserId(userId);
    }

    @Override
    public void addItemToCart(Cart cart) {
        Cart hasItemNum = shopMapper.hasItemByUserId(cart);
        if (hasItemNum == null) {
            shopMapper.addItemToCart(cart);
        } else {
            Integer existItemNum = hasItemNum.getItemNum();

            cart.setItemNum(cart.getItemNum() + existItemNum);

            shopMapper.updateItemNum(cart);
        }
    }

    @Override
    public void removeCartItem(Cart cart) {
        shopMapper.removeCartItemByCart(cart);
    }

    @Override
    public void updateItemNum(Cart cart) {
        shopMapper.updateItemNum(cart);
    }

    @Override
    public List<Area> findAreasByAreaId(Integer areaId) {
        return shopMapper.findAreas(areaId);
    }


    @Override
    public void createOrder(Order order, HttpSession session) {
        StringBuilder orderId = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            orderId.append((int) (Math.random() * 10));
        }
        String createTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        User user = (User) session.getAttribute("user");

        order.setOrderId(orderId.toString());
        order.setCreateTime(createTime);
        order.setUser(user);
        shopMapper.createOrder(order);

        for (OrderItem oi :
                order.getOrderItems()) {
            oi.setOrderId(orderId.toString());
            shopMapper.addOrderItem(oi);
            shopMapper.removeCartItemByOrder(user.getUserId(), oi.getItemId());
        }

    }

    @Override
    public List<Order> findOrdersByUserId(Integer userId) {
        List<Order> orders = shopMapper.findOrdersByUserId(userId);
        return setOrderItems(orders);
    }

    @Override
    public List<Order> findUnPaidOrdersByUserId(Integer userId) {
        List<Order> orders = shopMapper.findUnPaidOrdersByUserId(userId);
        return setOrderItems(orders);
    }

    @Override
    public List<Order> findUnReceivedOrdersByUserId(Integer userId) {
        List<Order> orders = shopMapper.findUnReceivedOrdersByUserId(userId);
        return setOrderItems(orders);
    }

    private List<Order> setOrderItems(List<Order> orders) {
        for (Order order :
                orders) {
            String orderId = order.getOrderId();
            List<OrderItem> orderItems = shopMapper.findOrderItemsByOrderId(orderId);
            order.setOrderItems(orderItems);
        }
        return orders;
    }


    @Autowired
    public void setShopMapper(ShopMapper shopMapper) {
        this.shopMapper = shopMapper;
    }
}
