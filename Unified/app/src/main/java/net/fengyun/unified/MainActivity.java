package net.fengyun.unified;

import android.content.Context;
import android.content.Intent;

import net.fengyun.common.app.Activity;
import net.fengyun.unified.activities.CustomCameraActivity;

public class MainActivity extends Activity {

    /**
     * MainActivity跳转的入口
     * @param context 上下文
     */
    public static void show(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

//    @Override
//    protected boolean initArgs(Bundle bundle) {
//        if (Account.isComplete()) {
//            //判断用户是否完全，完全走正常流程
//            return super.initArgs(bundle);
//        } else {
//            AccountActivity.show(this);
//            //返回false会自动关闭activity
//            return false;
//        }
//    }

    @Override
    protected void initData() {
        super.initData();
        CustomCameraActivity.show(this);
    }

    @Override
    protected int getContentLayoutId() {

        return R.layout.activity_main;
    }
}
