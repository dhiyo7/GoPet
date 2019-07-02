package plugin.id.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import plugin.id.Adapter.AdapterFood;
import plugin.id.Converter.BaseListResponse;
import plugin.id.Model.ModelFood;
import plugin.id.R;
import plugin.id.Server.ApiClient;
import plugin.id.Server.FoodInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodActivity extends AppCompatActivity {

    private RecyclerView rvFood;
    private RecyclerView.LayoutManager layoutManager;
    private List<ModelFood> modelFoods;
    private RecyclerView.Adapter adapter;
    private FoodInterface foodInterface;
    ProgressBar prograss;
    private retrofit2.Call<BaseListResponse<ModelFood>> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        getSupportActionBar().setTitle("Pakan n Obat");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        foodInterface = ApiClient.getFoodInterfaceService();
        prograss = findViewById(R.id.prograss);
        rvFood = findViewById(R.id.rvFood);
        layoutManager = new LinearLayoutManager(this);
        rvFood.setLayoutManager(new LinearLayoutManager(this));
//        adapter.notifyDataSetChanged();
        rvFood.setHasFixedSize(true);

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public void fetchData(){
        call = foodInterface.getFood();
        call.enqueue(new Callback<BaseListResponse<ModelFood>>() {
            @Override
            public void onResponse(Call<BaseListResponse<ModelFood>> call, Response<BaseListResponse<ModelFood>> response) {
                if(response.isSuccessful()){
                    BaseListResponse bs = response.body();
                    if(bs.getStatus()){
                        modelFoods = bs.getData();
                        adapter = new AdapterFood(FoodActivity.this, modelFoods);
                        rvFood.setAdapter(adapter);
                    }
                }else{
                    Toast.makeText(FoodActivity.this, "Cannot fetch data from server", Toast.LENGTH_SHORT).show();
                }
                prograss.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<BaseListResponse<ModelFood>> call, Throwable t) {
                prograss.setVisibility(View.INVISIBLE);
                Toast.makeText(FoodActivity.this, "Error : "+ t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        fetchData();
    }
}
