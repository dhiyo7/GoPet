package plugin.id.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import plugin.id.Adapter.AdapterPetshop;
import plugin.id.Converter.BaseListResponse;
import plugin.id.Model.ModelFood;
import plugin.id.Model.ModelPetshop;
import plugin.id.R;
import plugin.id.Server.ApiClient;
import plugin.id.Server.PetshopInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetshopActivity extends AppCompatActivity {

    private RecyclerView rvPetshop;
    private RecyclerView.LayoutManager layoutManager;
    private List<ModelPetshop> modelPetshops;
    private RecyclerView.Adapter adapter;
    private PetshopInterface petshopInterface;
    private retrofit2.Call<BaseListResponse<ModelPetshop>> call;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petshop);

        getSupportActionBar().setTitle("Toko Hewan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        petshopInterface = ApiClient.getPetshopInterface();
        progressBar = findViewById(R.id.prograss);
        rvPetshop = findViewById(R.id.rvPetshop);
        layoutManager = new LinearLayoutManager(this);
        rvPetshop.setLayoutManager(new LinearLayoutManager(this));
        rvPetshop.setHasFixedSize(true);
    }

    public void fetchData(){
        call = petshopInterface.getPetshop();
        call.enqueue(new Callback<BaseListResponse<ModelPetshop>>() {
            @Override
            public void onResponse(Call<BaseListResponse<ModelPetshop>> call, Response<BaseListResponse<ModelPetshop>> response) {
                if(response.isSuccessful()){
                    BaseListResponse bs = response.body();
                    if(bs.getStatus()){
                        modelPetshops = bs.getData();
                        adapter = new AdapterPetshop(PetshopActivity.this, modelPetshops);
                        rvPetshop.setAdapter(adapter);
                    }
                }else{
                    Toast.makeText(PetshopActivity.this, "Cannot fetch data from server", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<BaseListResponse<ModelPetshop>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(PetshopActivity.this, "Error : "+ t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }
}
