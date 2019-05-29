package plugin.id.Server;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String ENDPOINT = "http://gopet-app.herokuapp.com/";
    private static final String BASE_URL = "http://gopet-app.herokuapp.com//api/";
    private static Retrofit retrofit;

    private static Retrofit getApiClient(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiInterface getApiInterfaceService(){
        return getApiClient().create(ApiInterface.class);
    }
}
