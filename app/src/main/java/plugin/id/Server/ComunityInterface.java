package plugin.id.Server;

import plugin.id.Converter.BaseListResponse;
import plugin.id.Converter.BaseResponse;
import plugin.id.Model.ModelComunity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ComunityInterface {

    @GET("community")
    Call<BaseListResponse<ModelComunity>> getComunity();

    @GET("community/{id}")
    Call<BaseResponse<ModelComunity>> showById(@Path("id") String id);
}
