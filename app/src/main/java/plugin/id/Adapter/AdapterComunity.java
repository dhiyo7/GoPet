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

import plugin.id.Activity.DetailComunityActivity;
import plugin.id.Activity.DetailFoodActivity;
import plugin.id.Model.ModelComunity;
import plugin.id.R;
import plugin.id.Server.ApiClient;

public class AdapterComunity extends RecyclerView.Adapter<AdapterComunity.ViewHolder> {

    private Context context;
    private List<ModelComunity> data;

    public AdapterComunity(Context context, List<ModelComunity> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public AdapterComunity.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_list_comunity, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.binding(data.get(i), context);


    }

    @Override
    public int getItemCount() {
        if(data!=null){
            return data.size();
        } else{
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNameComunity,tvLeader;
        private ImageView ivComunity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameComunity = itemView.findViewById(R.id.tvNameComunity);
            tvLeader = itemView.findViewById(R.id.tvLeader);
            ivComunity = itemView.findViewById(R.id.ivComunity);
        }

        public void binding(final ModelComunity modelComunity, final Context context) {

            tvLeader.setText(modelComunity.getLeader());
            tvNameComunity.setText(modelComunity.getName());
            Glide.with(context)
                    .load(ApiClient.ENDPOINT+"images/"+modelComunity
                            .getImage())
                    .error((R.drawable.item_error))
                    .into(ivComunity);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailComunityActivity.class);
                    intent.putExtra("ID", modelComunity.getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
