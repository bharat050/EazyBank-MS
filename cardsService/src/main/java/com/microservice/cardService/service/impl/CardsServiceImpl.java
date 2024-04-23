package com.microservice.cardService.service.impl;

import com.microservice.cardService.constants.CardsConstants;
import com.microservice.cardService.dto.CardsDto;
import com.microservice.cardService.entity.CardEntity;
import com.microservice.cardService.exception.CardAlreadyExistsException;
import com.microservice.cardService.exception.ResourceNotFoundException;
import com.microservice.cardService.mapper.CardsMapper;
import com.microservice.cardService.repository.CardsRepository;
import com.microservice.cardService.service.ICardsService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

	private CardsRepository cardsRepository;

	/**
	 * @param mobileNumber - Mobile Number of the Customer
	 */
	@Override
	public void createCard(String mobileNumber) {
		Optional<CardEntity> optionalCards = cardsRepository.findByMobileNumber(mobileNumber);
		if (optionalCards.isPresent()) {
			throw new CardAlreadyExistsException("Card already registered with given mobileNumber " + mobileNumber);
		}
		cardsRepository.save(createNewCard(mobileNumber));
	}

	/**
	 * @param mobileNumber - Mobile Number of the Customer
	 * @return the new card details
	 */
	private CardEntity createNewCard(String mobileNumber) {
		CardEntity newCard = new CardEntity();
		long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
		newCard.setCardNumber(Long.toString(randomCardNumber));
		newCard.setMobileNumber(mobileNumber);
		newCard.setCardType(CardsConstants.CREDIT_CARD);
		newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
		newCard.setAmountUsed(0);
		newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
		return newCard;
	}

	/**
	 *
	 * @param mobileNumber - Input mobile Number
	 * @return Card Details based on a given mobileNumber
	 */
	@Override
	public CardsDto fetchCard(String mobileNumber) {
		CardEntity cards = cardsRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
		return CardsMapper.mapToCardsDto(cards, new CardsDto());
	}

	/**
	 *
	 * @param cardsDto - CardsDto Object
	 * @return boolean indicating if the update of card details is successful or not
	 */
	@Override
	public boolean updateCard(CardsDto cardsDto) {
		CardEntity cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber())
				.orElseThrow(() -> new ResourceNotFoundException("Card", "CardNumber", cardsDto.getCardNumber()));
		CardsMapper.mapToCards(cardsDto, cards);
		cardsRepository.save(cards);
		return true;
	}

	/**
	 * @param mobileNumber - Input MobileNumber
	 * @return boolean indicating if the delete of card details is successful or not
	 */
	@Override
	public boolean deleteCard(String mobileNumber) {
		CardEntity cards = cardsRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
		cardsRepository.deleteById(cards.getCardId());
		return true;
	}

}