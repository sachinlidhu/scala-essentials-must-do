package lectures.part1basics

object ValuesVariablesTypes extends App {

  val x: Int =42 // compiler can infer types so val x =42 will also work
  println(x)

  //x = 23 --VALS are immutable so it can not be reassigned
  val aString: String = "hello"
  val anotherString = "goodbuy"
  val aBoolean:Boolean = false
  val aChar: Char = 'a'
  val anInt: Int = x
  val aShort:Short =4545
  val aLong:Long = 6675786788L //FOR LONG PUT L at end
  val aFloat:Float =2.347f //if you dont use f compiler will treat it as double
  val aDouble:Double = 2.7647834

  //variables
  var a: Int = 5
  println(a)
  a=7//side-effect
  println(a)
// functional programming prefers vals over var beacuse vars have side effects
}
