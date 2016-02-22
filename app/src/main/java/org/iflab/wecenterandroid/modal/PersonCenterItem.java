package org.iflab.wecenterandroid.modal;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.iflab.wecenterandroid.util.RoundedTransformation;

/**
 * Created by Lyn on 16/1/30.
 */
public class PersonCenterItem extends ObservableField{
    int avatar;
    String content;
    int num;
    int type;

    public PersonCenterItem(){}

    public PersonCenterItem(int avatar,String content,int num,int type){
        this.avatar = avatar;
        this.content = content;
        this.num = num;
        this.type = type;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @BindingAdapter({"bind:avatarUrl"})
    public static void loadImage(ImageView view, int resID) {
        view.setImageResource(resID);
    }

}
