package com.ndmitrenko.passwordservice;

import com.ndmitrenko.passwordservice.model.entity.ResourceData;
import com.ndmitrenko.passwordservice.model.request.CreateResourceFromFileRequest;
import com.ndmitrenko.passwordservice.repository.ResourceDataRepository;
import com.ndmitrenko.passwordservice.service.ResourceService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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

	@Test
	void test2() {
		String first = new String("asd");
		String second = new String("asd");

		org.junit.jupiter.api.Assertions.assertEquals(first.hashCode(), second.hashCode());

		org.junit.jupiter.api.Assertions.assertNotSame(first, second);

		org.junit.jupiter.api.Assertions.assertEquals(first, second);
	}

	@Test
	void test3() {
		List<String> list = new ArrayList<>(List.of("Java", "Python"));

//			list.removeIf(str -> str.equals("Python"));
		for (String item : list) {
			if (item.equals("Java")) {
				list.remove(item);  // Это НЕ вызовет ConcurrentModificationException т.к. "Java" первый элемент
			}
		}
//		List<String> list2 = new ArrayList<>(List.of("ASD", "BFD"));
		List<String> list2 = new CopyOnWriteArrayList<>(List.of("ASD", "BFD"));
		for (String item : list2) {
			if (item.equals("BFD")) {
				list2.remove(item);  // Это вызовет ConcurrentModificationException
			}
		}
		
		System.out.println(list2);
		Map<String, String> map = new HashMap<>();
		map.put("asd", "ASD");

		for (String item : map.keySet()) {
			map.remove(item);
		}
	}

	@Test
	void test4() {
		CreateResourceFromFileRequest req1 = new CreateResourceFromFileRequest("1", "1");
		CreateResourceFromFileRequest req2 = new CreateResourceFromFileRequest("2", "2");

		HashSet<CreateResourceFromFileRequest> set = new HashSet<>(Set.of(req1, req2));

		set.add(null);
		System.out.println(set);

//		List<List<Integer>> listOfLists = Arrays.asList(
//				Arrays.asList(1, 2, 3),
//				Arrays.asList(4, 5, 6),
//				Arrays.asList(7, 8, 9)
//		);
//
//		Stream<Integer> flattenedStream = listOfLists.stream()
//				.flatMap(Collection::stream);

		req1.setFileName("2");
		req1.setProstoTakField("2");

		System.out.println(set);

		CreateResourceFromFileRequest req3 = new CreateResourceFromFileRequest("2", "2");
		set.add(req3);

		System.out.println(set); // будут два req в сете и все идентичные, т.к. изменение req1 произошло после добавления в коллекцию

		Map<String, Set<CreateResourceFromFileRequest>> map = Map.of("2", set);
		for (Map.Entry<String, Set<CreateResourceFromFileRequest>> entry : map.entrySet()) {

		}
		Map<CreateResourceFromFileRequest, String> mapColision = new HashMap<>();
		mapColision.put(req2, "2");
		mapColision.put(req3, "3");
		System.out.println();

//		Set<CreateResourceFromFileRequest> set2 = set.stream().sorted(Comparator.comparing(CreateResourceFromFileRequest::getFileName)).collect(Collectors.toSet());

	}

	@Test
	void test5() {
		CreateResourceFromFileRequest req1 = new CreateResourceFromFileRequest("1", "1");
		CreateResourceFromFileRequest req2 = new CreateResourceFromFileRequest("2", "2");
		CreateResourceFromFileRequest req3 = new CreateResourceFromFileRequest("2", "2");
		CreateResourceFromFileRequest req4 = new CreateResourceFromFileRequest("1", "1");

		List<CreateResourceFromFileRequest> list = Arrays.asList(req1, req2, req3, req4);

		Map<String, Set<CreateResourceFromFileRequest>> map = list.stream()
				.collect(Collectors.groupingBy(item -> item.getFileName(), Collectors.toSet()));

		AtomicInteger count  = new AtomicInteger(5);
		System.out.println(map.entrySet().stream().map(item -> {
			count.getAndIncrement();
			return "Item " + count + ": " + item.getValue();

		}).collect(Collectors.joining("\n"))
		);
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

	@Test
	void test6() {
		System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 6, 5}, 9)));
		System.out.println(Arrays.toString(twoSum(new int[]{3, 2, 4}, 6)));
	}

	public int[] twoSum(int[] nums, int target) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			Integer toFind = target - nums[i];
			if (!map.containsKey(toFind)) {
				map.put(nums[i], i);
			} else {
				return new int[]{map.get(toFind), i};
			}
		}
		return new int[]{};
	}

}
