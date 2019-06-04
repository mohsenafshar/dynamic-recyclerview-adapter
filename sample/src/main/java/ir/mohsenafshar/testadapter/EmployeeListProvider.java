package ir.mohsenafshar.testadapter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EmployeeListProvider {

    public static List<Employee> getList(Context context) {
        InputStream is = null;
        StringBuilder sb = new StringBuilder();
        try {
            is = context.getAssets().open("users.json");
            BufferedReader r = new BufferedReader(new InputStreamReader(is));

            String line;
            while((line = r.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        String json = sb.toString();
        Type listType = new TypeToken<ArrayList<Employee>>() {}.getType();
        List<Employee> list = new Gson().fromJson(json, listType);
        return list;
    }
}
