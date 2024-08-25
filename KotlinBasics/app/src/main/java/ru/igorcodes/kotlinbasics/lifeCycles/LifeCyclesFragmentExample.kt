package ru.igorcodes.kotlinbasics.lifeCycles
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.igorcodes.kotlinbasics.R

class LifeCyclesFragmentExample: Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)

        Log.i("Fragment", "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("Fragment", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("Fragment", "onCreateView")
        return inflater.inflate(R.layout.fragment_lifecycles_new, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("Fragment", "onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.i("Fragment", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Fragment", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Fragment", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Fragment", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("Fragment", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Fragment", "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.i("Fragment", "onDetach")
    }
}