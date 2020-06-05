package com.uv.mytracker.reminders

import com.uv.mytracker.R
import com.uv.mytracker.base.BaseBottomSheet

class SleepReminderFragment : BaseBottomSheet() {
    override fun shouldShowFullScreenDialog() = false
    override fun layoutResourceId() = R.layout.fragment_sleep_reminder
}