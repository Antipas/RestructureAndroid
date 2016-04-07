package org.iflab.wecenterandroid.view.recyclerView;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.databinding.ExploreArticleItemBinding;
import org.iflab.wecenterandroid.databinding.ExploreFamousMediaItemBinding;
import org.iflab.wecenterandroid.modal.explore.Explore;
import org.iflab.wecenterandroid.modal.explore.ExploreArticle;
import org.iflab.wecenterandroid.modal.explore.Famous;
import org.iflab.wecenterandroid.view.activity.ArticleActivity;
import org.iflab.wecenterandroid.view.activity.PersonCenterActivity;
import org.iflab.wecenterandroid.viewmodal.ExploreViewModal;

import java.util.List;

/**
 * Created by Lyn on 16/1/23.
 */
public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {

    private static final int ARTICLE = 1;
    private static final int FAMOUS_MEDIA = 2;
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
            case FAMOUS_MEDIA:
                return new FamousMediaViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.explore_famous_media_item, viewGroup, false),viewType);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(holder instanceof ArticleViewHolder){
            final ExploreArticle article = (ExploreArticle)list.get(position);
            ArticleViewHolder articleViewHolder = ((ArticleViewHolder) holder);
            articleViewHolder.bind(new ExploreViewModal(host,article));

        }else if(holder instanceof FamousMediaViewHolder){
            final Famous.RsmBean.RowsBean famousMedia = (Famous.RsmBean.RowsBean)list.get(position);
            FamousMediaViewHolder famousMediaViewHolder = ((FamousMediaViewHolder)holder);
            famousMediaViewHolder.bind(new ExploreViewModal(host,famousMedia));

        }
    }

    @Override
    public int getItemViewType(int position) {
        String type = list.get(position).getPost_type();
        switch (type){
            case Explore.ARTICLE:
                return ARTICLE;
            case Explore.FAMOUS_MEDIA:
                return FAMOUS_MEDIA;
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


    static class ArticleViewHolder extends ViewHolder{
        ExploreArticleItemBinding exploreQuestionItemBinding;
        public ArticleViewHolder(View itemView, int action) {
            super(itemView, action);
            exploreQuestionItemBinding = DataBindingUtil.bind(itemView);
        }

        @Override
        void bind(ExploreViewModal explore) {
            exploreQuestionItemBinding.setExplore(explore);
        }

        ExploreArticleItemBinding getBinding(){
            return exploreQuestionItemBinding;
        }
    }

    static class FamousMediaViewHolder extends ViewHolder{
        ExploreFamousMediaItemBinding exploreFamousMediaItemBinding;
        public FamousMediaViewHolder(View itemView, int action) {
            super(itemView, action);
            exploreFamousMediaItemBinding = DataBindingUtil.bind(itemView);
        }

        @Override
        void bind(ExploreViewModal explore) {
            exploreFamousMediaItemBinding.setFamousMedia(explore);
        }

        ExploreFamousMediaItemBinding getBinding(){
            return exploreFamousMediaItemBinding;
        }
    }
}
