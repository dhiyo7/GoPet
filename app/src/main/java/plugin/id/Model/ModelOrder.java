package plugin.id.Model;

import com.google.gson.annotations.SerializedName;

public class ModelOrder {

    @SerializedName("api_token") private String api_token;
    @SerializedName("id_petshop") private String id_petshop;
    @SerializedName("id_food") private int id_food;
    @SerializedName("id") private int id;
    @SerializedName("petshop") private String petshop;
    @SerializedName("price") private String price;
    @SerializedName("item") private  String item;
    @SerializedName("status") private String status;
    @SerializedName("created_at") private String created_at;

    public ModelOrder() {
    }

    public ModelOrder(String api_token, String id_petshop, int id_food, int id, String petshop, String price, String item, String status, String created_at) {
        this.api_token = api_token;
        this.id_petshop = id_petshop;
        this.id_food = id_food;
        this.id = id;
        this.petshop = petshop;
        this.price = price;
        this.item = item;
        this.status = status;
        this.created_at = created_at;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    public String getId_petshop() {
        return id_petshop;
    }

    public void setId_petshop(String id_petshop) {
        this.id_petshop = id_petshop;
    }

    public int getId_food() {
        return id_food;
    }

    public void setId_food(int id_food) {
        this.id_food = id_food;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPetshop() {
        return petshop;
    }

    public void setPetshop(String petshop) {
        this.petshop = petshop;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
