package lectures.part2oop

import scala.language.postfixOps

object MethodNotations extends App{

  class Person(val name:String,favoriteMovie:String,val age:Int = 0){
    def likes(movie:String):Boolean = movie == favoriteMovie
    def hangoutWith(person:Person):String = s"${this.name} is hanging out with ${person.name}"
    def +(person:Person):String = s"${this.name} is hanging out with ${person.name}"
    def +(nickname:String):Person = new Person(s"$name ($nickname)", favoriteMovie)
    def unary_! : String = s"$name what the heck"
    def unary_+ : Person = new Person(name,favoriteMovie,age+1)
    def isAlive :Boolean = true //function that do not use any parameters can be used in postfix notation
    def apply():String = s"hi my name is $name and i like $favoriteMovie"
    def apply(n:Int):String = s"$name watched $favoriteMovie $n times"
    def learns(thing:String) = s"$name is learning $thing"
    def learnsScala = this learns "Scala"
  }

  val mary = new Person("MARY","INCEPTION")
  println(mary.likes("INCEPTION"))
  println(mary likes "INCEPTION") // this is equivalent above one, just a new sybtax

  //this is called infix notation or operator notation or syntatic sugar
  //but this syntax works with method with one parameter only

  val tom = new Person("Tom","fight Club")
  println(mary hangoutWith tom) //this will work beacuse both parameter are clibed into newer single val
// this hanoutWith can be traeated as operator..remebr math operators +,-etc are used this way only

  println(mary + tom)
  println(mary.+(tom))

  //prefix notation
  val x = -1 // equivalent with 1.unary_-
  val y = 1.unary_-
  //unary_prefix only work with + - ~ !
  println(!mary) // equivalent with println(mary.unary_!)
  println(!tom)

  //postfix notation
  println(mary.isAlive)
  //println(mary isAlive) // should work but dont know why eror here
  println(mary.apply())
  println(mary()) //just pass () in front it will automatically call the apply
  //println(mary())--when a compiler sees object being passed as a function, it automatically searches for apply
  /**
   * overload the + operator which receives a string and returns a new person with nickname
   * eg.. mary + "the rockstar" => new person "Mary (the rockstar)"
   *
   * q2..add a age to person class with default 0 value
   *     and add a unary + operator which increments the age and returnds a new person with age +1
   *     eg.. +mary => mary with the age incremented
   *     this resembles the ++ in c++..
   *
   * q3..add a learns method in the person class
   *      this resceives a string parameter and returns mary learns "that parameter"
   *     add a learnScala methd which doesnot receives any parameter and calls above learns method with scala as a parameter.
   *     use it in postfix notation
   *
   * q4..overload apply method to receive a int and return a string
   *      eg.. mary.apply(2)=>"Mary watched inception 2 times"
   *
   */
  println((mary + "the rocksatr")())//this empty braces means apply method, we have used aplly method over new person instance which is taking a parameter.
  println(+mary)
  println((+mary).age)
  println(mary learnsScala)
  println(mary(10))
}
