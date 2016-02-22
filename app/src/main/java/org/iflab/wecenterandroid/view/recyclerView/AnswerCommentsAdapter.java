package org.iflab.wecenterandroid.view.recyclerView;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.databinding.AnswerCommentsItemBinding;
import org.iflab.wecenterandroid.databinding.CommentsItemBinding;
import org.iflab.wecenterandroid.modal.AnswerComment;
import org.iflab.wecenterandroid.modal.Comments;

import java.util.List;

/**
 * Created by Lyn on 16/1/12.
 */
public class AnswerCommentsAdapter extends RecyclerView.Adapter<AnswerCommentsAdapter.ViewHolder> {
    private List<AnswerComment.RsmEntity> commentsList;
    private Context context;

    public AnswerCommentsAdapter(Context context,List list){
        this.commentsList = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.answer_comments_item, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(commentsList.get(position));
    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        AnswerCommentsItemBinding commentsItemBinding;
        public ViewHolder(View itemView) {
            super(itemView);
            commentsItemBinding = DataBindingUtil.bind(itemView);
        }

        public void bind(AnswerComment.RsmEntity rowsEntity){
            commentsItemBinding.setComment(rowsEntity);
        }
    }
}
