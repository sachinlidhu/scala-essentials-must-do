package lectures.part3fp

/*
 *Sequence supports various operations
 * apply,iterator,lenth,reverse for indexing and iterarting
 * concatination, appending, prepending
 * and lots of others like--grouping,sorting, zipping, searching, slicing
 *
 */
object Sequences extends App{
  val aSequence = Seq(1,2,3,4)
  println(aSequence) //in output u will seee it returns list
  println(aSequence.reverse)
  println(aSequence(2)) //it means aSequence.apply(2)---it will get elemenet at index 2
  println(aSequence ++ Seq(7,6,5))
  println(aSequence.sorted)

  //Range
  val aRange : Seq[Int] = 1 to 10
  val aRange1 : Seq[Int] = 1 until 10
  println(aRange)
  aRange.foreach(println)

  (1 to 10).map(x => println("hello"))
  //above one is very handy...if u want to print hello 10 times and dont want to use recurssion then use range

  //Lists
  val aList = List(4,3,2,1)
  val prepended = 42 :: aList //it will prepend the value
  val prepended1 = 41 +: aList //it will also do prepending
  println(prepended)
  val append = aList :+ 43 //APPEND

  //if u want to create list of 5 apples lets say....use fill method
  val apples5 = List.fill(5)("apples")
  println(apples5)

  //to seperate elements of list with some seperator use mkstring method
  println(aList.mkString("  ***|***  "))

  //ARRAYS
  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[Int](3) //this allocates spaces for 3 Int elements in advance
  println(threeElements)
  threeElements.foreach(println) //u will seee some default values are given

  //mutation
  numbers(2) = 0 //now it will replace value at index 2 with newer one---numbers.update(2,0)--its similar to apply
  println(numbers) // note we cant print array elements directly
  println(numbers.mkString(" ")) //doubt why for arrays to print we use mkstring

  //connection between arrays and sequence
  val numberSeq:Seq[Int] = numbers //here "implicit conversion" will happen
  println(numberSeq) //u will see it will be of type ArraySeq

  //vectors
  val vector:Vector[Int] = Vector(1,2,3)
  println(vector)
}
