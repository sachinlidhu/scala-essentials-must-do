package lectures.part1basics

//extends App will make the application runnable in intelliJ
object expressions extends App {
  val x =1+2 //Expression
  println(x)
  println(2+3*4)
  println(1==x)
  println(!(1==x))
  var a =2
  a+= 4 //-=, *= works only with vars...these are all side effects
  println(a)

  // Instructions (do) vs Expressions (value)

  // "if" is a expression because it returns a value

  val aCondition = true
  val aConditionValue = if (aCondition) 5 else 3
  println(aConditionValue)
  println(if(aCondition) 5 else 3)

  // avoid using loops in scala
  // loops are not good for functional programing
  var i =0
  while (i<10){
    println(s"${i}...")
    println(i,"...")// println can also return tuple
    i+=1
  }
// loop is an instruction but scala need everything to be in expression
  // while loop also returns unit, which is a side effect

  val aWiredValue =  (a = 3) //a is var defined above
  println(aWiredValue) // it returns unit , empty brackets which is a side effect
  //some side effects are println,whiles, reassigning

  //code blocks
  // code block returns the last line as value so its a expression
   val aCodeBlock = {
     val y =2
     val z =y+1
     if (z>2) "hello" else "goodbye"
   }

  //Qustion1--difference between "hello world" and println("hello world")---ans first one is string other one returns Unit
  //Question2-- what is value of
    /*  val someValue = {
        2<3
  }
    val someOtherValue ={
      if (someValue) 239 else 986
      42
    }
    answer is-- at last INt will be returned and value 42*/
}
