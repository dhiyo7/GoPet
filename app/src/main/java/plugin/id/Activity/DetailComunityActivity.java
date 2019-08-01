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
import plugin.id.Model.ModelComunity;
import plugin.id.R;
import plugin.id.Server.ApiClient;
import plugin.id.Server.ComunityInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailComunityActivity extends AppCompatActivity {

    private TextView leader, alamat, telpon;
    private ImageView ivDetailCommunity;
    private JustifiedTextView deskripsi;
    private ComunityInterface comunityInterface;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private String tittle = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_comunity);
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
        leader = findViewById(R.id.tvLeader);
        alamat = findViewById(R.id.tvAddress);
        telpon = findViewById(R.id.tvNohp);
        deskripsi = (JustifiedTextView) findViewById(R.id.tvDescrib);
        comunityInterface = ApiClient.getComunityInterface();
        ivDetailCommunity = findViewById(R.id.ivDetailCommunity);
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
        Call<BaseResponse<ModelComunity>> request = comunityInterface.showById(getId());
        request.enqueue(new Callback<BaseResponse<ModelComunity>>() {
            @Override
            public void onResponse(Call<BaseResponse<ModelComunity>> call, Response<BaseResponse<ModelComunity>> response) {
                if (response.isSuccessful()){
                    BaseResponse body = response.body();
                    if (body.getStatus()){
                        fillAll((ModelComunity) body.getData());
                    }
                }else {
                    Toast.makeText(DetailComunityActivity.this, "Tidak dapat mengambil data ke server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<ModelComunity>> call, Throwable t) {
                Toast.makeText(DetailComunityActivity.this, "ada sesuatu yg salah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fillAll(final ModelComunity data) {
        leader.setText(data.getLeader());
        alamat.setText(data.getAddress());
        telpon.setText(data.getPhone());
        deskripsi.setText(fromHtml(data.getDescription()));
        collapsingToolbarLayout.setTitle(data.getName());
        tittle = data.getName();
        Glide.with(getApplicationContext()).load(ApiClient.ENDPOINT+"images/"+data.getImage()).into(ivDetailCommunity);
    }

}
