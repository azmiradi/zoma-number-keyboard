package azmi.radi.com.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import azmi.radi.com.keyboard.ZomaNumberKeyBoard;
import azmi.radi.com.keyboard.ZomaNumberKeyBoardListener;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

public class ZomaKeyBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoma_key_board);
        EditText editText=findViewById(R.id.edit);
        ZomaNumberKeyBoard zomaNumberKeyBoard =findViewById(R.id.ZomaKeyBoard);
         zomaNumberKeyBoard.setOnListener(new ZomaNumberKeyBoardListener() {
            @Override
            public void onNumberClicked(int value) {
                editText.append(String.valueOf(value));
             }

            @Override
            public void onCommaClicked() {
                editText.append(",");

            }

            @Override
            public void onClearClicked() {
                if (!TextUtils.isEmpty( editText.getText().toString())) {
                    String str = editText.getText().toString();
                    str = str.substring(0, str.length() - 1);
                    editText.setText(str);
                }

            }
            @Override
            public void onLongClearClicked() {
                editText.setText("");
            }
        });
         zomaNumberKeyBoard.setNumberKeyBackgroundColors(R.color.bu_color,R.color.gray,0);
     }
}