package ru.igorcodes.kotlinbasics.listFragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.ListFragment
import ru.igorcodes.kotlinbasics.R

class MyListMainFragment: ListFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_my_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arrayAdapter = activity?.let { ArrayAdapter.createFromResource(it, R.array.cities, android.R.layout.simple_list_item_1) }
        listAdapter = arrayAdapter

        listView.setOnItemClickListener { adapterView, view, index, l ->
            val intent = Intent(activity, ListFragmentSecondActivity::class.java)
            intent.putExtra("index", index)
            startActivity(intent)
        }
    }
}