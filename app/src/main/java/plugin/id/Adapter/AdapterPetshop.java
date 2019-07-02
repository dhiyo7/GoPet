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

import org.w3c.dom.Text;

import java.util.List;

import javax.microedition.khronos.opengles.GL;

import plugin.id.Activity.DetailPetshopActivity;
import plugin.id.Model.ModelPetshop;
import plugin.id.R;
import plugin.id.Server.ApiClient;

public class AdapterPetshop extends RecyclerView.Adapter<AdapterPetshop.ViewHolder> {

    private Context context;
    private List<ModelPetshop> data;

    public AdapterPetshop(Context context, List<ModelPetshop> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public AdapterPetshop.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_list_petshop, viewGroup, false);
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
            tvDisctrict = itemView.findViewById(R.id.tvDisctrictpet);
            ivPetshop = itemView.findViewById(R.id.ivPetshop);
        }

        public void binding(final ModelPetshop petshop, final Context context) {

            tvName.setText(petshop.getName());
            tvCity.setText(petshop.getCity());
            tvDisctrict.setText(petshop.getDistricts());
            Glide.with(context)
                    .load(ApiClient.ENDPOINT+"images/"+petshop
                    .getImage()).error(R.drawable.item_error)
                    .into(ivPetshop);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailPetshopActivity.class);
                    intent.putExtra("ID", petshop.getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
