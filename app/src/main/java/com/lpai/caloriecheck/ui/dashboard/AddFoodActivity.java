package com.lpai.caloriecheck.ui.dashboard;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.lpai.caloriecheck.R;

public class AddFoodActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY="REPLY";
    private EditText newFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        newFood =findViewById(R.id.newFoodName);

        final Button button =findViewById(R.id.button2);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if(TextUtils.isEmpty(newFood.getText())){
                setResult(RESULT_CANCELED,replyIntent);
            } else{
                String name = newFood.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY,name);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });

    }
}
