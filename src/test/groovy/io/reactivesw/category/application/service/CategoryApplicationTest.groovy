package io.reactivesw.category.application.service

import com.google.common.collect.Lists
import io.reactivesw.category.domain.service.CategoryService
import io.reactivesw.category.domain.service.EventMessageService
import spock.lang.Specification

/**
 * Test for CategoryApplication class.
 */
class CategoryApplicationTest extends Specification {
    CategoryService categoryService = Mock()
    EventMessageService eventService = Mock()
    CategoryApplication application = new CategoryApplication(categoryService, eventService)

    def categoryId = "categoryId"
    def version = 1

    def "Test1: delete category"() {
        given:
        categoryService.deleteCategory(_, _) >> Lists.newArrayList(categoryId)

        when:
        application.deleteCategory(categoryId, version)

        then:
        true
    }
}
