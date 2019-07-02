package plugin.id.Activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

import plugin.id.Converter.BaseResponse;
import plugin.id.Model.ModelWashSpa;
import plugin.id.R;
import plugin.id.Server.ApiClient;
import plugin.id.Server.WashspaInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailWashSpaActivity extends AppCompatActivity {

    private TextView tvStreet, tvDistricts, tvCity;
    private ImageView ivDetailWash;
    private JustifiedTextView tvDescripWash;
    private WashspaInterface washspaInterface;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private String tittle = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wash_spa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initComponents();

        fetchData();

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private Spanned fromHtml(String s) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(s, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(s);
        }
    }

    private void initComponents() {

        tvStreet = findViewById(R.id.tvStreet);
        tvDistricts = findViewById(R.id.tvDistricts);
        tvCity = findViewById(R.id.tvCity);
        washspaInterface = ApiClient.getWashspaInterface();
        ivDetailWash = (ImageView) findViewById(R.id.ivDetailWash);
        collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle("");
        appBarLayout = findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                boolean isShow = false;
                int scrollRange = -1;
                if (scrollRange == -1){
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + i == 0){
                    collapsingToolbarLayout.setTitle(tittle);
                    isShow = true;
                }else if (isShow){
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }else if (scrollRange + i >0 ){
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });

        tvDescripWash = (JustifiedTextView) findViewById(R.id.tvDescripWash);
        if (tvDescripWash == null){
            tvDescripWash.setText("Belum ada deskripsi");
        }

    }

    private String getId(){ return String.valueOf(getIntent().getIntExtra("ID", 0)); }

    private void fetchData() {

        Call<BaseResponse<ModelWashSpa>> request = washspaInterface.showById(getId());
        request.enqueue(new Callback<BaseResponse<ModelWashSpa>>() {
            @Override
            public void onResponse(Call<BaseResponse<ModelWashSpa>> call, Response<BaseResponse<ModelWashSpa>> response) {
                if (response.isSuccessful()){
                    BaseResponse body = response.body();
                    if (body.getStatus()){
                        fillAll((ModelWashSpa) body.getData());
                    }
                }else {
                    Toast.makeText(DetailWashSpaActivity.this, "Tidak dapat mengambil data ke server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<ModelWashSpa>> call, Throwable t) {
                Toast.makeText(DetailWashSpaActivity.this, "Ada sesuatu yang salah", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void fillAll(final ModelWashSpa data) {

        tvStreet.setText(data.getStreet());
        tvDistricts.setText(data.getDistricts());
        tvCity.setText(data.getCity());
        tvDescripWash.setText(fromHtml(data.getDescription()));
        collapsingToolbarLayout.setTitle(data.getName());
        tittle = data.getName();
        Glide.with(getApplicationContext()).load(ApiClient.ENDPOINT+"images/"+data.getImage()).into(ivDetailWash);
    }
}
