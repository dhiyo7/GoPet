package plugin.id.Server;

import plugin.id.Converter.BaseResponse;
import plugin.id.Model.ModelUser;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserInterface {

    @FormUrlEncoded
    @POST("user/login")
    Call<BaseResponse<ModelUser>> login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("user/register")
    Call<BaseResponse<ModelUser>> register(@Field("name") String name, @Field("email") String email, @Field("password") String password, @Field("address") String address, @Field("phone") String phone);

    @FormUrlEncoded
    @POST("user/diagnosis")
    Call<BaseResponse<ModelUser>> diagnosa(@Header("Authorization") String api_token, @Field("pet_name") String pet_name);
}
