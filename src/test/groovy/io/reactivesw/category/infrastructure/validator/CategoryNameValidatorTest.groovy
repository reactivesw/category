package io.reactivesw.category.infrastructure.validator

import io.reactivesw.category.application.model.CategoryDraft
import io.reactivesw.exception.ParametersException
import io.reactivesw.model.LocalizedString
import spock.lang.Specification

/**
 *
 */
class CategoryNameValidatorTest extends Specification {
    CategoryNameValidator categoryNameValidator = new CategoryNameValidator()

    def "test 1 : category name is null and throw ParametersException"() {
        given:
        CategoryDraft draft = new CategoryDraft()

        when:
        CategoryNameValidator.validateNull(draft)

        then:
        thrown(ParametersException)
    }

    def "test 2 : category name's localized is null and throw ParametersException"() {
        given:
        CategoryDraft draft = new CategoryDraft()
        draft.name = new LocalizedString()

        when:
        CategoryNameValidator.validateNull(draft)

        then:
        thrown(ParametersException)
    }
}
