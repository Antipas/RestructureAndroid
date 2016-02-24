package org.iflab.wecenterandroid.view.recyclerView;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.databinding.PersonAnswerItemBinding;
import org.iflab.wecenterandroid.databinding.PersonArticleItemBinding;
import org.iflab.wecenterandroid.databinding.PersonCenterItemBinding;
import org.iflab.wecenterandroid.databinding.PersonFollowsFansItemBinding;
import org.iflab.wecenterandroid.databinding.PersonQuestionItemBinding;
import org.iflab.wecenterandroid.modal.PersonCenterItem;
import org.iflab.wecenterandroid.modal.person.PersonFollow;
import org.iflab.wecenterandroid.modal.person.PersonInfo;
import org.iflab.wecenterandroid.modal.person.PersonalAnswer;
import org.iflab.wecenterandroid.modal.person.PersonalArticle;
import org.iflab.wecenterandroid.modal.person.PersonalQuestion;
import org.iflab.wecenterandroid.viewmodal.AnswerViewModel;
import org.iflab.wecenterandroid.viewmodal.PersonInfoViewModal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lyn on 16/1/31.
 */
public class PersonInfoAdapter extends RecyclerView.Adapter {

    List<PersonInfo> dataList = new ArrayList<>();
    Context context;
    PersonInfoViewModal personInfoViewModal;
    int type;
    public PersonInfoAdapter(Context context, PersonInfoViewModal personInfoViewModal,List list, int type){
        this.dataList = list;
        this.context = context;
        this.type = type;
        this.personInfoViewModal = personInfoViewModal;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        switch (viewType){
            case PersonInfo.FANS:
            case PersonInfo.FOLLOW:
                return new FollowFansViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.person_follows_fans_item, viewGroup, false));
            case PersonInfo.ARTICLE:
                return new ArticleViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.person_article_item, viewGroup, false));
            case PersonInfo.QUESTION:
                return new QuestionViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.person_question_item, viewGroup, false));
            case PersonInfo.ANSWER:
                return new AnswerViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.person_answer_item, viewGroup, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (type){
            case PersonInfo.FANS:
            case PersonInfo.FOLLOW:
                ((FollowFansViewHolder)viewHolder).bind(dataList.get(position));
                break;
            case PersonInfo.ARTICLE:
                ((ArticleViewHolder)viewHolder).bind(dataList.get(position),personInfoViewModal);
                break;
            case PersonInfo.QUESTION:
                ((QuestionViewHolder)viewHolder).bind(dataList.get(position),personInfoViewModal);
                break;
            case PersonInfo.ANSWER:
                ((AnswerViewHolder)viewHolder).bind(dataList.get(position),personInfoViewModal);
                break;
        }

    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position)
                .getType();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class FollowFansViewHolder extends ViewHolder{
        PersonFollowsFansItemBinding personFollowsFansItemBinding;
        public FollowFansViewHolder(View itemView) {
            super(itemView);
            personFollowsFansItemBinding = DataBindingUtil.bind(itemView);
        }

        public void bind(PersonInfo item){
            personFollowsFansItemBinding.setUser((PersonFollow.RsmEntity.RowsEntity) item);
        }
    }

    static class ArticleViewHolder extends ViewHolder{
        PersonArticleItemBinding personArticleItemBinding;
        public ArticleViewHolder(View itemView) {
            super(itemView);
            personArticleItemBinding = DataBindingUtil.bind(itemView);
        }

        public void bind(PersonInfo item,PersonInfoViewModal personInfoViewModal){
            personArticleItemBinding.setArticle((PersonalArticle.RsmEntity.RowsEntity) item);
            personArticleItemBinding.setPersonInfo(personInfoViewModal);
        }
    }

    static class QuestionViewHolder extends ViewHolder{
        PersonQuestionItemBinding personQuestionItemBinding;
        public QuestionViewHolder(View itemView) {
            super(itemView);
            personQuestionItemBinding = DataBindingUtil.bind(itemView);
        }

        public void bind(PersonInfo item,PersonInfoViewModal personInfoViewModal){
            personQuestionItemBinding.setQuestion((PersonalQuestion.RsmEntity.RowsEntity) item);
            personQuestionItemBinding.setPersonInfo(personInfoViewModal);
        }
    }

    static class AnswerViewHolder extends ViewHolder{
        PersonAnswerItemBinding personAnswerItemBinding;
        public AnswerViewHolder(View itemView) {
            super(itemView);
            personAnswerItemBinding = DataBindingUtil.bind(itemView);
        }

        public void bind(PersonInfo item,PersonInfoViewModal personInfoViewModal){
            personAnswerItemBinding.setAnswer((PersonalAnswer.RsmEntity.RowsEntity) item);
            personAnswerItemBinding.setPersonInfo(personInfoViewModal);
        }
    }
}
