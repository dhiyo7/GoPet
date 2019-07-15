package plugin.id.Model;

import com.google.gson.annotations.SerializedName;

public class ModelOrder {

    @SerializedName("id_user") private int id_user;
    @SerializedName("id_petshop") private int id_petshop;
    @SerializedName("id_food") private int id_food;

    public ModelOrder() {
    }

    public ModelOrder(int id_user, int id_petshop, int id_food) {
        this.id_user = id_user;
        this.id_petshop = id_petshop;
        this.id_food = id_food;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_petshop() {
        return id_petshop;
    }

    public void setId_petshop(int id_petshop) {
        this.id_petshop = id_petshop;
    }

    public int getId_food() {
        return id_food;
    }

    public void setId_food(int id_food) {
        this.id_food = id_food;
    }
}
