package ir.mohsenafshar.dynamicrecyclerviewadapter;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class EmployeeViewHolder extends BaseViewHolder<Employee> {

    private static final String TAG = EmployeeViewHolder.class.getSimpleName();


    private TextView tv1;
    private TextView tv2;
    private View itemView;

    EmployeeViewHolder(@NonNull View itemView) {
        super(itemView);

        tv1 = itemView.findViewById(R.id.tv1);
        tv2 = itemView.findViewById(R.id.tv2);
        this.itemView = itemView;

    }

    @Override
    public void onBindViewHolder(final Employee e) {

        tv1.setText(e.getEmployeeName());
        tv2.setText(e.getEmployeeAge());

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                getClickListener().onItemClicked(v, e);
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return getLongPressListener().onItemLongPressed(v, e);
            }
        });
    }

}
