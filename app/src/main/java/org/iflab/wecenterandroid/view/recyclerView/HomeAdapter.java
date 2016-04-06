package org.iflab.wecenterandroid.view.recyclerView;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.databinding.HomeAddArticleItemBinding;
import org.iflab.wecenterandroid.modal.home.Home;
import org.iflab.wecenterandroid.modal.home.Home501;
import org.iflab.wecenterandroid.viewmodal.HomeViewModel;

import java.util.List;

/**
 * Created by Lyn on 15/12/15.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{

    private static final int ACTION_501 = 501;
    private static final int ACTION_502 = 502;
    private static final int ACTION_503 = 503;

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
            if (viewType == ACTION_501 || viewType == ACTION_502 ||viewType == ACTION_503) {
                return new ArticleViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.home_add_article_item, viewGroup, false),viewType);

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
            articleViewHolder.bind(new HomeViewModel(host,home501));

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

        public void bind(@Nullable HomeViewModel home){
            binding.setArticle(home);
        }

        public HomeAddArticleItemBinding getBinding(){
            return binding;
        }

    }
}
