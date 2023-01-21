package lectures.part3fp

object WhatsAFunction extends App {
  val doubler = new MyFunction[Int,Int]{
    override def apply(element:Int):Int = element*2
  }
//now power of scala is that we can call doubler like its a function (power of apply method) see below
  println(doubler(2)) // doubler acting like a function .....doubler.apply(2)

  //scala supports function types ...Function1, Function2.....Function22
  val stringToIntConverter = new Function[String,Int] {
    override def apply(string:String):Int = string.toInt
  }
  println(stringToIntConverter("3") + 4)

  //Function2 has 3 generics...3 Ints ....2 Ints for input and 1 Int for output
  //In our case Function2[Int,Int,Int] can also written as (Int,Int) => Int
  val added: (Int, Int) => Int = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  /*EXERCISE
   * 1...define a function which takes 2 strings and concatinates them
   * 2...go to Mylist implementation and transform the MyPrediacate and My Transformer into function types
   * 3...define a function which takes an Int as a argument and returns another function which takes an Int and returns an Int
   */

  def concatinator: (String,String) => String = new Function2[String,String,String] {
    override def apply(a:String,b:String):String = a +b
  }
  println(concatinator("Hello","Scala"))

  val superAdder:Function1[Int,Function1[Int,Int]] = new Function1[Int,Function1[Int,Int]]{
    override def apply(x:Int):Function1[Int,Int] = new Function1[Int,Int]{
      override def apply(y:Int):Int = x+y
    }
  }
  val adder3 = superAdder(3)
  println(adder3(4))

  //above adder 3 can be simplified...first we provide value to first apply then we store it and provide value to second apply
  //same can be simplified using function curry
  println(superAdder(3)(4)) //curried function
}

//what we did earlier in OOP is following

class Action{
  def execute(element:Int):String = ???
}
 // above action class is receiving Int and returning string
//To improve above function at MOST what OOP can do is genericise it as below

class Action1[A,B]{
  def execute(element:A):B = ???
}
// Now scala can make above code more functional using some tricks
//1...change function name to apply and use its power
class MyFunction[A,B]{
  def apply(element:A):B = ???
}