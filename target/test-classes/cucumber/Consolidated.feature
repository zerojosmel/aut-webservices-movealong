@consolidado @consulta
Feature: Consolidado

@ConsultaConsolidado @tets
Scenario: Consulta de Consolidado
Given solicito accesos al portal de QA
Then Consulta de servico de consolidado


@EnvioCriptomoneda @tets
Scenario: Envio de criptomoneda
Given solicito accesos al portal de QA
When Consulta de id de usuario por correo "josmel.ps4@gmail.com"
Then Consulta de servico de nombre, correo o direccion con "dash"
