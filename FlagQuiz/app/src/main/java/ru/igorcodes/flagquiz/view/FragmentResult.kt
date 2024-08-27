package ru.igorcodes.flagquiz.view
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.igorcodes.flagquiz.R
import ru.igorcodes.flagquiz.databinding.FragmentQuizBinding
import ru.igorcodes.flagquiz.databinding.FragmentResultBinding

class FragmentResult : Fragment() {

    lateinit var fragmentResultBinding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fragmentResultBinding = FragmentResultBinding.inflate(inflater, container, false)

        fragmentResultBinding.buttonNewQuiz.setOnClickListener {

        }

        fragmentResultBinding.buttonExit.setOnClickListener {

        }
        return fragmentResultBinding.root
    }
}