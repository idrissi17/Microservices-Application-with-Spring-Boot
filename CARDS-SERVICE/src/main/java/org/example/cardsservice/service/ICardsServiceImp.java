package org.example.cardsservice.service;


import org.example.cardsservice.constants.CardsConstants;
import org.example.cardsservice.dao.entities.Card;
import org.example.cardsservice.dao.repository.CardRepository;
import org.example.cardsservice.dto.CardsDto;
import org.example.cardsservice.exception.CardAlreadyExistsException;
import org.example.cardsservice.exception.ResourceNotFoundException;
import org.example.cardsservice.mapper.CardsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class ICardsServiceImp implements ICardsService {

    private final CardRepository cardsRepository;

    public ICardsServiceImp(CardRepository cardsRepository) {
        this.cardsRepository = cardsRepository;
    }


    @Override
    public void createCard(String mobileNumber) {
        Optional<Card> card = cardsRepository.findByMobileNumber(mobileNumber);
        if(card.isPresent()){
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber "+mobileNumber);
        }
        cardsRepository.save(createNewCard(mobileNumber));
    }

    private Card createNewCard(String mobileNumber) {

        Card card = new Card();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        card.setCardNumber(String.valueOf(randomCardNumber));
        card.setMobileNumber(mobileNumber);
        card.setCardType(CardsConstants.CREDIT_CARD);
        card.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        card.setAmountUsed(0);
        card.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return card;
    }

    @Override
    public CardsDto fetchCard(String mobileNumber) {
       Card card = cardsRepository.findByMobileNumber(mobileNumber).
               orElseThrow(() -> new CardAlreadyExistsException("Card not found with given mobileNumber "+mobileNumber));
        return CardsMapper.fromCardsToCardsDto(card, new CardsDto());
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Card card = cardsRepository.findByMobileNumber(cardsDto.getMobileNumber()).
                orElseThrow(() -> new ResourceNotFoundException("Card", "mobile number", cardsDto.getMobileNumber()));
        CardsMapper.fromCardsDtoToCards(cardsDto, card);
        cardsRepository.save(card);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Card card = cardsRepository.findByMobileNumber(mobileNumber).
                orElseThrow(() -> new ResourceNotFoundException("Card", "mobile number", mobileNumber));
        cardsRepository.delete(card);
        return true;
    }
}
