package com.sample.criminalintent

import com.sample.criminalintent.model.Intent

interface IntentListener {
    fun onIntentClicked(intent: Intent)
}