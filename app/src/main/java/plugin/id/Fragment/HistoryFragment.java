package plugin.id.Fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import plugin.id.Adapter.AdapterHistory;
import plugin.id.Converter.BaseListResponse;
import plugin.id.Converter.BaseResponse;
import plugin.id.Model.ModelOrder;
import plugin.id.R;
import plugin.id.Server.ApiClient;
import plugin.id.Server.OrderInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    private RecyclerView rvHistory;
//    private boolean TextView tvStatus;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<ModelOrder> data;
    private OrderInterface orderInterface;
    private retrofit2.Call<BaseListResponse<ModelOrder>> call;



    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        orderInterface = ApiClient.getOrderInterface();
        rvHistory = view.findViewById(R.id.rvHistory);
        layoutManager = new LinearLayoutManager(getActivity());
        rvHistory.setLayoutManager(layoutManager);
        rvHistory.setHasFixedSize(true);
//        tvStatus = view.findViewById(R.id.tvStatus);
    }

    public void fetchData(){
        call = orderInterface.getOrder("Bearer " + getToken());
        call.enqueue(new Callback<BaseListResponse<ModelOrder>>() {
            @Override
            public void onResponse(Call<BaseListResponse<ModelOrder>> call, Response<BaseListResponse<ModelOrder>> response) {
                if(response.isSuccessful()){
                    BaseListResponse body = response.body();
                    if (body.getStatus()){
                        data = body.getData();
                        mAdapter = new AdapterHistory(getActivity(),data);
                        rvHistory.setAdapter(mAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseListResponse<ModelOrder>> call, Throwable t) {
//                Toast.makeText(getActivity(), "Error : "+ t.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        fetchData();

//        if(tvStatus = true){
//            tvStatus("Pesanan Selesai");
//        }else {
//            tvStatus.setText("Pesanan Belum Selesai");
//        };
    }

    private final String getToken() {
        SharedPreferences SharedPreferences = this.getActivity().getSharedPreferences("USER", MODE_PRIVATE);
        String api_token = SharedPreferences.getString("TOKEN", "UNDIFINED");
        return api_token;
    }
}
