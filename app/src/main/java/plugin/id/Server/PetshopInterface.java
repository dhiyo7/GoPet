package plugin.id.Server;

import plugin.id.Converter.BaseListResponse;
import plugin.id.Converter.BaseResponse;
import plugin.id.Model.ModelPetshop;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PetshopInterface {

    @GET("petshop")
    Call<BaseListResponse<ModelPetshop>> getPetshop();

    @GET("petshop/{id}")
    Call<BaseResponse<ModelPetshop>> showById (@Path("id") String id);
}
