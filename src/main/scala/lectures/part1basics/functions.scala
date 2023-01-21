package lectures.part1basics

object functions extends App {

  def aFunction(a:String,b:Int):String =
    a+" "+b //it will concat a and b with space inbetween---"+" is used to concat

  def aFunction1(a: String, b: Int): String = {
    a + " " + b // here we did implementation inside a block , it will return last line
  }
  println(aFunction("hello",3))
  println(aFunction1("sachin",7))

  def aParameterless():Int = 42
  println(aParameterless())
  println(aParameterless)

  //loops alternative in functional programiing lamguage
  //when u need loops use recursion
  //for a normal function if u dont mention its return type compile will detect it but for recursive function its mandate to provide return type
  def aRepeatedFunction(aString: String, n:Int):String = {
    if(n==1) aString
    else
      aString + aRepeatedFunction(aString, n-1)
  }
  println("meaw",3)
  println(aRepeatedFunction("meaw",3))

  /*
  question1...create a function with 2 parameters name and age and return my name is $name and i am  $age years old
  question2...factorial
  question3...fibonacci numbers
  question4...test if a number is prime
   */
  def fun1(name:String,age:Int):String = s"My name is ${name}, I am $age years old"
  println(fun1("sachin",28))

  // in recursive function we need to specify the return type of function beacuse in
  // if clause it will know its then in else the number is also Int
  // but the recursive call is confuse what return type it has to be.

  def factorial(number:Int):Long = {
    if(number<=0) 1
    else number * factorial(number-1)
  }
  println(factorial(4))

  def fibonacci(number:Int):Long ={
  if(number<=2) 1
  else
    fibonacci(number-1)+fibonacci(number-2)

  }
  println(fibonacci(6))

  def isPrime(n:Int):Boolean = {
    def isPrimeUntill(t:Int):Boolean = {
      if (t<=1) true
      else n%t !=0 && isPrimeUntill(t-1)
    }
    isPrimeUntill(n/2)
  }
  println(isPrime(37))
  println(isPrime(7))
  println(isPrime(7*3))
}
