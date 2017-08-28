package net.fengyun.factory.net;


import net.fengyun.factory.model.api.RspModel;
import net.fengyun.factory.model.api.account.AccountRspModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author fengyun
 *         网络请求的所有接口
 */
public interface RemoteService {



    /**
     * 注册接口
     * @return AccountRspModel
     */
    @GET("app.php")/**?m=App&c=api&a=processing 10000接口 注册**/
    Call<RspModel<AccountRspModel>> accountRegister(
            @Query("m") String App,
            @Query("c") String api,
            @Query("a") String processing,
            @Query("requestData") String requestData);

    /**
     * 登录接口
     * @param
     * @return AccountRspModel
     */
    @GET("app.php")/**?m=App&c=api&a=processing 10000接口 登录**/
    Call<RspModel<AccountRspModel>> accountLogin(
            @Query("m") String App,
            @Query("c") String api,
            @Query("a") String processing,
            @Query("requestData") String requestData);

}
