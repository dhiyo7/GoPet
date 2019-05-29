package plugin.id.Model;

import com.google.gson.annotations.SerializedName;

public class ModelFood {

    @SerializedName("id") private int Id;
    @SerializedName("name") private String name;
    @SerializedName("price") private String price;
    @SerializedName("description") private String description;
    @SerializedName("category") private String category;
    @SerializedName("seller") private String seller;
    @SerializedName("image") private String image;

    public ModelFood() {
    }

    public ModelFood(int id, String name, String price, String description, String category, String seller, String image) {
        Id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.seller = seller;
        this.image = image;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
