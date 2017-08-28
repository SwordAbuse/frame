package net.fengyun.factory.presenter.account;

import android.support.annotation.StringRes;
import android.text.TextUtils;

import net.fengyun.factory.R;
import net.fengyun.factory.data.DataSource;
import net.fengyun.factory.data.helper.AccountHelper;
import net.fengyun.factory.model.api.account.LoginModel;
import net.fengyun.factory.model.db.User;
import net.fengyun.factory.presenter.BasePresenter;
import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

/**
 * @author fengyun
 *         登录的逻辑
 */
public class LoginPresenter extends BasePresenter<LoginContract.View>
        implements LoginContract.Presenter, DataSource.Callback<User> {

    public LoginPresenter(LoginContract.View view) {
        super(view);
    }

    @Override
    public void login(String phone, String password) {
        start();
        final LoginContract.View view = getView();
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
            view.showError(R.string.data_account_login_invalid_parameter);
        } else {
//            Map<String,String> data = new ArrayMap<>();
//            data.put("mobile",phone);
//            data.put("password",password);
            LoginModel model = new LoginModel(phone,password);
            AccountHelper.login(model, this);
        }
    }

    @Override
    public void onDataNotAvailable(@StringRes final int strRes) {
        final LoginContract.View view = getView();
        if (view == null)
            return;
        //强制执行在主线程
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                view.showError(strRes);
            }
        });
    }

    @Override
    public void onDataLoaded(User user) {
        final LoginContract.View view = getView();
        if (view == null)
            return;
        //强制执行在主线程
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                view.loginSuccess();
            }
        });
    }
}
