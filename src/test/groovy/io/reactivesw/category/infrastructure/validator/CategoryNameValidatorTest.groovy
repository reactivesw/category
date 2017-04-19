package io.reactivesw.category.infrastructure.validator

import io.reactivesw.category.application.model.CategoryDraft
import io.reactivesw.exception.ParametersException
import io.reactivesw.model.LocalizedString
import spock.lang.Specification

/**
 * Created by Davis on 17/3/28.
 */
class CategoryNameValidatorTest extends Specification {
    CategoryNameValidator categoryNameValidator = new CategoryNameValidator()

    def "Test1: category name is null, should throw parametersException"() {
        given: "prepare data"
        CategoryDraft draft = new CategoryDraft()

        when: "call function"
        CategoryNameValidator.validateNull(draft)

        then: "should throw parameterException"
        thrown(ParametersException)
    }

    def "Test2: category name's localized is null, should throw ParametersException"() {
        given: "prepare data"
        CategoryDraft draft = new CategoryDraft()
        draft.name = new LocalizedString()

        when: "call function"
        CategoryNameValidator.validateNull(draft)

        then: "should throw parameterException"
        thrown(ParametersException)
    }

    def "Test3: the content of localized  in category name is empty, should throw parametersException"() {
        given: "prepare data"
        CategoryDraft categoryDraft = new CategoryDraft()
        categoryDraft.name = new LocalizedString()
        categoryDraft.name.addKeyValue("en", "")

        when: "call function"
        CategoryNameValidator.validateNull(categoryDraft)

        then: "should throw parameterException"
        thrown(ParametersException)
    }

    def "Test4: the language of localized in category name is empty, should throw parametersException"() {
        given: "prepare data"
        CategoryDraft categoryDraft = new CategoryDraft()
        categoryDraft.name = new LocalizedString()
        categoryDraft.name.addKeyValue("", "this  is content")

        when: "call function"
        CategoryNameValidator.validateNull(categoryDraft)

        then: "should throw parametersException"
        thrown(ParametersException)
    }
}
