package lectures.part2oop

object Exceptions extends App {

  val x: String = null
  //println(x.length) -- this will crash with nul pointer exception

  //how to manually throw exceptions--see below commented code
  //val aWeiredValue: String = throw new NullPointerException()

  //how to catch exception
  def getInt(withExceptions: Boolean) =
    if (withExceptions) throw new RuntimeException()
    else 42

  try {
    //code that might throw exception
    getInt(true)
  } catch {
    case e: RuntimeException => println("caught a runtime exception")
    //case e:NullPointerException => println("caught a runtime exception")--if we use Null Pointer Exception instead of actual one then it will crash
  } finally {
    //here the code will get executed no matter what
    //finally is optional and does not influence the return type of this expression
    //so use finally only for side effects like printing logs
    println("string finally")
  }

  // how to define your own exceptions
  class MyException extends Exception

  val exception = new MyException
  throw exception

  /*
   *Exercise
   * 1..crash your program with out of memory error, out of memory occurs when when u try to allocate momemory more than jvm has
   * 2..crash with stack overflow error
   * 3..create a pocket calculator class with below methods
   *      add(x,y),subtract(x,y),multiply(x,y),divide(x,y)
   * 4...these methods will throw a cistom exception if something wrong happens
   *      throw Overflow exception if add(x,y) exceeds INT.MAX_VALUE
   *      throw Underflow exception if subtract(x,y) exceeds int.MIN_VALUE
   *      mathCalculationException for division by 0
   *
   */
  val array = Array.ofDim(Int.MaxValue) //this will crash the system with out of memory error

  def infinite: Int = 1 + infinite //this will be infinite recurssion causing stack overflow error

  val noLimit = infinite

  class OverflowException extends RuntimeException

  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("divide by zero")//runtimeexception can also take string as a parameter

  object PocketCalculator {
    def add(x: Int, y: Int) = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def sub(x: Int, y: Int) = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def mul(x: Int, y: Int) = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }
    def divide(x:Int,y:Int) = {
      if(y==0) throw new MathCalculationException
      else x/y
    }
  }
  //println(PocketCalculator.add(Int.MaxValue,10))
  println(PocketCalculator.divide(2,0))
}