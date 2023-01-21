package lectures.part3fp

object MapFlatmapFilterFor extends App{
  val list = List(1,2,3)
  println(list)
  println(list.head)
  println(list.tail)

  //map
  println(list.map(_+1))
  println(list.map(_ + "--i have passed string as a argument"))

  //flatMap
  println(list.flatMap(_ + "1"))
  println(list.flatMap(_ + "--i have passed string as a argument"))

  val toPair = (x:Int)=> List(x,x+1)
  println(list.flatMap(toPair))

  val toPair1 = (x: Int) => List(x, x + 1) //it has to be list or some single value...map/flat wont accept tuples etc
  println(list.flatMap(toPair1))

  //filter
  println(list.filter(_%2 == 0))
  println(list.filterNot(_%2 == 0))
  println(list.filter(_%2 != 0))

  //EXERCISE-- Print all combinations between two lists
  /*eg--val numbers = list(1,2,3,4)
        val chars = list("a","b","c","d")
        ans should be list("a1","a2"...."d4")
   *
   */
  val numbers = List(1,2,3,4)
  val chars = List("a",'b',"c","d")
  val colours = List("red","yellow","green")

  println(chars.flatMap(x => numbers.map(y => "" + y + x + " "))) // only y+x wont work as it will consider it math addition

  println(numbers.flatMap(x => chars.flatMap(y => colours.map(z => " "+x+"--"+y+"--"+z))))
  //the same above thing can be written using for comprehensions
  val forComprehensions = for{
    x <- numbers
    y <- chars
    z <- colours
  }yield(" "+x+"--"+y+"--"+z)
  println(forComprehensions)
  //this forComprehension is internally converted to our flatMap,map logic

  //println(chars.map(x => numbers.map(y => "" + y + x + " ")))
  //println(chars.map(x => numbers.flatMap(y => "" + y + x + " ")))
  /*
   *IMPOERTANT
   * if u have 2 loops replace it with flatmap then map
   * if u have 3 loops replace it with flatmap then flatmap then map
   *
   */


  //foreach will print each element of list in seperate line
  list.foreach(println)

  println(numbers.filter(_%2 == 0).flatMap(x => chars.flatMap(y => colours.map(z=> "-"+x+y+z))))

  val alternative1 = for {
    x <- numbers if(x%2 == 0)
    y <- chars
    z <- colours
  }yield ("-"+x+y+z)

  println(alternative1)


  println(list.map(x=>x*2))
  //alternative syntax for map is below:-------imp--------
  val a = list.map { x =>
    x*2
  }
  println(a)//------------remeber this syntax

  /*
   *EXERCISE
   * Check whether MyList supports for comprehensions
   * ---for that to work we should have folloeing signatures to our map, flatMap and filter
   * map(f:A=>B) => MyList[B]
   * filter(p: A=> Boolean) => MyList[A]
   * flatMap(f: A=>Mylist[B]) => MyList[B]
   * Create A small collections of atmost one element---eg--Maybe[+T]
   * --implement map,flatMap,filter for this collection
   *
   */
}
