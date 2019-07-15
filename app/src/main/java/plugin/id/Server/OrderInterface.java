package plugin.id.Server;

import plugin.id.Converter.BaseResponse;
import plugin.id.Model.ModelOrder;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface OrderInterface {

    @FormUrlEncoded
    @POST("user/order")
    Call<BaseResponse<ModelOrder>> order (@Field("id_user") String id_user, @Field("id_petshop") String id_petshop,
                                          @Field("id_food") String id_food);
}
