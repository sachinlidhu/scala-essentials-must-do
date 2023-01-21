package lectures.part4pm

import scala.util.Random

object PatternMatching extends App{
  val random = new Random
  val x = random.nextInt(10)

  val description = x match {
    case 1 => "THE ONE"
    case 2 => "double is nothing"
    case 3 => "THIRD time is the charm"
    case _ => "something else"
  }
  println(x)
  println(description)

  //1..decompose values
  case class Person(name:String, age:Int)

  val bob = Person("Bob",19)

  val greeting = bob match {
   // case Person(n,a) => s"Hi my name is $n and I am $a years old"
    //we can use if statement with pattern cases ..its called if guard
    case Person(n,a) if a < 21 => s"Hi I am $n and I am $a years old so I cant drink"
    //cases are matched in order ..if i pass age = 19, then both first and second case hold true...but first onw will be printed
    case _ => s"I dont know who I am"
  }
  println(greeting)

  /*
   *--cases are matches in order
   *--what if no case is matched?--then we get match error
   *--what is the type of pattern match expresiion--in our case what is type of description
   *   --suppose all the cases return String then type is string but suppose some cases return other type then type will be Any
   *    --so answer is the unified type of all the types in all the cases
   *
   */

  //2..pattern matching on sealed hierarchies----check on google
  sealed class Animal
  case class Dog(breed:String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal:Animal = Dog("Terra Nova")
  animal match {
    case Dog(abc) => println(s"matched a dog of $abc breed")
  }

  //students try to match everything see below
  val isEven = x%2 == 0
  //instead of writing above logic directly students write below one

  val isEven1 = x match {
    case n if n%2 == 0 => true
    case _ => false
  }
  //why to do that ..so pattern matching will make it more bulky..use it wisely
  // so use common sense..dont use pattern match evrywhere

  //even dont use below logic too

  val isEven2 = if (x%2 == 0) true else false
  //why....again....directly use--val isEven2 = x%2 == 0

  /*
   *EXERCISE
   * create a simple function which uses pattern match
   *  --takes an expr => human readble form
   *
   * Sum(Number(2),Number(3)) => 2+3
   * Sum(Number(2), Number(3), Number(4)) => 2+3+4
   * Prod(Sum(Number(2),Number(1)),Number(3)) => (2+1)*3
   * Sum(Prod(Number(2),Number(1)),Number(3)) => 2*1+3
   */
  trait Expr
  case class Number(n:Int) extends Expr
  case class Sum(e1:Expr,e2:Expr) extends Expr
  case class Prod(e1:Expr,e2:Expr) extends Expr

  def show(e:Expr):String = e match {
    case Number(n) =>s"$n"
    case Sum(e1,e2) => show(e1) + "+" + show(e2)
    case Prod(e1,e2) => {
      def maybeShowParanthesis(exp: Expr) = exp match {
        case Prod(_,_) => show(exp)
        case Number(_) => show(exp)
        case _ =>"(" + show(exp) + ")"
      }
      maybeShowParanthesis(e1) + "*" + maybeShowParanthesis(e2)
    }
  }
  println(show(Sum(Number(2),Number(3))))
  println(show(Sum(Sum(Number(2),Number(3)),Number(4))))
  println(show(Prod(Sum(Number(2),Number(1)),Number(3))))
  println(show(Sum(Prod(Number(2),Number(1)),Number(3))))


}
