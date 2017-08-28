package net.fengyun.factory.data.helper;

import net.fengyun.common.Common;
import net.fengyun.factory.Factory;
import net.fengyun.factory.R;
import net.fengyun.factory.data.DataSource;
import net.fengyun.factory.model.api.RspModel;
import net.fengyun.factory.model.api.account.AccountRspModel;
import net.fengyun.factory.model.api.account.LoginModel;
import net.fengyun.factory.model.api.account.RegisterModel;
import net.fengyun.factory.model.db.User;
import net.fengyun.factory.net.Network;
import net.fengyun.factory.net.RemoteService;
import net.fengyun.factory.utils.PackNoUtil;
import net.fengyun.factory.utils.PackUtil;
import net.fengyun.utils.LogUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * @author fengyun
 *         账户的接口
 */
public class AccountHelper {

    /**
     * 注册的调用
     *
     * @param model    传递一个一个注册的model
     * @param callback 成功和失败的回送
     */
    public static void register(final RegisterModel model, final DataSource.Callback<User> callback) {
        //调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();
        //得到一个Call
        Call<RspModel<AccountRspModel>> call =
                service.accountRegister(
                        Common.StringEnum.APP.getName(),
                        Common.StringEnum.API.getName(),
                        Common.StringEnum.PROCESSING.getName(),
                        Factory.getGson().toJson(model));
        call.enqueue(new AccountRspCallback(callback));
    }

    /**
     * 登录的调用
     *
     * @param model    集合
     * @param callback 成功和失败的回送
     */
    public static void login(LoginModel model, final DataSource.Callback<User> callback) {
        //调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();
        //得到一个Call
        Call<RspModel<AccountRspModel>> call = service
                .accountLogin(
                        Common.StringEnum.APP.getName(),
                        Common.StringEnum.API.getName(),
                        Common.StringEnum.PROCESSING.getName(),
                        PackUtil.getInstance().getRequestPacket2
                                (PackNoUtil.StringEnum.LOGIN.getName(), Factory.getGson().toJson(model)));
        //异步请求
        call.enqueue(new AccountRspCallback(callback));
    }


    /**
     * 请求的回调封装
     */
    private static class AccountRspCallback implements Callback<RspModel<AccountRspModel>> {
        final DataSource.Callback<User> callback;

        public AccountRspCallback(DataSource.Callback<User> callback) {
            this.callback = callback;
        }

        @Override
        public void onResponse(Call<RspModel<AccountRspModel>> call, Response<RspModel<AccountRspModel>> response) {
            //网络请求成功返回
            //从返回中得到我们的全局Model 内部是使用的Gson进行解析
            RspModel<AccountRspModel> rspModel = response.body();
            LogUtil.getInstance().e(response.body().getPack_no());
            LogUtil.getInstance().e(response.body().getStatus().getCode());
            LogUtil.getInstance().e(response.body().getStatus().getMessage());

            if (rspModel.success()) {
                AccountRspModel model = rspModel.getData();
                LogUtil.getInstance().e(model.getType());
                LogUtil.getInstance().e(model.getLogin_id());
            } else {
                //对返回responce中的失败的code进行解析，解析到对应的String资源上面
                Factory.decodeRspCode(rspModel, callback);
            }
        }

        @Override
        public void onFailure(Call<RspModel<AccountRspModel>> call, Throwable t) {
            //网络请求失败
            if (callback != null) {
                callback.onDataNotAvailable(R.string.data_network_error);
            }

        }
    }

}
