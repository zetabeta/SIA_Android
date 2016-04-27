package ch.checkbit.sia.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ch.checkbit.sia.R;
import ch.checkbit.sia.db.SiaDbHelper;
import ch.checkbit.sia.helpers.SiaConstants;
import ch.checkbit.sia.helpers.Todo;

public class TodoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        setupToolbar();

        loadTODO();

        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.add_todo);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(TodoActivity.this, AddTodoActivity.class);
                TodoActivity.this.startActivity(myIntent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        loadTODO();
    }

    private void loadTODO() {
        List<Todo> todoItems = getTODO();
        final TodoAdapter adapter = new TodoAdapter(this, todoItems);
        final List<Todo> items = todoItems;
        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);
        listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listview.setClickable(true);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Todo todo = items.get(position);
                Intent intent = new Intent(TodoActivity.this, TodoDetailActivity.class);
                intent.putExtra("id", todo.id);
                intent.putExtra("desc", todo.desc);
                intent.putExtra("note", todo.note);
                intent.putExtra("create_date", todo.createDate.toString());
                intent.putExtra("type", todo.type.name());
                intent.putExtra("done", todo.done);
                intent.putExtra("done_date", todo.doneDate);
                startActivity(intent);
            }

        });
    }


    private void setupToolbar() {
         /* font */
        final Typeface SIA_FONT = Typeface.createFromAsset(getAssets(), SiaConstants.FONT_HANDWRITTEN);

        /* toolbar */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.todo_title);
        toolbarTitle.setTypeface(SIA_FONT);
        toolbar.setLogo(R.drawable.btn_todo);
        setSupportActionBar(toolbar);
    }


    public class TodoAdapter extends ArrayAdapter<Todo> {
        private final Context context;
        private final List<Todo> values;

        public TodoAdapter(Context context, List<Todo> values) {
            super(context, -1, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView;

            if(convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                rowView = inflater.inflate(R.layout.todo_item, parent, false);
            } else {
                rowView = convertView;
            }

            Todo todo = values.get(position);
            TextView textView = (TextView) rowView.findViewById(R.id.label);
            textView.setText(todo.desc);

            AppCompatCheckBox checkbox = (AppCompatCheckBox)rowView.findViewById(R.id.checkbox);
            checkbox.setClickable(false);
            checkbox.setChecked(todo.done);

            ImageView imageView = (ImageView) rowView.findViewById(R.id.todo_type);

            if (todo.type != null && todo.type.equals(Todo.Type.LONG_TERM)) {
                imageView.setImageResource(R.drawable.long_term_todo);
            } else if (todo.type != null && todo.type.equals(Todo.Type.SHORT_TERM)) {
                imageView.setImageResource(R.drawable.short_term_todo);
            } else {
                imageView.setImageResource(R.drawable.delegate_todo);
            }
            return rowView;

        }

    }



    private List<Todo> getTODO() {

        SiaDbHelper dbHelper = new SiaDbHelper(getApplicationContext());
        List<Todo> todo = dbHelper.getTodos(dbHelper.getReadableDatabase());

        if (todo == null) {
            todo = new ArrayList<>();
        }

        return todo;
    }


}