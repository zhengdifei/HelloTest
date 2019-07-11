package com.example.hellotest.datastorage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.hellotest.R;
import com.example.hellotest.datastorage.entity.User;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class RoomTestActivity extends AppCompatActivity {

    private Context context;
    //private UserDB userdb = Room.databaseBuilder(this, UserDB.class, "users").build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_test);

        context = this;
        ButterKnife.bind(this);

        //UserDao userDao = userdb.getUserDao();
//        Flowable.range(0, 20).map(res ->{
//            User user = new User(0, "sample_" + res, "la", res + "phone" + res);
//            long size = userDao.insert(user);
//            return size;
//        }).subscribeOn(Scheduler.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(every -> { count = count + every});
    }

//    private void databaseOperation(){
//        UserDB userDB = Room.databaseBuilder(getApplicationContext(), UserDB.class, "users").build();
//        UserDao userDao = userDB.getUserDao();
//
//        writeUserDatabase(userDao, new User(0, "sample_" + 1, "la", "phone_" + 1));
//        readDatabase(userDao);
//    }

//    private void readDatabase(UserDao userDao){
//        Log.d("zdf", "读数据库……");
//        List<User> users = userDao.getAllUsers();
//        for (User u : users) {
//            Log.d("zdf", u.toString());
//        }
//        Log.d("zdf", "读数据库完毕.");
//    }
//
//    private void writeUserDatabase(UserDao userDao, User u){
//        userDao.insert(u);
//    }

    @OnClick({R.id.add, R.id.delete, R.id.getall})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.add:
                Observable.just(new User("zdf2", "wuhan", "1862"))
                        .subscribeOn(Schedulers.io())
                        .subscribe(user -> UserDB.getINSTANCE(context).getUserDao().insert(user));
                break;
            case R.id.delete:
                Observable.just(new User(2))
                        .subscribeOn(Schedulers.io())
                        .subscribe(user -> UserDB.getINSTANCE(context).getUserDao().delete(user));
                //UserDB.getINSTANCE(context).getUserDao().delete(new User(2, "zdf2", "wuhan", "1862"));
                break;
            case R.id.getall:
                UserDB.getINSTANCE(context).getUserDao().getAllAsync()
                        .subscribe(users -> {
                            Log.e("zdf", "accept: users = " + users.size());
                            for(User u : users){
                                Log.e("zdf", u.toString());
                            }
                        });
                break;
            default:
                break;
        }
    }
}
