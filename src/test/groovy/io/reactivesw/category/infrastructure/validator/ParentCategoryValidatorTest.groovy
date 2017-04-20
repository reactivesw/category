package io.reactivesw.category.infrastructure.validator

import io.reactivesw.category.domain.model.Category
import io.reactivesw.exception.NotExistException
import spock.lang.Specification

/**
 *
 */
class ParentCategoryValidatorTest extends Specification {
    ParentCategoryValidator validator = new ParentCategoryValidator()

    def "test 1 : category parent is not null and expect true"() {
        given:
        Category entity = new Category()
        def parentId = "11111111"

        when:
        ParentCategoryValidator.validate(parentId, entity)

        then:
        true
    }

    def "test 2 : category parent is null and throw NotExistException"() {
        given:
        Category entity = null
        def parentId = "11111111"

        when:
        ParentCategoryValidator.validate(parentId, entity)

        then:
        thrown(NotExistException)
    }


}
