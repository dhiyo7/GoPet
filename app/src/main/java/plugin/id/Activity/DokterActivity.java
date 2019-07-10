package plugin.id.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import plugin.id.R;

public class DokterActivity extends AppCompatActivity {

    private SharedPreferences setting;
    private Button btnScan,btnMasuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doket);
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

    @Override
    protected void onResume() {
        super.onResume();

        if (isNotLogedIn()){
            btnMasuk.setVisibility(View.VISIBLE);
            btnScan.setVisibility(View.INVISIBLE);
        }else {
            btnMasuk.setVisibility(View.INVISIBLE);
            btnScan.setVisibility(View.VISIBLE);
        }


    }

    private boolean isNotLogedIn() {
        String token = setting.getString("TOKEN", "UNDIFINED");
        return token == null || token.equals("UNDIFINED");
    }
}
