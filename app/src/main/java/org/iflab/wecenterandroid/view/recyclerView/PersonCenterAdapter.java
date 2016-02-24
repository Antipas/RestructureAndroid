package org.iflab.wecenterandroid.view.recyclerView;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.databinding.PersonCenterItemBinding;
import org.iflab.wecenterandroid.modal.PersonCenterItem;
import org.iflab.wecenterandroid.view.activity.PersonInfoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lyn on 16/1/30.
 */
public class PersonCenterAdapter extends RecyclerView.Adapter<PersonCenterAdapter.ViewHolder> {

    public static final int SETTINGS_EMPTY_ITEM = 1;

    List<PersonCenterItem> dataList = new ArrayList<>();
    Context context;
    int uid;
    String avatar;
    String name;
    public PersonCenterAdapter(Context context,List list,int uid,String avatar,String name){
        this.dataList = list;
        this.context = context;
        this.uid = uid;
        this.avatar = avatar;
        this.name = name;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if(viewType == SETTINGS_EMPTY_ITEM){
            View emptyView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.empty_item, viewGroup, false);
            return new BackgroundViewHolder(emptyView);
        }else {
            View itemView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.person_center_item, viewGroup, false);
            return new ItemViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        if(dataList.get(position).getType() == SETTINGS_EMPTY_ITEM){
            return;
        }
        ItemViewHolder item = ((ItemViewHolder)viewHolder);
        item.bind(dataList.get(position));
        final int type = dataList.get(position).getType();
        item.getBind().linearTopicItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonInfoActivity.startPersonInfo(uid+"",type,avatar,name,context);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class ItemViewHolder extends ViewHolder{
        PersonCenterItemBinding personCenterItemBinding;
        public ItemViewHolder(View itemView) {
            super(itemView);
            personCenterItemBinding = DataBindingUtil.bind(itemView);
        }

        public void bind(PersonCenterItem item){
            personCenterItemBinding.setPerson(item);
        }

        public PersonCenterItemBinding getBind(){
            return personCenterItemBinding;
        }
    }

    static class BackgroundViewHolder extends ViewHolder{

        public BackgroundViewHolder(View itemView) {
            super(itemView);
        }
    }
}
