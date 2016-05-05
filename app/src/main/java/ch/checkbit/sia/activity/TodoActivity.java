package ch.checkbit.sia.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ch.checkbit.sia.R;
import ch.checkbit.sia.db.SiaDbHelper;
import ch.checkbit.sia.helpers.Todo;

public class TodoActivity extends SiaAbstractActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        setupToolbar(getAssets(), R.id.todo_toolbar, R.id.todo_title, R.drawable.btn_todo_sml );

        Switch modeSwitch = (Switch) findViewById(R.id.todo_archive_mode);
        modeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                state = (b ? Todo.State.COMPLETED : Todo.State.ONGOING);
                loadTODO();
            }
        });

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

    private Todo.State state = Todo.State.ONGOING;

    private List<Todo> getTODO() {

        SiaDbHelper dbHelper = new SiaDbHelper(getApplicationContext());
        List<Todo> todo = dbHelper.getTodosByState(dbHelper.getReadableDatabase(), state);

        if (todo == null) {
            todo = new ArrayList<>();
        }

        return todo;
    }


}
