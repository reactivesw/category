package io.reactivesw.category.infrastructure.validator

import io.reactivesw.category.domain.model.Category
import io.reactivesw.exception.ConflictException
import spock.lang.Specification

/**
 * Created by Davis on 17/3/28.
 */
class CategoryVersionValidatorTest extends Specification {
    CategoryVersionValidator validator = new CategoryVersionValidator()

    def "test 1 : entity's version and input version is not match and throw ConflictException"() {
        given:
        Category entity = new Category(version: 1)
        Integer version = 2

        when:
        CategoryVersionValidator.validate(entity, version)

        then:
        thrown(ConflictException)
    }

    def "test 2 : entity's version and input version is match and expect true"() {
        given:
        Integer version = 1
        Category entity = new Category(version: version)

        when:
        CategoryVersionValidator.validate(entity, version)

        then:
        true
    }
}
