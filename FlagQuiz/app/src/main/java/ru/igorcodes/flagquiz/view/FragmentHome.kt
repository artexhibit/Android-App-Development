package ru.igorcodes.flagquiz.view
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import ru.igorcodes.flagquiz.DatabaseCopyHelper
import ru.igorcodes.flagquiz.R
import ru.igorcodes.flagquiz.databinding.FragmentHomeBinding

class FragmentHome: Fragment() {

    lateinit var fragmentHomeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        createAndOpenDataBase()

        fragmentHomeBinding.buttonStart.setOnClickListener {
            val direction = FragmentHomeDirections.actionFragmentHomeToFragmentQuiz()
            this.findNavController().navigate(direction)
            //it.findNavController()
            //requireActivity().findNavController()

        }
        return fragmentHomeBinding.root
    }

   private fun createAndOpenDataBase() {
        try {
            val helper = DatabaseCopyHelper(requireActivity())
            helper.createDataBase()
            helper.openDataBase()
        } catch(e: Exception) {
            e.printStackTrace()
        }
    }
}