package plugin.id.Server;

import plugin.id.Converter.BaseListResponse;
import plugin.id.Converter.BaseResponse;
import plugin.id.Model.ModelWashSpa;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WashspaInterface {

    @GET("washing-and-spa")
    Call<BaseListResponse<ModelWashSpa>> getWashSpa();

    @GET("washing-and-spa/{id}")
    Call<BaseResponse<ModelWashSpa>> showById (@Path("id") String id);
}
