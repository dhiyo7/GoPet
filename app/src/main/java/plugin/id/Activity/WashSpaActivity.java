package plugin.id.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import plugin.id.Adapter.AdapterWashSpa;
import plugin.id.Converter.BaseListResponse;
import plugin.id.Model.ModelPetshop;
import plugin.id.Model.ModelWashSpa;
import plugin.id.R;
import plugin.id.Server.ApiClient;
import plugin.id.Server.WashspaInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WashSpaActivity extends AppCompatActivity {

    private RecyclerView rvWash;
    private RecyclerView.LayoutManager layoutManager;
    private List<ModelWashSpa> modelWashSpas;
    private RecyclerView.Adapter adapter;
    private WashspaInterface washspaInterface;
    private retrofit2.Call<BaseListResponse<ModelWashSpa>> call;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wash_spa);

        getSupportActionBar().setTitle("Mandi n Spa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        washspaInterface = ApiClient.getWashspaInterface();
        progressBar = findViewById(R.id.prograss);
        rvWash = findViewById(R.id.rvWash);
        layoutManager = new LinearLayoutManager(this);
        rvWash.setLayoutManager(new LinearLayoutManager(this));
        rvWash.setHasFixedSize(true);
    }

    public void fetchData(){
        call = washspaInterface.getWashSpa();
        call.enqueue(new Callback<BaseListResponse<ModelWashSpa>>() {
            @Override
            public void onResponse(Call<BaseListResponse<ModelWashSpa>> call, Response<BaseListResponse<ModelWashSpa>> response) {
                if(response.isSuccessful()){
                    BaseListResponse bs = response.body();
                    if(bs.getStatus()){
                        modelWashSpas = bs.getData();
                        adapter = new AdapterWashSpa(WashSpaActivity.this, modelWashSpas);
                        rvWash.setAdapter(adapter);
                    }
                }else{
                    Toast.makeText(WashSpaActivity.this, "Cannot fetch data from server", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<BaseListResponse<ModelWashSpa>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(WashSpaActivity.this, "Error : "+ t.toString(), Toast.LENGTH_SHORT).show();
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
