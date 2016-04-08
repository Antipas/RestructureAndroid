package org.iflab.wecenterandroid.view.recyclerView;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding.widget.RxCompoundButton;

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
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Lyn on 16/1/23.
 */
public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {

    private static final int ARTICLE = 1;
    private static final int FAMOUS_MEDIA = 2;
    List<Explore> list;
    Activity host;
    ExploreListener exploreListener;

    public ExploreAdapter(@Nullable Activity host, List list,ExploreListener exploreListener){
        this.list = list;
        this.host = host;
        this.exploreListener = exploreListener;
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
            final ExploreViewModal exploreViewModal = new ExploreViewModal(host,famousMedia);

            famousMediaViewHolder.getBinding().checkboxFocus.setChecked(famousMedia.getHas_focus() == 1);
            RxCompoundButton.checkedChanges(famousMediaViewHolder.getBinding().checkboxFocus)
                    .debounce(700, TimeUnit.MILLISECONDS)
                    .subscribe(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean state) {
                            //已关注为true，state为下个状态值
                            //用debounce而不是throttleFirst
                            //根据最终状态和原始状态，通过异或判断发不发请求
                            if(exploreViewModal.getFMHasFocus() ^ state) {
                                exploreListener.onAddFocusPeople(String.valueOf(famousMedia.getUid()));
                                exploreViewModal.setFMHasFocus(state);
                                exploreViewModal.setFMFansCount(state);
                            }
                        }
                    });

            famousMediaViewHolder.bind(exploreViewModal);
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

    public interface ExploreListener{
        Subscription onAddFocusPeople(String uid);
    }
}
