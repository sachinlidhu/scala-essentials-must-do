package exercises

abstract class /*MyList*/ MyList[+A] {
  /**
   * this list will be singly Linkedlist which holds integers and then will have following methods
   * 1..head ..it will return first element of the list
   * 2..tail...it will return all elements except first
   * 3..isEmpty..it will retuen boolean ---if this list empty or not
   * add(int)..return new list with this element added
   * override special method toString which will return string representation of the list
   */
/*
  def head: Int
  def tail: MyList
  def isEmpty: Boolean
  def add(element:Int): MyList
  def printElements:String
  override def toString: String = "[" + printElements + "]"
  */
  //converting above to generic
  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyList[B]

  def printElements: String

  override def toString: String = "[" + printElements + "]"
  //def map[B](transformer: MyTransformer[A, B]): MyList[B]
  def map[B](transformer: A => B): MyList[B]
 // def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
 // def filter(predicate: MyPredicate[A]):MyList[A]
  //--------below are the higher oredr functions---------------------
  //higher order functions either receive functions as a parameter or returns a functions
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]
  def ++[B>:A](list: MyList[B]):MyList[B]

  //HOFs
  def foreach(f: A => Unit):Unit
  def sort(f: (A,A) =>Int): MyList[A]
  def zipWith[B,C](list: MyList[B],zip:(A,B)=>C) : MyList[C]
  def fold[B](start:B)(operator: (B,A)=>B):B



}
//////////////////////////
/*object Empty extends MyList{
  def head: Int = throw new NoSuchElementException()
  def tail: MyList = throw new NoSuchElementException()
  def isEmpty: Boolean = true
  def add(element: Int): MyList = new Cons(element,Empty)
  def printElements:String = ""
}*/
// CONVERTING EMPTY TO GENERIC
case object Empty extends MyList[Nothing]{
  def head: Nothing = throw new NoSuchElementException()
  def tail: MyList[Nothing] = throw new NoSuchElementException()
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element,Empty)
  def printElements:String = ""

 //def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty

 //def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty

 //def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty
 def map[B](transformer: Nothing => B): MyList[B] = Empty

  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty

  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty
  def ++[B >: Nothing](list:MyList[B]):MyList[B] = list

  def foreach(f: Nothing => Unit):Unit = ()

  def sort(compare:(Nothing,Nothing)=>Int) = Empty

  def zipWith[B,C](list:MyList[B], zip:(Nothing,B)=>C):MyList[C] ={
    if (!list.isEmpty) throw new RuntimeException("lists donot have same lenght")
    else Empty
  }
  def fold[B](start:B)(opeartor:(B,Nothing)=>B):B = start
}

//cons is non empty list
/*
class Cons(h:Int, t:MyList) extends MyList{
  def head: Int = h
  def tail: MyList = t
  def isEmpty: Boolean = false
  def add(element: Int): MyList = new Cons(element,this)
  def printElements:String =
    if(t.isEmpty) "" + h
    else h + " " + t.printElements
}*/
//--CONVERTING CONS TO GENERICS
case class Cons[+A](h:A, t:MyList[A]) extends MyList[A]{
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >:A](element: B): MyList[B] = new Cons(element,this)
  def printElements:String =
    if(t.isEmpty) "" + h
    else h + " " + t.printElements

  /*def filter(predicate: MyPredicate[A]): MyList[A] =
    if(predicate.test(h)) new Cons(h,t.filter(predicate))
    else t.filter(predicate) */

  def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)



  /*def map[B](transformer: MyTransformer[A, B]): MyList[B] =
    new Cons(transformer.transform(h),t.map(transformer))*/

  def map[B](transformer: A => B): MyList[B] =
    new Cons(transformer(h), t.map(transformer))


  def ++[B >:A](list :MyList[B]):MyList[B] = new Cons(h, t ++ list)
  /*def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
    transformer.transform(h) ++ t.flatMap(transformer)*/

  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

  //HOFs
  def foreach(f: A =>Unit):Unit ={
    f(h)
    t.foreach(f)
  }

  def sort(compare: (A,A)=> Int):MyList[A] = {
    //lets use the insertion sort as its easier to implement
    def insert(x:A, sortedList:MyList[A]):MyList[A] = {
      if (sortedList.isEmpty) new Cons(x,Empty)
      else if (compare(x,sortedList.head) <=0) new Cons(x,sortedList)
      else new Cons(sortedList.head,insert(x, sortedList.tail))
    }

    val sortedTail = t.sort(compare)
    insert(h,sortedTail)
  }
  def zipWith[B,C](list:MyList[B],zip:(A,B)=>C):MyList[C] = {
    if (list.isEmpty) throw new RuntimeException("lists dont have same length")
    else new Cons(zip(h,list.head),t.zipWith(list.tail,zip))

  }

  /*
   * [1,2,3].fold(0)(+)=
   * [2,3].fold(1)(+)=
   * [3].fold(3)(+)=
   * [].fold(6)(+)=
   * 6
   *
   * @param start
   * @param operator
   * @tparam B
   * @return
   */
  def fold[B](start:B)(operator:(B,A)=>B):B = {
    t.fold(operator(start,h))(operator)
  }
}

