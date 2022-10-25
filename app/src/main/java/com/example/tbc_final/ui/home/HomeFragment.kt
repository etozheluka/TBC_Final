package com.example.tbc_final.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.ResultReceiver
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.tbc_final.R
import com.example.tbc_final.databinding.FragmentHomeBinding
import com.example.tbc_final.service.MotionActivityService
import dagger.hilt.android.AndroidEntryPoint
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var currentSteps: Int = 0
    private var totalStepsCount:Int = 0
    private var totalPointsCount:Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeService()

        val date = SimpleDateFormat(getString(R.string.dateFormat)).format(Calendar.getInstance().time)
        binding.date.text = date.toString()

    }




    private fun subscribeService() {

        val i = Intent(requireContext(), MotionActivityService::class.java)
        i.action = MotionActivityService.ACTION_SUBSCRIBE
        i.putExtra(RECEIVER_TAG, object : ResultReceiver(null) {
            override fun onReceiveResult(resultCode: Int, resultData: Bundle) {
                if (resultCode == 0) {
                    requireActivity().runOnUiThread { updateView(resultData.getInt(MotionActivityService.KEY_STEPS),resultData.getInt(MotionActivityService.KEY_TOTAL),resultData.getInt(MotionActivityService.KEY_CURRENT),resultData.getInt(MotionActivityService.KEY_POINTS))}

                }
            }
        })
        requireActivity().startService(i)
    }


    private fun updateView(steps: Int,total:Int,session:Int,points:Int) {
        currentSteps = steps
        totalStepsCount = total
        totalPointsCount = points
        binding.apply {
            current.text = session.toString()
            totalPoints.text = totalPointsCount.toString()
            totalSteps.text = totalStepsCount.toString()
            stepsCurrent.text = currentSteps.toString()
            circularProgressBar.setProgressWithAnimation(currentSteps.toFloat())
            timeText.text = buildString {
                append(((totalStepsCount / 7320.0)).toBigDecimal().setScale(1,RoundingMode.UP))
                append(" H")
            }
            distanceText.text = buildString {
                append((totalStepsCount / 1312.33595801).toBigDecimal().setScale(1,RoundingMode.UP))
                append(" KM")
            }
            caloriesText.text = buildString {
                append((totalStepsCount * 0.035).toBigDecimal().setScale(1,RoundingMode.UP))
                append(" Cal")
            }


        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        const val RECEIVER_TAG = "RECEIVER_TAG"
    }
}
