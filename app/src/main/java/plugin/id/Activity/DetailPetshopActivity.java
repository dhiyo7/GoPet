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

import org.w3c.dom.Text;

import plugin.id.Converter.BaseResponse;
import plugin.id.Model.ModelPetshop;
import plugin.id.R;
import plugin.id.Server.ApiClient;
import plugin.id.Server.PetshopInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPetshopActivity extends AppCompatActivity {
    private TextView tvStreet, tvDistricts, tvCity;
    private ImageView ivDetailPet;
    private JustifiedTextView deskripsi;
    private PetshopInterface petshopInterface;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private String tittle = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_petshop);
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
        deskripsi = (JustifiedTextView) findViewById(R.id.tvDescrib);
        petshopInterface = ApiClient.getPetshopInterface();
        ivDetailPet = (ImageView) findViewById(R.id.ivDetailPet);
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
        Call<BaseResponse<ModelPetshop>> request = petshopInterface.showById(getId());
        request.enqueue(new Callback<BaseResponse<ModelPetshop>>() {
            @Override
            public void onResponse(Call<BaseResponse<ModelPetshop>> call, Response<BaseResponse<ModelPetshop>> response) {
                if (response.isSuccessful()){
                    BaseResponse body = response.body();
                    if (body.getStatus()){
                        fillAll((ModelPetshop) body.getData());
                    }
                }else {
                    Toast.makeText(DetailPetshopActivity.this, "Tidak dapat mengambil data ke server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<ModelPetshop>> call, Throwable t) {
                Toast.makeText(DetailPetshopActivity.this, "Ada sesuatu yang salah", Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void fillAll(final ModelPetshop data) {

        tvStreet.setText(data.getStreet());
        tvDistricts.setText(data.getDistricts());
        tvCity.setText(data.getCity());
        deskripsi.setText(fromHtml(data.getDescription()));
        collapsingToolbarLayout.setTitle(data.getName());
        tittle = data.getName();
        Glide.with(getApplicationContext()).load(ApiClient.ENDPOINT+"images/"+data.getImage()).into(ivDetailPet);
    }
}
