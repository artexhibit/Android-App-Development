package ru.igorcodes.flagquiz.view
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import ru.igorcodes.flagquiz.DatabaseCopyHelper
import ru.igorcodes.flagquiz.R
import ru.igorcodes.flagquiz.database.FlagsDAO
import ru.igorcodes.flagquiz.databinding.FragmentQuizBinding
import ru.igorcodes.flagquiz.model.FlagsModel

class FragmentQuiz: Fragment() {

    lateinit var fragmentQuizBinding: FragmentQuizBinding
    lateinit var correctFlag: FlagsModel

    var flagList = ArrayList<FlagsModel>()
    var wrongFlags = ArrayList<FlagsModel>()
    val dao = FlagsDAO()

    var correctNumber = 0
    var wrongNumber = 0
    var emptyNumber = 0
    var questionNumber = 0
    var optionControl = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentQuizBinding = FragmentQuizBinding.inflate(inflater, container, false)

        flagList = dao.getRandomTenRecords(DatabaseCopyHelper(requireActivity()))
        showData()

        fragmentQuizBinding.buttonA.setOnClickListener {
            answerControl(fragmentQuizBinding.buttonA)
        }

        fragmentQuizBinding.buttonB.setOnClickListener {
            answerControl(fragmentQuizBinding.buttonB)
        }

        fragmentQuizBinding.buttonC.setOnClickListener {
            answerControl(fragmentQuizBinding.buttonC)
        }

        fragmentQuizBinding.buttonD.setOnClickListener {
            answerControl(fragmentQuizBinding.buttonD)
        }

        fragmentQuizBinding.buttonNext.setOnClickListener {
            questionNumber++

            if (questionNumber > 9) {

                if (!optionControl) { emptyNumber++ }
                //Toast.makeText(requireActivity(), "The quiz is finished", Toast.LENGTH_SHORT).show()
                val direction = FragmentQuizDirections.actionFragmentQuizToFragmentResult().apply {
                    correct = correctNumber
                    wrong = wrongNumber
                    empty = emptyNumber
                }
                this.findNavController().apply {
                    navigate(direction)
                    popBackStack(R.id.fragmentResult, false)
                }

            } else {
                showData()

                if (!optionControl) {
                    emptyNumber++
                    fragmentQuizBinding.textViewEmpty.text = emptyNumber.toString()
                } else {
                    setButtonInitialProperties()
                }
            }
            optionControl = false
        }
        return fragmentQuizBinding.root
    }

    @SuppressLint("DiscouragedApi")
    private fun showData() {
        fragmentQuizBinding.textViewQuestion.text = resources.getString(R.string.question_number).plus(" ").plus(questionNumber + 1)
        correctFlag = flagList[questionNumber]

        fragmentQuizBinding.imageViewFlag.setImageResource(resources.getIdentifier(correctFlag.flagName, "drawable", requireActivity().packageName))
        wrongFlags = dao.getRandomThreeRecords(DatabaseCopyHelper(requireActivity()), correctFlag.id)

        val mixOptions = HashSet<FlagsModel>()

        mixOptions.clear()
        mixOptions.add(correctFlag)
        mixOptions.add(wrongFlags[0])
        mixOptions.add(wrongFlags[1])
        mixOptions.add(wrongFlags[2])

        val options = ArrayList<FlagsModel>()
        options.clear()
        for (eachFlag in mixOptions) { options.add(eachFlag) }

        fragmentQuizBinding.buttonA.text = options[0].countryName
        fragmentQuizBinding.buttonB.text = options[1].countryName
        fragmentQuizBinding.buttonC.text = options[2].countryName
        fragmentQuizBinding.buttonD.text = options[3].countryName
    }

    private fun answerControl(button: Button) {
        val clickedOption = button.text.toString()
        val correctAnswer = correctFlag.countryName

        if (clickedOption == correctAnswer) {
            correctNumber++
            fragmentQuizBinding.textViewCorrect.text = correctNumber.toString()
            button.setBackgroundColor(Color.GREEN)
        } else {
            wrongNumber++
            fragmentQuizBinding.textViewWrong.text = wrongNumber.toString()
            button.setBackgroundColor(Color.RED)
            button.setTextColor(Color.WHITE)

            when(correctAnswer) {
                fragmentQuizBinding.buttonA.text -> fragmentQuizBinding.buttonA.setBackgroundColor(Color.GREEN)
                fragmentQuizBinding.buttonB.text -> fragmentQuizBinding.buttonB.setBackgroundColor(Color.GREEN)
                fragmentQuizBinding.buttonC.text -> fragmentQuizBinding.buttonC.setBackgroundColor(Color.GREEN)
                fragmentQuizBinding.buttonD.text -> fragmentQuizBinding.buttonD.setBackgroundColor(Color.GREEN)
            }
        }
        fragmentQuizBinding.buttonA.isClickable = false
        fragmentQuizBinding.buttonB.isClickable = false
        fragmentQuizBinding.buttonC.isClickable = false
        fragmentQuizBinding.buttonD.isClickable = false

        optionControl = true
    }

    private fun setButtonInitialProperties() {
        fragmentQuizBinding.buttonA.apply {
            setBackgroundColor(Color.WHITE)
            setTextColor(resources.getColor(R.color.pink, requireActivity().theme))
            isClickable = true
        }
        fragmentQuizBinding.buttonB.apply {
            setBackgroundColor(Color.WHITE)
            setTextColor(resources.getColor(R.color.pink, requireActivity().theme))
            isClickable = true
        }
        fragmentQuizBinding.buttonC.apply {
            setBackgroundColor(Color.WHITE)
            setTextColor(resources.getColor(R.color.pink, requireActivity().theme))
            isClickable = true
        }
        fragmentQuizBinding.buttonD.apply {
            setBackgroundColor(Color.WHITE)
            setTextColor(resources.getColor(R.color.pink, requireActivity().theme))
            isClickable = true
        }
    }
}