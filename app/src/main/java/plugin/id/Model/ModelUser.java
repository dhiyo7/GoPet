package plugin.id.Model;

import com.google.gson.annotations.SerializedName;

public class ModelUser {

    @SerializedName("id") private int id;
    @SerializedName("name") private String name;
    @SerializedName("email") private String email;
    @SerializedName("password") private String password;
    @SerializedName("api_token") private String api_token;
    @SerializedName("address") private String address;
    @SerializedName("phone") private  String phone;
    @SerializedName("pet_name") private String pet_name;
    @SerializedName("id_doctor") private int id_doctor;

    public ModelUser() {
    }

    public ModelUser(int id, String name, String email, String password, String api_token, String address, String phone, String pet_name, int id_doctor) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.api_token = api_token;
        this.address = address;
        this.phone = phone;
        this.pet_name = pet_name;
        this.id_doctor = id_doctor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
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

    public String getPet_name() {
        return pet_name;
    }

    public void setPet_name(String pet_name) {
        this.pet_name = pet_name;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }
}
