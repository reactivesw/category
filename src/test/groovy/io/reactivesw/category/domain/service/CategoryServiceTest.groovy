package io.reactivesw.category.domain.service

import com.google.common.collect.Lists
import io.reactivesw.category.application.model.CategoryDraft
import io.reactivesw.category.application.model.QueryConditions
import io.reactivesw.category.application.model.action.SetName
import io.reactivesw.category.application.model.action.SetSlug
import io.reactivesw.category.domain.model.Category
import io.reactivesw.category.domain.model.LocalizedStringValue
import io.reactivesw.category.infrastructure.repository.CategoryRepository
import io.reactivesw.category.infrastructure.update.UpdateAction
import io.reactivesw.category.infrastructure.update.UpdaterService
import io.reactivesw.category.infrastructure.util.ReferenceTypes
import io.reactivesw.exception.AlreadyExistException
import io.reactivesw.exception.ConflictException
import io.reactivesw.exception.NotExistException
import io.reactivesw.model.LocalizedString
import io.reactivesw.model.Reference
import org.springframework.dao.DataIntegrityViolationException
import spock.lang.Specification

/**
 *
 */
class CategoryServiceTest extends Specification {
    UpdaterService categoryUpdateService = Mock()
    CategoryRepository categoryRepository = Mock(CategoryRepository);
    CategoryService categoryService = new CategoryService(categoryRepository, categoryUpdateService);
    def categoryEntity = new Category()
    def id = "11111111"
    def version = 1
    CategoryDraft categoryDraft = new CategoryDraft()

    def setup() {
        LocalizedString name = new LocalizedString()
        name.addKeyValue("en", "cup")
        name.addKeyValue("zn", "杯子")
        categoryDraft.name = name

        categoryEntity.id = id
        categoryEntity.parent = "000000000000"
    }

    def "test 1.1 : query Category by id and get entity"() {
        given:
        categoryRepository.findOne(_) >> categoryEntity

        when:
        def result = categoryService.getCategoryById(id)

        then:
        result.id == categoryEntity.id
    }

    def "test 1.2 : query Category by id and get null entity"() {
        given:
        categoryRepository.findOne(_) >> null

        when:
        def result = categoryService.getCategoryById(id)

        then:
        thrown(NotExistException)
    }

    def "test 1.3 : query Category by QueryConditions"() {
        given:
        QueryConditions queryConditions = new QueryConditions()
        List<Category> entities = Lists.newArrayList(categoryEntity)
        categoryRepository.findOrderByOrderHint() >> entities

        when:
        def result = categoryService.queryCategories(queryConditions)

        then:
        result != null
        result.results.size() == entities.size()
    }

    def "test 2.1 : delete Category and get null entity"() {
        given:
        categoryRepository.findOne(_) >> null

        when:
        categoryService.deleteCategory(id, version)

        then:
        thrown(NotExistException)
    }

    def "test 2.2 : delete Category and can not match version"() {
        given:
        categoryEntity.version = 2
        categoryRepository.findOne(_) >> categoryEntity

        when:
        categoryService.deleteCategory(id, version)

        then:
        thrown(ConflictException)
    }

    def "test 2.3 : delete Category and get null subcategory list"() {
        given:
        categoryEntity.version = version
        categoryRepository.findOne(_) >> categoryEntity
        categoryRepository.querySubCategoriesByAncestorId(_) >> null

        when:
        categoryService.deleteCategory(id, version)

        then:
        true
    }

    def "test 2.4 : delete Category and get subCategory"() {
        given:
        categoryEntity.version = version
        categoryRepository.findOne(_) >> categoryEntity
        categoryRepository.querySubCategoriesByAncestorId(_) >> Lists.newArrayList(categoryEntity)

        when:
        categoryService.deleteCategory(id, version)

        then:
        true
    }

    def "test 3.1 : create Category"() {
        given:
        categoryRepository.save(_) >> categoryEntity

        when:
        def category = categoryService.createCategory(categoryDraft)

        then:
        category != null
    }

    def "test 3.2 : create Category and slug is already exist"() {
        given:
        categoryRepository.save(_) >> { throw new DataIntegrityViolationException("could not execute statement") }

        when:
        categoryService.createCategory(categoryDraft)

        then:
        thrown(AlreadyExistException)
    }

    def "test 3.2 : create Category with parent and subCategory with difference name"() {
        given:
        def parentId = "1"
        categoryDraft.parent = new Reference(ReferenceTypes.CATEGORY.getType(), parentId)
        categoryRepository.save(_) >> categoryEntity

        def parentEntity = new Category()
        parentEntity.id = parentId
        parentEntity.ancestors = Lists.newArrayList("0")
        categoryRepository.findOne(parentId) >> parentEntity

        List<Category> subCategories = new ArrayList<>()
        subCategories.add(new Category(name: Lists.newArrayList(LocalizedStringValue.build("en", "cup2"))))
        categoryRepository.queryCategoryByParent(parentId) >> subCategories

        when:
        def category = categoryService.createCategory(categoryDraft)

        then:
        category != null
    }

    def "test 3.3 : create Category with parent and subCategory with same name"() {
        given:
        def parentId = "1"
        categoryDraft.parent = new Reference(ReferenceTypes.CATEGORY.getType(), parentId)
        categoryRepository.save(_) >> categoryEntity

        def parentEntity = new Category()
        parentEntity.id = parentId
        parentEntity.ancestors = Lists.newArrayList("0")
        categoryRepository.findOne(parentId) >> parentEntity

        List<Category> subCategories = new ArrayList<>()
        subCategories.add(new Category(name: Lists.newArrayList(LocalizedStringValue.build("en", "cup"))))
        categoryRepository.queryCategoryByParent(parentId) >> subCategories

        when:
        def category = categoryService.createCategory(categoryDraft)

        then:
        thrown(AlreadyExistException)
    }

    def "test 3.4 : create Category with parent and get null parent"() {
        given:
        def parentId = "1"
        categoryDraft.parent = new Reference(ReferenceTypes.CATEGORY.getType(), parentId)
        categoryRepository.save(_) >> categoryEntity
        categoryRepository.findOne(parentId) >> null

        when:
        def category = categoryService.createCategory(categoryDraft)

        then:
        thrown(NotExistException)
    }

    def "test 4.1 : update Category"() {
        given:
        def updateActions = new ArrayList<UpdateAction>()
        Map<String, String> map = new HashMap<>()
        map.put("en", "value")
        def changeName = new SetName(name: new LocalizedString(localized: map))
        def changeSlug = new SetSlug(slug: new LocalizedString(localized: map))
        updateActions.add(changeName)
        updateActions.add(changeSlug)
        categoryEntity.version = version
        categoryRepository.findOne(_) >> categoryEntity
        categoryRepository.save(_) >> categoryEntity
        categoryUpdateService.handle(_, _) >> null

        when:
        def category = categoryService.updateCategory(id, version, updateActions)

        then:
        category != null

    }
}
