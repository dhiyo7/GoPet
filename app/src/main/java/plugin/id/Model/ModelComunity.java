package plugin.id.Model;

import com.google.gson.annotations.SerializedName;

public class ModelComunity {

    @SerializedName("id") private int Id;
    @SerializedName("name") private String name;
    @SerializedName("leader") private String leader;
    @SerializedName("address") private String address;
    @SerializedName("phone") private String phone;
    @SerializedName("description") private String description;
    @SerializedName("image") private String image;

    public ModelComunity() {
    }

    public ModelComunity(int id, String name, String leader, String address, String phone, String description, String image) {
        Id = id;
        this.name = name;
        this.leader = leader;
        this.address = address;
        this.phone = phone;
        this.description = description;
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

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
