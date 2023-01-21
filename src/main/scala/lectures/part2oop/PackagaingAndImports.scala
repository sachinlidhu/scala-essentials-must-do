package lectures.part2oop

import lectures.part2oop.OObasics.Writer
import playground.{PrinceCharming,Cyndrealla}
//---we can also do name alias
//import playground.{PrinceCharming,Cyndrealla => lol}
//---above alias feature is useful if u need to import more than 1 class with same name from different packages
import java.util.Date
import java.sql.{Date => sqlDate}
//---to import everything use below
//import playground._

object PackagaingAndImports extends App{
  // whatever we are writing here will be saved to our packaage
  //package members are accesible by their simple name that is field from one file can be used in another, u just need to import tahta package
  val writer =  new Writer("daniel","rockthejvm",2018) //This writer class already defined in OOBasics class
  /*-----important---------------
  if you dont want to import particular package
  where ur class is defined but still
  u want to access it then for that use packagename.class

   val princess = new Playground.ourClassName ---------note--use this if u dont want import
  */
  //if you want define some functions or values which can be used anywhere within same package then create package object and define there.
  //package object is only one per package

  //val princess = new Cyndrealla
  //val princess = new lol ------now u can replace Cyndrealla with lol----------check imports
  //val prince = new PrinceCharming


  val date = new Date //this will pick date from first import
  //one option to use date from desired package is below
  //val date  = new java.sql.Date(2018,5,4)
  //other option is to use aliasing--check in imports
  //val date = new sqlDate

}
