package lectures.part2oop

object OObasics extends App {
  //val person = new Person --1
  //println(person)

 // val person = new Person("john",26) --2 //we instantiate class with new keyword
//  println(person.age) // .age wont work here because its not treated as member of class, to make it member use val/var in class constructor/defination

  val person = new Person("john",26)
  println(person.age) //now it will work
  println(person.x)
  person.greet("sachin") //notice that in greet method same "name" is used as that of class, so it should take jon not sachin...to tackle it use this
  println(person.greet("sachinnn")) // two printsln involve so check the output
  println(person.greet1("sachinnn"))
  person.greet2("sachin") //see the usecase of this keyword... it will preserve the class parameter
  person.greet2
  val author = new Writer("Charles", "Dicknes", 1812)
  val author1 = new Writer("Charles", "Dicknes", 1812)
  val novel = new Novel("Great expectations", 1861, author)
  println(novel.authorAge)
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(author1)) //actually it should return true but its limitation of oop
  val counter = new Counter //no need to pass argument as we have alreday set default args in class itself
  counter.inc1
  counter.inc1.inc1.inc1
  counter.inc1(10).print


//class Person --1//here this will not work you need to pass arguments also
//class Person(name:String, age:Int) //it seems similar to function but in scala its called constructor
class Person(name:String, val age:Int) //now u can access person.age
{
  //body
  val x = 10 // this x also u can access now, like person.age
  println(1 + 3) // at every instantiation entire class will get executed. so if u person.x it will execute entire class and its side effects will be printed also.. like here this print(1+3) is also executed

  def greet(name: String) = println(s"$name says, Hi I am $name")

  def greet1(name: String) = s"$name says, hi $name"

  def greet2(name: String) = println(s"${this.name} says, Hi $name") //imp

  def greet2 = println(s"$name is lol") //method overloading--same function name but differnt parameters
  //but if only return type is differnt then compiler will be confused as which to pick up

  //multiple constructors --auxilary constuctor
  def this(name: String) = this(name, 0) //this will call Person(name,age) with age =0...but it can be achieved by default parametrs also
}


//EXERCISE
  /** implement a novel and writer class
  *   writer should have first name, surname,year as parameters
  *     and a method full which will do concat of two parameter
  *   novel should have name, year of release, author--author of type writer
  *     methods in novels are
  *       1. author age (age of author at the time of release)
  *       2. isWrittenBy(author)
  *       3. copy (new year of release) = new instance of novel
  * */
  class Writer(firstName:String, lastName:String, val year:Int){
    def fullName = firstName+" "+lastName
  }
  class Novel(name:String,year: Int, author: Writer){
    def authorAge = year - author.year //make year parameter in writer val/var to make it accessible
    def isWrittenBy(author:Writer) = author == this.author
    def copy(newYear:Int) = new Novel(name,newYear,author)
  }
  /**
   * create a counter class which will receive a int value
   *  create below methods
   *    method current count
   *   method to increment/decrement => new counter
   *   overload inc/dec to receive a amount
   */

  class Counter(val count:Int =0){
    def inc = new Counter(count+1)
    def dec = new Counter(count-1)
//inc1 and dec1 are better for logging at least u will get print statements for each increment
    def inc1 = {
      println("incrementing")
      new Counter(count + 1)}

    def dec1 = {
      println("decrementing")
      new Counter(count - 1)}

    def inc(n:Int) = new Counter(count+n)
    def dec(n:Int) = new Counter(count-n)

    //overload inc1 and increment it n times
    def inc1(n:Int):Counter={

      if (n<=0) this
      else inc1.inc1(n-1) //this syntax is imp...it will call first function by "second funct" number of times
    }

    def dec1(n: Int): Counter = {

      if (n <= 0) this
      else dec1.dec1(n - 1)
    }
    println(count)
    def print = println(count)
  }

}