package lectures.part1basics

import scala.annotation.tailrec
import scala.math.Fractional.Implicits.infixFractionalOps
import scala.math.Integral.Implicits.infixIntegralOps
import scala.math.Numeric.BigDecimalAsIfIntegral.mkNumericOps
import scala.math.Numeric.BigIntIsIntegral.mkNumericOps

object Recurssion extends App {

  // factorial is recusive function
  def factorial(n:Int):Int =
    if (n<=1) 1
    else n *factorial(n-1)

  //let change little bit and add block to a else clause to see whats happeing inside

  def factorial1(n:Int):Int = {
    if (n<=1) 1
    else {
      println("computing factorial of " + n + "first I need factorial of " + (n-1))
      val result = n * factorial1(n-1) //here factorail(n-1) is using seperate stack to save the result
      println("computed factorial of "+n)
      result
    }
  }
  println(factorial1(10))
  //if you do println(factorial1(5000)) it will crash
  //because two seperate stacks are being used here, one for recursive calls then other for multiply
  //better to go with tail rwcuseion

  def factorialTail(n:Int):BigInt = {
    @tailrec
    def helperFunctn(x:Int, acc: BigInt):BigInt=
      if (x<=1) acc
      else helperFunctn(x-1,x * acc)

    //println(helperFunctn(n,1)) //this will give error as println return unit
    helperFunctn(n,1)
  }
  println(factorialTail(3))

// So when u need loops use tail recursion
  /*
  Exercise
  1..concat a string n times
  2..isPrime Function (tailrec)
  3..fibonacci(tailrec)
  In tailrec we need accumulatores , sometimes we need more then one accumulator
  */

  @tailrec
  def concatTailrec(astring:String,n:Int, acc:String):String =
    if (n<=0) acc
    else concatTailrec(astring, n-1, astring + acc)
  println(concatTailrec("lol",3,"- haha"))

  def isPrime(n:Int):Boolean = {
    def isPrimeTail(t:Int, acc:Boolean):Boolean={
      if (t<=1) true
      else if (!acc) false
      else isPrimeTail(t-1, n%t!=0 && acc)
    }
    isPrimeTail(n/2,true)
  }
  println(isPrime(7))
  println(isPrime(12))

  def fibonacci(n:Int):BigInt={
    //two accumulators needed beacuse two recurion call is needed
    def fibonacciTail(i:Int,last:BigInt,nextLast:BigInt):BigInt={
      if(i>=n) last
      else fibonacciTail(i+1,last+nextLast,last)
    }
    if (n<=2) 1
    else fibonacciTail(2,1,1)
  }
  println(fibonacci(8))
}
