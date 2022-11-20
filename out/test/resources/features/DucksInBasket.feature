
Feature: Test ducks in basket and delete

  Example: Ducks
    When Add duck in basket in quantity '4'
    Then Go to the basket, check the contents and remove all the ducks one by one in quantity '4'