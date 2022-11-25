package com.akshaj02.shopnsplit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.akshaj02.shopnsplit.Adapter.ToDoAdapter;
import com.akshaj02.shopnsplit.Model.ToDoModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChecklistPage extends AppCompatActivity implements OnDialogCloseListner{

    private RecyclerView recyclerView;
    private FloatingActionButton mFab;
    private FirebaseFirestore firestore;
    private ToDoAdapter adapter;
    private List<ToDoModel> mList;
    private ArrayList<String> products = new ArrayList<>();
    private Query query;
    private ListenerRegistration listenerRegistration;
    private Button mExplorePrices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist_page);

        recyclerView = findViewById(R.id.recycerlview);
        mFab = findViewById(R.id.floatingActionButton);
        firestore = FirebaseFirestore.getInstance();
        mExplorePrices = findViewById(R.id.ExpPrices);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(ChecklistPage.this));

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager() , AddNewTask.TAG);
            }
        });

        //When ExpPrices is clicked it will take you to the Explore Prices page and sent the list of items to the next page

        mList = new ArrayList<>();
        adapter = new ToDoAdapter(ChecklistPage.this , mList);

        // Copy mList to products
//        for (int i = 0; i < mList.size(); i++) {
//            products.add(mList.get(i).getTask());
//        }

        //Convert mList to String

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new TouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        showData();
        recyclerView.setAdapter(adapter);



        mExplorePrices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChecklistPage.this, productSearch.class);
                intent.putExtra("productlist", products);
                startActivity(intent);
            }
        });
    }
    private void showData(){
        query = firestore.collection("task").orderBy("time" , Query.Direction.DESCENDING);

        listenerRegistration = query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                assert value != null;
                for (DocumentChange documentChange : value.getDocumentChanges()){
                    if (documentChange.getType() == DocumentChange.Type.ADDED){
                        String id = documentChange.getDocument().getId();
                        ToDoModel toDoModel = documentChange.getDocument().toObject(ToDoModel.class).withId(id);
                        mList.add(toDoModel);
                        adapter.notifyDataSetChanged();
                        //Add to products
//                        products.add(toDoModel.getTask());
                        //Check if the item already exists in the list
                        if (!products.contains(toDoModel.getTask())) {
                            products.add(toDoModel.getTask());
                        }
                    }
                }
                listenerRegistration.remove();

            }
        });
    }

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        mList.clear();
        showData();
        adapter.notifyDataSetChanged();
    }
}