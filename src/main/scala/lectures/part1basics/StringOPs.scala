package lectures.part1basics

object StringOPs extends App{

  val str:String = "Hello, I am learning scala"
  println(str.charAt(2))
  println(str.substring(7,11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ","-->"))
  println(str.toUpperCase())
  println(str.length)

  val aNumberString = "45"
  val aNumber = aNumberString.toInt
  println("a" + aNumber + "z")
  println("a" + aNumberString + "z")
  println('a' + aNumber + 'z')
  println('a' + aNumberString + 'z')
  println('a' +: aNumberString :+ 'z')//+: is prepend operator and other one is append operator
  println(str.reverse)
  println(str.take(2))

  //string interpolation
  //s-interpolation
  val name = "David"
  val age = 12
  val greeting= s"Hello, my name is $name and I am $age years old"
  val anothergreeting= s"Hello, my name is $name and I will br turning ${age+1} years old" //in s interpolation you can do computaion on variable also in curly braces
  println(anothergreeting)

  //f-interpolation
  val speed = 1.2f
  val myth = f"$name can eat $speed%9.3f burgers" //in f interpolation you can u can do fomating to variable also
  println(myth)

  //raw-interpolated

  println(raw"this is a \n newline")
  val a = "this is a \n newline"
  println(raw"$a") // when u use val then put it in raw, that time only u can see effect \n
}
