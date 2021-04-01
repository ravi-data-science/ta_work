
//import org.scalatest.FunSuite

//class TA_Work1_Test extends FunSuite {
////  test("CubeCalculator.cube") {
////    assert(CubeCalculator.cube(3) === 27)
////  }
//}

import collection.mutable.Stack
import org.scalatest._
import flatspec._
import matchers._

class Example_Test extends AnyFlatSpec with should.Matchers {

  "A Stack" should "pop values in last-in-first-out order" in {
    val stack = new Stack[Int]
    stack.push(1)
    stack.push(2)
    stack.pop() should be (2)
    stack.pop() should be (1)
  }

  it should "throw NoSuchElementException if an empty stack is popped" in {
    val emptyStack = new Stack[Int]
    a [NoSuchElementException] should be thrownBy {
      emptyStack.pop()
    }
  }
}