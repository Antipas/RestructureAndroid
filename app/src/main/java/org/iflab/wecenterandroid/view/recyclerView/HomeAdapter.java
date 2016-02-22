package org.iflab.wecenterandroid.view.recyclerView;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.squareup.picasso.Picasso;

import org.iflab.wecenterandroid.Constant;
import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.databinding.HomeAddAnswerItemBinding;
import org.iflab.wecenterandroid.databinding.HomeAddArticleItemBinding;
import org.iflab.wecenterandroid.databinding.HomeAddQuestionItemBinding;
import org.iflab.wecenterandroid.databinding.TopicsItemBinding;
import org.iflab.wecenterandroid.modal.Answer;
import org.iflab.wecenterandroid.modal.home.Home;
import org.iflab.wecenterandroid.modal.home.Home101;
import org.iflab.wecenterandroid.modal.home.Home201;
import org.iflab.wecenterandroid.modal.home.Home501;
import org.iflab.wecenterandroid.util.RoundedTransformation;
import org.iflab.wecenterandroid.view.activity.AnswerActivity;
import org.iflab.wecenterandroid.view.activity.ArticleActivity;
import org.iflab.wecenterandroid.view.activity.PersonCenterActivity;
import org.iflab.wecenterandroid.view.activity.QuestionActivity;
import org.iflab.wecenterandroid.viewmodal.AnswerViewModel;
import org.iflab.wecenterandroid.viewmodal.ArticleViewModel;

import java.util.List;

/**
 * Created by Lyn on 15/12/15.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{

    private static final int ACTION_101 = 101;
    private static final int ACTION_501 = 501;
    private static final int ACTION_502 = 502;
    private static final int ACTION_503 = 503;
    private static final int ACTION_105 = 105;
    private static final int ACTION_201 = 201;
    private static final int ACTION_204 = 204;

    List<Home> dataList;
    Activity host;

    public HomeAdapter(@Nullable Activity host,List<Home> dataList){
        this.host = host;
        this.dataList = dataList;
    }

    @Override
    public int getItemViewType(int position) {
        int action = dataList.get(position).getAssociate_action();
        return action;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            if(viewType == ACTION_101 || viewType == ACTION_105){
                return new QuestionViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.home_add_question_item, viewGroup, false),viewType);

            } else if (viewType == ACTION_501 || viewType == ACTION_502 ||viewType == ACTION_503) {
                return new ArticleViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.home_add_article_item, viewGroup, false),viewType);

            }else if (viewType == ACTION_201 || viewType == ACTION_204){
                return new AnswerViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.home_add_answer_item, viewGroup, false),viewType);

            }else {
                Log.e("viewType","viewType null");
                return null;
            }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(holder instanceof ArticleViewHolder){
            final Home501 home501 = (Home501) dataList.get(position);
            ArticleViewHolder articleViewHolder = ((ArticleViewHolder) holder);
            articleViewHolder.bind(home501);
            articleViewHolder.getBinding().ivAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PersonCenterActivity.startPersonCenter(home501.getUser_info().getUid(),host,v);
                }
            });

            articleViewHolder.getBinding().tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArticleActivity.startArticle(home501.getArticle_info().getId(),host);
                }
            });

        }else if(holder instanceof QuestionViewHolder){
            final Home101 home101 = (Home101) dataList.get(position);
            QuestionViewHolder questionViewHolder = ((QuestionViewHolder) holder);
            questionViewHolder.bind(home101);

            questionViewHolder.getBinding().ivAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PersonCenterActivity.startPersonCenter(home101.getUser_info().getUid(),host,v);
                }
            });

            questionViewHolder.getBinding().tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    QuestionActivity.startQuestion(home101.getQuestion_info().getQuestion_id(),host,v);
                }
            });

        }else if(holder instanceof AnswerViewHolder){
            final Home201 home201 = (Home201) dataList.get(position);
            AnswerViewHolder answerViewModel = ((AnswerViewHolder) holder);
            answerViewModel.bind(home201);

            answerViewModel.getBinding().ivAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PersonCenterActivity.startPersonCenter(home201.getUser_info().getUid(),host,v);
                }
            });

            answerViewModel.getBinding().tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AnswerActivity.startAnswer(home201.getAnswer_info().getAnswer_id(),host);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView,int action) {
            super(itemView);
        }

    }

    static class ArticleViewHolder extends ViewHolder{
        HomeAddArticleItemBinding binding;
        public ArticleViewHolder(View itemView, int action) {
            super(itemView, action);
            binding = DataBindingUtil.bind(itemView);
        }

        public void bind(@Nullable Home501 home){
            binding.setArticle(home);
        }

        public HomeAddArticleItemBinding getBinding(){
            return binding;
        }

    }

    static class QuestionViewHolder extends ViewHolder {
        HomeAddQuestionItemBinding binding;

        public QuestionViewHolder(View itemView, int action) {
            super(itemView, action);
            binding = DataBindingUtil.bind(itemView);
        }

        public void bind(@Nullable Home101 home101){
            binding.setQuestion(home101);
        }

        public HomeAddQuestionItemBinding getBinding(){
            return binding;
        }

    }

    static class AnswerViewHolder extends ViewHolder{
        HomeAddAnswerItemBinding binding;

        public AnswerViewHolder(View itemView, int action) {
            super(itemView, action);
            binding = DataBindingUtil.bind(itemView);
        }
        public void bind(@Nullable Home201 home201){
            binding.setAnswer(home201);
        }

        public HomeAddAnswerItemBinding getBinding(){
            return binding;
        }
    }
}
