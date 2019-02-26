package controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import model.customer.BaseDict;
import model.customer.Customer;
import model.customer.QueryVo;
import model.shop.*;
import model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.customer.CustomerService;
import service.shop.ShopService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/system")
public class SystemController {

    @Value("${customer.dict.industry}")
    private String industry;
    @Value("${customer.dict.source}")
    private String source;
    @Value("${customer.dict.level}")
    private String level;

    private CustomerService customerService;
    private ShopService shopService;

    @RequestMapping("/home")
    public String home() {
        return "system/home";
    }

    @RequestMapping("/about")
    public String about() {
        return "system/about";
    }

    @RequestMapping("/customer/logical")
    public String customer(Model model) {
        queryVoList(model);
        return "system/cust_logical";
    }

    @RequestMapping("/customer/list")
    @ResponseBody
    public Map<String, List<Customer>> customerList(QueryVo vo) {
        return customerService.findCustomerByVo(vo);
    }


    @RequestMapping("/customer/physical/{page}")
    public String customer(@PathVariable("page") Integer page, QueryVo vo, Model model) {
        if (page == null) {
            page = 1;
        }
        PageHelper.startPage(page, 15);
        List<Customer> customerList = customerService.findCustomerByVo(vo).get("data");
        PageInfo<Customer> pageInfo = new PageInfo<>(customerList);

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("customerList", customerList);

        return "system/cust_physical";
    }

    @RequestMapping("/item")
    public String item(Model model) {
        List<Item> items = shopService.findAllItems();
        model.addAttribute("item",items);
        return "system/shop_item";
    }

    @RequestMapping("/itemInfo/{itemId}")
    public String itemInfo(@PathVariable("itemId") Integer itemId,Model model){
        Item item = shopService.findItemById(itemId);
        model.addAttribute("item",item);
        return "system/shop_itemInfo";
    }

    @RequestMapping(value = "/addItemToCart",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addItemToCart(Cart cart){
        shopService.addItemToCart(cart);
        return "添加成功";
    }

    @RequestMapping("/cart")
    public String cart(HttpSession session,Model model) {
        User user = (User) session.getAttribute("user");
        List<Cart> cartItems = shopService.findCartItemsByUserId(user.getUserId());

        model.addAttribute("cartItems",cartItems);
        return "system/shop_cart";
    }

    @RequestMapping("/cart/itemNumChanged")
    public String updateItemNum(Cart cart){
        shopService.updateItemNum(cart);
        return "system/shop_cart";
    }

    @RequestMapping("/cart/removeItem")
    public String removeCartItem(Cart cart){
        shopService.removeCartItem(cart);
        return "system/shop_cart";
    }

    @RequestMapping("/cart/settle")
    public String itemsSettle(@RequestBody List<OrderItem> orderItems,HttpSession session){
        for (OrderItem orderItem :
                orderItems) {
            Item item = shopService.findItemById(orderItem.getItemId());
            orderItem.setItem(item);
        }
        session.setAttribute("orderItems",orderItems);
        return "system/shop_cart";
    }

    @RequestMapping("/settle")
    public String settle(){
        return "system/shop_settle";
    }

    @RequestMapping("/settle/submit")
    @ResponseBody
    public String settleSubmit(@RequestBody Order order,HttpSession session){
        shopService.createOrder(order,session);
        return "system/shop_settle";
    }

    @RequestMapping("/settle/area")
    @ResponseBody
    public List<Area> area(Integer areaId){
        return shopService.findAreasByAreaId(areaId);
    }


    @RequestMapping("/order")
    public String order(HttpSession session,Model model) {
        User user = (User) session.getAttribute("user");
        List<Order> orders = shopService.findOrdersByUserId(user.getUserId());

        model.addAttribute("orders",orders);
        return "system/shop_order";
    }

    @RequestMapping("/order/unpaid")
    public String unpaidOrder(HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        List<Order> orders = shopService.findUnPaidOrdersByUserId(user.getUserId());
        model.addAttribute("orders",orders);
        return "system/shop_order::orders";
    }

    @RequestMapping("/order/unreceived")
    public String unreceivedOrder(HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        List<Order> orders = shopService.findUnReceivedOrdersByUserId(user.getUserId());
        model.addAttribute("orders",orders);
        return "system/shop_order::orders";
    }


    private void queryVoList(Model model) {
        List<BaseDict> industryList = customerService.findDictByCode(industry);
        List<BaseDict> sourceList = customerService.findDictByCode(source);
        List<BaseDict> levelList = customerService.findDictByCode(level);

        model.addAttribute("industryList", industryList);
        model.addAttribute("sourceList", sourceList);
        model.addAttribute("levelList", levelList);
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }
}
