package com.smalaca.spring.ioc.xmlbased.persistance.inmemory;

import com.smalaca.spring.ioc.xmlbased.domain.Bean;
import com.smalaca.spring.ioc.xmlbased.domain.BeanDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class InMemoryBeanRepositoryTest {
    private static final String SOME_ID = "13";
    private static final String SOME_NAME = "Norman Osborn";
    private static final String SOME_DESCRIPTION = "Guy known as Green Goblin.";
    private static final String DIFFERENT_ID = "69";
    private static final String DIFFERENT_NAME = "Peter Parker";
    private static final String DIFFERENT_DESCRIPTION = "Wasting time on the web";

    @Mock private UniqueIdentifierFactory uniqueIdentifierFactory;
    private InMemoryBeanRepository repository;

    @Before
    public void initInMemoryBeanRepository() {
        repository = new InMemoryBeanRepository(uniqueIdentifierFactory);
    }

    @Test
    public void shouldPersistBean() {
        given(uniqueIdentifierFactory.anId()).willReturn(SOME_ID);

        Bean persisted = repository.persist(givenBean(SOME_NAME, SOME_DESCRIPTION));

        assertBeanDto(persisted.asDto());
    }

    @Test
    public void shouldNotAllowToPersistBeanWithTheSameNameTwice() {
        givenSomePersistedBean();

        Throwable exception = catchThrowable(() -> repository.persist(givenBean(SOME_NAME, DIFFERENT_DESCRIPTION)));

        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Bean with name: \"" + SOME_NAME + "\" already exist.");
    }

    @Test
    public void shouldRecognizeWhenBeanWithGivenIdDoesNotExist() {
        givenSomePersistedBean();

        assertThat(repository.existWithId(DIFFERENT_ID)).isFalse();
    }

    @Test
    public void shouldRecognizeWhenBeanWithGivenIdExists() {
        givenSomePersistedBean();

        assertThat(repository.existWithId(SOME_ID)).isTrue();
    }

    @Test
    public void shouldRecognizeWhenBeanWithGivenNameDoesNotExist() {
        givenSomePersistedBean();

        assertThat(repository.existWithName(DIFFERENT_NAME)).isFalse();
    }

    @Test
    public void shouldRecognizeWhenBeanWithGivenNameExists() {
        givenSomePersistedBean();

        assertThat(repository.existWithName(SOME_NAME)).isTrue();
    }

    @Test
    public void shouldThrowExceptionWhenGetsNotExistingBean() {
        givenSomePersistedBean();

        Throwable exception = catchThrowable(() -> repository.getById(DIFFERENT_ID));

        assertThat(exception)
                .isInstanceOf(NullPointerException.class)
                .hasMessage("There is no value for given id: " + DIFFERENT_ID + ".");
    }

    @Test
    public void shouldReturnBeanById() {
        givenSomePersistedBean();

        Bean found = repository.getById(SOME_ID);

        assertBeanDto(found.asDto());
    }

    private void assertBeanDto(BeanDto persistedBeanDto) {
        assertThat(persistedBeanDto.id()).isEqualTo(SOME_ID);
        assertThat(persistedBeanDto.name()).isEqualTo(SOME_NAME);
        assertThat(persistedBeanDto.description()).isEqualTo(SOME_DESCRIPTION);
    }

    private void givenSomePersistedBean() {
        givenPersistedBean(SOME_ID, SOME_NAME, SOME_DESCRIPTION);
    }

    private void givenPersistedBean(String id, String name, String description) {
        given(uniqueIdentifierFactory.anId()).willReturn(id);
        repository.persist(givenBean(name, description));
    }

    private Bean givenBean(String name, String description) {
        return Bean.Builder.aBean(BeanDto.Builder.aBean(name, description).build()).build();
    }
}