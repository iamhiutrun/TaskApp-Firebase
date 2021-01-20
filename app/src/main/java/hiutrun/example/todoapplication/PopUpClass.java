package hiutrun.example.todoapplication;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class PopUpClass {
    public void showPopupWindow(final View view){
        LayoutInflater layoutInflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.fragment_create_new_task,null);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT ;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;

        boolean focusable = true;

        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(view, Gravity.CENTER,0,0);

        // Link java code with element in popup windows
        TextView tv_title = popupView.findViewById(R.id.tv_title);
        TextView tv_topic = popupView.findViewById(R.id.tv_topic);
        TextView tv_description = popupView.findViewById(R.id.tv_description);
        TextView tv_notification = popupView.findViewById(R.id.tv_notification);

        EditText edt_topic = popupView.findViewById(R.id.edt_topic);
        EditText edt_description = popupView.findViewById(R.id.edt_description);

        //EditText edt_notification = popupView.findViewById(R.id.edt_notification);

        Button btn_add = popupView.findViewById(R.id.btn_add);


        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
}
