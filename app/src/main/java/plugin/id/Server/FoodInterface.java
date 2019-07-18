package plugin.id.Server;

import plugin.id.Converter.BaseListResponse;
import plugin.id.Converter.BaseResponse;
import plugin.id.Model.ModelFood;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FoodInterface {

    @GET("item")
    Call<BaseListResponse<ModelFood>> getFood();

    @GET("item/{id}")
    Call<BaseResponse<ModelFood>> showById(@Path("id") String id);
}
