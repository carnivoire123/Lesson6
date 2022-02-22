package ru.gb.lesson6.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import ru.gb.lesson6.R;
import ru.gb.lesson6.data.InMemoryRepoImp;
import ru.gb.lesson6.data.Note;
import ru.gb.lesson6.data.Repo;
import ru.gb.lesson6.recycler.NotesAdapter;

public class NotesListActivity extends AppCompatActivity implements NotesAdapter.OnNoteClickListener {

    private RecyclerView list;
    private Repo repo = InMemoryRepoImp.getInstance();
    private NotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Intent intent = getIntent();
        // intent.getIntExtra()
        // R.layout.activity_main
        // int id = getIntent().getIntExtra("THEME", R.style.MyTheme);
        // setTheme(id);

        setContentView(R.layout.activity_notes_list);
        list = findViewById(R.id.list);

        adapter = new NotesAdapter();

        adapter.setOnNoteClickListener(this);

        adapter.setNotes(repo.getAll());
        list.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this));


        /*
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        TextView textView = new TextView(this);
        linearLayout.addView(textView);

        setContentView(linearLayout);
         */
    }

    public static final int EDIT_NOTE_REQUEST = 66;

    @Override
    public void onNoteClick(Note note) {
        Log.d("happy", note.getDescription());

        Intent editNoteIntent = new Intent(this, EditNoteActivity.class);
        editNoteIntent.putExtra(Note.NOTE, note);

        startActivityForResult(editNoteIntent, EDIT_NOTE_REQUEST);

    }

    // TODO написать функцию обработки возвращаемой заметки -
    //  добавить ее в репо и обновить данные в адаптере данными из репо
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}