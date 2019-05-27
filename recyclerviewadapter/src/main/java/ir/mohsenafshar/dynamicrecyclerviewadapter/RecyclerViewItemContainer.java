package ir.mohsenafshar.dynamicrecyclerviewadapter;

import androidx.annotation.LayoutRes;

public class RecyclerViewItemContainer<VH extends BaseViewHolder<Model>, Model> {

    private VH baseViewHolder;
    private Model model;
    @LayoutRes private int layoutRes;

}
