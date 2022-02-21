package com.uxstate.core.domain.use_cases

class FilterOutDigits() {

   operator fun invoke(text:String):String{

       //only keep characters with digits
      return text.filter { c -> c.isDigit() }

   }
}