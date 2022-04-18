package com.uxstate.tracker_data.repository


import com.uxstate.tracker_data.remote.OpenFoodAPI
import io.mockk.mockk
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

//tests handling of valid, invalid and malformed responses by FoodAPI
class TrackerRepositoryImplTest {
    private lateinit var repository: TrackerRepositoryImpl
    private lateinit var mockWebServer: MockWebServer
    
    //okhttp to configure the mockServer
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var api: OpenFoodAPI
    
    @Before
    fun setUp() {
        
        //initialize variables
        mockWebServer = MockWebServer()
        
        //initial with short timeouts, Default is 10s
        okHttpClient = OkHttpClient
                .Builder()
                .writeTimeout(1, TimeUnit.SECONDS)
                .readTimeout(1, TimeUnit.SECONDS)
                .connectTimeout(1, TimeUnit.SECONDS)
                .build()
        
        //initialize api
        
        api = Retrofit
                .Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(mockWebServer.url("/"))//we get base url from MockWebServer
                //ensuring the retrofit object talks to the local Mock Web server instead
                .build()
                .create(OpenFoodAPI::class.java)
        
        /*initialize repository - use a mock k for dao since it
        is not needed + ROOM Library is already well tested unless
        for complex queries*/
        
        repository = TrackerRepositoryImpl(dao = mockk(relaxed = true), api =api)
    }
    
    
    //executed after every test case
    @After
    fun tearDown() {
        
        //ensures we start with a new instance of the server
        mockWebServer.shutdown()
    }
    
    @Test
    
    
}