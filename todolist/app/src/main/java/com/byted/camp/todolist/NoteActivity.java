package com.byted.camp.todolist;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.byted.camp.todolist.beans.State;
import com.byted.camp.todolist.db.TodoContract;
import com.byted.camp.todolist.db.TodoDbHelper;

import java.util.Calendar;

import static com.byted.camp.todolist.R.id.radio;
import static com.byted.camp.todolist.R.id.radioGroup;

public class NoteActivity extends AppCompatActivity {

    private EditText editText;
    private Button addBtn;
    private RadioGroup chooseBtn;
    private TodoDbHelper todoDbHelper;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        setTitle(R.string.take_a_note);

        editText = findViewById(R.id.edit_text);
        editText.setFocusable(true);
        editText.requestFocus();

        todoDbHelper = new TodoDbHelper(this);
        sqLiteDatabase = todoDbHelper.getWritableDatabase();

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.showSoftInput(editText, 0);
        }

        chooseBtn = findViewById(R.id.radioGroup);



        addBtn = findViewById(R.id.btn_add);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int len = chooseBtn.getChildCount();

                CharSequence content = editText.getText();
                String priorityContent = null;
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(NoteActivity.this,
                            "No content to add", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    for(int i=0; i<len; i++){
                        RadioButton radio = (RadioButton) chooseBtn.getChildAt(i);
                        if (radio.isChecked()) {
                            priorityContent = (String) radio.getText();
                            System.out.println("1" + priorityContent);
                            break;
                        }
                    }
                }
                boolean succeed = saveNote2Database(content.toString().trim(),priorityContent);
                if (succeed) {
                    Toast.makeText(NoteActivity.this,
                            "Note added", Toast.LENGTH_SHORT).show();
                    setResult(Activity.RESULT_OK);
                } else {
                    Toast.makeText(NoteActivity.this,
                            "Error", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        todoDbHelper.close();
        super.onDestroy();
    }

    private boolean saveNote2Database(String content,String priority) {
        // TODO 插入一条新数据，返回是否插入成功

        ContentValues values = new ContentValues();
        values.put(TodoContract.Todo.COLUMN_CONTENT, content);
        values.put(TodoContract.Todo.COLUMN_DATE, System.currentTimeMillis());
        values.put(TodoContract.Todo.COLUMN_STATE, State.TODO.intValue);
        values.put(TodoContract.Todo.COLUNM_PRIORITY, priority);
        System.out.println("2"+priority);

        if(sqLiteDatabase.insert(TodoContract.Todo.COLUNM_NAME,null,values) != -1L){
            System.out.println("insert OK!");
            return true;
        }

        return false;
    }
}
