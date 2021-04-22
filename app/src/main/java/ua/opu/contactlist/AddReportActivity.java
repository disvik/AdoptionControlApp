package ua.opu.contactlist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        setWindow();

        EditText dataEditText = findViewById(R.id.name_et);
        EditText weightEditText = findViewById(R.id.limit_et);
        EditText heightEditText = findViewById(R.id.amount_et);

        Button cancelButton = findViewById(R.id.button_cancel);
        cancelButton.setOnClickListener(v -> onBackPressed());


        Button addContactButton = findViewById(R.id.button_add);
        addContactButton.setOnClickListener(v -> {

            String data = dataEditText.getText().toString();
            String weight = weightEditText.getText().toString();
            String height = heightEditText.getText().toString();

            Intent i = new Intent();
            i.putExtra(Intent.EXTRA_USER, data);
            i.putExtra(Intent.EXTRA_EMAIL, weight);
            i.putExtra(Intent.EXTRA_PHONE_NUMBER, height);

            setResult(RESULT_OK, i);
            finish();
        });
    }

    private void setWindow() {
        // Метод устанавливает StatusBar в цвет фона
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getColor(R.color.activity_background));

        View decor = getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        setResult(RESULT_CANCELED);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

        }
    }
}