Feature: Consultar dados de endereço
  Eu quero consultar os dados de um endereço atraves de um numero de CEP
  para validadar os dados e obter o codigo do IBGE refente o local solicitado

  Scenario: Consultar CEP com sucesso
    Given Tenho um CEP valido
    Then Exibir status sucesso
    And Exiba o numero do IBGE refente a este local

  Scenario: Consultar CEP invalido
    Given Tenho um CEP invalido
    Then Exibir mensagem CEP INVALIDO