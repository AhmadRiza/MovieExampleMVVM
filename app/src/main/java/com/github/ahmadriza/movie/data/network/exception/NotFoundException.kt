package com.github.ahmadriza.movie.data.network.exception

import java.io.IOException

class NotFoundException : IOException() {

    override val message: String?
        get() = "Not Found"
}