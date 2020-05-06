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


    private EditText newFoodName;
    private EditText newFoodProteins;
    private EditText newFoodCarbs;
    private EditText newFoodFats;
    private EditText newFoodClories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        newFoodName =findViewById(R.id.newFoodName);
        newFoodCarbs =findViewById(R.id.newFoodCarbs);
        newFoodFats =findViewById(R.id.newFoodFats);
        newFoodClories =findViewById(R.id.newFoodCalories);
        newFoodProteins= findViewById(R.id.newFoodProteins);
        final Button button =findViewById(R.id.confirm_food);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if(TextUtils.isEmpty(newFoodProteins.getText()) || TextUtils.isEmpty(newFoodCarbs.getText()) || TextUtils.isEmpty(newFoodFats.getText())){
                Toast.makeText(getApplicationContext(),"Protein ,Carbohidrates and Fat are mandatory",Toast.LENGTH_LONG).show();
            } else{
                String name = TextUtils.isEmpty(newFoodName.getText())? "Unnamed":newFoodName.getText().toString();
                double protein = Double.parseDouble(newFoodProteins.getText().toString());
                double carbs = Double.parseDouble(newFoodCarbs.getText().toString());
                double fat = Double.parseDouble(newFoodFats.getText().toString());
                double calories = TextUtils.isEmpty(newFoodClories.getText())? protein*4+carbs*4+fat*9: Double.parseDouble(newFoodClories.getText().toString());
                Bundle bundle = new Bundle();
                bundle.putString("name",name);
                bundle.putDouble("protein",protein);
                bundle.putDouble("carbs",carbs);
                bundle.putDouble("fat",fat);
                bundle.putDouble("calories",calories);
                replyIntent.putExtras(bundle);
                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });

    }
}
