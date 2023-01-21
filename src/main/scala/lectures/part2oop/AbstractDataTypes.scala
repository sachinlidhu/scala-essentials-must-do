package lectures.part2oop

object AbstractDataTypes extends App {
  abstract class Animal{
    //abstract means we dont provide any values or implementation because u want ur sub  classes to do that for u
    val creatureType:String  = "wild"//for this field override keyword is needed in child
    def eat:Unit //here also no implementation given
  }

  /**
   *   abstract class can have both implemented and un implemented fields.
   *   we can not instantiate the abstract class beacuse its fields may not implemented
   *   for instantiation fields must be implemented
   *   Reason..lest say u have instantiated the abstract class somehow but then when u try to access its members it will give error as nothing is implemented there in that field.
   *   if nothing is implemented then why to create its instance
    */

  class Dog extends Animal{
    //override val creatureType:String = "caninie"
    //override def eat:Unit = println("crunch crunch")
    /**
     * overwrite keyword is not needed if u r overwriting from abstract class
     * check below implementation
     */
     override val creatureType: String = "caninie"
     def eat: Unit = println("crunch crunch")  // no need for overwrite
  }

  //traits
  // traits can also have both implemented and unimplemented fields
  trait ColdBloded
  trait Carnivour{
    def eat(animal: Animal):Unit
    val preferredMeal:String = "fresh Meat"
  }
  class Croc extends Animal with Carnivour with ColdBloded {
    /* we can add as many tarits as we wish*/
    override val creatureType: String = "croc"
    def eat:Unit = "nomnomnom"
    def eat(animal: Animal):Unit = println(s"I am $creatureType and I am eating ${animal.creatureType}")
  }
  val dog = new Dog
  val croc = new Croc
  croc.eat(dog)

  //traits vs abstract class
  /**
   * 1...traits do not have constructor parameters (in Scala 3 it have now)
   *    that is u can not pass parameters to traits
   * 2...multiple traits can be inherited by the same class but only one abstract class is extended
   * 3...Imp--traits are behaviour ..note carnivour is trait but animal is abstract class
   */

}
