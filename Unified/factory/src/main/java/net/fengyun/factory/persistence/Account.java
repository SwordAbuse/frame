package net.fengyun.factory.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import net.fengyun.factory.Factory;
import net.fengyun.factory.model.api.account.AccountRspModel;


/**
 * @author fengyun
 *
 */
public class Account {

    private static final String KEY_LOGIN_ID = "KEY_LOGIN_ID";
    //登录的login_id,用来接口请求
    private static String login_id;
    /**
     * 存储数据到xml文件，持久化
     */
    public static void init(Context context){
        //初始化这些数据
        login_id="";
        save(context);
    }

    /**
     * 存储数据到xml文件，持久化
     */
    private static void save(Context context){
        //获取数据持久化的SP
        SharedPreferences sp = context.getSharedPreferences(
                Account.class.getName(),Context.MODE_PRIVATE);
        //存储数据
        sp.edit().putString(KEY_LOGIN_ID,login_id)
                .apply();
    }

    /**
     * 进行数据加载
     * @param context 上下文
     */
    public static void load(Context context){
        SharedPreferences sp = context.getSharedPreferences(
                Account.class.getName(),Context.MODE_PRIVATE);
        login_id = sp.getString(KEY_LOGIN_ID,"");
    }



    /**
     * 返回当前账户是否登录
     * @return True 已登录
     */
    public static boolean isLogin(){
       //用户id和token不为空
        return !TextUtils.isEmpty(login_id);
    }

    /**
     * 是否已经完善了用户信息
     * @return True 完成了
     */
    public static boolean isComplete(){
        //首先保证登录成功
        if(isLogin()){
            return !TextUtils.isEmpty(Account.getLogin_id());
        }
        //未登录返回信息不完全
        return false;
    }

    public static String getLogin_id() {
        return login_id;
    }

    /**
     * 保存我自己的信息到持久化的xml中
     * @param model
     */
    public static void login(AccountRspModel model){
        //存储用户的token ,用户的id，方便从数据库查询到我的id
        Account.login_id = model.getLogin_id();
        save(Factory.app());
    }

}
