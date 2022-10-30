package com.example.tbc_final.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.os.ResultReceiver
import android.util.SparseArray
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.tbc_final.MainActivity
import com.example.tbc_final.R
import com.example.tbc_final.domain.repository.local.StepPreferencesRepository
import com.example.tbc_final.domain.use_case.preferences.GetStepUseCase
import com.example.tbc_final.domain.use_case.preferences.PutStepUseCase
import com.example.tbc_final.presentation.home.HomeFragment.Companion.RECEIVER_TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MotionActivityService: Service() {
    private var todaySteps: Int = 0
    private var totalSteps: Int = 0
    private var currentSteps: Int = 0
    private var session:Int = 0
    private var lastSteps = -1
    private var points: Int = 0
    private var receiver: ResultReceiver? = null
    private lateinit var sensorListener: SensorEventListener
    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationBuilder: NotificationCompat.Builder
    private var motionUpdateService: SparseArray<MotionUpdateService> = SparseArray()
    private var motionUpdateServiceId = 0

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    @Inject
    lateinit var getStepUseCase: GetStepUseCase
    @Inject
    lateinit var putStepUseCase: PutStepUseCase

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override fun onCreate() {
        super.onCreate()
        setUpService()
        setUpSensor()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null){
            when (intent.action) {
                ACTION_SUBSCRIBE -> receiver = intent.getParcelableExtra(
                    RECEIVER_TAG)
                ACTION_START_ACTIVITY -> {
                    val id = motionUpdateServiceId++
                    motionUpdateService.put(id, MotionUpdateService(id, currentSteps))
                }
                ACTION_STOP_ACTIVITY -> motionUpdateService.remove(intent.getIntExtra(KEY_ID, 0))
                ACTION_TOGGLE_ACTIVITY -> motionUpdateService.get(intent.getIntExtra(KEY_ID, 0)).toggle()
            }
            sendUpdate()
        }
        return START_STICKY
    }

    private fun setUpSensor() {

        scope.launch {
            todaySteps =  getStepUseCase.getStep().getOrNull()?.toInt() ?: 0
            totalSteps = getStepUseCase.getTotalStep().getOrNull()?.toInt() ?:0
            points = getStepUseCase.getPoints().getOrNull()?.toInt() ?: 0
        }



        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as? SensorManager ?: throw IllegalStateException(getString(R.string.service_error))
        val stepSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        sensorListener = object : SensorEventListener {

            override fun onSensorChanged(event: SensorEvent) {
                eventHandler(event.values[0].toInt())
            }

            override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            }
        }

        if (stepSensor != null) {
            sensorManager.registerListener(sensorListener, stepSensor, SensorManager.SENSOR_DELAY_FASTEST)
        } else {
            Toast.makeText(this, "Your Device Is Not Supported", Toast.LENGTH_LONG).show()
        }
    }

    private fun setUpService() {
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager ?: throw java.lang.IllegalStateException(getString(R.string.service_error))
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,PendingIntent.FLAG_IMMUTABLE)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }

        notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.login_logo)
            .setContentTitle(getString(R.string.app_name))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        startForeground(FOREGROUND_ID, notificationBuilder.build())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        if (notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                getString(R.string.app_name),
                NotificationManager.IMPORTANCE_NONE)

            notificationChannel.description = getString(R.string.steps)

            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun eventHandler(value:Int){
        currentSteps = value
        if (lastSteps == -1) {
            lastSteps = value
        }
        todaySteps += value - lastSteps
        totalSteps += value - lastSteps
        session += value - lastSteps
        lastSteps = value
        saveEvent()
    }

    private fun saveEvent() {

        scope.launch {
            putStepUseCase.putStep(todaySteps.toString())
            putStepUseCase.putTotalStep(totalSteps.toString())

        }


        for (i in 0 until motionUpdateService.size()) {
            motionUpdateService.valueAt(i).update(currentSteps)
        }

        sendUpdate()
    }

    private fun sendUpdate(){
        notificationBuilder.setContentText(String.format(Locale.getDefault(),getString(R.string.steps_format),todaySteps))
        notificationManager.notify(FOREGROUND_ID, notificationBuilder.build())
        receiver?.let {
            val bundle = Bundle()

            if (todaySteps > 2000){
                todaySteps = 0
                points += 25
                runBlocking {
                    putStepUseCase.putStep(todaySteps.toString())
                    putStepUseCase.putPoints(points.toString())
                } //TODO runblocking problme ?
                bundle.putInt(KEY_STEPS, todaySteps)
                bundle.putInt(KEY_POINTS, points)

            }else{
                bundle.putInt(KEY_POINTS, points)
                bundle.putInt(KEY_STEPS, todaySteps)

            }
            bundle.putInt(KEY_TOTAL,totalSteps)
            bundle.putInt(KEY_CURRENT,session)
            for (i in 0 until motionUpdateService.size()) {
                val motionActivity = motionUpdateService.valueAt(i)
                val activityBundle = Bundle()
                activityBundle.putInt(KEY_ID, motionActivity.id)//???
                activityBundle.putInt(KEY_STEPS, motionActivity.steps)
                activityBundle.putInt(KEY_TOTAL, motionActivity.steps)
                activityBundle.putBoolean(KEY_ACTIVE, motionActivity.active)//??
            }
            it.send(0, bundle)
        }
    }

    companion object{
        internal const val ACTION_SUBSCRIBE = "ACTION_SUBSCRIBE"
        private const val ACTION_START_ACTIVITY = "ACTION_START_ACTIVITY"
        private const val ACTION_STOP_ACTIVITY = "ACTION_STOP_ACTIVITY"
        private const val ACTION_TOGGLE_ACTIVITY = "ACTION_TOGGLE_ACTIVITY"
        private const val KEY_ID = "ID"
        internal const val KEY_STEPS = "STEPS"
        internal const val KEY_POINTS = "POINTS"
        internal const val KEY_CURRENT = "STEPS_CURRENT"
        internal const val KEY_TOTAL = "TOTAL"
        private const val KEY_ACTIVE = "ACTIVE"
        private const val FOREGROUND_ID = 1488
        private const val CHANNEL_ID = "com.example.tbc_final.CHANNEL_ID"
    }
}