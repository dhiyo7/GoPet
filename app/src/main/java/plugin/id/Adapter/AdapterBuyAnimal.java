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

import plugin.id.Activity.DetailBuyActivity;
import plugin.id.Model.ModelBuyAnimal;
import plugin.id.R;
import plugin.id.Server.ApiClient;

public class AdapterBuyAnimal extends RecyclerView.Adapter<AdapterBuyAnimal.ViewHolder> {

    private Context context;
    private List<ModelBuyAnimal> data;

    public AdapterBuyAnimal(Context context, List<ModelBuyAnimal> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public AdapterBuyAnimal.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_buy_animal, viewGroup, false);
        ViewHolder viewHolder =  new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBuyAnimal.ViewHolder viewHolder, int i) {
        viewHolder.binding(data.get(i), context);

    }

    @Override
    public int getItemCount() {
        if (data != null){
            return data.size();
        }else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvAddress;
        private ImageView ivBuyAnimal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            ivBuyAnimal = itemView.findViewById(R.id.ivBuyAnimal);
        }

        public void binding(final ModelBuyAnimal modelBuyAnimal, final Context context) {

            tvName.setText(modelBuyAnimal.getName());
            tvAddress.setText(modelBuyAnimal.getAddress());
            Glide.with(context)
                    .load(ApiClient.ENDPOINT+"images/"+modelBuyAnimal
                            .getImage()).error(R.drawable.item_error)
                    .into(ivBuyAnimal);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailBuyActivity.class);
                    intent.putExtra("ID", modelBuyAnimal.getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
