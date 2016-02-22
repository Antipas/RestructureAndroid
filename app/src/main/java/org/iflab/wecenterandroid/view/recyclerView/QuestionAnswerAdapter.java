package org.iflab.wecenterandroid.view.recyclerView;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.squareup.picasso.Picasso;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.databinding.AnswerItemBinding;
import org.iflab.wecenterandroid.databinding.QuestionButtonItemBinding;
import org.iflab.wecenterandroid.databinding.QuestionContentItemBinding;
import org.iflab.wecenterandroid.databinding.QuestionInfoItemBinding;
import org.iflab.wecenterandroid.databinding.QuestionTopicsItemBinding;
import org.iflab.wecenterandroid.databinding.QuestionUserinfoItemBinding;
import org.iflab.wecenterandroid.modal.Answer;
import org.iflab.wecenterandroid.modal.Question;
import org.iflab.wecenterandroid.view.activity.AnswerActivity;
import org.iflab.wecenterandroid.view.activity.PersonCenterActivity;

import java.util.List;

import rx.functions.Action1;

/**
 * Created by Lyn on 15/12/21.
 */
public class QuestionAnswerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_TYPE_TOPICS = 0;
    private static final int ITEM_TYPE_USERINFO = 1;
    private static final int ITEM_TYPE_CONTENT = 2;
    private static final int ITEM_TYPE_INFO = 3;
//    private static final int ITEM_TYPE_BUTTON = 4;
    private static final int ITEM_TYPE_NORMAL = 5;

    List<Question.RsmEntity.AnswersEntity> list;
    List<Question.RsmEntity.QuestionTopicsEntity> topicsEntity;
    Question.RsmEntity.QuestionInfoEntity questionInfoEntity;
    Activity host;
    onQuestionItemViewClickListener questionItemViewClickListener;

    public QuestionAnswerAdapter(Activity host,@NonNull List list,
                @NonNull List topicsEntity){
        this.host = host;
        this.list = list;
        this.topicsEntity = topicsEntity;

    }

    public void setInfo(@NonNull Question.RsmEntity.QuestionInfoEntity questionInfoEntity){
        this.questionInfoEntity = questionInfoEntity;
    }


    @Override
    public int getItemViewType(int position) {
        switch (position){
            case 0:
                return ITEM_TYPE_TOPICS;
            case 1:
                return ITEM_TYPE_USERINFO;
            case 2:
                return ITEM_TYPE_CONTENT;
            case 3:
                return ITEM_TYPE_INFO;
//            case 4:
//                return ITEM_TYPE_BUTTON;
            default:
                return ITEM_TYPE_NORMAL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        switch (viewType){
            case ITEM_TYPE_TOPICS:
                return new TopicsViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.question_topics_item, viewGroup, false));
            case ITEM_TYPE_USERINFO:
                return new UserInfoViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.question_userinfo_item, viewGroup, false));
            case ITEM_TYPE_CONTENT:
                return new ContentViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.question_content_item, viewGroup, false));
            case ITEM_TYPE_INFO:
                return new InfoViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.question_info_item, viewGroup, false));
