package model.shop;


public class Cart {
    private Integer userId;
    private Item item;
    private Integer itemNum;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getItemNum() {
        return itemNum;
    }

    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "userId=" + userId +
                ", item=" + item +
                ", itemNum=" + itemNum +
                '}';
    }
}
