package com.uxstate.tracker_data.repository


import com.google.common.truth.Truth.assertThat
import com.uxstate.tracker_data.remote.OpenFoodAPI
import com.uxstate.tracker_data.remote.malformedFoodResponse
import com.uxstate.tracker_data.remote.validFoodResponse
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

//tests handling of valid, invalid and malformed responses by FoodAPI
class TrackerRepositoryImplTest {
    
    //Repo and MockWebServer
    private lateinit var repository: TrackerRepositoryImpl
    private lateinit var mockWebServer: MockWebServer
    
    //API and OkHttp to configure the mockServer
    private lateinit var api: OpenFoodAPI
    private lateinit var okHttpClient: OkHttpClient
    
    
    @Before
    fun setUp() {
        
        
       
        
        
        //initialize variables
        mockWebServer = MockWebServer()/*initialize repository - use a mock k for dao since it
       is not needed + ROOM Library is already well tested unless
       for complex queries*/
        
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
                .baseUrl(mockWebServer.url("/")) //we get base url from MockWebServer
                //ensuring the retrofit object talks to the local Mock Web server instead
                .build()
                .create(OpenFoodAPI::class.java)
        
        //dao is mocked
        repository = TrackerRepositoryImpl(dao = mockk(relaxed = true), api = api)
        
    }
    
    
    //executed after every test case
    @After
    fun tearDown() {
        
        //ensures we start with a new instance of the server
        mockWebServer.shutdown()
    }
    
    
    @Test
    fun `Search food, valid response, return results`() = runBlocking {
        
        //200 OK  - Mock server returns this response, destroyed after test case
        mockWebServer.enqueue(
            MockResponse()
                    .setResponseCode(200)
                    //validFoodResponse is a string constant
                    .setBody(validFoodResponse)
        )
        //make the API Call, args provided don't matter
        
        //need to run inside a coroutine so we use run blocking
        
        //result:Result<T> encapsulates a successful outcome with
        // a value of type T or a failure
        val result = repository.searchFood("banana", 1, 40)
        
        //assertion
        assertThat(result.isSuccess).isTrue()
    }
    
    
    @Test
    fun `Search food, invalid response return failure`() = runBlocking {
        
        //	403 Forbidden  - Mock server returns this response, destroyed after test case
        mockWebServer.enqueue(
            MockResponse()
                    .setResponseCode(403)
                    //validFoodResponse is a string constant
                    .setBody(validFoodResponse)
        )
        //make the API Call, args provided don't matter
    
        //need to run inside a coroutine so we use run blocking
    
        //result:Result<T> encapsulates a successful outcome with
        // a value of type T or a failure
        val result = repository.searchFood("banana", 1, 40)
        
        assertThat(result.isFailure).isTrue()
    }
    
    
    @Test
    
    fun `Search food, malformed response return failure`() = runBlocking {
        //Mock server returns this response, destroyed after test case
        mockWebServer.enqueue(
            
            //malformedFoodResponse is string constant
            MockResponse().setBody(malformedFoodResponse)
        
        )
    
        //make the API Call, args provided don't matter
    
        //need to run inside a coroutine so we use run blocking
    
        //result:Result<T> encapsulates a successful outcome with
        // a value of type T or a failure
        val result = repository.searchFood("banana", 1, 40)
        
        assertThat(result.isFailure).isTrue()
    }
    
    
}