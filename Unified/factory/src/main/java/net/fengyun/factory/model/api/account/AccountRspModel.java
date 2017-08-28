package net.fengyun.factory.model.api.account;


/**
 * 账户的返回model
 * @author fengyun
 */
public class AccountRspModel {

    //加密的账户id
    private String login_id;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }
}
