package hiutrun.example.todoapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import hiutrun.example.todoapplication.R;
import hiutrun.example.todoapplication.adapter.TaskAdapter;
import hiutrun.example.todoapplication.firebase.DatabaseFirebaseHelper;
import hiutrun.example.todoapplication.model.Task;

public class MonthlyFragment extends Fragment {
    private SimpleDateFormat dateFormat;
    private RecyclerView taskRecyclerView;
    private TaskAdapter adapter;
    private CompactCalendarView compactCalendarView;

    public MonthlyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_monthly, container, false);
        initial(view);
        return view;
    }

    public void showTask(String date){
        FirebaseRecyclerOptions<Task> options =
                new FirebaseRecyclerOptions.Builder<Task>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child(date), Task.class)
                        .build();
        adapter.updateOptions(options);
        adapter.notifyDataSetChanged();
    }
    public void initial(View view) {
        //
        compactCalendarView = (CompactCalendarView)view.findViewById(R.id.calendarView);
        compactCalendarView.setUseThreeLetterAbbreviation(true);
        taskRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);

        taskRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        dateFormat = new SimpleDateFormat("DD_M_yyyy", Locale.getDefault());

        //taskRecyclerView.setHasFixedSize(true);
        Calendar calendar = Calendar.getInstance();
        String s = dateFormat.format(calendar.getTime());
        FirebaseRecyclerOptions<Task> options =
                new FirebaseRecyclerOptions.Builder<Task>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child(s), Task.class)
                        .build();
        adapter = new TaskAdapter(options);
        taskRecyclerView.setAdapter(adapter);
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Log.e("Tag", dateFormat.format(dateClicked));
                showTask(dateFormat.format(dateClicked));
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {

            }
        });
    }


    @Override
    public void onStart() {
        if(adapter!=null)
        adapter.startListening();
        super.onStart();

    }

    @Override
    public void onStop() {
        if(adapter!=null)
        adapter.stopListening();
        super.onStop();

    }
}