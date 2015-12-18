package com.ipragmatech.multiplefilters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.ipragmatech.multiplefilters.Adapter.FilterRecyclerAdapter;
import com.ipragmatech.multiplefilters.Adapter.FilterValRecyclerAdapter;
import com.ipragmatech.multiplefilters.Model.FilterDefaultMultipleListModel;
import com.ipragmatech.multiplefilters.Model.MainFilterModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView filterListView = null;
    private RecyclerView filterValListView;
    private FilterRecyclerAdapter adapter;
    private FilterValRecyclerAdapter filterValAdapter;
    private ArrayList<String> sizes = new ArrayList<>();
    private ArrayList<String> styles = new ArrayList<>();
    private ArrayList<String> colors = new ArrayList<>();
    private ArrayList<FilterDefaultMultipleListModel> sizeMultipleListModels = new ArrayList<>();
    private ArrayList<FilterDefaultMultipleListModel> styleMultipleListModels = new ArrayList<>();
    private ArrayList<FilterDefaultMultipleListModel> colorMultipleListModels = new ArrayList<>();
    private ArrayList<MainFilterModel> filterModels = new ArrayList<>();
    private List<String> rootFilters;
    private Button btnClear;
    private Button btnFilter;
    private ArrayList<String> sizeSelected = new ArrayList<String>();
    private ArrayList<String> colorSelected = new ArrayList<String>();
    private ArrayList<String> styleSelected = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootFilters = Arrays.asList(this.getResources().getStringArray(R.array.filter_category));
        for (int i = 0; i < rootFilters.size(); i++) {
            /* Create new MainFilterModel object and set array value to @model
            * Description:
            * -- Class: MainFilterModel.java
            * -- Package:main.shop.javaxerp.com.shoppingapp.model
            * */
            MainFilterModel model = new MainFilterModel();
            /*Title for list item*/
            model.setTitle(rootFilters.get(i));
            /*Subtitle for list item*/
            model.setSub("All");
            /*Example:
            * --------------------------------------------
            * Brand => title
            * All => subtitle
            * --------------------------------------------
            * Color => title
            * All => subtitle
            * --------------------------------------------
            * */

            /*add MainFilterModel object @model to ArrayList*/
            filterModels.add(model);
        }

        filterListView = (RecyclerView) findViewById(R.id.filter_dialog_listview);
        adapter = new FilterRecyclerAdapter(this, R.layout.filter_list_item_layout, filterModels);
        filterListView.setAdapter(adapter);
        filterListView.setLayoutManager(new LinearLayoutManager(this));
        filterListView.setHasFixedSize(true);

        filterValListView = (RecyclerView) findViewById(R.id.filter_value_listview);
        filterValAdapter = new FilterValRecyclerAdapter(this,R.layout.filter_list_val_item_layout, sizeMultipleListModels, MainFilterModel.SIZE);
        filterValListView.setAdapter(filterValAdapter);
        filterValListView.setLayoutManager(new LinearLayoutManager(this));
        filterValListView.setHasFixedSize(true);

        btnFilter = (Button) findViewById(R.id.btn_filter);
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (FilterDefaultMultipleListModel model : sizeMultipleListModels) {
                    if (model.isChecked()) {
                        filterModels.get(MainFilterModel.INDEX_SIZE).getSubtitles().add(model.getName());
                    }
                }

                for (FilterDefaultMultipleListModel model : colorMultipleListModels) {
                    if (model.isChecked()) {
                        filterModels.get(MainFilterModel.INDEX_COLOR).getSubtitles().add(model.getName());
                    }
                }

                for (FilterDefaultMultipleListModel model : styleMultipleListModels) {
                    if (model.isChecked()) {
                        filterModels.get(MainFilterModel.INDEX_STYLE).getSubtitles().add(model.getName());
                    }

                }
                        /*Get value from checked of size checkbox*/
                sizeSelected = filterModels.get(MainFilterModel.INDEX_SIZE).getSubtitles();
                filterModels.get(MainFilterModel.INDEX_SIZE).setSubtitles(new ArrayList<String>());

                        /*Get value from checked of color checkbox*/
                colorSelected = filterModels.get(MainFilterModel.INDEX_COLOR).getSubtitles();
                filterModels.get(MainFilterModel.INDEX_COLOR).setSubtitles(new ArrayList<String>());

                        /*Get value from checked of price checkbox*/
                styleSelected = filterModels.get(MainFilterModel.INDEX_STYLE).getSubtitles();
                filterModels.get(MainFilterModel.INDEX_STYLE).setSubtitles(new ArrayList<String>());

                if(sizeSelected.isEmpty() && colorSelected.isEmpty() && styleSelected.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please select size,color,brand", Toast.LENGTH_SHORT).show();
                }

                if(!sizeSelected.isEmpty() || !colorSelected.isEmpty() || !styleSelected.isEmpty()){
                    Toast.makeText(MainActivity.this,"Selected Size is "+sizeSelected.toString()+"\n"+"Selected Color is "+colorSelected.toString() +"\n"+"Selected Brand is "+styleSelected.toString(),Toast.LENGTH_LONG).show();
                }
            }
        });

        btnClear = (Button) findViewById(R.id.btn_clear);
         /*TODO: Clear user selected */
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                for (FilterDefaultMultipleListModel selectedModel : sizeMultipleListModels) {
                    selectedModel.setChecked(false);

                }

                for (FilterDefaultMultipleListModel selectedModel : styleMultipleListModels) {
                    selectedModel.setChecked(false);

                }

                for (FilterDefaultMultipleListModel selectedModel : colorMultipleListModels) {
                    selectedModel.setChecked(false);

                }
                adapter.notifyDataSetChanged();
                filterValAdapter.notifyDataSetChanged();
            }
        });


        adapter.setOnItemClickListener(new FilterRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                filterItemListClicked(position, v);
                adapter.setItemSelected(position);
            }
        });
        filterItemListClicked(0, null);
        adapter.setItemSelected(0);

        sizes = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.filter_size)));
        for (String size : sizes) {

            /* Create new FilterDefaultMultipleListModel object for brand and set array value to brand model {@model}
            * Description:
            * -- Class: FilterDefaultMultipleListModel.java
            * -- Package:main.shop.javaxerp.com.shoppingapp.model
            * NOTE: #checked value @FilterDefaultMultipleListModel is false;
            * */
            FilterDefaultMultipleListModel model = new FilterDefaultMultipleListModel();
            model.setName(size);

            /*add brand model @model to ArrayList*/
            sizeMultipleListModels.add(model);
        }
        styles = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.filter_brand)));
        for (String style : styles) {

            /* Create new FilterDefaultMultipleListModel object for brand and set array value to brand model {@model}
            * Description:
            * -- Class: FilterDefaultMultipleListModel.java
            * -- Package:main.shop.javaxerp.com.shoppingapp.model
            * NOTE: #checked value @FilterDefaultMultipleListModel is false;
            * */
            FilterDefaultMultipleListModel model = new FilterDefaultMultipleListModel();
            model.setName(style);

            /*add brand model @model to ArrayList*/
            styleMultipleListModels.add(model);
        }
        colors = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.filter_color)));
        for (String color : colors) {

            /* Create new FilterDefaultMultipleListModel object for brand and set array value to brand model {@model}
            * Description:
            * -- Class: FilterDefaultMultipleListModel.java
            * -- Package:main.shop.javaxerp.com.shoppingapp.model
            * NOTE: #checked value @FilterDefaultMultipleListModel is false;
            * */
            FilterDefaultMultipleListModel model = new FilterDefaultMultipleListModel();
            model.setName(color);

            /*add brand model @model to ArrayList*/
            colorMultipleListModels.add(model);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void filterItemListClicked(int position, View v) {
        if (position == 0) {
            filterValAdapter = new FilterValRecyclerAdapter(this, R.layout.filter_list_val_item_layout, sizeMultipleListModels, MainFilterModel.SIZE);
        } else if (position == 1) {
            filterValAdapter = new FilterValRecyclerAdapter(this, R.layout.filter_list_val_item_layout, colorMultipleListModels, MainFilterModel.COLOR);
        } else {
            filterValAdapter = new FilterValRecyclerAdapter(this, R.layout.filter_list_val_item_layout, styleMultipleListModels, MainFilterModel.STYLE);
        }

        filterValListView.setAdapter(filterValAdapter);

        filterValAdapter.setOnItemClickListener(new FilterValRecyclerAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                filterValitemListClicked(position);
            }
        });
        filterValAdapter.notifyDataSetChanged();
    }

    private void filterValitemListClicked(int position) {
        filterValAdapter.setItemSelected(position);
    }
}
