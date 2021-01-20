package hiutrun.example.todoapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import hiutrun.example.todoapplication.fragments.CreateNewTaskFragment;
import hiutrun.example.todoapplication.fragments.DailyFragment;
import hiutrun.example.todoapplication.fragments.MonthlyFragment;
import hiutrun.example.todoapplication.model.Task;
import io.ghyeok.stickyswitch.widget.StickySwitch;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private BottomNavigationView bottomNavigationView;
    private FloatingActionButton floatingActionButton;
    private FragmentManager fm;
    private StickySwitch stickySwitch;

    MonthlyFragment monthlyFragment;
    DailyFragment dailyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initial();
    }
    public void initial(){
        monthlyFragment = new MonthlyFragment();
        dailyFragment = new DailyFragment();
        // Initial Fragment
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.frame_layout,monthlyFragment);
        ft.commit();
        // Initial Bottom Navigation and Floating Action Button
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.fab);
        stickySwitch = (StickySwitch) findViewById(R.id.sticky_switch);
        stickySwitch.setOnSelectedChangeListener(new StickySwitch.OnSelectedChangeListener() {
            @Override
            public void onSelectedChange(StickySwitch.@NotNull Direction direction, @NotNull String s) {
                if(direction.name().equals("LEFT")){
                    Log.e("Tag","LEFT");
                    FragmentTransaction ft_replace = fm.beginTransaction();
                    ft_replace.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft_replace.replace(R.id.frame_layout, monthlyFragment);
                    ft_replace.commit();
                    //ft.replace(R.id.frame_layout,monthlyFragment).addToBackStack(null).commit();
                }
                    if(direction.name().equals("RIGHT")){
                   // ft.replace(R.id.frame_layout,new DailyFragment());
                    //ft.replace(R.id.frame_layout,dailyFragment).addToBackStack(null).commit();
                    FragmentTransaction ft_replace = fm.beginTransaction();
                    ft_replace.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft_replace.replace(R.id.frame_layout, dailyFragment);
                    ft_replace.commit();
                }
            }
        });
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Show dialog create a new task
        CreateNewTaskFragment dialog_CreateNewTaskFragment = new CreateNewTaskFragment();
        dialog_CreateNewTaskFragment.show(getSupportFragmentManager(),"Dialog create a new task");
    }
}