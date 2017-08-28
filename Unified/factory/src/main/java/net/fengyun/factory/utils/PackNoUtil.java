package net.fengyun.factory.utils;

import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * 报文编号 工具类
 * @author fengyun
 */
public interface  PackNoUtil {

    enum StringEnum {
        LOGIN("10001");     //手机号的正则,11位手机号
        String name;

        StringEnum(@Nullable String name) {
            if (TextUtils.isEmpty(name)) {
                throw new ArithmeticException("Parameter can not be empty");
            }
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }

}
