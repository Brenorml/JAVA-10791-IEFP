package classes;

public class Item {
	private int id;
    private String description;
    private int quantity;
    private float price;

    // Construtor
    public Item(int id, String description, int quantity, float price) {
        this.id = id;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    // Métodos getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotal() {
        return price * quantity;
    }   
   
}
