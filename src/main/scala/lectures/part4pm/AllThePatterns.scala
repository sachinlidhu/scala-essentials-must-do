package lectures.part4pm

object AllThePatterns extends App {

  val numbers = List(1,2,3,4)
  val numbersMatch = numbers match {
    case listOfStrings:List[String] => "a list of strings"
    case  listOfNumbers:List[Int] => "a list of integers"
    case _ => "bchjfgfgffikdhfgykjhhhifgi2u3ry2iofgf"
  }
  println(numbersMatch) //u should see list of integers as a output , but u will see list of strings

  //Its the JVM fault, it cant predict types ie) both List[String] and List[Int] will be treated as List only
  //due to this it will match the case which will come first in order


}
