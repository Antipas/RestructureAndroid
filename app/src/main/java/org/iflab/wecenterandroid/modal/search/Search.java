package org.iflab.wecenterandroid.modal.search;

/**
 * Created by Lyn on 16/2/10.
 */
public interface Search {
    static final String TOPIC = "topics";
    static final String QUESTION = "questions";
    static final String ARTICLE = "articles";
    static final String USER = "users";

    String getType();
    int getSearch_id();
}
