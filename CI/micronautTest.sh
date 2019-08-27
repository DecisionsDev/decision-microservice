#!/usr/bin/env bash

set -e

./buildDocker.sh

./CI/runTest.sh MiniloanOpt.json MiniloanTest /DecisionService/rest/miniloanruleapp/miniloanrules micronaut
./CI/runTest.sh ShipmentOpt.json ShipmentTest /DecisionService/rest/Shipment_Pricing_RuleApp/Shipment_Pricing micronaut
./CI/runTest.sh LoanScoreOpt.json LoanScoreTest /DecisionService/rest/LoanValidationDS/loan_validation_with_score_and_grade micronaut
./CI/runTest.sh LoanProdOpt.json LoanProdTest /DecisionService/rest/LoanValidationDS/loan_validation_production micronaut
./CI/runTest.sh HelloWorld.json HelloTest /DecisionService/rest/simple_dep/1.0/simple_dop/1.0 micronaut

echo success