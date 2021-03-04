package com.sd.saruj.personalallproject.ListDataSelect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sd.saruj.personalallproject.MoreDataLoad.MoreDataLoadAdapter;
import com.sd.saruj.personalallproject.MoreDataLoad.MoreDataModel;
import com.sd.saruj.personalallproject.R;
import com.sd.saruj.personalallproject.controller.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ListDataSelectActivity extends AppCompatActivity implements OnCheckItemList {
    String JSON_URL = "https://jsonplaceholder.typicode.com/users" ;

    private RecyclerView mDataSelectRV;
    private Button mAddBtn, mCancelBtn ;
    private ListDataSelectAdapter mListDataSelectAdapter ;
    private ArrayList<ListDataModel> mItemList ;

    private List<String> mPhoneList ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data_select);

        mDataSelectRV = findViewById(R.id.recyclerview_data_select) ;
        mAddBtn = findViewById(R.id.btn_add) ;
        mCancelBtn = findViewById(R.id.btn_cancel) ;
        mItemList = new ArrayList<>() ;

        mDataSelectRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        mListDataSelectAdapter = new ListDataSelectAdapter(getApplicationContext(), mItemList, this);
        mDataSelectRV.setAdapter(mListDataSelectAdapter);
        mDataSelectRV.setHasFixedSize(false);

        loadProducts();

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppController.getAppController().getInAppNotifier().showToast("size: "+ mPhoneList.size());
            }
        });

        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhoneList.clear();
                mListDataSelectAdapter.notifyDataSetChanged();
            }
        });

    }

    private void loadProducts() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for(int i=0;i<array.length();i++){
                                JSONObject mCategoryObject = array.getJSONObject(i);
                                ListDataModel mListModel = new ListDataModel() ;
                                Field[] fields =  mListModel.getAllFields() ;

                                for(int j=0; j<fields.length; j++ ){
                                    String fieldName = fields[j].getName() ;
                                    String fieldValueInJson =mCategoryObject.has(fieldName)? mCategoryObject.getString(fieldName) : "" ;
                                    try{
                                        fields[j].set(mListModel, fieldValueInJson) ;
                                    }catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                    }
                                }
                                mItemList.add(mListModel) ;
                            }
                            mListDataSelectAdapter.notifyDataSetChanged();



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    @Override
    public List<String> OnCheckItemList(List<String> list) {
        mPhoneList = list ;
        AppController.getAppController().getInAppNotifier().log("log", "phnList: " + list );
        return list;
    }
}