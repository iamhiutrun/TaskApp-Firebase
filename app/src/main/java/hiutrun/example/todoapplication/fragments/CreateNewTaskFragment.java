package hiutrun.example.todoapplication.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import hiutrun.example.todoapplication.MainActivity;
import hiutrun.example.todoapplication.R;
import hiutrun.example.todoapplication.firebase.DatabaseFirebaseHelper;
import hiutrun.example.todoapplication.model.Task;


public class CreateNewTaskFragment extends DialogFragment {
    private List<String> spinnerList;
    private TextView tv_title;
    private TextView tv_topic;
    private TextView tv_description;
    private TextView tv_notification;
    private TextView tv_date;
    private TextView tv_time;


    private EditText edt_topic;
    private EditText edt_description;
    private EditText edt_date;
    private EditText edt_time;

    private  Button btn_add;

    private Spinner spinner;

    private Calendar mcurrentTime;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    public CreateNewTaskFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
        View view = inflater.inflate(R.layout.fragment_create_new_task, container, false);
        initial(view);
        initialSpinner(view);
        return view;
    }
    public void initialSpinner(View view){
        spinnerList = new ArrayList<>();
        spinnerList.add("15 minutes before");
        spinnerList.add("30 minutes before");
        spinnerList.add("1 hour before");
        spinnerList.add("1 day before");

        ArrayAdapter spinnerAdapter =  new ArrayAdapter(view.getContext(),R.layout.support_simple_spinner_dropdown_item,spinnerList);
        spinner.setAdapter(spinnerAdapter);
    }
    public void initial(View view){
        // initial time
        mcurrentTime = Calendar.getInstance();

        // TextView
        tv_title = view.findViewById(R.id.tv_title);
        tv_topic = view.findViewById(R.id.tv_topic);
        tv_description = view.findViewById(R.id.tv_description);
        tv_notification = view.findViewById(R.id.tv_notification);
        tv_date = view.findViewById(R.id.tv_date);
        tv_time = view.findViewById(R.id.tv_time);

        // Edit Text
        edt_topic = view.findViewById(R.id.edt_topic);
        edt_description = view.findViewById(R.id.edt_description);
        edt_date = view.findViewById(R.id.edt_date);
        edt_time = view.findViewById(R.id.edt_time);

        // Button
        btn_add = view.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic = edt_topic.getText().toString();
                String description = edt_description.getText().toString();
                String date = edt_date.getText().toString();
                String time = edt_time.getText().toString();
                Task task = new Task(topic,description,date,time,false);
                DatabaseFirebaseHelper db = new DatabaseFirebaseHelper();
                db.addTask(task);
                getDialog().dismiss();
            }
        });

        // Spinner
        spinner = view.findViewById(R.id.spinner_notification);

        // Dismiss Dialog when click description and topic
        edt_description.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                checkDialogShow();
            }
        });
        edt_topic.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                checkDialogShow();
            }
        });

        //On click EditText date
        edt_date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                showDateDialog();
            }
        });
        // On click EditText time
       edt_time.setOnFocusChangeListener(new View.OnFocusChangeListener() {
           @Override
           public void onFocusChange(View v, boolean hasFocus) {
               showTimeDialog();
           }
       });



    }
    //Check Dialog isShow?
    private void checkDialogShow(){
        if(datePickerDialog!=null){
            datePickerDialog.dismiss();
        }
        if(timePickerDialog!=null){
            timePickerDialog.dismiss();
        }
    }

    private void showTimeDialog(){
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
         timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                edt_time.setText(hourOfDay+":"+minute);
            }
        }, hour, minute,true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
        if(datePickerDialog!=null){
            datePickerDialog.dismiss();
        }


    }

    private void showDateDialog(){
        int day = mcurrentTime.get(Calendar.DAY_OF_MONTH);
        int month = mcurrentTime.get(Calendar.MONTH);
        int year = mcurrentTime.get(Calendar.YEAR);


        datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edt_date.setText(dayOfMonth+"_"+(month+1)+"_"+year);
            }
        },year,month,day);
        datePickerDialog.setTitle("Select Date");
        datePickerDialog.show();
        if(timePickerDialog!=null){
            timePickerDialog.dismiss();
        }
    }
}