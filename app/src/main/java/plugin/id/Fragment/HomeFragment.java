package plugin.id.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import plugin.id.Activity.FoodActivity;
import plugin.id.Activity.PetshopActivity;
import plugin.id.Activity.WashSpaActivity;
import plugin.id.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private LinearLayout crdFood, crdPetshop, crdSpa;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        crdSpa = (LinearLayout) view.findViewById(R.id.crdSpa);
        crdSpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WashSpaActivity.class);
                startActivity(intent);
            }
        });

        crdPetshop = (LinearLayout)view.findViewById(R.id.crdPetshop);
        crdPetshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PetshopActivity.class);
                startActivity(intent);
            }
        });

        crdFood = (LinearLayout)view.findViewById(R.id.crdFood);
        crdFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FoodActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
