package com.uxstate.core.domain.model

sealed class Gender( val name:String){

/*the name arg helps us save entry on SharedPrefs as we
    cannot save sealed class in SharedPrefs*/
    object Male:Gender(name ="male")
    object Female:Gender(name ="female")


    //easily take a string and return a gender  object
    companion object{
        fun fromString(name: String) :Gender {

            return when (name){

                "male" -> Male
                else -> Female
            }
        }
    }
}
