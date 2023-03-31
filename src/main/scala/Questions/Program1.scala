package Questions
//WAP to exchange the first and last character in a given string
object Program1 extends App{
  val input:String = "Sachin"
  def exchange(inputString:String):String = {
    val a = inputString.length
   /* var a = inputString.toArray
    val b = a.length
    val c = a[0]
    a[0] = a[b-1]
    a[b] = a[c]*/

    inputString.charAt(a-1) + inputString.substring(1,a-1) + inputString.charAt(0)
  }
  println(exchange("Sachin"))

  //create a new string with last char added at the front and back of given string of lenght 1 or more

  def frontlast(inputString: String):String = {
    val a = inputString.length
    if (a >=1) inputString.charAt(a-1) + inputString.substring(0,a) + inputString.charAt(a-1)
    else
      inputString
  }
  println(frontlast("Sachin"))

  // check wheather a given positive number is multiple of 3 or a multiple of 7
  def checkMultiple(inputNumber: Int):Boolean = {
    (inputNumber % 3 == 0 || inputNumber % 7 == 0)
  }
  println(checkMultiple(21))
  println(checkMultiple(7))
  println(checkMultiple(6))
  println(checkMultiple(25))

}
