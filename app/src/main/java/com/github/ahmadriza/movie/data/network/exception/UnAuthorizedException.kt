package com.github.ahmadriza.movie.data.network.exception

import java.io.IOException

class UnAuthorizedException : IOException() {

    override val message: String?
        get() = "User Unauthorized"
}