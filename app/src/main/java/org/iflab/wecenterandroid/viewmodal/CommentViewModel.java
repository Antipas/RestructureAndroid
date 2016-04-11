package org.iflab.wecenterandroid.viewmodal;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.iflab.wecenterandroid.modal.AnswerComment;
import org.iflab.wecenterandroid.base.BaseViewModel;
import org.iflab.wecenterandroid.modal.Comments;
import org.iflab.wecenterandroid.modal.SaveComment;
import org.iflab.wecenterandroid.util.RoundedTransformation;

import java.util.List;

import rx.Observable;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Lyn on 16/1/1.
 */
public class CommentViewModel extends BaseViewModel{

    private Comments.RsmEntity.RowsEntity comment;
    private ObservableInt commentCount ;
    private ObservableInt likeCount ;
    private onClickSendListener onClickSendListener;

    public interface onClickSendListener{
        void onClickSend();
    }

    public CommentViewModel(Context context,onClickSendListener onClickSendListener) {
        super(context);
        commentCount = new ObservableInt();
        likeCount = new ObservableInt();
        this.onClickSendListener = onClickSendListener;
    }

    public CommentViewModel(Context context,Comments.RsmEntity.RowsEntity comment) {
        super(context);
        this.comment = comment;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount.set(commentCount);
        notifyChange();
    }

    public void setLikeCount(int likeCount) {
        this.likeCount.set(likeCount);
        notifyChange();
    }

    public int getCommentCount() {
        return commentCount.get();
    }

    public int getLikeCount() {
        return likeCount.get();
    }

    public String getCommentInfo(){
        return String.format("%d 评论   %d 赞",getCommentCount(),getLikeCount());
    }

    public View.OnClickListener onClickSend(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSendListener.onClickSend();
            }
        };
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).transform(new RoundedTransformation()).into(view);
    }

    public String getAvatar(){
        return comment.getUser_info().getAvatar_file();
    }

    public String getUserName(){
        return comment.getUser_info().getUser_name();
    }

    public long getAddTime(){
        return comment.getAdd_time();
    }

    public String getMessage(){
        return comment.getMessage();
    }

}
