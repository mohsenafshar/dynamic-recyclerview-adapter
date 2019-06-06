package ir.mohsenafshar.dynamicrecyclerviewadapter;

import androidx.annotation.LayoutRes;

public class AdapterItemContainer<VH extends BaseViewHolder<Model>, Model> {

    private Model model;
    private Class<VH> viewHolderClass;
    @LayoutRes
    private int layoutRes;

    private DynamicAdapter.ClickListener<Model> clickListener;
    private DynamicAdapter.LongPressListener<Model> longPressListener;

    AdapterItemContainer(Model model, Class<VH> viewHolderClass, int layoutRes) {
        this.model = model;
        this.viewHolderClass = viewHolderClass;
        this.layoutRes = layoutRes;
    }

    Model getModel() {
        return model;
    }

    Class<VH> getViewHolderClass() {
        return viewHolderClass;
    }

    int getLayoutRes() {
        return layoutRes;
    }

    public DynamicAdapter.ClickListener<Model> getClickListener() {
        return clickListener;
    }

    public void setClickListener(DynamicAdapter.ClickListener<Model> clickListener) {
        this.clickListener = clickListener;
    }

    public DynamicAdapter.LongPressListener<Model> getLongPressListener() {
        return longPressListener;
    }

    public void setLongPressListener(DynamicAdapter.LongPressListener<Model> longPressListener) {
        this.longPressListener = longPressListener;
    }
}
