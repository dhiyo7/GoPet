package plugin.id.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.List;

import plugin.id.Adapter.AdapterHistoryDiagnosa;
import plugin.id.Converter.BaseListResponse;
import plugin.id.Model.ModelUser;
import plugin.id.R;
import plugin.id.Server.ApiClient;
import plugin.id.Server.UserInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DokterActivity extends AppCompatActivity {

    private SharedPreferences setting;
    private Button btnScan,btnMasuk;
    private RecyclerView rvDokter;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<ModelUser> data;
    private UserInterface userInterface;
    private retrofit2.Call<BaseListResponse<ModelUser>> call;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }


    private void init() {

        userInterface = ApiClient.getUserInterface();
        rvDokter = findViewById(R.id.rvDokter);
        rvDokter.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvDokter.setLayoutManager(layoutManager);
        setting = getSharedPreferences("USER", MODE_PRIVATE);
        btnScan = findViewById(R.id.btnScan);
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(DokterActivity.this, ScanQR.class);
                startActivity(intent);
            }
        });
        btnMasuk = findViewById(R.id.btnMasuk);
        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DokterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void fetchData(){
        call = userInterface.getHistoriDiagnosi("Bearer " +getToken());
        call.enqueue(new Callback<BaseListResponse<ModelUser>>() {
            @Override
            public void onResponse(Call<BaseListResponse<ModelUser>> call, Response<BaseListResponse<ModelUser>> response) {
                if (response.isSuccessful()){
                    BaseListResponse body = response.body();
                    if (body.getStatus()){
                        data = body.getData();
                        mAdapter = new AdapterHistoryDiagnosa(DokterActivity.this, data);
                        rvDokter.setAdapter(mAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseListResponse<ModelUser>> call, Throwable t) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        fetchData();

        if (isNotLogedIn()){
            btnMasuk.setVisibility(View.VISIBLE);
            btnScan.setVisibility(View.INVISIBLE);
        }else {
            btnMasuk.setVisibility(View.INVISIBLE);
            btnScan.setVisibility(View.VISIBLE);
        }


    }

    private String getToken(){
        SharedPreferences settings = getSharedPreferences("USER", MODE_PRIVATE);
        return settings.getString("TOKEN", "UNDIFINED");
    }

    private boolean isNotLogedIn() {
        String token = setting.getString("TOKEN", "UNDIFINED");
        return token == null || token.equals("UNDIFINED");
    }
}
