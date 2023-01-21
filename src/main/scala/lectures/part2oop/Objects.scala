package lectures.part2oop

//in scala instance of class is not an object...object is different here

object Objects extends App{
//---in java
  /**
   * public static void main(String args[]){
   *  System.out.println(Person.N_eyes); --in java we access N_eyes directly through class Person(not its instance)...this is called class level functionality
   * }
   * class Person{
   *  public static final int N_eyes = 2 ;
   * }
   */
  // in scala we can access it either using instance of class or directly with object , so it makes scala more OOP
  //so Scala does not have class level functionality (static)
  //as a alternative to this scala provide singlrton objects where u can access it using person name directly

  // in scala objects can be defined in the same way as that of class but objects dont have parameters
  //scala object is a singletopn instance means its of type lets say Person and its also its own instance , no need to create seperate instance
  object Person{
    //static/class level functionaity
    val N_eyes=2
    def canFly:Boolean = false
    //often we have factory methods in singlton objects
    def from(mother:Person, father:Person):Person = new Person("bobbie")
    //generally we name these factory methods as apply...so use apply instead of from
    def apply(mother:Person, father:Person):Person = new Person("bobbie")
  }
  class Person(val name: String){
    // this is to seperate instance level functionality from static/class level functionailty

  }
  // this pattern of writting class and object with same name is called companions
  /**
   * what we achieve here with companions is that the whole code which will either reside in
   * class (accessed by instance) or singleton object (accessed by object)
   * it means code will be accesed by some kind of instance either class instance or singletopn instance
   * so its more OOP than java as in java u can directly access with class name
   */
  println(Person.N_eyes)
  println(Person.canFly)

  //scala object is a singletopn instance means its of type lets say Person and its also its own instance , no need to create seperate instance using new keyword
  val mary = Person //no need to use new
  val john = Person //both mary and john are of same instance
  println(mary == john) //this will return true..but if we had created using new keyword for class then it will retuen false as new keywod will give fresh new instance
  //mary and john points to same instance here above
  val mary1 = new Person("Mary")
  val john1 = new Person("john")
  println(mary1 == john1)

  val bobbie1 = Person.from(mary1,john1)
  //val bobbie = Person.apply(mary1,john1)--below is better syntax
  //or
  val bobbie = Person(mary1,john1)
}
