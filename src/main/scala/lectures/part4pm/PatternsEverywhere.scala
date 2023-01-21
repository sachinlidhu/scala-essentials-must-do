package lectures.part4pm

object PatternsEverywhere extends App {

  val list = List(1, 2, 3, 4)
  val mappedList = list.map {
    case v if v % 2 == 0 => v + "is even"
    case 1 => "the one"
    case _ => "something else"
  }//this code is called partial function literal

  //the above thing is similar to below code
  val mappedList2 = list.map {
    x =>
      x match {
        case v if v % 2 == 0 => v + "is even"
        case 1 => "the one"
        case _ => "something else"
      }


  }
  println(mappedList)
  println(mappedList2)

}
