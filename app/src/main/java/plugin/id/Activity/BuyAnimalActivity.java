package plugin.id.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import plugin.id.Adapter.AdapterBuyAnimal;
import plugin.id.Adapter.AdapterFood;
import plugin.id.Converter.BaseListResponse;
import plugin.id.Model.ModelBuyAnimal;
import plugin.id.R;
import plugin.id.Server.ApiClient;
import plugin.id.Server.BuyanimalInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyAnimalActivity extends AppCompatActivity {

    private RecyclerView rvBuy;
    private RecyclerView.LayoutManager layoutManager;
    private List<ModelBuyAnimal> modelBuyAnimals;
    private RecyclerView.Adapter adapter;
    private BuyanimalInterface buyanimalInterface;
    private ProgressBar progressBar;
    private retrofit2.Call<BaseListResponse<ModelBuyAnimal>> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_animal);

        getSupportActionBar().setTitle("Jual Beli Hewan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buyanimalInterface = ApiClient.getBuyanimalInterface();
        progressBar = findViewById(R.id.prograss);
        rvBuy =  findViewById(R.id.rvBuy);
        layoutManager = new LinearLayoutManager(this);
        rvBuy.setLayoutManager(new LinearLayoutManager(this));
        rvBuy.setHasFixedSize(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void fetchData(){
        call = buyanimalInterface.getBuy();
        call.enqueue(new Callback<BaseListResponse<ModelBuyAnimal>>() {
            @Override
            public void onResponse(Call<BaseListResponse<ModelBuyAnimal>> call, Response<BaseListResponse<ModelBuyAnimal>> response) {
                if(response.isSuccessful()){
                    BaseListResponse bs = response.body();
                    if(bs.getStatus()){
                        modelBuyAnimals = bs.getData();
                        adapter = new AdapterBuyAnimal(BuyAnimalActivity.this, modelBuyAnimals);
                        rvBuy.setAdapter(adapter);
                    }
                }else{
                    Toast.makeText(BuyAnimalActivity.this, "Cannot fetch data from server", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<BaseListResponse<ModelBuyAnimal>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(BuyAnimalActivity.this, "Error : "+ t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        fetchData();
    }
}
