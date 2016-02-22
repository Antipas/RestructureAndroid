package org.iflab.wecenterandroid.viewmodal;

import android.content.Context;

import org.iflab.wecenterandroid.base.BaseViewModel;
import org.iflab.wecenterandroid.modal.search.Search;

import java.util.List;

import rx.Observable;

/**
 * Created by Lyn on 16/2/8.
 */
public class SearchViewModal extends BaseViewModel {
    public SearchViewModal(Context context) {
        super(context);
    }

    public Observable<List<Search>> doQuery(String query,int page){
        return dataManager.doQuery(query,page);
    }
}
