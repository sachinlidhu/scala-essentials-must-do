package lectures.part2oop

object AnnonymousClass extends App {

  abstract class Animal{
    def eat: Unit
  }

  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("ahahahahahahahahah")
  }
  //but how can we instantiante a abstract class..shocker!!!
  //actually when we did new animal, it will treat entire bolck as a new class and assign random name to it.
  //in short it will create a anonymous function and instantiate that one
  //below is the keyword to check that

  println(funnyAnimal.getClass) //it will give u the class name which is instantiated in real.

  /*
   * what compiler internally did is
   * class AnnonymousClass$$anon$1 extends Animal{
   *  override def eat: Unit = println("ahahahahahahahahah")
   * }
   * val funnyAnimal: Animal = new AnnonymousClass$
   */

  //Example 2--anonymous class
  class Person(name:String){
    def sayHi: Unit = println(s"My name is $name, how can i help")
  }
  val jim = new Person("jim"){
    //pass proper argument othewise some dummy class will be instantiated
   override  def sayHi: Unit = println(s"My name is jim, how can i help")
  }
  // with annomouys class, we learn that we can instantiate types and overrides methods on the spot

  /*
   *Exercise
   * extend MyList functionality
   * create a generic trait MyPredicate[-T] and it will have a condition that whether the value of type T passes the condition
   * it will have a method test(T)=>boolean
   * and every sb class will have its method to check that T value condition
   * create generic trait My transformer[-A,B] and it will have  a small method to convert the value from type A to type B
   * every sub class will have different implementation fo that
   * Implement the following functions on MyList
   *  ...map which takes MyTransformer and returns MyList of differnet type
   *  ...filter which takes MyPredicate and returns MyList
   *  ...flatMap which takes MyTransformer from A to MyList[B] and returns MyList[B]
   *
   */
}
