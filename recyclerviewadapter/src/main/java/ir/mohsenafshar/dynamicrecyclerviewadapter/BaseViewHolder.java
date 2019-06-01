package ir.mohsenafshar.dynamicrecyclerviewadapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<Model> extends RecyclerView.ViewHolder {

    private DynamicAdapter.ClickListener clickListener;
    private DynamicAdapter.LongPressListener longPressListener;

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


    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void onBindViewHolder(Model model);

}
