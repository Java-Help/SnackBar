package ru.javahelp.snackbar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mrengineer13.snackbar.*;

public class MainActivity extends Activity {

	EditText etText;
	CheckBox chbButton;
	Spinner spStyle;
	Button btnShow;
	Context context = this;
	SnackBar.Style[] styles = new SnackBar.Style[]{SnackBar.Style.DEFAULT, SnackBar.Style.ALERT, 
			SnackBar.Style.CONFIRM, SnackBar.Style.INFO};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        etText = (EditText) findViewById(R.id.etText);
        chbButton = (CheckBox) findViewById(R.id.chbButton);
        spStyle = (Spinner) findViewById(R.id.spStyle);
        btnShow = (Button) findViewById(R.id.btnShow);
        
        chbButton.setOnCheckedChangeListener(onCheck);
        btnShow.setOnClickListener(onClick);
        
    }
    
    OnCheckedChangeListener onCheck = new OnCheckedChangeListener(){

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			spStyle.setVisibility(isChecked?View.VISIBLE:View.GONE);
		}

    };
    
    OnClickListener onClick = new OnClickListener(){

		@Override
		public void onClick(View v) {
			SnackBar.Builder mSnackBarBuilder = new SnackBar.Builder(MainActivity.this)
			.withMessage(etText.getText().toString())
			.withOnClickListener(onMessageClick);
			if(chbButton.isChecked()){
				mSnackBarBuilder.withActionMessage("Кнопка");
				mSnackBarBuilder.withStyle(styles[spStyle.getSelectedItemPosition()]);
			}
			mSnackBarBuilder.show();
			
		}
    };
    
    SnackBar.OnMessageClickListener onMessageClick = new SnackBar.OnMessageClickListener(){

		@Override
		public void onMessageClick(Parcelable token) {
			Toast.makeText(context, "Клик", Toast.LENGTH_LONG).show();
		}
    	
    };

}
