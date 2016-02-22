package org.iflab.wecenterandroid.view.recyclerView;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.databinding.TopicsItemBinding;
import org.iflab.wecenterandroid.modal.Topic;

import java.util.List;

/**
 * Created by Lyn on 15/12/14.
 */
public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.ViewHolder> {

    List<Topic.RsmEntity.RowsEntity> dataList;
    Context context;

    public TopicsAdapter(@NonNull Context context,@NonNull List dataList) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.topics_item, viewGroup, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.bind(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TopicsItemBinding topicsItemBinding;

        public ViewHolder(View view){
            super(view);
            topicsItemBinding = DataBindingUtil.bind(itemView);
        }

        public void bind(@NonNull Topic.RsmEntity.RowsEntity topic) {
            topicsItemBinding.setTopic(topic);
        }
    }
}
