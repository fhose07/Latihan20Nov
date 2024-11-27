package paba.c14220151.latihan20nov
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var taskAdapter: TaskAdapter
    private val tasks = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initiate adapter dan recyclerview untuk menampilkan data
        recyclerView = findViewById(R.id.recyclerView)
        taskAdapter = TaskAdapter(tasks)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = taskAdapter

        //tombol add untuk membuka ke halaman baru menuju task form
        val fabAddTask = findViewById<FloatingActionButton>(R.id.fabAddTask)
        fabAddTask.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
        }
    }

    // Menangani hasil dari FormActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_ADD_TASK && resultCode == RESULT_OK) {
            // Ambil data dari intent yang dikembalikan
            val taskName = data?.getStringExtra("taskName") ?: ""
            val taskDate = data?.getStringExtra("taskDate") ?: ""
            val taskCategory = data?.getStringExtra("taskCategory") ?: ""
            val taskDescription = data?.getStringExtra("taskDescription") ?: ""

            // Menambahkan data baru ke dalam list
            val newTask = Task(taskName, taskDate, taskCategory, taskDescription)
            tasks.add(newTask)

            // Memberitahukan adapter bahwa data baru telah ditambahkan
            taskAdapter.notifyItemInserted(tasks.size - 1)
        }
    }

    companion object {
        private const val REQUEST_CODE_ADD_TASK = 1 // request code untuk identifikasi
    }
}




