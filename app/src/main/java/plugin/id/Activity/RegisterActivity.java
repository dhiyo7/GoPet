package plugin.id.Activity;

import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import plugin.id.Converter.BaseResponse;
import plugin.id.Model.ModelUser;
import plugin.id.R;
import plugin.id.Server.ApiClient;
import plugin.id.Server.UserInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText etUsername, etEmail, etPassword, etAddress, etHP;
    private TextView tvLogin;
    private Button btnRegis;
    private UserInterface userInterface = ApiClient.getUserInterface();
    private SharedPreferences settings;
    private ModelUser mUser = new ModelUser();
    private static final String TAG = "SignUpActivity";
    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        getSupportActionBar().hide();

        declarate();
        doRegister();

    }

    private void declarate() {

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etAddress = findViewById(R.id.etAddress);
        etHP = findViewById(R.id.etHP);
        btnRegis = findViewById(R.id.btnRegis);
        loading = findViewById(R.id.loading);
        settings = getSharedPreferences("USER", MODE_PRIVATE);
    }

    private void doRegister() {

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etUsername.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String address = etAddress.getText().toString().trim();
                String phone = etHP.getText().toString().trim();
                //Toast.makeText(SignUpActivity.this, name +"\n"+ email +"\n"+password, Toast.LENGTH_SHORT).show();
                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && password.length() > 6 && name.length() > 5 ){
                    loading.setVisibility(View.VISIBLE);
                    btnRegis.setEnabled(false);
                    Call<BaseResponse<ModelUser>> user_ = userInterface.register(name, email, password, address, phone);
                    user_.enqueue(new Callback<BaseResponse<ModelUser>>() {
                        @Override
                        public void onResponse(Call<BaseResponse<ModelUser>> call, Response<BaseResponse<ModelUser>> response) {
                            if (response.isSuccessful()){
                                BaseResponse<ModelUser> body = response.body();
                                if (body.getStatus()){
                                    mUser = body.getData();
                                    if (mUser != null){
                                        Log.d(TAG, mUser.getApi_token());
                                        setLoggedIn(mUser.getApi_token(), String.valueOf(mUser.getId()));
                                        finish();
                                        Toast.makeText(RegisterActivity.this, "Selamat datang "+mUser.getName(), Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(RegisterActivity.this, "Respons sukses tanpa error hehe", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }else {
                                Toast.makeText(RegisterActivity.this, "daftar gagal", Toast.LENGTH_SHORT).show();
                            }
                            loading.setVisibility(View.INVISIBLE);
                            btnRegis.setEnabled(true);
                        }

                        @Override
                        public void onFailure(Call<BaseResponse<ModelUser>> call, Throwable t) {
                            Log.d(TAG, "LokerIT "+t.getMessage());
                            loading.setVisibility(View.INVISIBLE);
                            btnRegis.setEnabled(true);
                            Toast.makeText(RegisterActivity.this, "Ada yang tidak beres : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    Toast.makeText(RegisterActivity.this, "Isikan form Nama minimal 5 karakter dan password 6", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setLoggedIn(String token, String id) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("TOKEN", token);
        editor.putString("USER_ID", id);
        editor.commit();
        finish();
    }
}
