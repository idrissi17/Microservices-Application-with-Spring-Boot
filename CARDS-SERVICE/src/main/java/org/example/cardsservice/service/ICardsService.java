package org.example.cardsservice.service;

import org.example.cardsservice.dto.CardsDto;

public interface ICardsService {


    public void createCard(String mobileNumber);

    public CardsDto fetchCard(String mobileNumber);

    boolean updateCard(CardsDto cardsDto);

    boolean deleteCard(String mobileNumber);

}
