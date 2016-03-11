package org.iflab.wecenterandroid.view.recyclerView;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.databinding.AnswerItemBinding;
import org.iflab.wecenterandroid.databinding.QuestionHeaderBinding;
import org.iflab.wecenterandroid.modal.IAdapterType;
import org.iflab.wecenterandroid.modal.Question;
import org.iflab.wecenterandroid.viewmodal.QuestionAnswerViewModal;
import org.iflab.wecenterandroid.viewmodal.QuestionHeaderViewModal;

import java.util.List;

/**
 * Created by Lyn on 15/12/21.
 */
public class QuestionAnswerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<IAdapterType> list;
    Activity host;

    public QuestionAnswerAdapter(Activity host,@NonNull List<IAdapterType> list){
        this.host = host;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if(viewType == IAdapterType.CONTENT) {
            return new AnswerViewHolder(LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.answer_item, viewGroup, false));
        }else if(viewType == IAdapterType.HEADER){
            return new HeaderViewHolder(LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.question_header, viewGroup, false));
        }else
            return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof AnswerViewHolder){
            Question.RsmEntity.AnswersEntity answersEntity = (Question.RsmEntity.AnswersEntity)list.get(position);
            ((AnswerViewHolder)viewHolder).bind(new QuestionAnswerViewModal(host,answersEntity));
        }else if(viewHolder instanceof HeaderViewHolder){
            Question.RsmEntity entity = (Question.RsmEntity)list.get(position);
            ((HeaderViewHolder)viewHolder).bind(new QuestionHeaderViewModal(host,entity));
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    static class AnswerViewHolder extends RecyclerView.ViewHolder{
        AnswerItemBinding binding;
        public AnswerViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void bind(@NonNull QuestionAnswerViewModal viewModal){
            binding.setAnswer(viewModal);
        }
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder{
        QuestionHeaderBinding binding;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void bind(@NonNull QuestionHeaderViewModal viewModal){
            binding.setHeader(viewModal);
        }
    }
}
