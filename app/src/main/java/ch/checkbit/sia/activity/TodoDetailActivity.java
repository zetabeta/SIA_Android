package ch.checkbit.sia.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import ch.checkbit.sia.R;
import ch.checkbit.sia.db.SiaDbHelper;
import ch.checkbit.sia.helpers.Todo;

public class TodoDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        final Integer id = bundle.getInt("id");
        String desc = bundle.getString("desc");
        String note = bundle.getString("note");
        String createDate = bundle.getString("create_date");
        String type = bundle.getString("type");
        Boolean done = bundle.getBoolean("done");
        String doneDate = bundle.getString("done_date");

        /** type icon **/
        ImageView typeIcon = (ImageView) findViewById(R.id.todo_detail_type_icon);

        if ( type != null && type.equals(Todo.Type.LONG_TERM.name())) {
            typeIcon.setImageResource(R.drawable.long_term_todo);
        } else if (type != null && type.equals(Todo.Type.SHORT_TERM.name())) {
            typeIcon.setImageResource(R.drawable.short_term_todo);
        } else {
            typeIcon.setImageResource(R.drawable.delegate_todo);
        }

        /** desc **/
        if(desc != null){
            TextView description = (TextView) findViewById(R.id.todo_detail_desc);
            description.setText(desc);
        }

        /** desc **/
        if(note != null){
            TextView notes = (TextView) findViewById(R.id.todo_detail_notes);
            notes.setText(note);
        }

        /** create date **/
        if(note != null){
            TextView creationDate = (TextView) findViewById(R.id.todo_detail_create_date);
            creationDate.setText(createDate);
        }

        LinearLayoutCompat buttonsLayout = (LinearLayoutCompat) findViewById(R.id.todo_detail_buttons_layout);

        boolean includeMarkAsDoneButton = !done;

        /** done **/
        if(done != null && done) {
            //ImageView doneImage = new ImageView(this);
            //doneImage.setImageDrawable(getDrawable(R.drawable.));
            //buttonsLayout.addView(doneImage);

            /** done date **/
            if(doneDate != null) {
                //TextView doneAt = new TextView(this);
                //doneAt.setText(doneDate);
                //doneAt.setTextSize(16);
                //buttonsLayout.addView(doneAt);
            }
        }


        final SiaDbHelper dbHelper = new SiaDbHelper(this);

        if(includeMarkAsDoneButton) {
            /** delete button **/
            Button deleteButton = new Button(this);
            deleteButton.setText("Delete");
            buttonsLayout.addView(deleteButton);

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbHelper.deleteTodo(dbHelper.getWritableDatabase(), id );
                    finish();
                }
            });

            /** done button **/
            Button markAsDone = new Button(this);
            markAsDone.setText("It's DONE!");
            buttonsLayout.addView(markAsDone);

            markAsDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbHelper.markTodoAsDone(dbHelper.getWritableDatabase(), id );
                    finish();
                }
            });

        }

    }

}
