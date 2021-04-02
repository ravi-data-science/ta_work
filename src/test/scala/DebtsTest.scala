import collection.mutable.Stack
import org.scalatest.flatspec.AnyFlatSpec

class DebtsTest extends AnyFlatSpec {

  "Debts Is Not An empty List" should "Have size Greater Than 0" in {
    assert(Debts.getDebts.size > 0)
  }

  it should "Debts getDebts List throw an IndexOutOfBoundsException when trying to access any element outside of the Size" in {
    val sizeOfDebtsList = Debts.getDebts.size
    assertThrows[IndexOutOfBoundsException] {
      Debts.getDebts()(sizeOfDebtsList+1)
    }
  }
}
