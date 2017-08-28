package net.fengyun.common.widget.recycler;

/**
 * @author fengyun
 */
public interface AdapterCallback<Data> {

    void update(Data data, RecyclerAdapter.ViewHolder<Data> holder);

}
