import mapper.CustomerMapper;
import mapper.ShopMapper;
import mapper.UserMapper;
import model.customer.BaseDict;
import model.customer.Customer;
import model.shop.Area;
import model.shop.Cart;
import model.shop.Item;
import model.user.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MapperTest {

    private ApplicationContext applicationContext;

    @Before
    public void setUp(){
        applicationContext = new ClassPathXmlApplicationContext("config/spring-mybatis.xml");
    }

    @Test
    public void testAddUser(){
        UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");

        User user = new User();
        user.setUserName("李四");
        user.setUserPassword("123");
        user.setUserEmail("273@qq.com");

        userMapper.addUser(user);
    }

    @Test
    public void testCheckUser(){
        UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");

        User user = new User();
        user.setUserName("张三");
        User userCheck = userMapper.checkUser(user);
        System.out.println(userCheck);
    }

    @Test
    public void testDict(){
        CustomerMapper customerMapper = (CustomerMapper) applicationContext.getBean("customerMapper");

        List<BaseDict> dictList = customerMapper.findDictByCode("001");

        System.out.println(dictList);
    }

    @Test
    public void testFindCustomerByVo(){
        CustomerMapper customerMapper = (CustomerMapper) applicationContext.getBean("customerMapper");
        List<Customer> customerByVo = customerMapper.findCustomerByVo(null);
        for (Customer c :
                customerByVo) {
            System.out.println(c);
        }
    }

    @Test
    public void testFindItems(){
        ShopMapper shopMapper = (ShopMapper) applicationContext.getBean("shopMapper");
        List<Item> items = shopMapper.findAllItems();
        for (Item i :
                items) {
            System.out.println(i);
        }
    }


    @Test
    public void testFindCartItemByUserId(){
        ShopMapper shopMapper = (ShopMapper) applicationContext.getBean("shopMapper");
        List<Cart> cartItems = shopMapper.findCartItemsByUserId(9);
        System.out.println(cartItems);
    }

    @Test
    public void testAddItemToCart(){
        ShopMapper shopMapper = (ShopMapper) applicationContext.getBean("shopMapper");
        Cart cart = new Cart();
        Item item = new Item();
        item.setItemId(4);
        cart.setUserId(9);
        cart.setItem(item);
        cart.setItemNum(1);
        shopMapper.addItemToCart(cart);
    }

    @Test
    public void testAddCartItemNum(){
        ShopMapper shopMapper = (ShopMapper) applicationContext.getBean("shopMapper");

    }

    @Test
    public void testUpdateItemNum(){
        ShopMapper shopMapper = (ShopMapper) applicationContext.getBean("shopMapper");

    }

    @Test
    public void testFindAreaName(){
        ShopMapper shopMapper = (ShopMapper) applicationContext.getBean("shopMapper");
        List<Area> area = shopMapper.findAreas(0);
        for (Area a :
                area) {
            System.out.println(a);
        }
    }

    @Test
    public void testUpdateCartItemNum(){
        ShopMapper shopMapper = (ShopMapper) applicationContext.getBean("shopMapper");
        Cart cart = new Cart();
        Item item = new Item();
        item.setItemId(1);
        cart.setUserId(9);
        cart.setItem(item);

        System.out.println( shopMapper.hasItemByUserId(cart));
    }
}
