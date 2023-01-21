package lectures.part3fp

object AnonymousFunction extends App{

  //below is still aOOP way to define a doubler
  val doubler = new Function[Int,Int] {
    override def apply(x: Int): Int = x*2
  }
  //let see functinal way for that which is syntactic sugar for all the above crap

  val doubler1 = (x:Int) => x*2 //its called anonymous function or lambda...what its doing is providing you the function name and apply method at the backend and u just need to provide parameter and its implementation

  //below is more better way
  val doubler2 : Int => Int = x =>x*2 // if compiler already know return type of doubler then it will predict the return type of x of its own

  //exercise...multiply two numbers using lamda
  val mul = (a:Int,b:Int) => a*b

  val mul1 : (Int,Int)=> Int = (a,b)=> a*b

  //how to write lambda if u dont have any parametrs

  val justDoSomething = ()=>3 //provide empty brackets if you dont have any parametr

  val justDoSomething1 : ()=>Int = ()=>3 //nect approach

  println(justDoSomething) //this will not work
  println(justDoSomething()) // this will work
  //note in lambdas if no paramater is there then u must used empty braces while calling

  //another way to write lambdas is to use curly braces
  val stringToInt = { (str:String) =>
    str.toInt
  }

  //more simplification and syntactic sugars
  val niceIncrementor : Int => Int = _ +1 // _ +1 is equivalent to x => x+1
  val niceAdder : (Int,Int)=>Int = _+_ // equivalent to (a,b)=> a+b

  //while using underscores be very careful, u need to provide the types in advance otherwise underscore will not understand which tyope to consider

  /*EXERCISE
   * 1...go to MyList and replace all FunctionX calls with lambdas
   * 2...rewrite the "special" adder which is a curried one, as a anonymous function
   *
   */

  //lets see the lambda version of curried function
  val specialAdd = (x:Int)=> (y:Int) => x+y //function => function => result
  val specialAdd1: Int =>Int =>Int = x => y => x+y
  println(specialAdd(3)(7))
  println(specialAdd1(3)(7))
}
