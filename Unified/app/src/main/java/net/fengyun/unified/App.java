package net.fengyun.unified;

import android.content.Context;

import net.fengyun.common.app.Application;
import net.fengyun.factory.Factory;
import net.fengyun.factory.persistence.Account;
import net.fengyun.unified.activities.AccountActivity;

/**
 * @author fengyun
 * 应用程序
 */
public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        //调用factory进行初始化
        Factory.setup();


    }

    @Override
    protected void showAccountActivity(Context context) {
        Account.init(context);
        //登录界面的显示
        AccountActivity.show(context);


    }


}