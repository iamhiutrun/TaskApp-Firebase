package hiutrun.example.todoapplication.firebase;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import hiutrun.example.todoapplication.model.Task;

public class DatabaseFirebaseHelper {
    FirebaseDatabase db;
    DatabaseReference myRef;


    public void addTask(Task task){
        initialDatabase();
        if(TextUtils.isEmpty(task.getDate())){
            myRef.push().child(task.getDate());
        }
        myRef.child(task.getDate()).child(task.getTime()).setValue(task);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void initialDatabase(){
        db = FirebaseDatabase.getInstance();
        myRef = db.getReference();
    }

    public void retrieveData(List<Task> taskList,String date){

        initialDatabase();
        myRef.child(date).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot item : snapshot.getChildren()) {
                    Task task = item.getValue(Task.class);
                    taskList.add(task);
                }
                System.out.println(taskList.get(0).getDate());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
