package ru.igorcodes.kotlinbasics.recyclerView
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import ru.igorcodes.kotlinbasics.R

class RecyclerViewCountriesAdapter(
    var countryNameList: ArrayList<String>,
    var detailsList: ArrayList<String>,
    var imageList: ArrayList<Int>,
    var context: Context
): RecyclerView.Adapter<RecyclerViewCountriesAdapter.CountryViewHolder>() {

    class CountryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var textViewCountryName: TextView = itemView.findViewById(R.id.textViewCountryName)
        var textViewDetail: TextView = itemView.findViewById(R.id.textViewDetail)
        var imageView: CircleImageView = itemView.findViewById(R.id.imageView)
        var cardView: CardView = itemView.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_card_design, parent, false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryNameList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.textViewCountryName.text = countryNameList.get(position)
        holder.textViewDetail.text = detailsList.get(position)
        holder.imageView.setImageResource(imageList[position])

        holder.cardView.setOnClickListener {
            Toast.makeText(context, "You selected the ${countryNameList.get(position)}", Toast.LENGTH_SHORT).show()
        }
    }
}