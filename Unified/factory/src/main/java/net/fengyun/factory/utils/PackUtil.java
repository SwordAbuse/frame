package net.fengyun.factory.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import net.fengyun.common.Common;
import net.fengyun.common.app.Application;
import net.fengyun.factory.persistence.Account;
import net.fengyun.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 请求参数 进行加密
 *
 * @author fengyun
 */

public class PackUtil {
    private static PackUtil packUtil;

    private PackUtil() {
    }

    static {
        if (packUtil == null) {
            packUtil = new PackUtil();
        }
    }

    public static PackUtil getInstance() {
        return packUtil;
    }


    /**
     * @param packetNo 报文编号
     * @param data     报文体
     * @return 请求报文
     */
    public static String getRequestPacket(String packetNo, Map<String, String> data) {
        String result = "";
        String UserId;
        JSONObject rows = new JSONObject();
        try {
            UserId = Account.getLogin_id();
            String date = String.valueOf(System.currentTimeMillis());
            String token = Common.StringEnum.KEY.getName() + packetNo + date + UserId;
            token = MD5keyBean.newInstance().getkeyBeanofStr(token);
            rows.put(Common.StringEnum.TOKEN.getName(), token);
            TelephonyManager tm = (TelephonyManager) Application.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
            String deviceId = tm.getDeviceId();
            rows.put(Common.StringEnum.DEVICE_ID.getName(), deviceId);
            rows.put(Common.StringEnum.ROLES.getName(), "");
            rows.put(Common.StringEnum.PACK_NO.getName(), packetNo);
            rows.put(Common.StringEnum.REQ_DATE.getName(), date);
            rows.put(Common.StringEnum.USER_ID.getName(), UserId);
            rows.put(Common.StringEnum.DATA.getName(), data==null ? new JSONObject() : new JSONObject(data));
            result = ChangeCharset.toUTF_8(rows.toString());

            LogUtil.getInstance().e(rows.toString() );
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * @param packetNo 报文编号
     * @param data     报文体
     * @return 请求报文
     */
    public static String getRequestPacket2(String packetNo, String data) {
        String result = "";
        String UserId;
        JSONObject rows = new JSONObject();
        try {
            UserId = Account.getLogin_id();
            String date = String.valueOf(System.currentTimeMillis());
            String token = Common.StringEnum.KEY.getName() + packetNo + date + UserId;

            token = MD5keyBean.newInstance().getkeyBeanofStr(token);
            rows.put(Common.StringEnum.TOKEN.getName(), token);
            TelephonyManager tm = (TelephonyManager) Application.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
            String deviceId = tm.getDeviceId();
            rows.put(Common.StringEnum.DEVICE_ID.getName(), deviceId);
            rows.put(Common.StringEnum.ROLES.getName(), "");
            rows.put(Common.StringEnum.PACK_NO.getName(), packetNo);
            rows.put(Common.StringEnum.REQ_DATE.getName(), date);
            rows.put(Common.StringEnum.USER_ID.getName(), UserId);
            rows.put(Common.StringEnum.DATA.getName(), TextUtils.isEmpty(data) ? new JSONObject() :data);
            result = ChangeCharset.toUTF_8(rows.toString());

            LogUtil.getInstance().e(rows.toString() );
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
