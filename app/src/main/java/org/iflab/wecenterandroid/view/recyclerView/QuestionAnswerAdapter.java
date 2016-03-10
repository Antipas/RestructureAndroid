package org.iflab.wecenterandroid.view.recyclerView;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.squareup.picasso.Picasso;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.databinding.AnswerItemBinding;
import org.iflab.wecenterandroid.databinding.QuestionButtonItemBinding;
import org.iflab.wecenterandroid.databinding.QuestionContentItemBinding;
import org.iflab.wecenterandroid.databinding.QuestionInfoItemBinding;
import org.iflab.wecenterandroid.databinding.QuestionTopicsItemBinding;
import org.iflab.wecenterandroid.databinding.QuestionUserinfoItemBinding;
import org.iflab.wecenterandroid.modal.Answer;
import org.iflab.wecenterandroid.modal.Question;
import org.iflab.wecenterandroid.view.activity.AnswerActivity;
import org.iflab.wecenterandroid.view.activity.PersonCenterActivity;
import org.iflab.wecenterandroid.viewmodal.QuestionAnswerViewModal;
import org.iflab.wecenterandroid.viewmodal.QuestionViewModal;

import java.util.List;

import rx.functions.Action1;

/**
 * Created by Lyn on 15/12/21.
 */
public class QuestionAnswerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Question.RsmEntity.AnswersEntity> list;
    Activity host;

    public QuestionAnswerAdapter(Activity host,@NonNull List<Question.RsmEntity.AnswersEntity> list){
        this.host = host;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new AnswerViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.answer_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Question.RsmEntity.AnswersEntity answersEntity=  list.get(position);
        ((AnswerViewHolder)viewHolder).bind(new QuestionAnswerViewModal(host,answersEntity));
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
}
