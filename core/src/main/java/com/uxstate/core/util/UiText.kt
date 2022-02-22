package com.uxstate.core.util

import android.content.Context

//helper class
sealed class UiText {

    /*houses strings from String Resources and also Dynamic
   Strings e.g. from a remote API where we don't have the
    ability to read it from string resources. In this case
    we need to shaw the string as it is or as we get it from
    the API*/

    data class DynamicString(val text:String):UiText()
    data class StringResource(val resId:Int):UiText()


    //helper function to unwrap UiText Class
    fun asString(context:Context): String {
        //this refers to UiText sealed class
        return when(this){

           is DynamicString -> text



            is StringResource -> context.getString(resId)
        }

    }

}
