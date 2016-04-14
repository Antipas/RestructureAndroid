package org.iflab.wecenterandroid.view.recyclerView;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.databinding.HomeAddArticleItemBinding;
import org.iflab.wecenterandroid.databinding.PersoncenterHeaderBinding;
import org.iflab.wecenterandroid.databinding.PersoncenterUserinfoBinding;
import org.iflab.wecenterandroid.modal.PersonCenterItem;
import org.iflab.wecenterandroid.modal.UserAPIModal;
import org.iflab.wecenterandroid.modal.home.Home;
import org.iflab.wecenterandroid.modal.home.Home501;
import org.iflab.wecenterandroid.modal.home.Home503;
import org.iflab.wecenterandroid.viewmodal.HomeViewModel;
import org.iflab.wecenterandroid.viewmodal.PersonCenterViewModal;
import org.iflab.wecenterandroid.viewmodal.PersonInfoViewModal;

import java.util.List;

/**
 * Created by Lyn on 16/1/30.
 */
public class PersonCenterAdapter extends RecyclerView.Adapter<PersonCenterAdapter.ViewHolder> {

    private static int HEADER = 34;
    private static int USERINFO = 36;
    private static int HOMELIST = 38;

    List<Home> dataList;
    Context host;
    UserAPIModal.RsmEntity user;
    public PersonCenterAdapter(Context context, List list){
        this.dataList = list;
        this.host = context;
    }

    public void setUserAPIModal(UserAPIModal.RsmEntity user){
        this.user = user;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if(viewType == HEADER){
            View emptyView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.personcenter_header, viewGroup, false);
            return new HeaderViewHolder(emptyView);
        }else if(viewType == USERINFO){
            View itemView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.personcenter_userinfo, viewGroup, false);
            return new UserInfoViewHolder(itemView);
        }else{
            View itemView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.home_add_article_item, viewGroup, false);
            return new ArticleViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(holder instanceof ArticleViewHolder){
            Home home =  dataList.get(position);
            if(home instanceof Home501){
                Home501 home501 = (Home501)home;
                ArticleViewHolder articleViewHolder = ((ArticleViewHolder) holder);
                articleViewHolder.bind(new HomeViewModel(host,home501));
            }else if(home instanceof Home503){
                Home503 hme503 = (Home503)home;
                ArticleViewHolder articleViewHolder = ((ArticleViewHolder) holder);
                articleViewHolder.bind(new HomeViewModel(host,hme503));
            }
        }else if(holder instanceof HeaderViewHolder){
            HeaderViewHolder item = ((HeaderViewHolder)holder);
            item.bind(new PersonCenterViewModal(host,user));
        }else if(holder instanceof UserInfoViewHolder){
            UserInfoViewHolder item = ((UserInfoViewHolder)holder);
            item.bind(new PersonCenterViewModal(host,user));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position > 1){
            return HOMELIST;
        }else if(position == 1){
            return USERINFO;
        }else {
            return HEADER;
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size() + 2;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class ArticleViewHolder extends ViewHolder{
        HomeAddArticleItemBinding binding;
        public ArticleViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void bind(@Nullable HomeViewModel home){
            binding.setArticle(home);
        }

        public HomeAddArticleItemBinding getBinding(){
            return binding;
        }

    }

    static class HeaderViewHolder extends ViewHolder{
        PersoncenterHeaderBinding personcenterHeaderBinding;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            personcenterHeaderBinding = DataBindingUtil.bind(itemView);
        }

        public void bind(PersonCenterViewModal item){
            personcenterHeaderBinding.setHeader(item);
        }

        public PersoncenterHeaderBinding getBind(){
            return personcenterHeaderBinding;
        }
    }

    static class UserInfoViewHolder extends ViewHolder{
        PersoncenterUserinfoBinding personcenterUserinfoBinding;
        public UserInfoViewHolder(View itemView) {
            super(itemView);
            personcenterUserinfoBinding = DataBindingUtil.bind(itemView);
        }

        public void bind(PersonCenterViewModal item){
            personcenterUserinfoBinding.setUserinfo(item);
        }

        public PersoncenterUserinfoBinding getBind(){
            return personcenterUserinfoBinding;
        }
    }


}
