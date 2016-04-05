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

    private static final int ACTION_101 = 101;
    private static final int ACTION_501 = 501;
    private static final int ACTION_502 = 502;
    private static final int ACTION_503 = 503;
    private static final int ACTION_105 = 105;
    private static final int ACTION_201 = 201;
    private static final int ACTION_204 = 204;

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
//            if(viewType == ACTION_101 || viewType == ACTION_105){
//                return new QuestionViewHolder(LayoutInflater.from(viewGroup.getContext())
//                        .inflate(R.layout.home_add_question_item, viewGroup, false),viewType);
//            } else
            if (viewType == ACTION_501 || viewType == ACTION_502 ||viewType == ACTION_503) {
                return new ArticleViewHolder(LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.home_add_article_item, viewGroup, false),viewType);

//            }else if (viewType == ACTION_201 || viewType == ACTION_204){
//                return new AnswerViewHolder(LayoutInflater.from(viewGroup.getContext())
//                        .inflate(R.layout.home_add_answer_item, viewGroup, false),viewType);
            }
            else {
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
//        else if(holder instanceof QuestionViewHolder){
//            final Home101 home101 = (Home101) dataList.get(position);
//            QuestionViewHolder questionViewHolder = ((QuestionViewHolder) holder);
//            questionViewHolder.bind(new HomeViewModel(host,home101));
//
//        }else if(holder instanceof AnswerViewHolder){
//            final Home201 home201 = (Home201) dataList.get(position);
//            AnswerViewHolder answerViewModel = ((AnswerViewHolder) holder);
//            answerViewModel.bind(new HomeViewModel(host,home201));
//
//        }

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

//    static class QuestionViewHolder extends ViewHolder {
//        HomeAddQuestionItemBinding binding;
//
//        public QuestionViewHolder(View itemView, int action) {
//            super(itemView, action);
//            binding = DataBindingUtil.bind(itemView);
//        }
//
//        public void bind(@Nullable HomeViewModel home){
//            binding.setQuestion(home);
//        }
//
//        public HomeAddQuestionItemBinding getBinding(){
//            return binding;
//        }
//
//    }
//
//    static class AnswerViewHolder extends ViewHolder{
//        HomeAddAnswerItemBinding binding;
//
//        public AnswerViewHolder(View itemView, int action) {
//            super(itemView, action);
//            binding = DataBindingUtil.bind(itemView);
//        }
//        public void bind(@Nullable HomeViewModel home){
//            binding.setAnswer(home);
//        }
//
//        public HomeAddAnswerItemBinding getBinding(){
//            return binding;
//        }
//    }
}
