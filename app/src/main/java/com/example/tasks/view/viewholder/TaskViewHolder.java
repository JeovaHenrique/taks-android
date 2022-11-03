package com.example.tasks.view.viewholder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tasks.R;
import com.example.tasks.service.listener.TaskListener;
import com.example.tasks.service.model.TaskModel;
import com.example.tasks.service.repository.PriorityRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TaskViewHolder extends RecyclerView.ViewHolder {

    private PriorityRepository mPriorityrepository;
    private SimpleDateFormat mDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    private TaskListener mListener;

    private ImageView mImageComplete = itemView.findViewById(R.id.image_complete);
    private TextView mTextDescription = itemView.findViewById(R.id.text_description);
    private TextView mTextpriority = itemView.findViewById(R.id.text_priority);
    private TextView mTextDuedate = itemView.findViewById(R.id.text_duedate);


    public TaskViewHolder(@NonNull View itemView, TaskListener listener) {
        super(itemView);
        this.mListener = listener;
        this.mPriorityrepository = new PriorityRepository(itemView.getContext());
    }


    public void bindData(TaskModel taskModel) {
        this.mTextDescription.setText(taskModel.getDescription());

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(taskModel.getDueDate());
            this.mTextDuedate.setText(this.mDateFormat.format(date));
        } catch (ParseException e) {
            this.mTextDuedate.setText("--");
        }

        String priority = this.mPriorityrepository.getDescription(taskModel.getPriorityId());
        this.mTextpriority.setText(priority);

        if(taskModel.getComplete()) {
            this.mImageComplete.setImageResource(R.drawable.ic_done);
        }else {
            this.mImageComplete.setImageResource(R.drawable.ic_todo);
        }

        /*
        new AlertDialog.Builder(itemView.getContext())
                .setTitle(R.string.remocao_de_tarefa)
                .setMessage(R.string.remover_tarefa)
                .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // mListener.onDeleteClick(task.id);
                    }
                })
                .setNeutralButton(R.string.cancelar, null).show();*/


    }

}