/*trait MyPredicate[-T]{
  def test(element:T):Boolean
}*/
//------- MyPredicate is actually a function type from T => Boolean
/*trait MyTransformer[-A, B] {
  def transform(element:A):B
}*/
//----------MyTransformer is actually a function type from A =>B

object ListTest extends App {
  val list = new Cons(1,Empty)
  println(list.head)
  val list2 = new Cons(1,new Cons(2,new Cons(3,Empty)))
  println(list2.tail)
  println(list2.tail.head)
  println(list2.add(4).head)
  println(list2.isEmpty)

  println(list2.toString)

  val listOfIntegers: MyList[Int] = new Cons(1,new Cons(2,new Cons(3,Empty)))
  val anotherlistOfIntegers: MyList[Int] = new Cons(4,new Cons(5,new Cons(6,Empty)))
  val listOfStrings: MyList[String] = new Cons("Hello",new Cons("scala",new Cons("meaw",Empty)))


  println(listOfIntegers.toString)
  println(listOfStrings.toString)
  /*println(listOfIntegers.map(new MyTransformer[Int,Int] {
    override def transform(element: Int): Int = element*2
  }).toString)
  println(listOfIntegers.filter(new MyPredicate[Int] {
    override def test(element: Int): Boolean = element % 2 == 0
  }).toString)
  println(listOfIntegers ++ anotherlistOfIntegers).toString
  println(listOfIntegers.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transform(elem:Int):MyList[Int] = new Cons[Int](elem, new Cons(elem + 1, Empty))
  }))*/
  //now change all above methods to better functinal way
  /*
  println(listOfIntegers.map(new Function1[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }).toString)
  println(listOfIntegers.filter(new Function1[Int,Boolean] {
    override def apply(element: Int): Boolean = element % 2 == 0
  }).toString)
  println(listOfIntegers ++ anotherlistOfIntegers).toString
  println(listOfIntegers.flatMap(new Function1[Int, MyList[Int]] {
    override def apply(elem: Int): MyList[Int] = new Cons[Int](elem, new Cons(elem + 1, Empty))
  }))
  */
  //Now change all the above methods to shorter syntax (lambdas)
  /*println(listOfIntegers.map(
    element => element * 2
  ).toString)
  println(listOfIntegers.filter(
    element => element % 2 == 0
  ).toString)
  println(listOfIntegers ++ anotherlistOfIntegers).toString
  println(listOfIntegers.flatMap(
    elem => new Cons(elem, new Cons(elem + 1, Empty))
  ))
  */
  //can we make it even shorter...yes using under scores
  println(listOfIntegers.map(
    _ * 2
  ).toString)
  println(listOfIntegers.filter(
    _ % 2 == 0
  ).toString)
  println(listOfIntegers ++ anotherlistOfIntegers).toString

  println(listOfIntegers.flatMap(
    elem => new Cons(elem, new Cons(elem + 1, Empty)) // here underscore wont because elem is usd 2 times and we know that each underscore is used for different parameter
  ))

  listOfIntegers.foreach(println)
  println(listOfIntegers.sort((x,y)=>y-x))

  println(anotherlistOfIntegers.zipWith[String,String](listOfStrings, _ + "-" + _))

  println(listOfIntegers.fold(0)(_+_))

  //lets check if "for comprehension" is suppoerted here

  val combination = for {
    n <- listOfIntegers
    string <- listOfStrings
  }yield n+ " "+string
  println(combination)

}