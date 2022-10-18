package com.example.tbc_final.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.ResultReceiver
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.tbc_final.databinding.FragmentHomeBinding
import com.example.tbc_final.service.MotionActivityService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var currentSteps: Int = 0
    private val viewModel: HomeViewModel by viewModels()


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


        viewLifecycleOwner.lifecycleScope.launch {
           val x = viewModel.getStep().getOrNull().toString().toInt()
            updateView(x)
        }

    }

    private fun subscribeService() {

        val i = Intent(requireContext(), MotionActivityService::class.java)
        i.action = MotionActivityService.ACTION_SUBSCRIBE
        i.putExtra(RECEIVER_TAG, object : ResultReceiver(null) {
            override fun onReceiveResult(resultCode: Int, resultData: Bundle) {
                if (resultCode == 0) {
                    requireActivity().runOnUiThread { updateView(resultData.getInt(MotionActivityService.KEY_STEPS))}

                }
            }
        })
        requireActivity().startService(i)
    }



    private fun updateView(steps: Int) {
        currentSteps = steps
        binding.apply {
            stepsCurrent.text = currentSteps.toString()
            circularProgressBar.setProgressWithAnimation(currentSteps.toFloat())
        }


        Toast.makeText(requireContext(), "${steps}", Toast.LENGTH_SHORT).show()

    }

    private fun checkGoal(){
        if (currentSteps == 20){
//            viewLifecycleOwner.lifecycleScope.launch {
//                viewModel.putStep((0).toString())
//            }
            Toast.makeText(requireContext(), "asdasasdasd", Toast.LENGTH_SHORT).show()

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