//            case ITEM_TYPE_BUTTON:
//                return new ButtonViewHolder(LayoutInflater.from(viewGroup.getContext())
//                        .inflate(R.layout.question_button_item, viewGroup, false));
            default:
                return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.answer_item, viewGroup, false));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if(questionInfoEntity == null){
            return;
        }

        if(getItemViewType(position) == ITEM_TYPE_NORMAL){
            final Question.RsmEntity.AnswersEntity answer = list.get(position - 4);
            final ViewHolder answerViewHolder = ((ViewHolder)viewHolder);
            answerViewHolder.bind(answer);
//            Picasso.with(host.getApplicationContext()).load(answer.getUser_info().getAvatar_file()).into(answerViewHolder.getBinding().ivAvatar);

            RxView.clicks(answerViewHolder.getBinding().getRoot())
                    .subscribe(new Action1<Void>() {
                        @Override
                        public void call(Void aVoid) {
                            AnswerActivity.startAnswer(answer.getAnswer_id(),host);
                        }
                    });
            RxView.clicks(answerViewHolder.getBinding().ivAvatar)
                    .subscribe(new Action1<Void>() {
                        @Override
                        public void call(Void aVoid) {
                            PersonCenterActivity.startPersonCenter(answer.getUid(),host,answerViewHolder.getBinding().ivAvatar);
                        }
                    });
        }else {
            switch (getItemViewType(position)){
                case ITEM_TYPE_TOPICS:
                    TopicsViewHolder topicsViewHolder = ((TopicsViewHolder)viewHolder);
                    topicsViewHolder.bind(questionInfoEntity);
                    TagFlowLayout tagContainer = topicsViewHolder.getBinding().flowQuestionTopic;
                    String []topics = null;
                    int len = topicsEntity.size();
                    if( len > 0){
                        topics = new String[len];
                        for (int i = 0;i < topicsEntity.size();i++){
                            topics[i] = topicsEntity.get(i).getTopic_title();
                        }
                    }else {
                        tagContainer.setVisibility(View.GONE);
                    }

                    tagContainer.setAdapter(new TagAdapter<String>(topics) {
                        @Override
                        public View getView(FlowLayout mFlowLayout, int position, String topic) {
                            TextView tag = (TextView) LayoutInflater.from(mFlowLayout.getContext()).inflate(R.layout.topic_single_item,
                                    mFlowLayout, false);
                            tag.setText(topic);
                            return tag;
                        }
                    });

//                    tagContainer.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
//                        @Override
//                        public boolean onTagClick(View view, int position, FlowLayout parent) {
//                            //TODO
//                            return true;
//                        }
//                    });
                    break;
                case ITEM_TYPE_USERINFO:
                    final UserInfoViewHolder userInfoViewHolder = ((UserInfoViewHolder)viewHolder);
                    userInfoViewHolder.bind(questionInfoEntity.getUser_info());
                    Picasso.with(host).load(questionInfoEntity.getUser_info().getAvatar_file()).into(((UserInfoViewHolder) viewHolder).getBinding().ivAvatar);

                    RxView.clicks(userInfoViewHolder.getBinding().getRoot())
                            .subscribe(new Action1<Void>() {
                                @Override
                                public void call(Void aVoid) {
                                    PersonCenterActivity.startPersonCenter(questionInfoEntity.getUser_info().getUid(),
                                            host,
                                            userInfoViewHolder.getBinding().ivAvatar
                                    );
                                }
                            });
                    break;
                case ITEM_TYPE_CONTENT:
                    ((ContentViewHolder)viewHolder).bind(questionInfoEntity);
                    break;
                case ITEM_TYPE_INFO:
                    ((InfoViewHolder)viewHolder).bind(questionInfoEntity);
                    final View btn = ((InfoViewHolder)viewHolder).getBinding().btnFocus;
                    RxView.clicks(btn)
                            .subscribe(new Action1<Void>() {
                                @Override
                                public void call(Void aVoid) {
                                    questionItemViewClickListener.addFocus(btn);
                                }
                            });
                    break;
//                case ITEM_TYPE_BUTTON:
//                    ButtonViewHolder buttonViewHolder = ((ButtonViewHolder)viewHolder);
//                    buttonViewHolder.bind(questionInfoEntity);
//                    RxView.clicks(buttonViewHolder.getBinding().btnAddAnswer)
//                            .subscribe(new Action1<Void>() {
//                                @Override
//                                public void call(Void aVoid) {
//                                    questionItemViewClickListener.addAnswer();
//                                }
//                            });

//                    RxView.clicks(buttonViewHolder.getBinding().btnFavorite)
//                            .subscribe(new Action1<Void>() {
//                                @Override
//                                public void call(Void aVoid) {
//                                    questionItemViewClickListener.favorite();
//                                }
//                            });
//
//                    RxView.clicks(buttonViewHolder.getBinding().btnLove)
//                            .subscribe(new Action1<Void>() {
//                                @Override
//                                public void call(Void aVoid) {
//                                    questionItemViewClickListener.love();
//                                }
//                            });
//                    break;
                default:break;
            }
        }

    }

    public void setOnQuestionItemViewClickListener(onQuestionItemViewClickListener questionItemViewClickListener){
        this.questionItemViewClickListener = questionItemViewClickListener;

    }

    @Override
    public int getItemCount() {
        return list.size() + 4;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        AnswerItemBinding binding;
        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void bind(@NonNull Question.RsmEntity.AnswersEntity entity ){
            binding.setAnswer(entity);
        }

        public AnswerItemBinding getBinding(){
            return binding;
        }
    }

    static class TopicsViewHolder extends RecyclerView.ViewHolder{
        QuestionTopicsItemBinding binding;
        public TopicsViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void bind(@NonNull Question.RsmEntity.QuestionInfoEntity entity ){
            binding.setQuestion(entity);
        }

        public QuestionTopicsItemBinding getBinding(){
            return binding;
        }
    }

    static class UserInfoViewHolder extends RecyclerView.ViewHolder{
        QuestionUserinfoItemBinding binding;
        public UserInfoViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void bind(@NonNull Question.RsmEntity.QuestionInfoEntity.UserInfoEntity entity ){
            binding.setQuestionUser(entity);
        }

        public QuestionUserinfoItemBinding getBinding(){
            return binding;
        }
    }

    static class ContentViewHolder extends RecyclerView.ViewHolder{
        QuestionContentItemBinding binding;
        public ContentViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void bind(@NonNull Question.RsmEntity.QuestionInfoEntity entity ){
            binding.setQuestion(entity);
        }

        public QuestionContentItemBinding getBinding(){
            return binding;
        }
    }

    static class InfoViewHolder extends RecyclerView.ViewHolder{
        QuestionInfoItemBinding binding;
        public InfoViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void bind(@NonNull Question.RsmEntity.QuestionInfoEntity entity ){
            binding.setQuestion(entity);
        }

        public QuestionInfoItemBinding getBinding(){
            return binding;
        }
    }

    static class ButtonViewHolder extends RecyclerView.ViewHolder{
        QuestionButtonItemBinding binding;
        public ButtonViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void bind(@NonNull Question.RsmEntity.QuestionInfoEntity entity ){
            binding.setQuestion(entity);
        }

        public QuestionButtonItemBinding getBinding(){
            return binding;
        }
    }

    public interface onQuestionItemViewClickListener{
        void addFocus(View view);
        void addAnswer();
//        void love();
//        void favorite();
    }
}
