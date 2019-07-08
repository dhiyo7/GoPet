package plugin.id.Model;

import com.google.gson.annotations.SerializedName;

public class ModelBuyAnimal {

    @SerializedName("id") private int Id;
    @SerializedName("name") private String name;
    @SerializedName("description") private String description;
    @SerializedName("address") private String address;
    @SerializedName("phone") private String phone;
    @SerializedName("image") private String image;

    public ModelBuyAnimal() {
    }

    public ModelBuyAnimal(int id, String name, String description, String address, String phone, String image) {
        Id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.phone = phone;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
