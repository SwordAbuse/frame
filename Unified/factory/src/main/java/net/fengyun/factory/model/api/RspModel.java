package net.fengyun.factory.model.api;

/**
 * @author fengyun
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class RspModel<T> {
    public static final String SUCCEED = "000";

    public static final int ERROR_UNKNOWN = 0;

    public static final int ERROR_NOT_FOUND_USER = 4041;
    public static final int ERROR_NOT_FOUND_GROUP = 4042;
    public static final int ERROR_NOT_FOUND_GROUP_MEMBER = 4043;

    public static final int ERROR_CREATE_USER = 3001;
    public static final int ERROR_CREATE_GROUP = 3002;
    public static final int ERROR_CREATE_MESSAGE = 3003;

    public static final int ERROR_PARAMETERS = 4001;
    public static final int ERROR_PARAMETERS_EXIST_ACCOUNT = 4002;
    public static final int ERROR_PARAMETERS_EXIST_NAME = 4003;

    public static final int ERROR_SERVICE = 5001;

    public static final int ERROR_ACCOUNT_TOKEN = 2001;
    public static final int ERROR_ACCOUNT_LOGIN = 2002;
    public static final int ERROR_ACCOUNT_REGISTER = 2003;

    public static final int ERROR_ACCOUNT_NO_PERMISSION = 2010;

    private String scode;                //是否成功
    private String ERROR_Param_Format; //报错参数
    private T data;
    private StatusBean status;
    private String pack_no;

    public String getPack_no() {
        return pack_no;
    }

    public void setPack_no(String pack_no) {
        this.pack_no = pack_no;
    }

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    public boolean success() {
        return SUCCEED.equals(status.getCode());
    }

    public String getCode() {
        return scode;
    }

    public void setCode(String code) {
        this.scode = code;
    }


    public String getERROR_Param_Format() {
        return ERROR_Param_Format;
    }

    public void setERROR_Param_Format(String ERROR_Param_Format) {
        this.ERROR_Param_Format = ERROR_Param_Format;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static class StatusBean {
        /**
         * code : 002
         * message : 参数错误
         */

        private String code;
        private String message;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}