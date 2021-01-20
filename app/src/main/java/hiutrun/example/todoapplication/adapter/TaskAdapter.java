package hiutrun.example.todoapplication.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

import hiutrun.example.todoapplication.R;
import hiutrun.example.todoapplication.model.Task;

public class TaskAdapter extends FirebaseRecyclerAdapter<Task,TaskAdapter.ViewHolder> {

    public TaskAdapter(@NonNull FirebaseRecyclerOptions<Task> options) {
        super(options);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task,parent,false);
        return new ViewHolder(itemView);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Task model) {
        holder.tv_name.setText(model.getTaskName());
        holder.tv_date.setText(model.getDate());
        holder.tv_time.setText(model.getTime());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private TextView tv_date;
        private TextView tv_time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_taskNameItem);
            tv_date = (TextView) itemView.findViewById(R.id.tv_taskDateItem);
            tv_time = (TextView) itemView.findViewById(R.id.tv_taskTimeItem);
        }
    }
}
