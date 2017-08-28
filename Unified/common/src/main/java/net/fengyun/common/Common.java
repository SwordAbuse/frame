package net.fengyun.common;

import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * @author fengyun
 */

public interface Common {
    enum StringEnum{
        REGEX_MOBILE("[1][3,4,5,7,8][0-9]{9}$"),     //手机号的正则,11位手机号
        TOKEN("token"),
        KEY("n'NI&u#+lFA0y@;$6Wj=5(~9//*&#$%^%%^~@!@#$%^&$#"),
        PACK_NO("pack_no"),
        ROLES("roles"),
        REQ_DATE("date"),
        USER_ID("user_id"),
        DEVICE_ID("deviceId"),
        DATA("data"),

        APP("App"),//后台固定参数
        API("api"),
        PROCESSING("processing"),

        API_URL("http://dachabu.0791jr.com/");    //对应自己的虚拟ip地址
        String name;
        StringEnum(@Nullable String name){
            if(TextUtils.isEmpty(name)){
                throw new ArithmeticException("Parameter can not be empty");
            }
            this.name= name;
        }

        public String getName() {
            return name;
        }
    }


    enum LongEnum{
        MAX_UPLOAD_IMAGE_LENGTH(860 * 1024);  //最大的上传图片大小 860KB
        long x;
        LongEnum(long x){
            this.x = x;
        }

        public long getX() {
            return x;
        }

        public void setX(long x) {
            this.x = x;
        }
    }

}
