package com.example.tbc_final.service

class MotionUpdateService(val id: Int, private var previousSteps: Int) {
    var steps: Int = 0
    var active: Boolean = false

    init {
        this.active = true
    }

    internal fun update(currentSteps: Int) {
        if (active) {
            steps += currentSteps - previousSteps
        }
        previousSteps = currentSteps
    }

    internal fun toggle() {
        active = !active
    }
}