package com.github.ahmadriza.movie.data.repository

import com.github.ahmadriza.movie.data.remote.RemoteDataSource
import com.github.ahmadriza.movie.models.SocketCryptoMessage
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

@RunWith(MockitoJUnitRunner::class)
class MainRepositoryTest {

    private lateinit var repository: MainRepository

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var localDataSource: RemoteDataSource

    @Inject
    lateinit var analyticsAdapter: SocketCryptoMessage

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun processSocketRequest() {
    }
}