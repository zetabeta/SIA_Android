package ch.checkbit.sia.activity;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import ch.checkbit.sia.R;
import ch.checkbit.sia.db.SiaDbHelper;
import ch.checkbit.sia.helpers.SiaConstants;
import ch.checkbit.sia.helpers.Todo;

public class AddTodoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);
        setupToolbar();

        final SiaDbHelper dbHelper = new SiaDbHelper(getApplicationContext());

        /** load the types for the spinner here **/
        Spinner sp = (Spinner) findViewById(R.id.spinner_todo_types);
        sp.setAdapter(new TodoTypeAdapter(this));

        final EditText descField = (EditText) findViewById(R.id.task_description);
        final EditText notesField = (EditText) findViewById(R.id.task_notes);
        final Spinner typeSpinner = sp;

        /** add button listener to add the new entry **/
        Button insertButton = (Button) findViewById(R.id.insert_todo);


        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String desc = descField.getText().toString();
                final String notes = notesField.getText().toString();
                final Todo.Type type = Todo.Type.valueOf(typeSpinner.getSelectedItem().toString());
                dbHelper.insertTodo(dbHelper.getWritableDatabase() , desc, notes, type);
                finish();
            }
        });

    }

    private void setupToolbar() {
         /* font */
        final Typeface SIA_FONT = Typeface.createFromAsset(getAssets(), SiaConstants.FONT_HANDWRITTEN);

        /* toolbar */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.add_todo_title);
        toolbarTitle.setTypeface(SIA_FONT);
        toolbar.setLogo(R.drawable.btn_todo_sml);
        setSupportActionBar(toolbar);
    }


    public class TodoTypeAdapter extends ArrayAdapter<Todo.Type> {

        public TodoTypeAdapter (Context context) {
            super(context, 0, Todo.Type.values());
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView;
            if (convertView == null) {
                rowView = LayoutInflater.from(getContext()).inflate(R.layout.todo_type_spinner_item, parent, false);
                CheckedTextView textView = (CheckedTextView) rowView.findViewById(R.id.spinner_todo_type);
                textView.setText(getItem(position).name());
            } else {
                rowView = convertView;
                CheckedTextView textView = (CheckedTextView) rowView.findViewById(R.id.spinner_todo_type);
                textView.setText(getItem(position).name());
            }
            return rowView;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View rowView = LayoutInflater.from(getContext()).inflate(R.layout.todo_type_spinner_item, parent, false);
            CheckedTextView textView = (CheckedTextView) rowView.findViewById(R.id.spinner_todo_type);
            textView.setText(getItem(position).name());
            return rowView;
        }
    }

}
