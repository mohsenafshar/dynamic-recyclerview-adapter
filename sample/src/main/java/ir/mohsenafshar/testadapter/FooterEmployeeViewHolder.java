package ir.mohsenafshar.testadapter;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import ir.mohsenafshar.dynamicrecyclerviewadapter.BaseViewHolder;


public class FooterEmployeeViewHolder extends BaseViewHolder<Employee> {

    private static final String TAG = FooterEmployeeViewHolder.class.getSimpleName();


    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private View itemView;

    FooterEmployeeViewHolder(@NonNull View itemView) {
        super(itemView);

        tv1 = itemView.findViewById(R.id.tv1);
        tv2 = itemView.findViewById(R.id.tv2);
        tv3 = itemView.findViewById(R.id.tv3);
        this.itemView = itemView;

    }

    @Override
    public void onBindViewHolder(final Employee e) {

        tv1.setText(e.getEmployeeName());
        tv2.setText(e.getEmployeeAge());
        tv3.setText(e.getEmployeeSalary());

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
