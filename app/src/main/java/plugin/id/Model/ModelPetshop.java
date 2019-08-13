package plugin.id.Model;

import com.google.gson.annotations.SerializedName;

public class ModelPetshop {

    @SerializedName("id") private int Id;
    @SerializedName("name") private String name;
    @SerializedName("description") private String description;
    @SerializedName("street") private String street;
    @SerializedName("districts") private String districts;
    @SerializedName("city") private String city;
    @SerializedName("image") private String image;
    @SerializedName("latitude") private String latitude;
    @SerializedName("longitude") private String longitude;

    public ModelPetshop() {
    }

    public ModelPetshop(int id, String name, String description, String street, String districts, String city, String image, String latitude, String longitude) {
        Id = id;
        this.name = name;
        this.description = description;
        this.street = street;
        this.districts = districts;
        this.city = city;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistricts() {
        return districts;
    }

    public void setDistricts(String districts) {
        this.districts = districts;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
