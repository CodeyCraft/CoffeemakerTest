# CoffeeMaker - Unit Test 프로젝트

이 프로젝트는 **CoffeeMaker 시스템**의 기능을 테스트하고 모델링하는 과정을 다룹니다.</br> 
주어진 요구사항을 기반으로 **커피메이커**의 테스트 로직을 구현하고, 다양한 유닛 테스트를 작성하여 시스템의 동작을 검증합니다.

> ※ 본 프로젝트는 **S/W품질관리및테스팅** 과목의 일환으로 진행된 프로젝트입니다.

---

## 프로젝트 설명

`CoffeeMaker`는 사용자가 선택한 커피 레시피에 따라 음료를 제조하는 시스템입니다.
이 시스템은 **CLI 인터페이스**를 통해 사용자가 커피 레시피를 추가, 수정, 삭제하고, 인벤토리를 관리하며, 음료를 구매할 수 있는 기능을 제공합니다.

### 주요 기능

- 커피 레시피 관리 (추가, 수정, 삭제)
- 인벤토리 관리 (추가, 확인)
- 음료 구매 및 금액 확인

### 요구사항

본 프로젝트는 **User Story**에 따라 단위 테스트 프레임워크 **JUnit**, 코드 커버리지 **Clover**를 사용하여 테스트를 진행합니다.

#### User Story
1. **대기 상태**: 커피메이커는 사용자가 선택할 수 있는 6가지 옵션을 제공합니다. </br>
   1)레시피 추가, 2)레시피 삭제, 3)레시피 수정, 4)인벤토리 추가, 5)인벤토리 확인, 6)음료 구매

2. **레시피 추가**: 최대 3개의 레시피를 추가할 수 있으며, 각 레시피는 이름, 가격, 커피, 우유, 설탕, 초콜릿의 단위로 구성됩니다.

3. **레시피 삭제**: 이미 등록된 레시피를 삭제할 수 있습니다.

4. **레시피 수정**: 기존 레시피의 가격과 재료의 단위를 수정할 수 있습니다.

5. **인벤토리 추가**: 커피, 우유, 설탕, 초콜릿 등의 재료를 추가할 수 있습니다.

6. **인벤토리 확인**: 현재 보유한 인벤토리를 확인할 수 있습니다.

7. **음료 구매**: 사용자는 음료를 선택하고 금액을 지불하여 음료를 구매할 수 있습니다. 금액이 부족하거나 인벤토리가 부족한 경우, 음료는 제공되지 않습니다.

---

## 코드 리뷰

### 1. CoffeeMakerTest_Before_Edit 클래스 테스트 항목 (유스케이스별 정리)
| 유스케이스 | 테스트 명 | 설명 |
|------------|------------|------------------|
| UC1: 레시피 추가 | `testAddRecipe1` | 빈 상태에서 새 레시피 추가 시 정상 저장 여부 확인 |
|  | `testAddRecipe2_NG` | `"Mocha"` 이름을 `"Latte"`로 비교하는 오류 |
| UC2: 레시피 삭제 | `testDeleteRecipe1` | 빈 리스트에서 삭제 시 `null` 반환 확인 |
|  | `testDeleteRecipe2_NG` | 레시피 추가 후 삭제 시 해당 인덱스가 `null`인지 확인 |
| UC3: 레시피 수정 | `testEditRecipe1` | 가격 변경이 잘 반영되는지 확인 |
|  | `testEditRecipe2_NG` | 존재하지 않는 인덱스(-1) 수정 시 `null` 반환 확인 |
| UC4: 인벤토리 추가 | `testAddInventory1` | 정상 수량 입력 시 예외 없이 추가 확인 |
|  | `testAddInventory2_NG` | 음수 입력 시 `InventoryException` 발생 확인 |
| UC5: 인벤토리 확인 | `testCheckInventory1` | 초기 인벤토리 상태 검증 |
|  | `testCheckInventory2_NG` | 잘못된 기대값 사용 (`15 + 6`) |
| UC6: 음료 구매 | `testpurchaseBeverage1` | 충분한 금액일 때 거스름돈이 정확히 반환되는지 확인 |
|  | `testpurchaseBeverage2_NG` | 금액 부족 상황에서 구매 실패 및 반환값 확인 |


### 2. CoffeeMakerTest_After_Edit 클래스 테스트 항목 (추가 테스트 케이스 정리)
| 테스트 명 | 주요 목적 |
|-----------|------------|
| `testRecipeCoverage_Plus` | 레시피 비교 (`equals`, `hashCode`) |
| `testRecipeCoverage2_Plus` | 유효하지 않은 입력값에 대한 예외 발생 확인 |
| `testForRecipeBookCoverage_Plus` | 중복 레시피 등록 확인 |
| `testCoffeeMakerCoverage_Plus` | 재료 부족 시 구매 실패 확인 |
| `testInventoryCoverage_Plus` | 재고 부족 시 `enoughIngredients()` 반응 확인 |
| `testInventoryCoverage2_Plus` | 잘못된 입력값에 대한 예외 처리 검증 |

---

## 테스트 결과

### JUnit 테스트
**수정 전**과 **수정 후**의 테스트 결과는 아래와 같습니다.</br>
**수정 전**에는 User Story에 따라 `Positive Test` 6개, `Negative Test` 6개 **총 12개의 테스트 케이스**를 실행하였습니다.</br>
**수정 후**에는 **수정 전** 12개 테스트를 포함하고, 테스트 커버리지를 높이기 위해 **추가적인 테스트 케이스가 6개**가 포함되었습니다.

|수정 전|수정 후|
|-----|-----|
| ![Image](https://github.com/user-attachments/assets/89521ecb-7652-421b-860b-f928a361c969)  |![Image](https://github.com/user-attachments/assets/0ea96044-0249-4465-82e8-e6c3e17c1048)  |

### Open Clover 테스트 커버리지
테스트 커버리지를 높이기 위한 **Open Clover** 결과는 아래와 같습니다. </br>
수정 후 커버리지가 향상된 것을 확인할 수 있습니다.

|수정 전|수정 후|
|-----|-----|
| ![Image](https://github.com/user-attachments/assets/e5995b5d-3c54-4ddd-9dd8-a579a1678ce4)  |![Image](https://github.com/user-attachments/assets/98fddf19-a959-48d6-84c4-e69ba37a039c)  |

---

## 결론

본 프로젝트는 **CoffeeMaker 시스템**의 다양한 기능을 테스트하는 과정으로, **JUnit**을 이용한 유닛 테스트와 **Clover**를 이용한 코드 커버리지 향상을 목표로 진행되었습니다. 테스트 커버리지가 개선됨에 따라 시스템의 안정성과 신뢰성을 높일 수 있었습니다.
