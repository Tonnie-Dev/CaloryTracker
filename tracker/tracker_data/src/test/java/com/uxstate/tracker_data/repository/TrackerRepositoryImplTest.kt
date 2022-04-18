package com.uxstate.tracker_data.repository


import com.uxstate.tracker_data.remote.OpenFoodAPI
import io.mockk.mockk
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before

//tests handling of valid, invalid and malformed responses by FoodAPI
class TrackerRepositoryImplTest {
    private lateinit var repository: TrackerRepositoryImpl
    private lateinit var mockServer:MockWebServer
    //okhttp to configure the mockServer
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var api: OpenFoodAPI
    
    @Before
    fun setUp() {
        
        /*initialize repository - use a mocck for dao since it
        is not needed + ROOM Library is already well tested unless
        for complex queries*/
        
        repository = TrackerRepositoryImpl(dao = mockk(relaxed = true), api =)
    }
    
    @After
    fun tearDown() {
    }
}