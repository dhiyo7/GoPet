package plugin.id.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import plugin.id.Model.ModelFood;
import plugin.id.R;
import plugin.id.Server.ApiClient;

public class AdapterFood extends RecyclerView.Adapter<AdapterFood.ViewHolder> {

    private Context context;
    private List<ModelFood> data;

    public AdapterFood(Context context, List<ModelFood> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public AdapterFood.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_list_food, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int posisition) {
        holder.binding(data.get(posisition), context);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNameFood, tvPriceFood;
        private ImageView ivFood;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameFood = itemView.findViewById(R.id.tvNameFood);
            tvPriceFood = itemView.findViewById(R.id.tvPriceFood);
            ivFood = itemView.findViewById(R.id.ivFood);
        }

        public void binding(ModelFood modelFood, Context context) {

            tvNameFood.setText(modelFood.getName());
            tvPriceFood.setText(modelFood.getPrice());
            Glide.with(context).load(ApiClient.ENDPOINT+"images/"+modelFood.getImage()).into(ivFood);
        }
    }
}
