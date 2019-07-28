package plugin.id.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import plugin.id.Model.ModelUser;
import plugin.id.R;

public class AdapterHistoryDiagnosa extends RecyclerView.Adapter<AdapterHistoryDiagnosa.ViewHolder> {

    private Context context;
    private List<ModelUser> data;

    public AdapterHistoryDiagnosa(Context context, List<ModelUser> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_list_history_diagnosa, viewGroup, false);
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

        private TextView tvDokter, tvDiagnosa, tvPet, tvTgl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDokter = itemView.findViewById(R.id.tvDokter);
            tvDiagnosa = itemView.findViewById(R.id.tvDiagnosa);
            tvPet = itemView.findViewById(R.id.tvPet);
            tvTgl = itemView.findViewById(R.id.tvTgl);
        }

        public void binding(final ModelUser modelUser, final Context context) {
            tvDokter.setText(modelUser.getDoctor());
            tvDiagnosa.setText(modelUser.getDiagnosis());
            tvPet.setText(modelUser.getPet_name());
            tvTgl.setText(modelUser.getCreated_at());
        }
    }
}
