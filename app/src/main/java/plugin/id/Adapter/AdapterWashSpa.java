package plugin.id.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import plugin.id.Activity.DetailPetshopActivity;
import plugin.id.Activity.DetailWashSpaActivity;
import plugin.id.Model.ModelWashSpa;
import plugin.id.R;
import plugin.id.Server.ApiClient;

public class AdapterWashSpa extends RecyclerView.Adapter<AdapterWashSpa.ViewHolder> {

    private Context context;
    private List<ModelWashSpa> data;

    public AdapterWashSpa(Context context, List<ModelWashSpa> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public AdapterWashSpa.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_list_wash_spa, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.binding(data.get(i), context);

    }

    @Override
    public int getItemCount() {
        if (data!=null){
            return data.size();
        }else {
            return 0;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvCity, tvDisctrict;
        private ImageView ivPetshop;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvCity = itemView.findViewById(R.id.tvCity);
            tvDisctrict = itemView.findViewById(R.id.tvDisctrict);
            ivPetshop = itemView.findViewById(R.id.ivPetshop);
        }

        public void binding(final ModelWashSpa modelWashSpa, final Context context) {

            tvName.setText(modelWashSpa.getName());
            tvCity.setText(modelWashSpa.getCity());
            tvDisctrict.setText(modelWashSpa.getDistricts());
            Glide.with(context)
                    .load(ApiClient.ENDPOINT+"images/"+modelWashSpa
                            .getImage()).error(R.drawable.item_error)
                    .into(ivPetshop);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailWashSpaActivity.class);
                    intent.putExtra("ID", modelWashSpa.getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
