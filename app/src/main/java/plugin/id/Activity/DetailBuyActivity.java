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
import plugin.id.Model.ModelBuyAnimal;
import plugin.id.R;
import plugin.id.Server.ApiClient;
import plugin.id.Server.BuyanimalInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailBuyActivity extends AppCompatActivity {

    private TextView tvName, tvAddress, tvNohp;
    private JustifiedTextView tvDescribBuy;
    private ImageView ivDetailBuy;
    private BuyanimalInterface buyanimalInterface;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private String tittle = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_buy);
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
        tvName = findViewById(R.id.tvName);
        tvAddress = findViewById(R.id.tvAddress);
        tvNohp = findViewById(R.id.tvNohp);
        ivDetailBuy = findViewById(R.id.ivDetailBuy);
        tvDescribBuy = (JustifiedTextView) findViewById(R.id.tvDescribBuy);
        buyanimalInterface = ApiClient.getBuyanimalInterface();
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
    }

    private String getId(){ return String.valueOf(getIntent().getIntExtra("ID", 0));}

    private void fetchData() {
        Call<BaseResponse<ModelBuyAnimal>> request = buyanimalInterface.showById(getId());
        request.enqueue(new Callback<BaseResponse<ModelBuyAnimal>>() {
            @Override
            public void onResponse(Call<BaseResponse<ModelBuyAnimal>> call, Response<BaseResponse<ModelBuyAnimal>> response) {
                if (response.isSuccessful()){
                    BaseResponse body = response.body();
                    if (body.getStatus()){
                        fillAll((ModelBuyAnimal) body.getData());
                    }
                }else {
                    Toast.makeText(DetailBuyActivity.this, "Tidak dapat mengambil data ke server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<ModelBuyAnimal>> call, Throwable t) {
                Toast.makeText(DetailBuyActivity.this, "Ada sesuatu yang salah", Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void fillAll(final ModelBuyAnimal data) {

        tvAddress.setText(data.getAddress());
        tvNohp.setText(data.getPhone());
        tvDescribBuy.setText(fromHtml(data.getDescription()));
        collapsingToolbarLayout.setTitle(data.getName());
        tittle = data.getName();
        Glide.with(getApplicationContext()).load(ApiClient.ENDPOINT+"images/"+data.getImage()).into(ivDetailBuy);
    }
}
