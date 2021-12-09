package ar.edu.unlam.tallerweb1.modelo;

public class OrderData {

    private int quantity;
    private String title;
    private String description;
    private float price;

    public String getTitle() {
        return title;
    }

    public OrderData setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public OrderData setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderData setDescription(String description) {
        this.description = description;
        return this;
    }

    public float getPrice() {
        return price;
    }

    public OrderData setPrice(float price) {
        this.price = price;
        return this;
    }
}
