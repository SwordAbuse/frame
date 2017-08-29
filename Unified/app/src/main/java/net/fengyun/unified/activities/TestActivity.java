package net.fengyun.unified.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import net.fengyun.common.app.Activity;
import net.fengyun.common.widget.convention.DragSelectRecyclerView;
import net.fengyun.common.widget.convention.IDragSelectAdapter;
import net.fengyun.common.widget.recycler.RecyclerAdapter;
import net.fengyun.unified.R;
import net.fengyun.utils.LogUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.BindView;

public class TestActivity extends Activity {

    @BindView(R.id.im_bg)
    ImageView mBg;

    @BindView(R.id.recycler_view)
    DragSelectRecyclerView mRecyclerView;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected void initWeight() {
        super.initWeight();
        if (getIntent().getExtras() != null) {
            String picPath = getIntent().getExtras().getString("picPath");
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(new File(picPath));
                Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
                LogUtil.getInstance().e(picPath);
                mBg.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }finally {
                if (fileInputStream!=null){
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    protected void initData() {
        super.initData();


    }

    class Adapter extends RecyclerAdapter<String> implements IDragSelectAdapter {


        @Override
        public void setSelected(int index, boolean selected) {
            notifyItemChanged(index);
        }

        @Override
        public boolean isIndexSelectable(int index) {
            return true;
        }

        @Override
        protected ViewHolder<String> onCreateViewHolder(android.view.View root, int viewType) {
            return null;
        }

        @Override
        protected int getItemViewType(int position, String s) {
            return 0;
        }
    }

    class ViewHolder extends RecyclerAdapter.ViewHolder<String>{

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(String s) {

        }
    }

}
