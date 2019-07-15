package plugin.id.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import plugin.id.Adapter.AdapterComunity;
import plugin.id.Converter.BaseListResponse;
import plugin.id.Model.ModelComunity;
import plugin.id.R;
import plugin.id.Server.ApiClient;
import plugin.id.Server.ComunityInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComunityActivity extends AppCompatActivity {

    private RecyclerView rvComunity;
    private RecyclerView.LayoutManager layoutManager;
    private List<ModelComunity> modelComunities;
    private RecyclerView.Adapter adapter;
    private ComunityInterface comunityInterface;
    private ProgressBar progressBar;
    private retrofit2.Call<BaseListResponse<ModelComunity>> call;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comunity);

        getSupportActionBar().setTitle("Komunitas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();

    }

    private void initViews() {

        comunityInterface = ApiClient.getComunityInterface();
        progressBar = findViewById(R.id.prograss);
        rvComunity = findViewById(R.id.rvComunity);
        layoutManager = new LinearLayoutManager(this);
        rvComunity.setLayoutManager(new LinearLayoutManager(this));
        rvComunity.setHasFixedSize(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void fetchData(){
        call = comunityInterface.getComunity();
        call.enqueue(new Callback<BaseListResponse<ModelComunity>>() {
            @Override
            public void onResponse(Call<BaseListResponse<ModelComunity>> call, Response<BaseListResponse<ModelComunity>> response) {
                if(response.isSuccessful()){
                    BaseListResponse bs = response.body();
                    if(bs.getStatus()){
                        modelComunities = bs.getData();
                        adapter = new AdapterComunity(ComunityActivity.this, modelComunities);
                        rvComunity.setAdapter(adapter);
                    }
                }else{
                    Toast.makeText(ComunityActivity.this, "Cannot fetch data from server", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<BaseListResponse<ModelComunity>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(ComunityActivity.this, "Error : "+ t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        fetchData();
    }
}
