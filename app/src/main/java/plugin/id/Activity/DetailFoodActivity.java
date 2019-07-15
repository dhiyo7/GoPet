package plugin.id.Activity;

import android.content.SharedPreferences;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

import plugin.id.Converter.BaseResponse;
import plugin.id.Model.ModelFood;
import plugin.id.Model.ModelOrder;
import plugin.id.R;
import plugin.id.Server.ApiClient;
import plugin.id.Server.FoodInterface;
import plugin.id.Server.OrderInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailFoodActivity extends AppCompatActivity {

    private TextView tvPrice, tvCategory, tvSeller;
    private Button btnBeli;
    private ImageView ivDetailFood;
    private JustifiedTextView deskripsi;
    private FoodInterface foodInterface;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private String tittle = " ";
    private SharedPreferences setting;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food);
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
        setting = getSharedPreferences("USER", MODE_PRIVATE);
        tvPrice = findViewById(R.id.tvPrice);
        tvCategory = findViewById(R.id.tvCategory);
        tvSeller = findViewById(R.id.tvSeller);
        btnBeli = findViewById(R.id.btnBeli);
        deskripsi = (JustifiedTextView) findViewById(R.id.tvDesc);
        ivDetailFood = (ImageView) findViewById(R.id.ivDetailFood);
        foodInterface = ApiClient.getFoodInterfaceService();
        collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle(" ");
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
        Call<BaseResponse<ModelFood>> request = foodInterface.showById(getId());
        request.enqueue(new Callback<BaseResponse<ModelFood>>() {
            @Override
            public void onResponse(Call<BaseResponse<ModelFood>> call, Response<BaseResponse<ModelFood>> response) {
                if (response.isSuccessful()){
                    BaseResponse body = response.body();
                    if (body.getStatus()){
                        fillAll((ModelFood) body.getData());
                    }
                }else {
                    Toast.makeText(DetailFoodActivity.this, "Tidak dapat mengambil data ke server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<ModelFood>> call, Throwable t) {
                Toast.makeText(DetailFoodActivity.this, "Ada sesuatu yang salah", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void fillAll(final ModelFood data) {
        tvCategory.setText(data.getCategory());
        tvPrice.setText(data.getPrice());
//        tvSeller.setText(data.getId_petshop());
        deskripsi.setText(fromHtml(data.getDescription()));
        Glide.with(getApplicationContext()).load(ApiClient.ENDPOINT+"image/"+data.getImage()).into(ivDetailFood);
        collapsingToolbarLayout.setTitle(data.getName());
        tittle=data.getName();
        btnBeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnBeli.setEnabled(false);
                OrderInterface orderInterface = ApiClient.getOrderInterface();
                final String id_petshop = String.valueOf(data.getId_petshop());
                final String id = String.valueOf(data.getId());
                Call<BaseResponse<ModelOrder>> request = orderInterface.order(getUserId(), id_petshop, id);
                request.enqueue(new Callback<BaseResponse<ModelOrder>>(){
                    @Override
                    public void onResponse(Call<BaseResponse<ModelOrder>> call, Response<BaseResponse<ModelOrder>> response) {
                        if(response.isSuccessful()){
                            BaseResponse<ModelOrder> body = response.body();
                            if(body != null && body.getStatus()){
                                finish();
                                Toast.makeText(DetailFoodActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(DetailFoodActivity.this, "Tidak dapat membeli", Toast.LENGTH_SHORT).show();
                            }
                        }else{
//                            Toast.makeText(DetailFoodActivity.this, getUserId()+" "+ id_petshop+ " " + id, Toast.LENGTH_SHORT).show();
                            Toast.makeText(DetailFoodActivity.this, "Failed to get response from server", Toast.LENGTH_SHORT).show();
                        }
                        btnBeli.setEnabled(true);
                    }

                    @Override
                    public void onFailure(Call<BaseResponse<ModelOrder>> call, Throwable t) {
                        Toast.makeText(DetailFoodActivity.this, "Error code : "+ t.getMessage(), Toast.LENGTH_SHORT).show();
                        btnBeli.setEnabled(true);
                    }
                });
            }
        });

    }

    private String getUserId() {
        String token = setting.getString("USER_ID", "UNDIFINED");
        return token;
    }
}
