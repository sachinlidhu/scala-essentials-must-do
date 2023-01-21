package lectures.part3fp

object HOFsCurries extends App{

  //EXERCISE
  //implement a function that applies a function N times over a value X
  def nTimes(f:Int =>Int,n: Int,x:Int):Int ={
    if (n <= 0) x
    else nTimes(f,n-1,f(x))
  }
  val plusOne = (x:Int) => x+1
  println(nTimes(plusOne, 10,1))

  //another approach is as below
  /*
   *we can  make a function that takes 2 parameters a function and no of times it should be applied over
   * and return another function that takes that value and return solution
   * in short it will work like f(f(...f(x))) instead of n times f(f,n,x)
   */
  def nTimesBetter(f:Int=> Int, n:Int):Int=>Int ={
    if (n<=0) (x:Int)=>x
    else (x:Int) => nTimesBetter(f,n-1)(f(x)) //it means nTimesBetter function is applied to f(x)
  }
  val plus10 = nTimesBetter(plusOne,10)
  println(plus10(1))

  //curried functions
  val superAdder : Int =>(Int=>Int) = (x:Int) => (y:Int) => x+y
  val adder3 = superAdder(3) //it will return y => 3+y
  println(adder3(10))

  println(superAdder(3)(10)) // we can also do that
  //note in curried function we can also pass only one parameter if we want to run that implementation only

  //functions with multiple parameter list
  def curriedFormatter(c:String)(x:Double):String = c.format(x)

  val standardFormat:(Double => String) = curriedFormatter("%4.2f")
  val preciseFormatter:(Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormatter(Math.PI))

  /*EXERCISE
  expand myList
  -add foreach method A => Unit
  example--[1,2,3].foreach(x => println(x)) should give 1,2,3 in sperate lines
  -add sort function which basically compares 2 functions
  sorting function is of type ((A,A)=>Int) => MyList
  example--[1,2,3].sort((x,y) => y-x) should give [3,2,1]
  -add zipWith function which takes another list and and a zip function
  zipWith is of type (list, (A,A)=>B) => MyList[B]
  example--[1,2,3].zipWith([4,5,6], x*y) should give [1*4, 2*5, 4*6] that is [4,10,18]
  -add a fold method, this would be curried one
   *example --fold(start)(function) => value
   -----------[1,2,3].fold(0)(x+y) = 6 //sum of all the numbers

   2..Another Exercise
   --write a function to convert a non curried method into a curried one
   example--toCurry(f:(Int,Int)=>Int) => (Int => Int => Int)
   --also convert a curried one to non curried, vice-versa of above
   example--fromCurry(f:(Int => Int => Int )) => (Int,Int)=>Int

   3..Another
   --write a function which return a lambda which for every value of x, it apply g first then f on that
   example--compose(f,g)=> x=>f(g(x))
   --write another function which applies g(f(x))
   example--andThen(f,g) => x => g(f(x))

   */

  def toCurry(f:(Int,Int)=>Int):(Int => Int => Int) = {
    x => y => f(x,y)
  }
  def fromCurry(f:(Int => Int => Int)): (Int,Int)=>Int =
    (x,y)=> f(x)(y)

  def compose(f:Int => Int, g:Int => Int): Int => Int =
    x => f(g(x))

  //lets generecise the above function
  def compose1[A,B,T](f: A => B, g: T => A): T => B =
    x => f(g(x))

  //TRY TO TO UNDERSTAND USER OF A,B,T ABOVE---IMP

  def superAdder1:(Int =>Int => Int) = toCurry(_+_)
  def add4 =  superAdder1(4)
  println(add4(7))
  println("mcnknvknv")
}
