package lectures.part2oop

object Generics extends App {
  /*class MyList[A]{

  }*/
  //lets see how bounded types solve varaianc problem
  class MyList[+A]{
//use the type  A
    //def add(element:A):MyList[A]= ??? ---instead of this us below
    def add[B >: A](element: B): MyList[B]= ???

    /**
     * in our case lets say
     * A is Cat, B is Dog which i n particular is an animal
     * so u can say B is an animal
     */
  }

  class MyMap[key, value]

  val listOfIntegers = new MyList[Int] //this Int will replace the type A in calling
  val listOfStrings = new MyList[String] //THIS WILL REPLACE THE TYPE WITH STRING

  //generic methods
  object MyList{
    def empty[A]:MyList[A] = ???
  }
  val emptyListOfIntegers = MyList.empty[Int]

  //variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  //question is if cat extends animal then can List[Cat] extends List[Animal]


  // answer1 is... yes and its called covariance

  class CovariantList[+A]
  val animal:Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
 // val animalList: CovariantList[Cat] = new CovariantList[Animal]//this will not work as its +A...means u can treat all CATS animals but not vicevera(child can be assigned to parent)

  //now can i add animals to this animalList because its a list of animal
  // can i do animalList.add(new Dog)---answer to this is we return list of animals

  //answer 2...No and its called INVARIANCE
  class InvariantList[A]
  val invariantAnimalList:InvariantList[Animal] = new InvariantList[Animal]
  //val invariantAnimalList1:InvariantList[Cat] = new InvariantList[Animal] // here its A not +A, so lhs and rhs must be same

  //answer 3... No and its called Contravariance
  class ContravariantList[-A]
  val contravariantAnimalList:ContravariantList[Animal]= new ContravariantList[Animal]
  //val contravariantAnimalList1:ContravariantList[Animal]= new ContravariantList[Cat]
  val contravariantAnimalList2:ContravariantList[Cat]= new ContravariantList[Animal] //it works but it does not make sense how can u treat all animal as a cat....some animals may be dog etc

  //but suppose our logic is as below
  class Trainer[-A]
  val trainer:Trainer[Cat] = new Trainer[Animal] //here it makes sense as trainer of animals can train cat ..that is trainer of animals acn be trated as trainer of cats

  //bounded types
  class Cage[A <: Animal](animal: A) //it mean it accepts the types which are child of Animal class
  val cage = new Cage(new Dog)

  //class Cage[A >: Animal](animal: A) //similarly we have >: means it accepts super types of Animal class only

  /**
   * Bounded types solve variance problem
   *
   */
   // exercise ---expand mylist to be generic
}
