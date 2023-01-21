package lectures.part1basics

object DefaultArgs extends App {

  def trfact(n:Int,acc:Int):Int = {
    if(n<=1) acc
    else trfact(n-1,n*acc)
  }
  val fact =trfact(10,1)

  //here in above function while calling the function we have to 1 as a acc value every time
  //So in that cases where you need to pass same value again and again as a parameter, then u can that value as a default para,eter
  // default parameter will act as a implicit and u need not pass that explicitly
  //below the example

  def trfact1(n: Int, acc: Int =1): Int = {
    if (n <= 1) acc
    else trfact1(n - 1, n * acc)
  }

  val fact1 = trfact1(9)
  //val fact2 = trfact1(9,2) // in this case acc =2 will be given preference over 1

  def savePicture(format:String, width:Int, height:Int)= println("saving picture")
  //savePicture("jpg",500,800)

  def savePicture1(format: String="jpg", width: Int, height: Int) = println("saving picture")
  //savePicture1(500, 800) //here 500 will be treated as first parameter

  // to tackle this limitation we have to specify the parameter name in fun call also ex;

  def savePicture2(format: String="jpg", width: Int=1920, height: Int=1080) = println("saving picture")
  savePicture2(width= 500)

}
