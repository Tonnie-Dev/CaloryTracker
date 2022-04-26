package com.plcoding.calorytracker

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication


/*By default this class won't be used as a test runner,
* We need to define in our gradle the test runner to be used*/
class HiltTestRunner:AndroidJUnitRunner() {
    
    //override newApplication()
    override fun newApplication(
        cl: ClassLoader?, className: String?, context: Context?
    ): Application {
       // return super.newApplication(cl, className, context)
        
        //instead of className, we return
        
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}