package lectures.part2oop

object CaseClasses extends App {

  case class Person(name:String, age:Int)
  //1..all parameters are fields
  val jim = new Person("jim",43)
  println(jim.name) //in normal class parameter name has to be val to become field and to to get accessed

  //2..sensible toString for piece of debugging
  println(jim.toString) //in normal class it will give dummy result
  println(jim)//this is equivalent to above one----println(instance) is equivalent to println(instance.toString)

//3..equals and hascode implemented out of the box which makes case classes important in collections use
  val jim2 = new Person("jim",43)
  println(jim == jim2) // in normal class it would return false as it treats  both as seperate instances
  //so this equals is alreday present in case class but in  ormal class it used to pick it from references

  //4..case classes have handy copy methods
  val jim3 = jim.copy() //copy creates new instance of jim
  println(jim3)
  //copy method also receives named parameters
  val jim4 = jim.copy(age = 16) //it will instantiate and will change the age
  println(jim4)

  //5..case classes have companion objects
  val thePerson = Person //this means Person is companion object of case class....note we have not provided parameters to Person
  //now we can make use of object's factory methods like apply()
  val mary = Person("marry",33) //here we call Person's apply methods with 2 arguments
  //the above code looks same to that of class instantiaation or constructor
  //thats why we say no need to use new keyword.....actually we are calling apply() of object which is doing same thing

  //6..case classes are serializable
  //serialization is important when dealing with distributrd systems to send data over network
  //useful while using Akka...akka deals with sending serializable meesage over networks and those meaasge are case classes


  //7..case classes have extractor patterns..this means case classes can be used in patteren mattching

  // we have case objects also..which are similar to case class except they are not instantiated and dont have parameters

  case object UnitedKingdom {
    def name:String = "the uk is lil"
  }

  /*
   * EXERCISE
   * to expand mylist to use case class and case object
   *
   */

}
