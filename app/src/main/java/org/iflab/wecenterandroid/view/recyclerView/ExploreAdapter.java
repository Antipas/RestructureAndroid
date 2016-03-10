package org.iflab.wecenterandroid.view.recyclerView;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.databinding.ExploreArticleItemBinding;
import org.iflab.wecenterandroid.databinding.ExploreQuestionItemBinding;
import org.iflab.wecenterandroid.modal.explore.Explore;
import org.iflab.wecenterandroid.modal.explore.ExploreArticle;
import org.iflab.wecenterandroid.modal.explore.ExploreQuestion;
import org.iflab.wecenterandroid.util.RoundedTransformation;
import org.iflab.wecenterandroid.view.activity.ArticleActivity;
import org.iflab.wecenterandroid.view.activity.PersonCenterActivity;
import org.iflab.wecenterandroid.view.activity.QuestionActivity;
import org.iflab.wecenterandroid.viewmodal.ExploreViewModal;

import java.util.List;

/**
 * Created by Lyn on 16/1/23.
 */
public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {

    private static final int ARTICLE = 1;
    private static final int QUESTION = 2;
    List<Explore> list;
    Activity host;

    public ExploreAdapter(@Nullable Activity host, List list){
        this.list = list;
        this.host = host;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        switch (viewType){
            case ARTICLE:
                return new ArticleViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.explore_article_item, viewGroup, false),viewType);
            case QUESTION:
                return new QuestionViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.explore_question_item, viewGroup, false),viewType);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(holder instanceof QuestionViewHolder){
            final ExploreQuestion question = (ExploreQuestion)list.get(position);
            QuestionViewHolder questionViewHolder = ((QuestionViewHolder) holder);
            questionViewHolder.bind(new ExploreViewModal(host,question));

            questionViewHolder.getBinding().ivAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PersonCenterActivity.startPersonCenter(question.getUser_info().getUid(),host,v);
                }
            });

            questionViewHolder.getBinding().tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    QuestionActivity.startQuestion(question.getQuestion_id(),host,v);
                }
            });
        }else if(holder instanceof ArticleViewHolder){
            final ExploreArticle article = (ExploreArticle)list.get(position);
            ArticleViewHolder articleViewHolder = ((ArticleViewHolder) holder);
            articleViewHolder.bind(new ExploreViewModal(host,article));

            articleViewHolder.getBinding().ivAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PersonCenterActivity.startPersonCenter(article.getUser_info().getUid(),host,v);
                }
            });

            articleViewHolder.getBinding().tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArticleActivity.startArticle(article.getId(),host);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        String type = list.get(position).getPost_type();
        switch (type){
            case "article":
                return ARTICLE;
            case "question":
                return QUESTION;
            default:
                return 0;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static abstract class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView,int action) {
            super(itemView);
        }
        abstract void bind(ExploreViewModal explore);
    }

    static class QuestionViewHolder extends ViewHolder{
        ExploreQuestionItemBinding exploreQuestionItemBinding;
        public QuestionViewHolder(View itemView, int action) {
            super(itemView, action);
            exploreQuestionItemBinding = DataBindingUtil.bind(itemView);
        }

        @Override
        void bind(ExploreViewModal explore) {
            exploreQuestionItemBinding.setQuestion(explore);
        }

        ExploreQuestionItemBinding getBinding(){
            return exploreQuestionItemBinding;
        }
    }

    static class ArticleViewHolder extends ViewHolder{
        ExploreArticleItemBinding exploreQuestionItemBinding;
        public ArticleViewHolder(View itemView, int action) {
            super(itemView, action);
            exploreQuestionItemBinding = DataBindingUtil.bind(itemView);
        }

        @Override
        void bind(ExploreViewModal explore) {
            exploreQuestionItemBinding.setArticle(explore);
        }

        ExploreArticleItemBinding getBinding(){
            return exploreQuestionItemBinding;
        }
    }
}
