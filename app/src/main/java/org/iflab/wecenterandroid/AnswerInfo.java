package org.iflab.wecenterandroid;

import android.text.Spanned;

/**
 * Created by Lyn on 16/2/17.
 */
public class AnswerInfo {
    Spanned content;
    int uid;

    public Spanned getContent() {
        return content;
    }

    public void setContent(Spanned content) {
        this.content = content;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
