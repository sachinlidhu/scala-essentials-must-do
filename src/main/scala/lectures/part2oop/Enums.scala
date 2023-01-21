/*package lectures.part2oop

//import java.security.Permissions

object Enums extends App{
  // in scala 3 we have first class support for enums
  enum Permissions{
    //with keyword case we are declaring only possible instances of the our enum
    //in our case we have declared 4 possible instances
    //due to enum can not be extended beacuse we have alreday declared its only possible instances
    case READ,WRITE,EXECUTE,NONE
    //we can add fields/methods to enums
    def openDocument():Unit = {
      if(this == READ) println("opening documner")
      else println("reading not allowed")
    }
  }
val somePermissions:Permissions = Permissions.read //this is how we can use its instance
  println(somePermissions.openDocument)

  //we can create enums with constructor args also
  enum PermissionsWithBits(bits: Int){

    case READ extends PermissionsWithBits(4)
    case WRITE extends PermissionsWithBits(2)
    case EXECUTE extends PermissionsWithBits(1)
    case NONE extends PermissionsWithBits(0)
  }
  //we can also create singleton objects for enums
  object PermissionsWithBits{
    def fromBits(bits:Int): PermissionsWithBits = PermissionsWithBits.NONE
  }
  //standard API
  val somepermissionsordinal = somePermissions.ordinal
  val allpermisions = PermissionsWithBits.values //array of all the possible values of enum
  val readpermission = Permissions.valueOf("READ")//PERMISSION.READ

}
*/