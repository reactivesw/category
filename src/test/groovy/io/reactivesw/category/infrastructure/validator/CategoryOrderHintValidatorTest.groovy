import io.reactivesw.category.application.model.action.SetOrderHint
import io.reactivesw.category.infrastructure.util.CategoryUtils
import io.reactivesw.category.infrastructure.validator.CategoryOrderHintValidator
import io.reactivesw.exception.ParametersException
import spock.lang.Specification

/**
 * Test for CategoryOrderHintValidator class.
 */
class CategoryOrderHintValidatorTest extends Specification {
    CategoryOrderHintValidator categoryOrderHintValidator = new CategoryOrderHintValidator()

    def "Test1: the previousOrderHint in setOrderHint of category is empty, should throw a parametersException"() {
        given: "prepare data"
        SetOrderHint setOrderHint = new SetOrderHint()
        setOrderHint.previousOrderHint = ""
        setOrderHint.nextOrderHint = CategoryUtils.createOrderHint()

        when: "call function"
        CategoryOrderHintValidator.validateEmptyAndNumeric(setOrderHint)

        then: "should throw parametersException"
        thrown(ParametersException)
    }

    def "Test2: the two orderHint in setOrderHint of category is not number, should throw a parametersException"() {
        given: "prepare data"
        SetOrderHint setOrderHint = new SetOrderHint()
        setOrderHint.previousOrderHint = "this-is-not-a-number"
        setOrderHint.nextOrderHint = CategoryUtils.createOrderHint()

        when: "call function"
        CategoryOrderHintValidator.validateEmptyAndNumeric(setOrderHint)

        then: "should throw a parametersException"
        thrown(ParametersException)
    }

    def "Test3: the two orderHint in setOrderHint of category is number, should be true"() {
        given: "prepare data"
        SetOrderHint setOrderHint = new SetOrderHint()
        setOrderHint.previousOrderHint = CategoryUtils.createOrderHint()
        setOrderHint.nextOrderHint = CategoryUtils.createOrderHint()

        when: "call function"
        CategoryOrderHintValidator.validateEmptyAndNumeric(setOrderHint)

        then: "should be true"
        true
    }

    def "Test4: the previousOrderHint in setOrderHint is number and the nextOrderHint is empty, should be true"() {
        given: "prepare data"
        SetOrderHint setOrderHint = new SetOrderHint()
        setOrderHint.previousOrderHint = CategoryUtils.createOrderHint()
        setOrderHint.nextOrderHint = ""

        when: "call function"
        CategoryOrderHintValidator.validateEmptyAndNumeric(setOrderHint)

        then: "should be true"
        true
    }

}