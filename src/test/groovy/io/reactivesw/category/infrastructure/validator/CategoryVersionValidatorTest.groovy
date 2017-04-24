package io.reactivesw.category.infrastructure.validator

import io.reactivesw.category.domain.model.Category
import io.reactivesw.exception.ConflictException
import spock.lang.Specification

/**
 * Test for CategoryVersionValidator.
 */
class CategoryVersionValidatorTest extends Specification {
    CategoryVersionValidator validator = new CategoryVersionValidator()

    def "Test1: entity's version and input version is not match should throw a ConflictException"() {
        given: "prepare data"
        Category entity = new Category(version: 1)
        Integer version = 2

        when: "call function"
        CategoryVersionValidator.validate(entity, version)

        then: "should throw a conflictException"
        thrown(ConflictException)
    }

    def "Test2: entity's version and input version is match should be true"() {
        given: "prepare data"
        Integer version = 1
        Category entity = new Category(version: version)

        when: "call function"
        CategoryVersionValidator.validate(entity, version)

        then: "should be true"
        true
    }
}
