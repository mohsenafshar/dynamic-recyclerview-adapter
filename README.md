# dynamic-recyclerview-adapter
A Dynamic RecyclerViewAdapter with simple usage And compile-time type checking



### Installing


### Usage

1.Create your viewHolder By extending BaseViewHolder (for more info goto bottom of page)

2.Create a List of data
```
  List<Data> dataList = ...
  ```
  
3.Initialize your adapter with passing:
  * A. list of data 
  * B. created viewHolder at step 1
  * C. passing layout
  * D. add listeners(onclick, onLongClick)
  
  
  ```
new MyAdapter.Builder<>(dataList, DataViewHolder.class, R.layout.item)
                .setClickListener(new MyAdapter.ClickListener<data>() {
                    @Override
                    public void onItemClicked(View view, Data model) {
                        Log.d(TAG, "onItemClicked: " + model);
                    }
                })
                .setLongPressListener(new MyAdapter.LongPressListener<Employee>() {
                    @Override
                    public boolean onItemLongPressed(View view, Employee model) {
                        Log.d(TAG, "onItemLongPressed: " + model);
                        return true;
                    }
                })
                .build();
  
  }
  ```
  
  ### Creating ViewHolder
  
  Create a class And extend BaseViewHolder<Model> :
  
  
  ```
  public class DataViewHolder extends BaseViewHolder<Data>{

    private static final String TAG = DataViewHolder.class.getSimpleName();

    // View defind in R.layout.item
    private TextView textView;
    private View itemView;

    EmployeeViewHolder(@NonNull View itemView) {
        super(itemView);

        view = itemView.findViewById(R.id.textView);
        this.itemView = itemView;

    }

    @Override
    public void onBindViewHolder(final Data data) {

        textView.setText(data.getName());

        // Listeners that you passed to adapter object (Section 3 part D)
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                getClickListener().onItemClicked(v, e);
            }
        });
        
        // Listeners that you passed to adapter object (Section 3 part D)
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return getLongPressListener().onItemLongPressed(v, e);
            }
        });
    }

}

  ```

