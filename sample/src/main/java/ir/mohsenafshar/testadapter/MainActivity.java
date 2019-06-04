package ir.mohsenafshar.testadapter;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ir.mohsenafshar.dynamicrecyclerviewadapter.DynamicAdapter;

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
                .setClickListener((view, model) -> Log.d(TAG, "onItemClicked: " + model))
                .setLongPressListener((view, model) -> {
                    Log.d(TAG, "onItemLongPressed: " + model);
                    return true;
                })
                .build();
    }


    private DynamicAdapter createMultiViewHolderAdapter() {

        //Create A List Of Data
        List<Employee> employeeList = EmployeeListProvider.getList(this);

        ArrayList<Class<EmployeeViewHolder>> classes = new ArrayList<>();
        classes.add(EmployeeViewHolder.class);

        return new DynamicAdapter.MultiBuilder<>(
                employeeList,
                classes,
                R.layout.item_employee, R.layout.item_employee_gray, R.layout.item_employee_red
        ).build();
    }

}
