package com.example.tbc_final.presentation.home

import android.content.Intent
import android.os.Bundle
import android.os.ResultReceiver
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.tbc_final.R
import com.example.tbc_final.databinding.FragmentHomeBinding
import com.example.tbc_final.service.MotionActivityService
import com.example.tbc_final.presentation.base.BaseFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {


    private var currentSteps: Int = 0
    private var totalStepsCount:Int = 0
    private var totalPointsCount:Int = 0
    private lateinit var navController: NavController


    override fun onBind() {
        subscribeService()

        val date = SimpleDateFormat(getString(R.string.dateFormat)).format(Calendar.getInstance().time)
        binding?.date?.text = date.toString()


        toCalc()

        //TEST LOG OUT BUTTON
        binding?.logOutBtn?.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val i = Intent(requireContext(),MotionActivityService::class.java)
            i.action = MotionActivityService.ACTION_STOP_ACTIVITY
            requireContext().stopService(i)
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLogInFragment())
        }

        //TEST LOG OUT BUTTON
    }
    private fun navigation(){
        val appBarConfiguration = AppBarConfiguration(navController.graph, binding?.drawerLayout)


        val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        requireActivity().findViewById<NavigationView>(R.id.fragmentContainerView)
            .setupWithNavController(navController)
    }


    private fun toCalc(){
        binding?.currentStatsIV?.setOnClickListener {
            findNavController().navigate(directions = HomeFragmentDirections.actionHomeFragmentToCalculatorFragment())

        }
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
        binding?.apply {
            current.text = session.toString()
            totalPoints.text = totalPointsCount.toString()
            totalSteps.text = totalStepsCount.toString()
            stepsCurrent.text = currentSteps.toString()
            circularProgressBar.setProgressWithAnimation(currentSteps.toFloat())
            timeText.text = buildString {
                append(((totalStepsCount / TIME_FORMULA)).toBigDecimal().setScale(1,RoundingMode.UP))
                append(HOURS)
            }
            distanceText.text = buildString {
                append((totalStepsCount / DISTANCE_FORMULA).toBigDecimal().setScale(1,RoundingMode.UP))
                append(KM)
            }
            caloriesText.text = buildString {
                append((totalStepsCount * CALORIES_FORMULA).toBigDecimal().setScale(1,RoundingMode.UP))
                append(CALORIES)
            }


        }

    }

    companion object{
        const val HOURS = " H"
        const val KM = " KM"
        const val CALORIES = " Cal"
        const val RECEIVER_TAG = "RECEIVER_TAG"
        const val TIME_FORMULA = 7320.0
        const val DISTANCE_FORMULA = 1312.33595801
        const val CALORIES_FORMULA = 0.035
    }
}
