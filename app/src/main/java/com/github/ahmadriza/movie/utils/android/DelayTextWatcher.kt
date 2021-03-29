package com.github.ahmadriza.movie.utils.android

import android.text.Editable
import android.text.TextWatcher
import java.util.*

/**
 * Created on 8/20/20.
 */

class DelayTextWatcher(
    private val delay: Long,
    private val onSubmit: (s: String) -> Unit
) : TextWatcher {

    private var timer: Timer? = null

    override fun afterTextChanged(p0: Editable?) {
        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                onSubmit.invoke(p0.toString())
            }
        }, delay)
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        timer?.cancel()
    }

}