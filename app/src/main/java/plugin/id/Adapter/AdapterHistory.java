package plugin.id.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import plugin.id.Model.ModelOrder;
import plugin.id.R;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.ViewHolder> {

    private Context context;
    private List<ModelOrder> data;

    public AdapterHistory(Context context, List<ModelOrder> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_list_history, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
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

        private TextView tvStatus, tvTgl, tvItem, tvPetshop, tvHarga;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvTgl = itemView.findViewById(R.id.tvTgl);
            tvItem = itemView.findViewById(R.id.tvItem);
            tvPetshop = itemView.findViewById(R.id.tvPetshop);
            tvHarga = itemView.findViewById(R.id.tvHarga);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }

        public void binding(final ModelOrder modelOrder, final Context context) {

            tvTgl.setText(modelOrder.getCreated_at());
            tvItem.setText(modelOrder.getItem());
            tvPetshop.setText(modelOrder.getPetshop());
            tvHarga.setText(modelOrder.getPrice());
            tvStatus.setText(modelOrder.getStatus());
        }
    }
}
