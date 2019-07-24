package plugin.id.Fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import plugin.id.Activity.LoginActivity;
import plugin.id.R;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private Button btnMasuk, btnLogout;
    private SharedPreferences settings;



    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        settings = getActivity().getSharedPreferences("USER", MODE_PRIVATE);
        btnMasuk = view.findViewById(R.id.btnMasuk);
        btnLogout = view.findViewById(R.id.btnLogout);
    }

    @Override
    public void onResume() {
        super.onResume();

        if(!isNotLogedIn()){
            //fetch user data
            btnLogout.setVisibility(View.VISIBLE);
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnMasuk.setEnabled(false);
//                    loading.setIndeterminate(true);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.clear();
                    editor.commit();
                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            btnMasuk.setEnabled(true);
                            onResume();
                        }
                    }, 2000);

                }
            });
            btnMasuk.setText("Edit profil");
            btnMasuk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(getActivity(), EditProfileActivity.class);
//                    startActivity(intent);
                }
            });
        }else{

            btnLogout.setVisibility(View.INVISIBLE);
            //set textview jadi "Halo Job Seeker!"
            btnMasuk.setText("Masuk");
            btnMasuk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    private boolean isNotLogedIn() {
        String token = settings.getString("TOKEN", "UNDIFINED");
        return token == null || token.equals("UNDIFINED");
    }
}
