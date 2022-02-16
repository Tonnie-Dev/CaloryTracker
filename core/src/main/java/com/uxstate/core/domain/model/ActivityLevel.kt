package com.uxstate.core.domain.model

sealed class ActivityLevel(level:String){

    object Low:ActivityLevel(level = "low")
    object Medium:ActivityLevel(level = "medium")
    object High:ActivityLevel(level = "high")

    companion object{

        fun fromString(level: String):ActivityLevel {

            return when(level){
                "low" -> Low
                "medium" -> Medium
                "high" -> High
                else -> Medium

            }
        }
    }



}
