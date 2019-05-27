package ir.mohsenafshar.dynamicrecyclerviewadapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<Model> extends RecyclerView.ViewHolder {

    private MyAdapter.ClickListener clickListener;
    private MyAdapter.LongPressListener longPressListener;

    public MyAdapter.ClickListener<Model> getClickListener() {
        return clickListener;
    }

    public void setClickListener(MyAdapter.ClickListener<Model> clickListener) {
        this.clickListener = clickListener;
    }

    public MyAdapter.LongPressListener<Model> getLongPressListener() {
        return longPressListener;
    }

    public void setLongPressListener(MyAdapter.LongPressListener<Model> longPressListener) {
        this.longPressListener = longPressListener;
    }


    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void onBindViewHolder(Model model);

}
