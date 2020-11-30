package com.marcoelizalde.platziconf.view.adapter

import com.marcoelizalde.platziconf.model.Conference

interface ScheduleListener {
    fun onConferenceClick(conference: Conference, position: Int)

}