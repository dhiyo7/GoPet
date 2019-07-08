package plugin.id.Server;

import plugin.id.Converter.BaseListResponse;
import plugin.id.Converter.BaseResponse;
import plugin.id.Model.ModelBuyAnimal;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BuyanimalInterface {

    @GET("buying-animal")
    Call<BaseListResponse<ModelBuyAnimal>> getBuy();

    @GET("buying-animal/{id}")
    Call<BaseResponse<ModelBuyAnimal>> showById (@Path("id") String id);
}
