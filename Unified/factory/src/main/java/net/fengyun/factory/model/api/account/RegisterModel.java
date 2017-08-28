package net.fengyun.factory.model.api.account;

/**
 * @author fengyun
 * 注册使用的model
 * 参数比较多，防止传错参数，使用Builder
 */
public class RegisterModel {
    private final String mobile;
    private final String password;
    private final String checkbox;
    private final String title;
    private final String t_uid;
    private final String code;


    public static class Builder{
        private String mobile;
        private String password;
        private String title;
        private String checkbox;
        private String t_uid;
        private String code;

        public Builder setMobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setT_uid(String t_uid) {
            this.t_uid = t_uid;
            return this;
        }

        public Builder setCode(String code) {
            this.code = code;
            return this;
        }

        public Builder setCheckbox(String checkbox) {
            this.checkbox = checkbox;
            return this;
        }

        public RegisterModel build(){
            return new RegisterModel(this);
        }
    }

    public RegisterModel(Builder builder) {
        this.mobile = builder.mobile;
        this.password = builder.password;
        this.title = builder.title;
        this.t_uid = builder.t_uid;
        this.checkbox = builder.checkbox;
        this.code = builder.code;
    }
}
