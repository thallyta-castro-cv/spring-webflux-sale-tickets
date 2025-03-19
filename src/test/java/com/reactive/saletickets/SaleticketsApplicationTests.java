package com.reactive.saletickets;

import com.reactive.saletickets.SaleticketsApplication;
import com.reactive.saletickets.models.dtos.EventDto;
import com.reactive.saletickets.models.enuns.EventTypeEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ContextConfiguration(classes = SaleticketsApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SaleticketsApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void registerNewEvent() {
		EventDto dto = new EventDto(null, EventTypeEnum.SHOW, "Kiss",
				LocalDate.parse("2025-01-01"), "Concert of the best band ever");

		webTestClient.post().uri("/events").bodyValue(dto)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(EventDto.class)
				.value(response -> {
					assertNotNull(response.id());
					assertEquals(dto.type(), response.type());
					assertEquals(dto.name(), response.name());
					assertEquals(dto.date(), response.date());
					assertEquals(dto.description(), response.description());
				});
	}

	@Test
	void fetchEvent() {
		EventDto dto = new EventDto(14L, EventTypeEnum.WORKSHOP, "Coffee with Ideas",
				LocalDate.parse("2025-01-25"), "An event that teaches brainstorming and prioritization techniques to boost projects.");

		webTestClient.get().uri("/events")
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBodyList(EventDto.class)
				.value(response -> {
					EventDto eventResponse = response.get(12);
					assertEquals(dto.id(), eventResponse.id());
					assertEquals(dto.type(), eventResponse.type());
					assertEquals(dto.name(), eventResponse.name());
					assertEquals(dto.date(), eventResponse.date());
					assertEquals(dto.description(), eventResponse.description());
				});
	}

	@Test
	void updateEvent() {
		EventDto updatedDto = new EventDto(5L, EventTypeEnum.CONCERT, "Metallica",
				LocalDate.parse("2025-12-01"), "Metallica band concert");

		webTestClient.put().uri("/events/{id}", 5L).bodyValue(updatedDto)
				.exchange()
				.expectStatus().isOk()
				.expectBody(EventDto.class)
				.value(response -> {
					assertEquals(updatedDto.id(), response.id());
					assertEquals(updatedDto.type(), response.type());
					assertEquals(updatedDto.name(), response.name());
					assertEquals(updatedDto.date(), response.date());
					assertEquals(updatedDto.description(), response.description());
				});
	}

	@Test
	void deleteEvent() {
		webTestClient.delete().uri("/events/{id}", 10L)
				.exchange()
				.expectStatus().isNoContent();

		webTestClient.get().uri("/events/{id}", 10L)
				.exchange()
				.expectStatus().isNotFound();
	}
}
