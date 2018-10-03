package com.example.struggler.firebasejava;

import android.support.constraint.solver.widgets.Snapshot;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView mListFirebase;
    List<Mahasiswa> listMahasiswa;

    DatabaseReference refMahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListFirebase = findViewById(R.id.list_firebase);
        refMahasiswa = FirebaseDatabase.getInstance().getReference("Mahasiswa");

    }

    public void addDataToFirebase(View v) {
        Mahasiswa mahasiswa = new Mahasiswa("14121901", "Akil", "4");
        String id = refMahasiswa.push().getKey();
        refMahasiswa.child(id).setValue(mahasiswa);
    }

    @Override
    protected void onStart() {
        super.onStart();

        refMahasiswa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listMahasiswa = new ArrayList<>();

                if (dataSnapshot.getChildrenCount() == 0) {
                    Toast.makeText(MainActivity.this, "Data Kosong!", Toast.LENGTH_SHORT).show();
                } else {
                    for (DataSnapshot snapMahasiswa : dataSnapshot.getChildren()) {
                        Mahasiswa mahasiswa = snapMahasiswa.getValue(Mahasiswa.class);

                        listMahasiswa.add(mahasiswa);

                        setListItems(listMahasiswa);
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setListItems(List<Mahasiswa> listMahasiswa) {
        String[] arrayMahasiswa = new String[listMahasiswa.size()];
        for (int i = 0; i < listMahasiswa.size(); i++) {
            arrayMahasiswa[i] = listMahasiswa.get(i).getNama();
        }

        mListFirebase.setAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, arrayMahasiswa));
    }
}
