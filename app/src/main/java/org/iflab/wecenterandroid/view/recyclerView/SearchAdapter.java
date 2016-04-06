package org.iflab.wecenterandroid.view.recyclerView;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.databinding.SearchArticleItemBinding;
import org.iflab.wecenterandroid.databinding.SearchQuestionItemBinding;
import org.iflab.wecenterandroid.databinding.SearchTopicItemBinding;
import org.iflab.wecenterandroid.databinding.SearchUserItemBinding;
import org.iflab.wecenterandroid.modal.search.Search;
import org.iflab.wecenterandroid.modal.search.SearchArticle;
import org.iflab.wecenterandroid.modal.search.SearchQuestion;
import org.iflab.wecenterandroid.modal.search.SearchTopic;
import org.iflab.wecenterandroid.modal.search.SearchUser;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Lyn on 16/2/10.
 */
public class SearchAdapter extends RecyclerView.Adapter {
    private static final int TOPIC = 12;
    private static final int ARTICLE = 13;
    private static final int QUESTION = 14;
    private static final int USER = 15;
    
    List<Search> dataList = new ArrayList<>();
    Context context;
    
    public SearchAdapter(Context context,List<Search> list){
        this.context = context;
        this.dataList = list;
    }
    
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        switch (viewType){
            case ARTICLE:
                return new ArticleViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.search_article_item, viewGroup, false),viewType);
            case TOPIC:
                return new TopicViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.search_topic_item, viewGroup, false),viewType);
            case USER:
                return new UserViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.search_user_item, viewGroup, false),viewType);
            default:
                return null;
                
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Search search = dataList.get(position);
        if(holder instanceof ArticleViewHolder){
            ArticleViewHolder articleViewHolder = (ArticleViewHolder)holder;
            articleViewHolder.bind(search);
        }else if(holder instanceof TopicViewHolder){
            TopicViewHolder topicViewModel = (TopicViewHolder)holder;
            topicViewModel.bind(search);
        }else if(holder instanceof UserViewHolder){
            UserViewHolder userViewHolder = (UserViewHolder)holder;
            userViewHolder.bind(search);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        String type=  dataList.get(position).getType();
        switch (type){
            case Search.ARTICLE:
                return ARTICLE;
            case Search.TOPIC:
                return TOPIC;
            case Search.USER:
                return USER;
            default:
                return 0;
                        
        }
    }

    static abstract class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView, int action) {
            super(itemView);
        }
        abstract void bind(Search search);
    }

    static class ArticleViewHolder extends ViewHolder{
        SearchArticleItemBinding searchQuestionItemBinding;
        public ArticleViewHolder(View itemView, int action) {
            super(itemView, action);
            searchQuestionItemBinding = DataBindingUtil.bind(itemView);
        }

        @Override
        void bind(Search search) {
            searchQuestionItemBinding.setSearch((SearchArticle)search);
        }

        SearchArticleItemBinding getBinding(){
            return searchQuestionItemBinding;
        }
    }
    static class TopicViewHolder extends ViewHolder{
        SearchTopicItemBinding searchTopicItemBinding;
        public TopicViewHolder(View itemView, int action) {
            super(itemView, action);
            searchTopicItemBinding = DataBindingUtil.bind(itemView);
        }

        @Override
        void bind(Search search) {
            searchTopicItemBinding.setSearch((SearchTopic) search);
        }

        SearchTopicItemBinding getBinding(){
            return searchTopicItemBinding;
        }
    }

    static class UserViewHolder extends ViewHolder{
        SearchUserItemBinding searchUserItemBinding;
        public UserViewHolder(View itemView, int action) {
            super(itemView, action);
            searchUserItemBinding = DataBindingUtil.bind(itemView);
        }

        @Override
        void bind(Search search) {
            searchUserItemBinding.setSearch((SearchUser) search);
        }

        SearchUserItemBinding getBinding(){
            return searchUserItemBinding;
        }
    }

}
