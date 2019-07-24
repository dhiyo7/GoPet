package plugin.id.Activity;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import plugin.id.R;

public class DiagnosaActivity extends AppCompatActivity {

    private TextInputEditText etPet;
    private Button btnKirim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosa);

        initView();
        fetchData();
    }

    private void initView() {
        etPet = findViewById(R.id.etPet);
        btnKirim = findViewById(R.id.btnKirim);
    }

    private void fetchData() {
    }


}
