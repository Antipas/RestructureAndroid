package org.iflab.wecenterandroid.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.iflab.wecenterandroid.R;
import org.iflab.wecenterandroid.base.BaseActivity;
import org.iflab.wecenterandroid.databinding.ActivityLoginBinding;
import org.iflab.wecenterandroid.databinding.ContentLoginBinding;
import org.iflab.wecenterandroid.modal.LoginInfo;
import org.iflab.wecenterandroid.modal.User;
import org.iflab.wecenterandroid.modal.person.PersonInfo;
import org.iflab.wecenterandroid.modal.prefs.UserPrefs;
import org.iflab.wecenterandroid.viewmodal.UserViewModel;

import retrofit.Response;
import rx.Subscriber;
import rx.Subscription;

public class LoginActivity extends BaseActivity{
    UserViewModel userViewModal;
    ContentLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (UserPrefs.getInstance(getApplicationContext()).getUserId() != 0l){
            gotoMainActivity();
        }

        ActivityLoginBinding loginBinding=  DataBindingUtil.setContentView(this,R.layout.activity_login);
        setSupportActionBar(loginBinding.toolbar);


        userViewModal = new UserViewModel(getApplicationContext());

        binding = DataBindingUtil.setContentView(this, R.layout.content_login);
        binding.setUser(userViewModal);


    }


    public void onClickLogin(View view){
        String name = binding.editTxtName.getText().toString();
        String pwd = binding.editTxtPwd.getText().toString();

        name = "lyn";
        pwd = "123456";

        if(name.length() == 0){
            Toast.makeText(getApplicationContext(),"用户名不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(pwd.length() < 6){
            Toast.makeText(getApplicationContext(),"密码不能少于6位",Toast.LENGTH_SHORT).show();
            return;
        }

        Subscription subscription = userViewModal.loadLogin(name, pwd)
                .subscribe(new Subscriber<Response<LoginInfo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<LoginInfo> response) {

                        LoginInfo info = response.body();
                        switch (info.getErrno()) {
                            case -1:
                                //error
                                showToast(info.getErr().toString());
                                break;
                            case 1:
                                User user = new User.Builder()
                                        .setUser_name(info.getRsm().getUser_name())
                                        .setAvatar_file(info.getRsm().getAvatar_file())
                                        .setUid(info.getRsm().getUid())
                                        .build();
                                userViewModal.setLoggedInUser(user);
                                userViewModal.setCookie(response.raw().header("Set-Cookie"));

                                gotoMainActivity();
                        }
                    }
                });
        addSubscription(subscription);
    }

    private void gotoMainActivity(){
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void loadData() {

    }
}
