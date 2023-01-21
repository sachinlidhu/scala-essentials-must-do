package lectures.part1basics

object CBNvsCBV extends App {
  def callByValue(x:Long)={
    println("by value: " + x) //here suppose value of system.nanotime is 5, the that 5 will be considered
    println("by value: " + x) // again that 5 will taken not system.nanotime
  }
  def callByName(x: =>Long) ={
    println("by name: "+ x) // x will be treated as system.nanotime
    println("by name: " + x) //x will be treated as  system.nanotime so x will give current value of system.nanotime
  }
  callByValue(System.nanoTime())
  callByName(System.nanoTime())
  //also call by name delays the function call untill its not used . eg check below

  def infinite():Int = 1+infinite()
  def printFirst(x:Int,y: =>Int) = println(x)

  //printFirst(infinite(),34) // this will crash stackoverflow issue
  printFirst(34,infinite()) //this will print 34 and will not check into other parameter as its not being used
}
