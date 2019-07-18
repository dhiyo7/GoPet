package plugin.id.Server;

import plugin.id.Converter.BaseListResponse;
import plugin.id.Converter.BaseResponse;
import plugin.id.Model.ModelOrder;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface OrderInterface {

    @FormUrlEncoded
    @POST("user/order")
    Call<BaseResponse<ModelOrder>> order (@Header("Authorization") String api_token,
                                          @Field("id_petshop") String id_petshop,
                                          @Field("id_item") String id_item);

    @GET("user/order")
    Call<BaseListResponse<ModelOrder>> token (@Header("Authorization") String api_token);

    @GET("user/order/history")
    Call<BaseListResponse<ModelOrder>> getOrder(@Header("Authorization") String api_token);
}
