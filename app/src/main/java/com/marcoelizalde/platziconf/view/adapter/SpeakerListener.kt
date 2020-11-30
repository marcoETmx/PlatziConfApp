package com.marcoelizalde.platziconf.view.adapter

import com.marcoelizalde.platziconf.model.Speaker

interface SpeakerListener {
    fun onSpeakerClicked(speaker: Speaker, position: Int)

}