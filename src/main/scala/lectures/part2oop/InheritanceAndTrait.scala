package lectures.part2oop

object InheritanceAndTrait extends App{
  class Animal{
    val creatureType = "wild"
    def eat = println("nononoj")
    private def eat1 = println("djhgdj")// accessible in this class only
    protected def eat2 = println("meawwwww eaw") // this will be accessible in its child also but not outside
  }
  class Cat extends Animal{

    def crunch = {
      eat2
    }
  }

  val cats = new Cat

  cats.eat //that valid because extending means u can access all non private members
  //cats.eat1 // it will inaccisible as its a private member of parent
  //cats.eat2 //accesible in parent and child only not outside
  cats.crunch
  // scala offers a single class inheritance thats is you can extend only one class at a time

  //constructors

  class Person(name: String, age:Int){
    def this(name: String) = this(name,0)
  }
  class Adult(name:String, age:Int, idCard: String) extends Person(name,age)//while extending make sure u are providing correct parameters to super class
  class Adult1(name:String, age:Int, idCard: String) extends Person(name) //it will also work...u can extend aux constructor also

  //overriding
  class Dog extends Animal{
    // u can override super class members directly in constructor also
    //eg..class Dog(override val creatureType = "Domestic") extends Animal--will work same
    override val creatureType = "Domestic"
    override def eat = {
      super.eat //now eat from parent will be used
      println("jkkkkkkllklllklllklllkl")}
  }
  val dogs = new Dog
  dogs.eat
  println(dogs.creatureType)

  val unknownAnimal:Animal = new Dog
  unknownAnimal.eat //even though unknownAnimal is of type animal but its a instance of Dog in our case...so eat method Dog will be called.

  //super
  /**
   * super is used when u want to use implementation from parent class
   * in our case if u dont want to use eat method from Dog, but u neeed eat method from Animal ...use super
   */

  /**
   * how to prevent the overrides
   * 1..final --use final in front of def/class/member, now it can not be overriden
   * if u put final in front of class..it can not be extended
   * 2..use final on entire class , automatically its memners will become final too
   * 3..seal the class---means extend class in this file only but not in other files
   * to seal us keyword sealed in front of class----sealed class animal{......}
   *
   * if suppose u have only two animals cats and dogs..then make animal class sealed and use it for cats and dogs in this file only..now in other files someone canot do any extra changes
   *
   *
   */
}
