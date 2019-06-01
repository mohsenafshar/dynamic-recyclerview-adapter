package ir.mohsenafshar.dynamicrecyclerviewadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class DynamicAdapter<Model, VH extends BaseViewHolder<Model>> extends RecyclerView.Adapter<BaseViewHolder<Model>> {

    private static final String TAG = DynamicAdapter.class.getSimpleName();

    public static final int[] DEFAULT_PATTERN = new int[]{1, 0};

    private ArrayList<Class<? extends VH>> viewHolderClasses;
    private int[] layoutIds;
    private int[] pattern;

    /*
     * TODO: Find a Solution for changing arrayList To array
     * */

    private Class<VH> viewHolderClass;
    private List<Model> modelList;
    private int layoutId;

    private ClickListener<Model> clickListener;
    private LongPressListener<Model> longPressListener;
    private BindViewHolderListener<Model> bindViewHolderListener;

    private int type = -1;
    public static final int TYPE_SINGLE = 0;
    public static final int TYPE_MULTI = 1;

    private DynamicAdapter() {
    }

    @NonNull
    @Override
    public BaseViewHolder<Model> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (type == TYPE_SINGLE) {
            return getSingleViewHolder(parent);
        } else {
            return getMultiViewHolderFor(parent, viewType);
        }
    }

    private BaseViewHolder<Model> getSingleViewHolder(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);

        VH vh = null;
        try {
            vh = viewHolderClass.getConstructor(View.class).newInstance(view);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        if (vh == null) {
            throw new NullPointerException();
        } else {
            return vh;
        }
    }

    private BaseViewHolder<Model> getMultiViewHolderFor(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutIds[viewType], parent, false);

        VH vh = null;
        try {
            vh = viewHolderClasses.get(viewType).getConstructor(View.class).newInstance(view);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


        if (vh == null) {
            throw new NullPointerException();
        } else {
            return vh;
        }
    }


    @Override
    public void onBindViewHolder(@NonNull final BaseViewHolder<Model> holder, final int position) {
        holder.onBindViewHolder(modelList.get(position));

        if (clickListener != null) {
            holder.setClickListener(clickListener);
        }

        if (longPressListener != null) {
            holder.setLongPressListener(longPressListener);
        }
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (type == TYPE_SINGLE)
            return super.getItemViewType(position);
        else {
            int patterSize = pattern.length;
            return pattern[position % patterSize];
        }
    }


    public static final class Builder<VH extends BaseViewHolder<Model>, Model> {

        private Class<? extends VH> viewHolderClass;
        private int layoutId;
        private List<Model> list;
        private ClickListener<Model> clickListener;
        private LongPressListener<Model> longPressListener;

        Builder(List<Model> modelList, Class<? extends VH> viewHolderClass, @LayoutRes int layoutId) {
            this.list = modelList;
            this.layoutId = layoutId;
            this.viewHolderClass = viewHolderClass;
        }

        public Builder<VH, Model> setViewHolderClass(Class<? extends VH> viewHolderClass) {
            this.viewHolderClass = viewHolderClass;
            return this;
        }

        public Builder<VH, Model> setClickListener(ClickListener<Model> clickListener) {
            this.clickListener = clickListener;
            return this;
        }

        public Builder<VH, Model> setLongPressListener(LongPressListener<Model> longPressListener) {
            this.longPressListener = longPressListener;
            return this;
        }

        public DynamicAdapter build() {
            DynamicAdapter adapter = new DynamicAdapter<>();
            adapter.type = TYPE_SINGLE;
            adapter.modelList = list;
            adapter.viewHolderClass = viewHolderClass;
            adapter.layoutId = layoutId;
            adapter.clickListener = clickListener;
            adapter.longPressListener = longPressListener;
            return adapter;
        }

    }

    private static final class MultiBuilder<VH extends BaseViewHolder<Model>, Model> {

        private ArrayList<? extends Class<? extends VH>> viewHolderClasses;
        private int[] layoutIds;
        private List<Model> list;
        private int[] pattern;
        private ClickListener<Model> clickListener;
        private LongPressListener<Model> longPressListener;

        MultiBuilder() {
        }

        MultiBuilder(List<Model> modelList, ArrayList<? extends Class<? extends VH>> viewHolderClasses, @LayoutRes int... layoutId) {
            this.list = modelList;
            this.layoutIds = layoutId;
            this.viewHolderClasses = viewHolderClasses;
        }


        public MultiBuilder<VH, Model> setViewHolderClass(ArrayList<? extends Class<? extends VH>> viewHolderClasses) {
            this.viewHolderClasses = viewHolderClasses;
            return this;
        }


        public MultiBuilder<VH, Model> setContainer(RecyclerViewItemContainer container) {
            this.viewHolderClasses = viewHolderClasses;
            return this;
        }

        public MultiBuilder<VH, Model> setLayoutIds(@LayoutRes int... layoutIds) {
            this.layoutIds = layoutIds;
            return this;
        }

        public MultiBuilder<VH, Model> setList(List<Model> list) {
            this.list = list;
            return this;
        }

        public MultiBuilder<VH, Model> setPattern(int... pattern) {
            this.pattern = pattern;
            return this;
        }

        public MultiBuilder<VH, Model> setClickListener(ClickListener<Model> clickListener) {
            this.clickListener = clickListener;
            return this;
        }

        public MultiBuilder<VH, Model> setLongPressListener(LongPressListener<Model> longPressListener) {
            this.longPressListener = longPressListener;
            return this;
        }

        public DynamicAdapter build() {
            Validator.isListSizeValid(viewHolderClasses, layoutIds);
            Validator.isPatternValid(pattern, viewHolderClasses.size());

            DynamicAdapter adapter = new DynamicAdapter<>();
            adapter.type = TYPE_MULTI;
            adapter.viewHolderClasses = viewHolderClasses;
            adapter.layoutIds = layoutIds;
            adapter.modelList = list;
            adapter.clickListener = clickListener;
            adapter.longPressListener = longPressListener;
            adapter.pattern = pattern;
            return adapter;
        }

    }

    private static class Validator {

        private static void isListSizeValid(List list, int[] array) {
            if (list.size() != array.length) throw new AssertionError("size of viewHolderClasses must match the size of layouts");
        }

        private static void isPatternValid(int[] pattern, int sizeOfViewHolderClasses) {
            int max = 0;
            for (int i : pattern) {
                if (i < 0) throw new AssertionError("items in pattern list must be whole number ( >= 0 )");
                if (i > max) max = i;
            }

            if (max > sizeOfViewHolderClasses - 1) throw new AssertionError("max value in pattern must not >= size of layouts");
        }

    }


    interface BindViewHolderListener<T> {
        void onBindViewHolder(T model);
    }

    interface ClickListener<T> {
        void onItemClicked(View view, T model);
    }

    interface LongPressListener<T> {
        boolean onItemLongPressed(View view, T model);
    }

    interface Listeners extends BindViewHolderListener, ClickListener, LongPressListener {
    }


}
