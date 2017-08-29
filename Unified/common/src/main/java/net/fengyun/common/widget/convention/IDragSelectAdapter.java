package net.fengyun.common.widget.convention;

/**
 *
 */

public interface IDragSelectAdapter {
    void setSelected(int index, boolean selected);

    boolean isIndexSelectable(int index);

    int getItemCount();
}
