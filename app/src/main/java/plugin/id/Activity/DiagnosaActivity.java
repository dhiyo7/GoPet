package plugin.id.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import plugin.id.Converter.BaseResponse;
import plugin.id.Model.ModelUser;
import plugin.id.R;
import plugin.id.Server.ApiClient;
import plugin.id.Server.UserInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiagnosaActivity extends AppCompatActivity {

    private TextInputEditText etPet;
    private Button btnKirim;
    private UserInterface userInterface = ApiClient.getUserInterface();
    private SharedPreferences settings;
    private ModelUser mUser = new ModelUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosa);

        initView();
        doDiagnosa();
    }


    private void initView() {
        etPet = findViewById(R.id.etPet);
        btnKirim = findViewById(R.id.btnKirim);
        userInterface = ApiClient.getUserInterface();
        settings = getSharedPreferences("USER", MODE_PRIVATE);

    }

    private void doDiagnosa() {
        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String pet = etPet.getText().toString();
                    if (!pet.isEmpty()){
                        if (pet.length() < 2 ){
                            etPet.setError("Silahkan Masukan Nama Hewan Peliharan Anda Minimal 2 Karakter");
                            etPet.requestFocus();
                            return;
                        }
                    }

                    btnKirim.setEnabled(false);
                    Call<BaseResponse<ModelUser>> _diagnosa = userInterface.diagnosa("Bearer " + getToken(), pet, getDokter());
                    _diagnosa.enqueue(new Callback<BaseResponse<ModelUser>>() {
                        @Override
                        public void onResponse(Call<BaseResponse<ModelUser>> call, Response<BaseResponse<ModelUser>> response) {
                            if (response.isSuccessful()){
                                BaseResponse<ModelUser> body = response.body();
                                if (body != null && body.getStatus()){
                                    mUser = body.getData();
                                    setDiagnosa(mUser.getApi_token(), String.valueOf(mUser.getId()), String.valueOf(mUser.getId_doctor()));
                                    System.err.println("uwu7 " + response.body().getMessage().toString());
                                    Toast.makeText(DiagnosaActivity.this, "Sukses", Toast.LENGTH_SHORT).show();

                                    Intent a = new Intent(DiagnosaActivity.this, MainActivity.class);
                                    startActivity(a);
//                                    finish();
                                }else {
                                    Toast.makeText(DiagnosaActivity.this, "failed", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                System.err.println("mbuhlah " + response.body().getMessage());
                                Toast.makeText(DiagnosaActivity.this, "sukses tapi salah ", Toast.LENGTH_SHORT).show();
                            }
                            btnKirim.setEnabled(true);
                        }

                        @Override
                        public void onFailure(Call<BaseResponse<ModelUser>> call, Throwable t) {
                            Toast.makeText(DiagnosaActivity.this, "Gagal terkoneksi ke server, silahkan cek koneksi", Toast.LENGTH_SHORT).show();
                            System.err.println(t.getMessage());
                        }
                    });
                }catch (Exception e){
                    Toast.makeText(DiagnosaActivity.this, "belih ngarti maning nyong", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void setDiagnosa(String api_token, String id, String id_doctor ){
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("TOKEN", api_token);
        editor.putString("USER_ID", id);
        editor.putString("DOCTOR_ID", id_doctor);
//        editor.commit();
    }


    private String getDokter(){ return String.valueOf(getIntent().getStringExtra("DOCTOR_ID"));}

    private String getToken(){
        SharedPreferences settings = getSharedPreferences("USER", MODE_PRIVATE);
        return settings.getString("TOKEN", "UNDIFINED");
    }




}
