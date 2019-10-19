package model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Cart {

    private User user;
    private Date createdDate;
    private final Map<Product, Integer> items = new HashMap<>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public Map<Product, Integer> addItem(Product item, Integer quantity) {
        Integer oldQt = this.items.get(item);
        this.items.put(item, oldQt + quantity);
        return this.items;
    }

    public Map<Product, Integer> removeItem(Product item, Integer quantity) {
        this.items.remove(item);
        return this.items;
    }

    public Map<Product, Integer> clearItems() {
        this.items.clear();
        return this.items;
    }

}
