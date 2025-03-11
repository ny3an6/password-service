package com.ndmitrenko.passwordservice;

import com.ndmitrenko.passwordservice.model.entity.ResourceData;
import com.ndmitrenko.passwordservice.repository.ResourceDataRepository;
import com.ndmitrenko.passwordservice.service.ResourceService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class PasswordServiceApplicationTests {

	@Mock
	private ResourceDataRepository resourceDataRepository;

    @Spy
	@InjectMocks
	private ResourceService resourceService;

	@Test
	void getListTest() {
		//given
		List<ResourceData> resourceDataList = List.of(getResourceData());
        Mockito.when(resourceDataRepository.findAll()).thenReturn(resourceDataList);

        //when
		List<ResourceData> resourceDataList2 = resourceService.getResourceDataList();

		//then
		Assertions.assertThat(resourceDataList).isEqualTo(resourceDataList2);
		Mockito.verify(resourceDataRepository, Mockito.times(1)).findAll();
		Mockito.verify(resourceService, Mockito.never()).createListResourceData(any());
	}

	private ResourceData getResourceData() {
		return ResourceData.builder()
				.name("gpt")
				.password("spotikBest9891")
				.actual(true)
				.email("ndmitrenko38@gmail.com")
				.createdDateTime(LocalDateTime.now())
				.build();
	}

}
