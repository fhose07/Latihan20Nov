package paba.c14220151.latihan20nov
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class TaskAdapter(private val tasks: MutableList<Task>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_form, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]

        // Mengatur tampilan teks
        holder.tvTaskName.text = task.name
        holder.tvTaskDate.text = task.date
        holder.tvTaskCategory.text = task.category
        holder.tvTaskDescription.text = task.description

        // Tombol berdasarkan status task
        if (task.isCompleted) {
            holder.btnKerjakan.visibility = View.GONE
            holder.btnUbah.visibility = View.GONE
            holder.btnHapus.visibility = View.GONE
            holder.tvTaskDescription.visibility = View.VISIBLE

        } else if (task.isInProgress) {
            holder.btnKerjakan.setText("SELESAI")
            holder.btnUbah.isEnabled = false
            holder.btnUbah.setBackgroundColor(Color.GRAY) // Tombol 'UBAH' akan diubah menjadi abu-abu
            holder.btnKerjakan.visibility = View.VISIBLE
        } else {
            holder.btnKerjakan.text = "KERJAKAN"
            holder.btnUbah.isEnabled = true
            holder.btnKerjakan.visibility = View.VISIBLE
        }

        // Set listener untuk tombol kerjakan
        holder.btnKerjakan.setOnClickListener {
            if (!task.isInProgress) {
                task.isInProgress = true // Set task as in progress
                notifyItemChanged(position)
            } else {
                task.isCompleted = true // Set task as completed
                task.isInProgress = false // Stop progress
                notifyItemChanged(position)
            }
        }

        // Set listener untuk tombol ubah
        holder.btnUbah.setOnClickListener {
            // Logika untuk tombol ubah, jika perlu
        }

        // Set listener untuk tombol hapus
        holder.btnHapus.setOnClickListener {
            tasks.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount(): Int = tasks.size

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTaskName: TextView = itemView.findViewById(R.id.tvTaskName)
        val tvTaskDate: TextView = itemView.findViewById(R.id.tvTaskDate)
        val tvTaskCategory: TextView = itemView.findViewById(R.id.tvTaskCategory)
        val tvTaskDescription: TextView = itemView.findViewById(R.id.tvTaskDescription)
        val btnKerjakan: Button = itemView.findViewById(R.id.btnKerjakan)
        val btnUbah: Button = itemView.findViewById(R.id.btnUbah)
        val btnHapus: Button = itemView.findViewById(R.id.btnHapus)
    }
}



