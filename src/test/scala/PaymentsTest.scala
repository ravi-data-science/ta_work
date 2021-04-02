import collection.mutable.Stack
import org.scalatest.flatspec.AnyFlatSpec

class PaymentsTest extends AnyFlatSpec {

  "Payments Is Not An empty List" should "Have size Greater Than 0" in {
    assert(Payments.getPayments.size > 0)
  }

  it should "Payments getPayments List throw an IndexOutOfBoundsException when trying to access any element outside of the Size" in {
    val sizeOfPaymentsList = Payments.getPayments().size
    assertThrows[IndexOutOfBoundsException] {
      Payments.getPayments()(sizeOfPaymentsList+1)
    }
  }
}
