package ir.mohsenafshar.dynamicrecyclerviewadapter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private DynamicAdapter employeeAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerView);
        employeeAdapter = createSingleViewHolderAdapter();


        recyclerView.setAdapter(employeeAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));
    }


    private DynamicAdapter createSingleViewHolderAdapter() {

        //Create A List Of Data
        List<Employee> employeeList = EmployeeListProvider.getList(this);

        /*
         * Initialize Adapter With :
         *
         * @Param List of Data
         *
         * @Param Array Of Layouts for each ViewHolder
         *
         * @Param List Of ViewHolders
         *
         * At the end, set listeners to builder object
         *
         * */
        return new DynamicAdapter.Builder<>(employeeList, EmployeeViewHolder.class, R.layout.item_employee)
                .setClickListener(new DynamicAdapter.ClickListener<Employee>() {
                    @Override
                    public void onItemClicked(View view, Employee model) {
                        Log.d(TAG, "onItemClicked: " + model);
                    }
                })
                .setLongPressListener(new DynamicAdapter.LongPressListener<Employee>() {
                    @Override
                    public boolean onItemLongPressed(View view, Employee model) {
                        Log.d(TAG, "onItemLongPressed: " + model);
                        return true;
                    }
                })
                .build();
    }

}
